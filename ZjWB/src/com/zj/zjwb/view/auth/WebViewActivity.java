/*
 * @author Administrator
 * @version 创建时间：2015年2月18日 上午11:31:24 
 * 类说明 
 */
package com.zj.zjwb.view.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.zj.zjwb.R;
import com.zj.zjwb.bo.Constants;
import com.zj.zjwb.bo.UserBo;
import com.zj.zjwb.service.UserService;
import com.zj.zjwb.view.login.AuthActivity;

/**
 * @author zj
 *
 */
public class WebViewActivity extends Activity {

	private AuthInfo mAuthInfo;
	private SsoHandler mSsoHandler;
	/** 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能 */
	private Oauth2AccessToken mAccessToken;
	private int status = 0;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (status == msg.what) {
				Toast.makeText(WebViewActivity.this, "Login Error!", Toast.LENGTH_LONG).show();
			} else {
				// 进入登入选择界面
				Intent intent = new Intent(WebViewActivity.this, AuthActivity.class);
				startActivity(intent);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		mAuthInfo = new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
		mSsoHandler = new SsoHandler(WebViewActivity.this, mAuthInfo);
		mSsoHandler.authorizeWeb(new AuthListener());
	}

	/**
	 * 微博认证授权回调类。 1. SSO 授权时，需要在 {@link #onActivityResult} 中调用
	 * {@link SsoHandler#authorizeCallBack} 后， 该回调才会被执行。 2. 非 SSO
	 * 授权时，当授权结束后，该回调就会被执行。 当授权成功后，请保存该 access_token、expires_in、uid 等信息到
	 * SharedPreferences 中。
	 */
	class AuthListener implements WeiboAuthListener {
		@Override
		public void onCancel() {

		}

		@Override
		public void onComplete(Bundle values) {
			// 从 Bundle 中解析 Token
			mAccessToken = Oauth2AccessToken.parseAccessToken(values);
			// 保存到数据库
			saveUser();
		}

		@Override
		public void onWeiboException(WeiboException e) {
			Log.e(WebViewActivity.class.getName(), e.getMessage());
			Toast.makeText(WebViewActivity.this, "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	private void saveUser() {
		new Thread() {
			@Override
			public void run() {
				UserService uService = new UserService(WebViewActivity.this);
				try {
					UserBo user = uService.getRUserByUserId(mAccessToken);
					uService.saveUserInfo(user);
					status = 1;
				} catch (Exception e) {
					Log.e(Constants.TAG, e.getMessage());
				}
				handler.hasMessages(status);
			}
		}.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.auth, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

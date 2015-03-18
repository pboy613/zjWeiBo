/*
 * 
 */
package com.zj.zjwb.view.login;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.zj.zjwb.R;
import com.zj.zjwb.bo.Constants;
import com.zj.zjwb.bo.UserBo;
import com.zj.zjwb.framework.adapter.UserBoAdapter;
import com.zj.zjwb.service.UserService;

/**
 * login
 * 
 * @author zj
 * 
 */
public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Button addButton = (Button) findViewById(R.id.btn_addAccount);
		final Spinner userSpinner = (Spinner) findViewById(R.id.sp_user_list);
		// 添加用户
		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this, AuthActivity.class);
				startActivity(intent);
			}
		});
		// 登入
		Button loginButton = (Button) findViewById(R.id.btn_login);
		loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 判断有没有用户信息,如果没有用户进入添加用户
				Object selectItem = userSpinner.getSelectedItem();
				if (null == selectItem) {
					Intent intent = new Intent(LoginActivity.this, AuthActivity.class);
					startActivity(intent);
				} else {
					// TODO 进入用户主界面
				}
			}
		});
		// 下拉框事件
		List<UserBo> users = null;
		try {
			users = new UserService(this).getAllUsers();
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(Constants.TAG, e.getMessage());
		}
		userSpinner.setAdapter(new UserBoAdapter(this, users));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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

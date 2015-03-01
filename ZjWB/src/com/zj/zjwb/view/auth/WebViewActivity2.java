/*
 * @author Administrator
 * @version 创建时间：2015年2月18日 上午11:31:24 
 * 类说明 
 */
package com.zj.zjwb.view.auth;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zj.zjwb.R;

/**
 * @author zj
 *
 */
public class WebViewActivity2 extends Activity {

	private WebView webView;
	private ProgressDialog progressDialog;
	private final int CLOSE_DIALOG = 1;
	private final String URL = "http://www.baidu.com";
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == CLOSE_DIALOG)
				progressDialog.dismiss();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		init();
		load(URL, webView);

	}

	public void init() {
		if (null == progressDialog)
			progressDialog = new ProgressDialog(this);
		progressDialog.show();
		webView = (WebView) findViewById(R.id.wv_auth);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// load(url, view);
				return true;
			}
		});

		webView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				if (newProgress == 100) {
					handler.sendEmptyMessage(CLOSE_DIALOG);
				}
				super.onProgressChanged(view, newProgress);
			}
		});
	}

	public void load(final String url, final WebView view) {
		if (url.isEmpty())
			return;
		view.post(new Runnable() {
			@Override
			public void run() {
				view.loadUrl(url);
			}
		});
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

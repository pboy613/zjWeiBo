package com.zj.zjwb.view.main;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.zj.zjwb.R;

public class MainFrameActivity1 extends TabActivity {
	private static final String HOME_TAB = "home";
	private static final String AT_TAB = "at";
	private static final String MSG_TAB = "msg";
	private static final String MORE_TAB = "more";
	private TabHost tabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_frame);
		tabHost = this.getTabHost();

		TabSpec homeSpec = tabHost.newTabSpec(HOME_TAB).setIndicator(HOME_TAB).setContent(new Intent(this, HomeActivity.class));
		TabSpec atSpec = tabHost.newTabSpec(AT_TAB).setIndicator(AT_TAB).setContent(new Intent(this, AtActivity.class));
		TabSpec msgSpec = tabHost.newTabSpec(MSG_TAB).setIndicator(MSG_TAB).setContent(new Intent(this, MsgActivity.class));
		TabSpec moreSpec = tabHost.newTabSpec(MORE_TAB).setIndicator(MORE_TAB).setContent(new Intent(this, MoreActivity.class));

		tabHost.addTab(homeSpec);
		tabHost.addTab(atSpec);
		tabHost.addTab(msgSpec);
		tabHost.addTab(moreSpec);

		RadioGroup radioGroup = (RadioGroup) this.findViewById(R.id.rg_main_btns);

		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				switch (checkedId) {
				case R.id.rd_home:
					tabHost.setCurrentTabByTag(HOME_TAB);
					break;

				case R.id.rd_at:
					tabHost.setCurrentTabByTag(AT_TAB);
					break;

				case R.id.rd_msg:
					tabHost.setCurrentTabByTag(MSG_TAB);
					break;

				case R.id.rd_more:
					tabHost.setCurrentTabByTag(MORE_TAB);
					break;

				default:
					break;
				}

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_frame, menu);
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

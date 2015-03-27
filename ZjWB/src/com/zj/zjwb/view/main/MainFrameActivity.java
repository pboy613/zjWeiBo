/*
 * copyright
 */
package com.zj.zjwb.view.main;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.zj.zjwb.R;

/**
 * 系统主界面
 * 
 * @author zj
 *
 */
public class MainFrameActivity extends Activity {
	private FragmentManager fManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_frame);

		fManager = this.getFragmentManager();
		fManager.beginTransaction().replace(R.id.flayout1, new HomeActivity()).commit();

		RadioGroup radioGroup = (RadioGroup) this.findViewById(R.id.rg_main_btns);

		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rd_home:
					fManager.beginTransaction().replace(R.id.flayout1, new HomeActivity()).commit();
					break;
				case R.id.rd_at:
					fManager.beginTransaction().replace(R.id.flayout1, new AtActivity()).commit();
					break;
				case R.id.rd_msg:
					fManager.beginTransaction().replace(R.id.flayout1, new MsgActivity()).commit();
					break;
				case R.id.rd_more:
					fManager.beginTransaction().replace(R.id.flayout1, new MoreActivity()).commit();
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

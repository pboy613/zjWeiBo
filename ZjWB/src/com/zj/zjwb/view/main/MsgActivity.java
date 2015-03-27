package com.zj.zjwb.view.main;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zj.zjwb.R;
import com.zj.zjwb.bo.Constants;

public class MsgActivity extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(Constants.TAG, " Msg onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_msg,container, false);
		Log.i(Constants.TAG, "Msg onCreateView");
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.i(Constants.TAG, "Msg onStart");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.i(Constants.TAG, "Msg onStop");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.i(Constants.TAG, "Msg onDestroyView");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(Constants.TAG, "Msg onDestroy");
	}
}

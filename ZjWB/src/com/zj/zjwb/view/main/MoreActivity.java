package com.zj.zjwb.view.main;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zj.zjwb.R;
import com.zj.zjwb.bo.Constants;

public class MoreActivity extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(Constants.TAG, "onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_more, null);
		Log.i(Constants.TAG, "more onCreateView");
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.i(Constants.TAG, "more onStart");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.i(Constants.TAG, "more onStop");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.i(Constants.TAG, "more onDestroyView");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(Constants.TAG, "more onDestroy");
	}
}

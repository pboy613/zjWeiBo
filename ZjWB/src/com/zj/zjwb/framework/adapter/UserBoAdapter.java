/*
 * CopyRight
 * 下午9:39:09
 */
/**
 * 
 */
package com.zj.zjwb.framework.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zj.zjwb.R;
import com.zj.zjwb.bo.UserBo;

/**
 * @author Administrator
 *
 */
public class UserBoAdapter extends BaseAdapter {

	private Context context;
	private List<UserBo> users;

	public UserBoAdapter(Context context, List<UserBo> users) {
		this.context = context;
		this.users = users;

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return users != null ? users.size() : 0;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		return users != null ? users.get(position) : null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		return Long.parseLong(users.get(position).getUserId());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 *      android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (null == v) {
			v = LayoutInflater.from(context).inflate(R.layout.user_select_temp, null);

			ImageView imagUserHead = (ImageView) v.findViewById(R.id.img_user_head);
			TextView txtUserName = (TextView) v.findViewById(R.id.txt_show_name);

			UserBo userInfo = users.get(position);
			if (userInfo.getUserIcon() != null) {
				byte[] imgs = userInfo.getUserIcon();
				Bitmap bitmap = BitmapFactory.decodeByteArray(imgs, 0, imgs.length);
				BitmapDrawable bDrawable = new BitmapDrawable(context.getResources(), bitmap);
				imagUserHead.setImageDrawable(bDrawable);
			}
			txtUserName.setText(userInfo.getUserName());
		}
		return v;
	}

}

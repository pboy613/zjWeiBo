/*
 * CopyRight
 * 上午8:51:26
 */
package com.zj.zjwb.service;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.openapi.UsersAPI;
import com.sina.weibo.sdk.openapi.models.User;
import com.zj.zjwb.bo.Constants;
import com.zj.zjwb.bo.UserBo;
import com.zj.zjwb.dao.DBHelper;
import com.zj.zjwb.dao.DBInfo;

/**
 * @author zj
 *
 */
public class UserService {

	private DBHelper dbHelper;

	private Context context;

	public UserService(Context context) {
		this.context = context;
		this.dbHelper = new DBHelper(context);
	}

	/**
	 * 保存用户信息
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void saveUserInfo(UserBo user) throws Exception {
		ContentValues values = new ContentValues(6);

		values.put(UserBo.USER_ID, user.getUserId());
		values.put(UserBo.USER_NAME, user.getUserName());
		values.put(UserBo.TOKEN, user.getToken());
		values.put(UserBo.TOKEN_SECRET, user.getTokenSecret());
		values.put(UserBo.IS_DEFAULT, user.getIsDefault());
		values.put(UserBo.USER_ICON, user.getUserIcon());
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.insert(DBInfo.Table.USER_INFO_TB_NAME, null, values);
		db.close();
	}

	/**
	 * 获取远程用户信息
	 * 
	 * @param uId
	 * @return
	 * @throws Exception
	 */
	public UserBo getRUserByUserId(Oauth2AccessToken mAccessToken) throws Exception {
		UsersAPI usersAPI = new UsersAPI(this.context, Constants.APP_KEY, mAccessToken);
		long uid = Long.parseLong(mAccessToken.getUid());
		String userStr = usersAPI.showSync(uid);
		User user = User.parse(userStr);
		URL url = new URL(user.profile_image_url);
		Bitmap userImage = BitmapFactory.decodeStream(url.openStream());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		userImage.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return new UserBo(user.id, user.screen_name, mAccessToken.getToken(), "", "N", baos.toByteArray());
	}

	/**
	 * 查询所有用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<UserBo> getAllUsers() throws Exception {
		List<UserBo> users = new ArrayList<UserBo>();
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query(DBInfo.Table.USER_INFO_TB_NAME, UserBo.columns, null, null, null, null, null);
		while (cursor.moveToNext()) {

			UserBo userInfo = new UserBo();
			Long id = cursor.getLong(cursor.getColumnIndex(UserBo.ID));
			String uId = cursor.getString(cursor.getColumnIndex(UserBo.USER_ID));
			String userName = cursor.getString(cursor.getColumnIndex(UserBo.USER_NAME));
			String token = cursor.getString(cursor.getColumnIndex(UserBo.TOKEN));
			String tokenSecret = cursor.getString(cursor.getColumnIndex(UserBo.TOKEN_SECRET));
			String isDefault = cursor.getString(cursor.getColumnIndex(UserBo.IS_DEFAULT));
			byte[] byteIcon = cursor.getBlob(cursor.getColumnIndex(UserBo.USER_ICON));

			userInfo.set_id(id);
			userInfo.setUserId(uId);
			userInfo.setIsDefault(isDefault);
			userInfo.setToken(token);
			userInfo.setTokenSecret(tokenSecret);
			userInfo.setToken(token);
			userInfo.setUserName(userName);
			userInfo.setUserIcon(byteIcon);

			users.add(userInfo);
		}
		return users;
	}
}

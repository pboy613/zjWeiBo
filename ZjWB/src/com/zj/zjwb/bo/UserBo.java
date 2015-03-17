/*
 * CopyRight
 * 上午10:17:32
 */
package com.zj.zjwb.bo;


/**
 * @author Administrator
 *
 */
public class UserBo {

	private Long _id;
	private String userId;
	private String userName;
	private String token;
	private String tokenSecret;
	private String isDefault;
	private byte[] userIcon;

	public static final String ID = "_id";
	public static final String USER_ID = "userId";
	public static final String USER_NAME = "userName";
	public static final String TOKEN = "token";
	public static final String TOKEN_SECRET = "tokenSecret";
	public static final String IS_DEFAULT = "isDefault";
	public static final String USER_ICON = "userIcon";

	public UserBo(String userId, String userName, String token, String tokenSecret, String isDefault, byte[] userIcon) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.token = token;
		this.tokenSecret = tokenSecret;
		this.isDefault = isDefault;
		this.userIcon = userIcon;
	}

	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenSecret() {
		return tokenSecret;
	}

	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public byte[] getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(byte[] userIcon) {
		this.userIcon = userIcon;
	}


}

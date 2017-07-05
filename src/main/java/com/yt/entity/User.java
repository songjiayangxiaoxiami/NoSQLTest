package com.yt.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
/**
 * Title: User.java
 * Description: 
 * Company: HundSun
 * @author yangting
 * @date 2017年7月3日 上午11:01:44
 * @version 1.0
 */
public class User implements Serializable {

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -6872707197100588198L;
	@Id
	private String id;
	@Field
	private String name;
	@Field
	private String password;
	private Map<byte[], byte[]> hashMap = new HashMap<byte[], byte[]>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setHashMap(Map<byte[], byte[]> hashMap) {
		this.hashMap = hashMap;
	}
	public Map<byte[], byte[]> getHashMap() {
		return hashMap;
	}
	public User() {
		super();
	}
	public User(String id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}
}

package com.yt.dao;

import java.util.List;
import com.yt.entity.User;
/**
 * Title: IUserDao.java
 * Description: 
 * Company: HundSun
 * @author yangting
 * @date 2017年7月3日 上午11:01:27
 * @version 1.0
 */
public interface IUserDao {

	boolean add(User user);
	
	boolean add(List<User> users);
	
	void delete(String key);
	
	void delete(List<String> keys);
	
	boolean update(User user);
	
	User get(String keyId); 
}

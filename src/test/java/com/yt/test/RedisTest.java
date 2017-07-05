//package com.yt.test;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.yt.dao.UserDao;
//import com.yt.entity.User;
//
//import junit.framework.Assert;
//public class RedisTest  {
//
//	@Autowired
//	private  UserDao userDao;
//	
//	private static  int  counter;
//	
//	
//	@Test
//	public void testAddUser(){
//		int dal = -1;
//		Long time = System.currentTimeMillis();
//		ExecutorService executorService = Executors.newFixedThreadPool(200);
////		for(int i=0;i<100000;i++){
////			executorService.execute(new UserAdd(userDao));
////		}
//	
//		while(counter!=dal || counter==0){
//			try {
//				dal = counter;
//				Thread.sleep(10);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		double totalTime = (System.currentTimeMillis()-time)/1000.0;	
//		System.out.println("总耗时:  " + totalTime  + "s");
//		System.out.println("OPS:  " + 100000/totalTime);
//		System.out.println(counter);
//		
//	}
//	@Test
//	public void testAddUsers(){
//		List<User>list = new ArrayList<User>();
//		User user1 = new User("yangting", "xiaosong", "111");
//		User user2 = new User("user2", "yangyu", "111");
//		list.add(user1);
//		list.add(user2);
//		boolean result = userDao.add(list);
//		Assert.assertTrue(result);
//	}
//	@Test
//	public void deleteUser(){
//		userDao.delete("master");
//	}
//	@Test
//	public void deleteUsers(){
//		List<String>keys = new ArrayList<String>(Arrays.asList("user1","user2"));
//		userDao.delete(keys);
//	}
//	@Test
//	public void updateUser(){
//		User user = new User();
//		user.setId("master");
//		user.setName("tg");
//		boolean result = userDao.update(user);
//		Assert.assertTrue(result);
//
//	}
//	@Test
//	public void addHashMap(){
//		User user = new User();
//		user.setId("map");
//		boolean result = userDao.addHashMap(user);
//		Assert.assertTrue(result);
//	}
//	public void setUserDao(UserDao userDao) {
//		this.userDao = userDao;
//	}
////	class UserAdd implements Runnable{
////		private UserDao userDao;
////		public UserAdd(UserDao userDao) {
////			this.userDao = userDao;
////		}
////		public void run() {
////			User user = new User();
////			user.setId(StringGenerateUitil.generateString(10));
////			user.setName(StringGenerateUitil.generateString(100));
////			boolean result = userDao.add(user);
////			counter = counter + 1;
////			Assert.assertTrue(result);
////			
////		}
////		
////	}
//}
//

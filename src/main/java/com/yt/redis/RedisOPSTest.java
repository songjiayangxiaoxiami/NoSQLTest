package com.yt.redis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yt.dao.UserDao;
import com.yt.entity.User;
import com.yt.util.StringGenerateUitil;
/**
 * Title: RedisOPSTest.java
 * Description: 
 * Company: HundSun
 * @author yangting
 * @date 2017年7月3日 上午11:02:03
 * @version 1.0
 */
public class RedisOPSTest {
	@Autowired
	private  UserDao userDao;
	private int  counter;
	private   int  dalcounter;
	static long time;
	static long dalTime;
	static int poke;
	static Random random = new Random(System.currentTimeMillis()%1000);
//	static String FORMAT_PATTERN="yyyyMMddHHmmss";
//	private static final SimpleDateFormat format = new SimpleDateFormat(FORMAT_PATTERN);
	public  void incrCounter(){
		synchronized (this) {
			counter++;
		}
		
	}


	static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

	private static ApplicationContext context;
	public static void main(String[] args) {
		RedisOPSTest redisTest = new RedisOPSTest();
		context = new ClassPathXmlApplicationContext("classpath:redis-config.xml");
		redisTest.setUserDao((UserDao) context.getBean("userDao"));
		redisTest.testGetUser();
		System.exit(1);
	}
	
	
	public void testAddUser(){
		time = 0;
		dalTime = System.currentTimeMillis();
		ExecutorService executorService = Executors.newFixedThreadPool(userDao.getThreadNumber());
		for(int i=0;i<userDao.getWriteTimes();i++){
			executorService.execute(new UserAdd(userDao,10000000 + i));
			if(System.currentTimeMillis() - dalTime >= 2000){
				System.out.println(simpleDateFormat.format(new Date(System.currentTimeMillis())) + "-" + simpleDateFormat.format(new Date(dalTime)) 
						+ " OPS:  " + (counter-dalcounter) / ((System.currentTimeMillis()-dalTime)/1000.0));
				dalTime = System.currentTimeMillis();
				dalcounter = counter;
			}
		}

		try {
				executorService.shutdown();
				executorService.awaitTermination(2000, TimeUnit.SECONDS);
				while(!executorService.isTerminated()){
						if(System.currentTimeMillis() - dalTime >= 2000){
							System.out.println(simpleDateFormat.format(new Date(System.currentTimeMillis())) + "-" + simpleDateFormat.format(new Date(dalTime)) 
									+ " OPS:  " + (counter-dalcounter) / ((System.currentTimeMillis()-dalTime)/1000.0));
							dalTime = System.currentTimeMillis();
							dalcounter = counter;
						}
						Thread.yield();
				}	
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		double totalTime = (System.currentTimeMillis()-time)/1000.0;	
		System.out.println("结束时间:  " + simpleDateFormat.format(new Date(System.currentTimeMillis())));
		System.out.println("总耗时:  " + totalTime  + "s");
		System.out.println("OPS:  " + userDao.getWriteTimes()/totalTime);
		System.out.println("总次数:  " + counter);
		
	}
	public void testGetUser(){
		int dal = -1;
		time = 0;
		dalTime = System.currentTimeMillis();
		ExecutorService executorService = Executors.newFixedThreadPool(userDao.getThreadNumber());
		for(int i=0;i<userDao.getReadTimes();i++){
			executorService.execute(new UserGet(userDao,random.nextInt(20000000)));
		}

		try {
				executorService.shutdown();
				executorService.awaitTermination(1000, TimeUnit.SECONDS);
				while((counter!=dal || counter==0) && !executorService.isTerminated()){
						if(System.currentTimeMillis() - dalTime >= 2000){
							System.out.println(simpleDateFormat.format(new Date(System.currentTimeMillis())) + "-" + simpleDateFormat.format(new Date(dalTime)) 
									+ " OPS:  " + (counter-dalcounter) / ((System.currentTimeMillis()-dalTime)/1000.0));
							dalTime = System.currentTimeMillis();
							dalcounter = counter;
						}
						dal = counter;
						Thread.yield();
				}	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	
		double totalTime = (System.currentTimeMillis()-time)/1000.0;	
		System.out.println("结束时间:  " + simpleDateFormat.format(new Date(System.currentTimeMillis())));
		System.out.println("总耗时:  " + totalTime  + "s");
		System.out.println("OPS:  " + userDao.getReadTimes()/totalTime);
		System.out.println(counter);
		
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	class UserAdd implements Runnable{
		private UserDao userDao;
		private int i;
		public UserAdd(UserDao userDao,int i) {
			this.userDao = userDao;
			this.i = i;
		}
		public void run() {
			if(time == 0)
				time = System.currentTimeMillis();
			boolean result = false;
			User user = new User();
			user.setId("user" + i);
			HashMap<byte[],byte[]> map = new HashMap<byte[],byte[]>();
			map.put("expires".getBytes(), StringGenerateUitil.generateString(90).getBytes());
			user.setHashMap(map);
			result = userDao.addHashMap(user);
			incrCounter();
			if(!result)
				System.out.println("error");
			
		}
		
	}
	class UserGet implements Runnable{
		private UserDao userDao;
		private int i;
		public UserGet(UserDao userDao,int i) {
			this.userDao = userDao;
			this.i = i;
		}
		public void run() {
			
			
			if(time == 0)
				time = System.currentTimeMillis();
			userDao.get("user" + i);
			incrCounter();		
		}
		
	}
}

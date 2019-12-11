package cn.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.entity.User;
import cn.utils.HibernateUtils;

public class HibernateSelect {
	
	//事物的规范代码
	@Test
	public void testTx(){
		
		Session session = null;
		Transaction tx = null;
		try {
			//返回与本地线程绑定的session
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			
			User user = new User();
			user.setUsername("章子怡");
			user.setPassword("46578");
			user.setAddress("卧虎藏龙");
//			int i = 1/0;
			
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
//			session.close();
		}
	}
	
	//一级缓存特性
	@Test
	public void testDemo(){
		
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		User user = session.get(User.class, 4);
		user.setAddress("倩女幽魂");
//		session.update(user);//可以不调用update
		
		tx.commit();
		session.close();
		sessionFactory.close();
	}
	
	//一级缓存验证
	@Test
	public void testGet(){
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		//根据uid查询：调用session里面的get()
		User user1 = session.get(User.class, 1);
		System.out.println(user1);
		
		//再查一次
		User user2 = session.get(User.class, 1);
		System.out.println(user2);
	
		tx.commit();
		session.close();
		sessionFactory.close();
	}
}

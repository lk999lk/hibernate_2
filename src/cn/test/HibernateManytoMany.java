package cn.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.manytomany.Role;
import cn.manytomany.User;
import cn.utils.HibernateUtils;

public class HibernateManytoMany {
	
	//演示维护第三张表
	@Test
	public void testTable2() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			//得到sessionFactory
			sessionFactory = HibernateUtils.getSessionFactory();
			//得到session
			session = sessionFactory.openSession();
			//开启事务
			tx = session.beginTransaction();
			
			// 让某个用户没有有某个角色
			User user = session.get(User.class, 2);
			Role role = session.get(Role.class, 6);
			
			//2 从用户里面把角色去掉
			user.getSetRole().remove(role);
			
			//提交事务
			tx.commit();

		}catch(Exception e) {
			tx.rollback();
		}finally {
			session.close();
			//sessionFactory不需要关闭
			sessionFactory.close();
		}
	}
	
	//演示维护第三张表
	@Test
	public void testTable1() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			//得到sessionFactory
			sessionFactory = HibernateUtils.getSessionFactory();
			//得到session
			session = sessionFactory.openSession();
			//开启事务
			tx = session.beginTransaction();
			
			// 让某个用户有某个角色
			//让mary有经纪人角色
			//1 查询mary和经纪人
			User mary = session.get(User.class, 2);
			Role role = session.get(Role.class, 6);
			
			//2 把角色放到用户的set集合里面
			mary.getSetRole().add(role);
			
			//提交事务
			tx.commit();

		}catch(Exception e) {
			tx.rollback();
		}finally {
			session.close();
			//sessionFactory不需要关闭
			sessionFactory.close();
		}
	}
	
	//演示多对多修级联删除
	@Test
	public void testDelete() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			//得到sessionFactory
			sessionFactory = HibernateUtils.getSessionFactory();
			//得到session
			session = sessionFactory.openSession();
			//开启事务
			tx = session.beginTransaction();
			
			User user = session.get(User.class, 1);
			session.delete(user);
			//提交事务
			tx.commit();

		}catch(Exception e) {
			tx.rollback();
		}finally {
			session.close();
			//sessionFactory不需要关闭
			sessionFactory.close();
		}
	}
	
	//演示多对多修级联保存
	@Test
	public void testSave() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			//得到sessionFactory
			sessionFactory = HibernateUtils.getSessionFactory();
			//得到session
			session = sessionFactory.openSession();
			//开启事务
			tx = session.beginTransaction();
			
			//添加两个用户，为每个用户添加两个角色
			//1 创建对象
			User user1 = new User();
			user1.setUser_name("lucy");
			user1.setUser_password("123");
			
			User user2 = new User();
			user2.setUser_name("mary");
			user2.setUser_password("456");
			
			Role r1 = new Role();
			r1.setRole_name("总经理");
			r1.setRole_memo("总经理");
			
			Role r2 = new Role();
			r2.setRole_name("秘书");
			r2.setRole_memo("秘书");
			
			Role r3 = new Role();
			r3.setRole_name("保安");
			r3.setRole_memo("保安");
			
			//2 建立关系，把角色放到用户里面
			// user1 -- r1/r2
			user1.getSetRole().add(r1);
			user1.getSetRole().add(r2);
			
			// user2 -- r2/r3
			user2.getSetRole().add(r2);
			user2.getSetRole().add(r3);
			
			//3 保存用户
			session.save(user1);
			session.save(user2);
			
			//提交事务
			tx.commit();

		}catch(Exception e) {
			tx.rollback();
		}finally {
			session.close();
			//sessionFactory不需要关闭
			sessionFactory.close();
		}
	}
}



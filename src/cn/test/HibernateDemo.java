package cn.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.entity.User;
import cn.utils.HibernateUtils;

public class HibernateDemo {
	
	@Test
	public void testAdd(){
//		第一步 加载hibernate核心配置文件
		// 到src下面找到名称是hibernate.cfg.xml
		//在hibernate里面封装对象
//		Configuration cfg = new Configuration();
//		cfg.configure();
//		
//		第二步 创建SessionFactory对象
		//读取hibernate核心配置文件内容，创建sessionFactory
		//在过程中，根据映射关系，在配置数据库里面把表创建
//		SessionFactory sessionFactory = cfg.buildSessionFactory();
		
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		
//		第三步 使用SessionFactory创建session对象
		// 类似于连接
		Session session = sessionFactory.openSession();
		System.out.println(session);
		
//		第四步 开启事务
		Transaction tx = session.beginTransaction();

//		第五步 写具体逻辑 crud操作
		//添加功能
		User user = new User();
		user.setUsername("小wang");
		user.setPassword("1314520");
		user.setAddress("美国");
		//调用session的方法实现添加
		session.save(user);
		
//		第六步 提交事务
		tx.commit();

//		第七步 关闭资源
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void testGet(){
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		//根据id查询：调用session里面的get()
		//第一个参数：实体类的class，第二个参数：id值
		User user = session.get(User.class, 1);
		System.out.println(user);
		
		
		tx.commit();
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void testUpdate(){
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		//修改操作
		//1.根据id查询：调用session里面的get()
		User user = session.get(User.class, 2);
		//2.向返回的user对象设置修改之后的值
		user.setUsername("东方不败");
		//3.调用session中的update
		//执行过程：到user对象里面找到uid值，根据uid进行修改
		session.update(user);
		
		tx.commit();
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void testDelete(){
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		//删除操作
		//第一种
		User user = session.get(User.class, 2);
		session.delete(user);
		
		//第二种
//		User user = new User();
//		user.setUid(3);
//		session.delete(user);
		
		
		tx.commit();
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void testSaveOrUpdate(){
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		//不管有没有uid，都是添加
//		User user = new User();
//		user.setUid(1);//设不设置没有影响，都是添加
//		user.setUsername("mary");
//		user.setPassword("236");
//		user.setAddress("西安");
//		session.save(user);
		
//		//修改完除了username其余都是null
//		User user = new User();
//		user.setUid(1);
//		user.setUsername("tom");
//		session.update(user);
		
		//添加
//		User user = new User();
//		user.setUid(4);
//		user.setUsername("rose");
//		user.setPassword("1545");
//		user.setAddress("咸阳");
//		
//		//如果实体类对象是瞬时态，做添加
//		session.saveOrUpdate(user);
//		//如果实体类对象是托管态，做修改
//		session.saveOrUpdate(user);
		
		User user = session.get(User.class, 4);
		user.setUsername("李嘉欣");
		////如果实体类对象是持久态，做修改
		session.saveOrUpdate(user);
		
		tx.commit();
		session.close();
		sessionFactory.close();
	}
}

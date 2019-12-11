package cn.test;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.sql.Select;
import org.junit.Test;

import cn.entity.User;
import cn.utils.HibernateUtils;

public class HibernateQueryData { 
	
	//使用SQLQuery对象
	@Test
	public void testSQLQuery(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			//1.创建SQLQuery对象
			//参数：普通sql语句
			SQLQuery sqlQuery = session.createSQLQuery("Select * from t_user");
			sqlQuery.addEntity(User.class);
			List<User> list = sqlQuery.list();
			for(User user : list){
				System.out.println(user);
			}
//			//2.调用SQLQuery对象里面的方法得到结果
//			List<Object[]> list = sqlQuery.list();
//			for(Object[] object : list){
//				System.out.println(Arrays.toString(object));
//			}
			
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

	//使用criteria对象
	@Test
	public void testCriteria(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			//1.创建criteria对象
			Criteria criteria = session.createCriteria(User.class);
			//2.调用criteria对象里面的方法得到结果
			List<User> list = criteria.list();
			
			for(User user : list){
				System.out.println(user);
			}
			
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
	
	//使用query对象
	@Test
	public void testQuery(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			//1.创建Query对象
			Query query = session.createQuery("from User");
			//2.调用query对象里面的方法得到结果
			List<User> list = query.list();
			
			for(User user : list){
				System.out.println(user);
			}
			
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
}

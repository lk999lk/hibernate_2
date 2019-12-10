package cn.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static final Configuration cfg;
	private static final SessionFactory sessionFactory;
	//静态代码块
	static{
		//加载核心配置文件
		cfg = new Configuration();
		cfg.configure();
		
		//创建
		sessionFactory = cfg.buildSessionFactory();
	}
	
	//提供方法返回
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	public static void main(String[] args){
		
	}
}

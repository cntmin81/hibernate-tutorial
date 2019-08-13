package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).buildSessionFactory();
		
		// create session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			int studentId = 1;
			session.beginTransaction();
			
			System.out.println("Getting student with id : " + studentId);
			Student myStudent = session.get(Student.class, studentId);
			
//			System.out.println("Delete student : " + myStudent);
//			session.delete(myStudent);
			
			System.out.println("Delete student id=2");
			session.createQuery("delete from Student where id=2").executeUpdate();
			
			session.getTransaction().commit();
			System.out.println("Done");
		} finally {
			session.close();
		}

	}

}

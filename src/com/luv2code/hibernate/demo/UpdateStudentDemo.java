package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			int studentId = 1;
			session.beginTransaction();
			
			System.out.println("getting student with id : " + studentId);
			Student student = session.get(Student.class, studentId);
			
			System.out.println("get complete : " + student);
			
			System.out.println("update student");
			student.setFirstName("Scooby");
			
			session.getTransaction().commit();
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("update email for all students");
			session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
			
			session.getTransaction().commit();
			
			
			System.out.println("done");
		} finally {
			session.close();
		}

	}

}

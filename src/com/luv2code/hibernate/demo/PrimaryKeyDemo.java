package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// create 3 student objects
			System.out.println("Creating 3 objects");
			Student student1 = new Student("Jone", "doe", "hone@luv2code.com");
			Student student2 = new Student("Mary", "public", "mary@luv2code.com");
			Student student3 = new Student("Bonita", "applebaum", "bonita@luv2code.com");
			
			// start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Save object");
			session.save(student1);
			session.save(student2);
			session.save(student3);
			
			// commit a transaction
			System.out.println("Done");
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}

}

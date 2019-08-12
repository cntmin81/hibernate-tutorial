package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// create a new student object
			System.out.println("Create new student object");
			Student theStudent = new Student("Daffy", "Duck", "daffy@luv2code.com");
			
			// begin transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Save the student object");
			System.out.println(theStudent);
			session.save(theStudent);
			
			// commit transaction			
			session.getTransaction().commit();
			
			// My new code
			// find out the student ID
			System.out.println("Saved student. Generated ID : " + theStudent.getId());
			
			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve the student based on the ID: primary key
			System.out.println("\nGetting student with id : " + theStudent.getId());
			Student myStudent = session.get(Student.class, theStudent.getId());
			
			System.out.println("Get compleate : " + myStudent);
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
		} finally {
			session.close();
		}
	}

}

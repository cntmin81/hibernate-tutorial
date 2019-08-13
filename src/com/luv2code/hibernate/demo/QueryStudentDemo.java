package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).buildSessionFactory();

		// create session
		Session session = sessionFactory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();
			
			// query student
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			// display the students
			displayStudents(theStudents);
			
			// query students: lastName='doe'
			theStudents = session.createQuery("from Student s where s.lastName='doe'").getResultList();
			
			// display the students
			System.out.println("\n\nStudents who has last name of doe");
			displayStudents(theStudents);
			
			// query students: last name='doe' first name = 'Daffy'
			System.out.println("\n\nStudents who have last name of doe or first name Daffy");
			theStudents = session.createQuery("from Student s where s.lastName='doe' or s.firstName='Daffy'").getResultList();
			displayStudents(theStudents);
			
			theStudents = session.createQuery("from Student s"
					+ " where s.email LIKE '%luv2code.com'").getResultList();
			
			System.out.println("\n\nStudent whose email ends with luv2code.com");
			displayStudents(theStudents);
			
			
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done");

		} finally {
			session.close();
		}

	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}

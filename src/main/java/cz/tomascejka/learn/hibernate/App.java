package cz.tomascejka.learn.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import cz.tomascejka.learn.hibernate.domain.Department;
import cz.tomascejka.learn.hibernate.domain.Employee;
import cz.tomascejka.learn.hibernate.util.HibernateUtil;

public class App {
	private static HibernateUtil hibernate = new HibernateUtil();
	private static final String cfgFileName = "hibernate.cfg.xml";

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = hibernate.getSessionFactory(cfgFileName).openSession();

		session.beginTransaction();

		Department department = new Department("java");
		session.save(department);

		session.save(new Employee("Jakab Gipsz", department));
		session.save(new Employee("Captain Nemo", department));

		session.getTransaction().commit();

		Query q = session.createQuery("From Employee ");

		List<Employee> resultList = q.list();
		System.out.println("num of employess:" + resultList.size());
		for (Employee next : resultList) {
			System.out.println("next employee: " + next);
		}

		hibernate.shutdown(cfgFileName);
	}
}

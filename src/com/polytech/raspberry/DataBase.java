package com.polytech.raspberry;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class DataBase {

	private Session session;

	public DataBase() {

		Configuration configuration = new Configuration().addAnnotatedClass(Connector.class).configure();

		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
				configuration.getProperties()).build();

		// expensive initialization
		session = configuration.buildSessionFactory(serviceRegistry).getCurrentSession();

	}

	public void save(Connector c) {
		// store
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();
	}
	
	public Connector getLastConnector() {
		session.beginTransaction();
        Query q1 = session.createQuery("from connector order by date");
        List<Connector> connectors = q1.list();
        Connector connector = null;
        if(connectors.size() > 0) {
        	connector = connectors.get(0);
        }
		session.getTransaction().commit();
		return connector;
	}

}

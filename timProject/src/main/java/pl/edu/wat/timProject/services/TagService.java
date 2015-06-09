package pl.edu.wat.timProject.services;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.wat.timProject.dataModel.hibernate.Tag;

@Component
public class TagService implements Serializable{
	private static final long serialVersionUID = 7362486861803929816L;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void register(Tag emp) {
		Session session = sessionFactory.getCurrentSession();
		session.save(emp);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}

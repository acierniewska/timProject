package pl.edu.wat.timProject.services;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.wat.timProject.dataModel.hibernate.Tag;
import pl.edu.wat.timProject.dataModel.hibernate.Weather;

@Component
public class WeatherService implements Serializable {
	private static final long serialVersionUID = 439437636581462481L;

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void register(Weather w) {
		sessionFactory.getCurrentSession().save(w);
	}

	@Transactional
	public List<Tag> listTags() {
		return getSessionFactory().getCurrentSession()
				.createQuery("from Weather").list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}

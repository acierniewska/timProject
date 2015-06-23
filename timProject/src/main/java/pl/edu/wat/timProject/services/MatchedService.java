package pl.edu.wat.timProject.services;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.wat.timProject.dataModel.hibernate.Matched;

@Component
public class MatchedService implements Serializable {

	private static final long serialVersionUID = 4244246336163575018L;

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void register(Matched match) {
		sessionFactory.getCurrentSession().save(match);
	}

	@Transactional
	public void update(Matched match) {
		getSessionFactory().getCurrentSession().update(match);
	}

	@Transactional
	public void delete(Matched match) {
		match.getMatchedClothes().clear();
		sessionFactory.getCurrentSession().delete(match);
	}

	@Transactional
	public List<Matched> listAll() {
		return getSessionFactory().getCurrentSession()
				.createQuery("from Matched").list();
	}

	@Transactional
	public Matched getById(long matchedId) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery(" from Matched where matchedId = ?")
				.setParameter(0, matchedId).list();

		return (Matched) list.get(0);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}

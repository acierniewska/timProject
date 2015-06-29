package pl.edu.wat.timProject.services;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.wat.timProject.dataModel.hibernate.Tag;

@Component
public class TagService implements Serializable {
	private static final long serialVersionUID = 7362486861803929816L;

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public Tag getByName(String name) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery(" from Tag where tagName = ?")
				.setParameter(0, name).list();
		if (list.isEmpty())
			return null;

		return (Tag) list.get(0);
	}

	@Transactional
	public void register(Tag tag) {
		sessionFactory.getCurrentSession().save(tag);
	}

	@Transactional
	public void update(Tag tag) {
		sessionFactory.getCurrentSession().update(tag);
	}

	@Transactional
	public void delete(Tag tag) {
		sessionFactory.getCurrentSession().delete(tag);
	}

	@Transactional
	public List<Tag> listAll() {
		return getSessionFactory().getCurrentSession().createQuery("from Tag")
				.list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}

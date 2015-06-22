package pl.edu.wat.timProject.services;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.wat.timProject.dataModel.hibernate.ClothesType;

@Component
public class ClothesTypeService implements Serializable {
	private static final long serialVersionUID = 7362486861803929816L;

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public ClothesType getByName(String name) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery(" from ClothesType where clothesTypeName = ?")
				.setParameter(0, name).list();

		return (ClothesType) list.get(0);
	}

	@Transactional
	public void register(ClothesType ct) {
		sessionFactory.getCurrentSession().save(ct);
	}

	@Transactional
	public void update(ClothesType ct) {
		sessionFactory.getCurrentSession().update(ct);
	}

	@Transactional
	public void delete(ClothesType ct) {
		sessionFactory.getCurrentSession().delete(ct);
	}

	@Transactional
	public List<ClothesType> listAll() {
		return getSessionFactory().getCurrentSession()
				.createQuery("from ClothesType").list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}

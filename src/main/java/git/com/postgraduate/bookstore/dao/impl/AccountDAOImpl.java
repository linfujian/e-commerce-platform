package git.com.postgraduate.bookstore.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import git.com.postgraduate.bookstore.dao.AccountDAO;
import git.com.postgraduate.bookstore.entity.Account;

@Repository
@Transactional
public class AccountDAOImpl implements AccountDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Account findAccount(String name) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(Account.class);
		criteria.add(Restrictions.eq("userName", name));
		return (Account)criteria.uniqueResult();
	}
	
	public void save(Account account) {
		Session session = getSession();
		session.save("Account", account);
	}
	
	private Session getSession() {
		Session session = getSessionFactory().getCurrentSession();
		if(session == null)
			session = getSessionFactory().openSession();
		return session;
	}
	
	private SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}

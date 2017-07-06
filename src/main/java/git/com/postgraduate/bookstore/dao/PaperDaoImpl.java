package git.com.postgraduate.bookstore.dao;

import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import git.com.postgraduate.bookstore.entity.Paper;

@Repository
public class PaperDaoImpl implements PaperDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void savePaper(Paper paper) {
		
		getSession().merge(paper);
		
	}

	public List<Paper> listPaper() {
		
		return getSession().createCriteria(Paper.class).list();
	}

	public Paper getPaper(Long id) {
		return getSession().get(Paper.class, id);
	}
	
	public Paper getPaper(String province, String university, String school, String major) {
		
		SQLQuery query = getSession().createSQLQuery("SELECT * FROM paper WHERE province= :pro AND university= :uni AND school= :sch AND major= :maj");
		query.setString("pro", province);
		query.setString("uni", university);
		query.setString("sch", school);
		query.setString("maj", major);
		//TODO
		//假如无返回值如何处理
		List<Paper> paper = query.addEntity(Paper.class).list();
		return  paper.size() == 0? null : paper.get(0);
	}

	public void deletePaper(Long id) {
		
		Paper paper = getPaper(id);
		getSession().delete(paper);
		
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

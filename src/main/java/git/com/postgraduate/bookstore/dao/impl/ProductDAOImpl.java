package git.com.postgraduate.bookstore.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import git.com.postgraduate.bookstore.dao.ProductDAO;
import git.com.postgraduate.bookstore.entity.Paper;
import git.com.postgraduate.bookstore.model.ProductInfo;

@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public Paper findProduct(Long code) {
		return getSession().get(Paper.class, code);
	}

	public ProductInfo findProductInfo(Long code) {
		Paper paper = this.findProduct(code);
		if(paper == null) {
			return null;
		}
		return new ProductInfo(paper.getId(), paper.getName(), paper.getPrice());
	}
	
	//TODO
	//may be done in uplaod paper info as manager
	/*public void save(ProductInfo productInfo) {
		Long code = productInfo.getCode();
		
		Paper paper = null;
		
		boolean isNew = false;
		if(code != null) {
			paper = this.findProduct(code);
		}
		if(paper == null) {
			isNew = true;
			paper = new Paper();
			paper.setPublishedOn(new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).format(new Data()));
		}
		paper.setId(code);
		paper.set
		//paper.set
		
	}*/
	
	
	private Session getSession() {
		Session session = getSessionFactory().getCurrentSession();
		if(session == null)
			session = getSessionFactory().openSession();
		return session;
	}
	
	private SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public ProductInfo findProductInfo(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

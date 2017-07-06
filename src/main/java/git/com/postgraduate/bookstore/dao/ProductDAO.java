package git.com.postgraduate.bookstore.dao;

import git.com.postgraduate.bookstore.entity.Paper;
import git.com.postgraduate.bookstore.model.ProductInfo;

public interface ProductDAO {

	public Paper findProduct(Long code);
	
	public ProductInfo findProductInfo(String code);
	
	//public void save(ProductInfo productInfo);
}

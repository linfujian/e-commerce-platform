package git.com.postgraduate.bookstore.service;

import git.com.postgraduate.bookstore.model.CartInfo;
import git.com.postgraduate.bookstore.model.CartLineInfo;
import git.com.postgraduate.bookstore.model.ProductInfo;

public interface CartService {

	public void insertCartToRedis(CartInfo cartInfo,String userName);
	
	public void updateCartToRedis(CartLineInfo line, String userName);
	
	public void deleteProductInfoToRedis(ProductInfo productInfo, String userName);
	
	public CartInfo selectCartInfoFromRedis(String username);
	
	public void deleteCartInfo(String userName);
}

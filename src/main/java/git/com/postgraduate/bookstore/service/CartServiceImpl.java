package git.com.postgraduate.bookstore.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.IdClass;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import git.com.postgraduate.bookstore.dao.AbstractRedisDao;
import git.com.postgraduate.bookstore.dao.impl.RedisDaoImpl;
import git.com.postgraduate.bookstore.entity.Paper;
import git.com.postgraduate.bookstore.model.CartInfo;
import git.com.postgraduate.bookstore.model.CartLineInfo;
import git.com.postgraduate.bookstore.model.ProductInfo;
import git.com.postgraduate.bookstore.utils.RedisUtil;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private PaperService paperService;
	
	@Autowired
	RedisDaoImpl redisDao;
	
	public void insertCartToRedis(CartInfo cartInfo, String userName) {
		
		List<CartLineInfo> items = cartInfo.getCartLines();
		if(! items.isEmpty()) {
			//redis key: username; field: code; value: amount
			HashMap<String, String>  map =  new HashMap<String, String>();
			for(CartLineInfo item : items) {
				//if exist in redis ,increaby
				if(redisDao.exists(userName, String.valueOf(item.getProductInfo().getCode()))) {
					redisDao.incrBy(userName, String.valueOf(item.getProductInfo().getCode()), new Long(item.getQuantity()));
				} else {
					map.put(String.valueOf(item.getProductInfo().getCode()), String.valueOf(item.getQuantity()));
				}
				if(map.size()>0)
					redisDao.set(userName, map);
			}
			
		}
	}
	
	public void updateCartToRedis(CartLineInfo line, String userName) {
		
		if(line.getQuantity()>0) {
			HashMap<String, String> map = new HashMap<String, String>();
			if(redisDao.exists(userName, String.valueOf(line.getProductInfo().getCode()))) {
				map.put(String.valueOf(line.getProductInfo().getCode()), String.valueOf(line.getQuantity()));
				if(map.size()>0)
					redisDao.set(userName, map);
			}
		}
		
	}

	public void deleteProductInfoToRedis(ProductInfo productInfo, String userName) {
		
		if(redisDao.exists(userName, String.valueOf(productInfo.getCode()))) {
			redisDao.remove(userName, String.valueOf(productInfo.getCode()));;
		}
		
	}

	public CartInfo selectCartInfoFromRedis(String username) {
		
		CartInfo cartInfo = new CartInfo();
		//get all product redis has form of key=username; map(field=code;value=quantity)
		HashMap<String, String> getAll = (HashMap<String, String>) redisDao.get(username);
		Set<Entry<String, String>> entrySet = getAll.entrySet();
		for(Entry<String, String> entry: entrySet) {
			
			Paper paper = paperService.getPaper(Long.valueOf(entry.getKey()));
			ProductInfo productInfo = new ProductInfo(paper);
			//add to shoppingCart
			cartInfo.addProduct(productInfo, Integer.parseInt(entry.getValue()));
		}
		
		return cartInfo;
	}

	@Override
	public void deleteCartInfo(String userName) {
		
		if(redisDao.exists(userName)) {
			redisDao.remove(userName);
		}
	}

}

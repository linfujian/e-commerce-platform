package git.com.postgraduate.bookstore.dao;

import java.util.List;

import git.com.postgraduate.bookstore.model.CartInfo;
import git.com.postgraduate.bookstore.model.OrderDetailInfo;
import git.com.postgraduate.bookstore.model.OrderInfo;

public interface OrderDAO {

	public void saveOrder(CartInfo cartInfo);
	
	public OrderInfo getOrderInfo(String orderId);
	
	public List<OrderDetailInfo> listOrderDetailInfos(String orderId);

	public List<OrderInfo> getOrderInfoByLoginName(String userName);
}

package git.com.postgraduate.bookstore.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import git.com.postgraduate.bookstore.dao.OrderDAO;
import git.com.postgraduate.bookstore.dao.PaperDao;
import git.com.postgraduate.bookstore.entity.Order;
import git.com.postgraduate.bookstore.entity.OrderDetail;
import git.com.postgraduate.bookstore.entity.Paper;
import git.com.postgraduate.bookstore.model.CartInfo;
import git.com.postgraduate.bookstore.model.CartLineInfo;
import git.com.postgraduate.bookstore.model.CustomerInfo;
import git.com.postgraduate.bookstore.model.OrderDetailInfo;
import git.com.postgraduate.bookstore.model.OrderInfo;

@Repository
@Transactional
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private PaperDao paperDao;
	
	private int getMaxOrderNum() {
		String sql = "select max(o.orderNum) from " + Order.class.getName() + " o ";
		Query query = getSession().createQuery(sql);
		Integer value = (Integer) query.uniqueResult();
		if(value == null) {
			return 0;
		}
		return value;
	}
	
	public void saveOrder(CartInfo cartInfo) {
		
		int orderNum = this.getMaxOrderNum() + 1;
		Order order = new Order();
		
		order.setId(UUID.randomUUID().toString());
		order.setOrderNum(orderNum);
		order.setOrderDate(new Date());
		order.setAmount(cartInfo.getFareTotal());
		order.setOrderNote(cartInfo.getNote());
		order.setOrderStatus(cartInfo.getOrderStatus());
		order.setLoginName(cartInfo.getLoginName());
		order.setPayFlag(cartInfo.isPayFlag());
		order.setExpressName(cartInfo.getExpressName());
		order.setExpressCost(cartInfo.getExpressCost());
		
		
		CustomerInfo customerInfo = cartInfo.getCustomerInfo();
		order.setCustomerName(customerInfo.getName());
		order.setCustomerEmail(customerInfo.getEmail());
		order.setCustomerPhone(customerInfo.getPhone());
		order.setCustomerAddress(customerInfo.getAddress());
		order.setCustomerAddressDetail(customerInfo.getAddressDetail());
		
		getSession().persist(order);
		
		List<CartLineInfo> lines = cartInfo.getCartLines();
		
		for(CartLineInfo line : lines) {
			OrderDetail detail = new OrderDetail();
			detail.setId(UUID.randomUUID().toString());
			detail.setOrder(order);
			detail.setAmount(line.getAmount());
			detail.setPrice(line.getProductInfo().getPrice());
			detail.setQuantity(line.getQuantity());
			
			Long code = line.getProductInfo().getCode();
			Paper paper = this.paperDao.getPaper(code);
			detail.setPaper(paper);
			
			getSession().persist(detail);
			
			cartInfo.setOrderNum(orderNum);
		}
		
		
		
	}
	
	@Override
	public List<OrderInfo> getOrderInfoByLoginName(String userName) {
		String sql = "select new " + OrderInfo.class.getName() + "(ord.id, ord.orderDate, ord.orderNum, " + 
				"ord.amount, ord.orderNote, ord.payFlag, ord.orderStatus, " + 
				"ord.customerName, ord.customerAddress, ord.customerAddressDetail, ord.customerEmail, ord.customerPhone, " + 
				"ord.transactionNum, ord.payTime, ord.expressNum, ord.expressName, ord.expressCost" +  ") from " 
				+ Order.class.getName() + " ord where ord.loginName=:loginName order by ord.orderNum desc ";
		
		Query query = getSession().createQuery(sql);
		query.setParameter("loginName", userName);
		return query.list();
	}
	
	private Order findOrder(String orderId) {
		Criteria crit = getSession().createCriteria(Order.class);
		crit.add(Restrictions.eq("id", orderId));
		return (Order)crit.uniqueResult();
	}

	public OrderInfo getOrderInfo(String orderId) {
		Order order = this.findOrder(orderId);
		if(order == null) {
			return null;
		}
		return new OrderInfo(order.getId(), order.getorderDate(), //
				order.getOrderNum(), order.getAmount(), order.getOrderNote(),//
				order.isPayFlag(), order.getOrderStatus(), order.getCustomerName(), //
				order.getCustomerAddress(), order.getCustomerAddressDetail(),//
				order.getCustomerEmail(), order.getCustomerPhone(), //
				order.getTransactionNum(), order.getPayTime(),order.getExpressNum(), //
				order.getExpressName(),order.getExpressCost());
	}

	public List<OrderDetailInfo> listOrderDetailInfos(String orderId) {
		String sql = "select new " + OrderDetailInfo.class.getName() //
				+ "(d.id, d.paper.id, d.paper.name, d.quantity, d.price, d.amount) " //
				+ " from " +  OrderDetail.class.getName() + " d " //
				+ " where d.order.id = :orderId ";
		
		Query query = getSession().createQuery(sql);
		query.setParameter("orderId", orderId);
		
		return query.list();
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

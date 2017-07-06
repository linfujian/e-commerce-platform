package git.com.postgraduate.bookstore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.PrimitiveIterator.OfDouble;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import git.com.postgraduate.bookstore.dao.OrderDAO;
import git.com.postgraduate.bookstore.model.CartInfo;
import git.com.postgraduate.bookstore.model.CustomerInfo;
import git.com.postgraduate.bookstore.model.OrderDetailInfo;
import git.com.postgraduate.bookstore.model.OrderInfo;
import git.com.postgraduate.bookstore.service.CartService;
import git.com.postgraduate.bookstore.service.SecurityService;
import git.com.postgraduate.bookstore.utils.Utils;
import git.com.postgraduate.bookstore.validator.CustomerInfoValidator;

@Controller
public class OrderInfoController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private CustomerInfoValidator customerInfoValidator;
	
	@Autowired
	private OrderDAO orderDAO;
	
	
	//based on whether login to redirect
	@RequestMapping(value={"/checkLogin"}, method=RequestMethod.GET)
	public String checkLogin() {
		
		String userName = securityService.findLoggedUsername();
		if(null != userName) {
			//if login so to handle order
			return "redirect:/shoppingCartCustomer";
		} else {
			//if not login so to redirect login page
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value={"/shoppingCartCustomer"}, method=RequestMethod.GET)
	public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {
		
		String userName = securityService.findLoggedUsername();
		if(userName != null) {
			
			CartInfo cartInfo = Utils.getCartInSession(request);
			CustomerInfo customerInfo = cartInfo.getCustomerInfo();
			
			if(cartInfo.isEmpty()) {
				customerInfo = new CustomerInfo();
			}
			model.addAttribute("customerForm",customerInfo);
			return "shoppingCartCustomer";
			
		} else { //if do not login redirect to login page
			return "redirect:/login";
		}
		
	}
	
	@RequestMapping(value={"/shoppingCartCustomer"}, method=RequestMethod.POST)
	public String shoppingCartCustomerSave(@ModelAttribute("customerForm") CustomerInfo customerInfo, BindingResult bindingResult, Model model, HttpServletRequest request) {
		
		String userName = securityService.findLoggedUsername();
		
		if(userName != null) {
			
			customerInfoValidator.validate(customerInfo, bindingResult);
			if(bindingResult.hasErrors()) {
				customerInfo.setValid(false);
				return "shoppingCartCustomer";
			}
			
			customerInfo.setValid(true);
			CartInfo cartInfo = cartService.selectCartInfoFromRedis(userName);
			cartInfo.setQuantity(cartInfo.getQuantityTotal());
			cartInfo.setTotal(cartInfo.getAmountTotal());
			cartInfo.setCustomerInfo(customerInfo);
			
			//use http session to transfer cartInfor tempory
			CartInfo cartInfo2 = Utils.getCartInSession(request);
			
			if(cartInfo2.isEmpty()) {
				cartInfo2.setCartInfo(cartInfo); // put cartInfor with customerInfo to session cartInfo
			}
			
			return "redirect:/shoppingCartConfirmation";
			
		} else { //if do not login redirect to login page
			return "redirect:/login";
		}
		
	}
	
	@RequestMapping(value={"/shoppingCartConfirmation"}, method=RequestMethod.GET)
	public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
		
		String userName = securityService.findLoggedUsername();
		
		if(userName != null) {
		CartInfo cartInfo = Utils.getCartInSession(request);
		if(cartInfo.isEmpty()) {
			return "redirect:/toCart";
		} else if (! cartInfo.isValidCustomer()) {
			return "redirect:/shoppingCartCustomer";
		}
		
		return "shoppingCartConfirmation";
		} else { //if do not login redirect to login page
			return "redirect:/login";
		}
		
	}
	
	@RequestMapping(value={"/shoppingCartConfirmation"}, method=RequestMethod.POST)
	// Avoid UnexpectedRollbackException (See more explanations)
	@Transactional(propagation = Propagation.NEVER)
	public String shoppingCartConfirmationFinalize(@ModelAttribute("note") String note, @ModelAttribute("fare") String fare, HttpServletRequest request, Model model) {
		
		String userName = securityService.findLoggedUsername();
		
		if(userName != null) {
			
		CartInfo cartInfo = Utils.getCartInSession(request);
		if(cartInfo.isEmpty()) {
			return "redirect:/toCart";
		} else if (! cartInfo.isValidCustomer()) {
			return "redirect:/shoppingCartCustomer";
		}
		//add fare to total of cartInfo
		double fareCost = 0;
		
		switch (fare) {
		case "sf":
			fareCost = 12.0;
			break;
		case "yd":
			fareCost = 10.0;
			break;
		case "st":
			fareCost = 8.0;
			break;
		case "yt":
			fareCost = 7.0;
			break;
		case "zt":
			fareCost = 5.0;
			break;
		case "ht":
			fareCost = 4.0;
			break;
		}
		double fareTotal = cartInfo.getAmountTotal() + fareCost;
		cartInfo.setFareTotal(fareTotal);
		//add note to cartInfo
		cartInfo.setNote(note);
		
		//add 0-not pay to cartInfo
		cartInfo.setOrderStatus(0);
		
		//add unique key login name to order
		cartInfo.setLoginName(userName);
		//set pay_flag to false before user pay successfully
		cartInfo.setPayFlag(false);
		
		//add express name/cost to cart
		cartInfo.setExpressName(fare);
		cartInfo.setExpressCost(fareCost);
		
		try {
			orderDAO.saveOrder(cartInfo);
		} catch (Exception e) {
			// Need: Propagation.NEVER?
			return "shoppingCartConfirmation";
		}
		
		//remove cart in session to avoid user return to cartConformation page
		Utils.removeCartInSession(request);
		
		//store last ordered cart to session for use in last page
		Utils.storeLastOrderedCartInSession(request, cartInfo);
		
		//remove cartInfo in redis
		cartService.deleteCartInfo(userName);
		
		//redirect to order submit success / waiting for pay page
		return "redirect:/shoppingCartFinalizeAndToPay";
		
		} else { //if do not login redirect to login page
			return "redirect:/login";
		}
		
	}
	
	@RequestMapping(value={"/shoppingCartFinalizeAndToPay"}, method=RequestMethod.GET)
	public String shoppingCartFinalize(HttpServletRequest request, Model model) {
		String userName = securityService.findLoggedUsername();
		
		if(userName != null) {
			
			CartInfo cartInfo = Utils.getLastOrderedCartInSession(request);
			if(cartInfo.isEmpty()) {
				return "redirect:/toCart";
			}
			//if normal return topay page to user and add weixin pay api
			return "toPay";
			
		} else { //if do not login redirect to login page
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value={"/toOrder"}, method=RequestMethod.GET)
	public String getOrderInfo(Model model) {
		String userName = securityService.findLoggedUsername();
		
		if(userName != null) {
			
			List<OrderInfo> orderList = orderDAO.getOrderInfoByLoginName(userName);
			
			model.addAttribute("orderList", orderList);
			
			return "orderList";
			
		} else { //if do not login redirect to login page
			return "redirect:/login";	
		}
	}
	
	@RequestMapping(value={"/toOrderDetail"}, method=RequestMethod.GET)
	public String getOrderDetailInfo(Model model, @RequestParam("order_id") String orderId) {
		
		String userName = securityService.findLoggedUsername();
		if(null != userName) {
			
			OrderInfo orderInfo = null;
			if(orderId != null) {
				orderInfo = orderDAO.getOrderInfo(orderId);
			}
			if(orderInfo == null) {
				return "redirect:/toOrder";
			}
			List<OrderDetailInfo> details = orderDAO.listOrderDetailInfos(orderId);
			orderInfo.setDetails(details);
			
			model.addAttribute("orderInfo", orderInfo);
			
			return "orderDetail";
			
		} else { //if do not login redirect to login page
			return "redirect:/login";	
		}
	}
	
}

package git.com.postgraduate.bookstore.controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import git.com.postgraduate.bookstore.model.CartInfo;
import git.com.postgraduate.bookstore.service.CartService;
import git.com.postgraduate.bookstore.service.SecurityService;

@Controller
public class ToCartController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private SecurityService securityService;
	
	@RequestMapping(value= "/toCart")
	public String toCart(Model model, HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper om = new ObjectMapper();
		om.setSerializationInclusion(Include.NON_NULL);
		CartInfo cartInfo = null;
		//get cart from cookie
		Cookie[] cookies =  request.getCookies();
		if(null != cookies && cookies.length>0) {
			for(Cookie cookie : cookies) {
				if("BUYER_CART".equals(cookie.getName())) {
					String decode = URLDecoder.decode(cookie.getValue(), "UTF-8"); 
					cartInfo = om.readValue(decode, CartInfo.class);
					break;
				}
			}
		}
		
		//judge whether login in
		String userName = securityService.findLoggedUsername();
		if(null != userName) {
			//login in
			//if cart is not empty add cart to redis
			if(null != cartInfo) {
				cartService.insertCartToRedis(cartInfo, userName);
				// destroy cookie as before
				Cookie cookie = new Cookie("BUYER_CART", null);
				cookie.setPath("/");
				cookie.setMaxAge(-0);
				response.addCookie(cookie);
			}
			// get cart from redis
			cartInfo = cartService.selectCartInfoFromRedis(userName);
		}
		
		if(null == cartInfo) {
			cartInfo = new CartInfo();
		}
		
		cartInfo.setQuantity(cartInfo.getQuantityTotal());
		cartInfo.setTotal(cartInfo.getAmountTotal());
		
		//return cart to html react to construct components
		model.addAttribute("BUYER_CART", cartInfo);
		
		return "cart";
	}
}

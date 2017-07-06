package git.com.postgraduate.bookstore.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import git.com.postgraduate.bookstore.entity.Paper;
import git.com.postgraduate.bookstore.model.CartInfo;
import git.com.postgraduate.bookstore.model.CartLineInfo;
import git.com.postgraduate.bookstore.model.ProductInfo;
import git.com.postgraduate.bookstore.service.CartService;
import git.com.postgraduate.bookstore.service.PaperService;
import git.com.postgraduate.bookstore.service.SecurityService;
import git.com.postgraduate.bookstore.utils.Utils;

@Controller
public class RedisCartController {

	@Autowired
	private PaperService paperService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private SecurityService securityService;
	
	@RequestMapping(value={"/addcart/{code}/{quantity}"})
	public String buyerCart(@PathVariable("code") Long code, @PathVariable("quantity") Integer quantity, HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper om = new ObjectMapper();
		om.setSerializationInclusion(Include.NON_NULL);
		CartInfo cartInfo = null;
		Paper paper = null;
		
		//get cart from cookie
		Cookie[] cookies = request.getCookies();
		if(null != cookies && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if("BUYER_CART".equals(cookie.getName())) {
					String decode = URLDecoder.decode(cookie.getValue(), "UTF-8"); 
					cartInfo = om.readValue(decode, CartInfo.class);
					break;
				}
			}
		}
		
		//if no cart in cookie create it
		if(null == cartInfo)
			cartInfo = new CartInfo();
		
		//add current product into cart
		if(null != code) {
			paper = paperService.getPaper(code);
		}
		if(null != paper) {
			ProductInfo productInfo = new ProductInfo(paper);
			cartInfo.addProduct(productInfo, quantity);
			cartInfo.setQuantity(cartInfo.getQuantityTotal());
			cartInfo.setTotal(cartInfo.getAmountTotal());
		}
		
		//above login in or login out is the same
		//below need judge
		String userName = securityService.findLoggedUsername();
		if(null != userName) {
			//login in
			//add cart to redis
			cartService.insertCartToRedis(cartInfo, userName);
			//clear cookie, set survive time=0 and destroy
			Cookie cookie = new Cookie("BUYER_CART", null);
			cookie.setPath("/");
			cookie.setMaxAge(-0);
			response.addCookie(cookie);
			
		} else {
			//not login in
			//save cart into cookie
			//convert object into json
			Writer writer = new StringWriter();
			om.writeValue(writer, cartInfo);
			String encode = URLEncoder.encode(writer.toString(), "UTF-8");
			Cookie cookie = new Cookie("BUYER_CART", encode);
			//set cookie is public share
			cookie.setPath("/");
			//max exist time is 24h
			cookie.setMaxAge(24*60*60);
			//cookie is writed into browser
			response.addCookie(cookie);
		}
		
		return "redirect:/toCart";
	}
	
	@RequestMapping("/singleupdate/{code}/{num}")
	public @ResponseBody String updateAndReturn( 
			@PathVariable("code") Long code, @PathVariable("num") Integer quantity, HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper om = new ObjectMapper();
		om.setSerializationInclusion(Include.NON_NULL);
		CartInfo cartInfo = null;
		Paper paper = null;
		
		//get cart from cookie
		Cookie[] cookies = request.getCookies();
		if(null != cookies && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if("BUYER_CART".equals(cookie.getName())) {
					String decode = URLDecoder.decode(cookie.getValue(), "UTF-8"); 
					cartInfo = om.readValue(decode, CartInfo.class);
					break;
				}
			}
		}
		
		//if no cart in cookie create it
		if(null == cartInfo)
			cartInfo = new CartInfo();
		
		//add  product into cart to instead fronted product infor
		if(null != code) {
			paper = paperService.getPaper(code);
		}
		
		if(null != paper) {
			ProductInfo productInfo = new ProductInfo(paper);
			
			//remove existing productInfo
			cartInfo.removeProduct(productInfo);
			
			cartInfo.addProduct(productInfo, quantity);
			cartInfo.setQuantity(cartInfo.getQuantityTotal());
			cartInfo.setTotal(cartInfo.getAmountTotal());
		}
		
		//above login in or login out is the same
		//below need judge
		String userName = securityService.findLoggedUsername();
		if(null != userName) {
			//login in
			//update line single to redis
			cartService.updateCartToRedis(cartInfo.findLineByCode(code), userName);
			//clear cookie, set survive time=0 and destroy
			Cookie cookie = new Cookie("BUYER_CART", null);
			cookie.setPath("/");
			cookie.setMaxAge(-0);
			response.addCookie(cookie);
			
		} else {
			//not login in
			//save cart into cookie
			//convert object into json
			Writer writer = new StringWriter();
			om.writeValue(writer, cartInfo);
			String encode = URLEncoder.encode(writer.toString(), "UTF-8");
			Cookie cookie = new Cookie("BUYER_CART", encode);
			//set cookie is public share
			cookie.setPath("/");
			//max exist time is 24h
			cookie.setMaxAge(24*60*60);
			//cookie is writed into browser
			response.addCookie(cookie);
		}
		
		return "success";
		
	}
	
	@RequestMapping("deleteproduct/{code}")
	public @ResponseBody String deleteProduct(@PathVariable("code") Long code, HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper om = new ObjectMapper();
		om.setSerializationInclusion(Include.NON_NULL);
		CartInfo cartInfo = null;
		ProductInfo productInfo = null;
		Paper paper = null;
		
		//get cart from cookie
		Cookie[] cookies = request.getCookies();
		if(null != cookies && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if("BUYER_CART".equals(cookie.getName())) {
					String decode = URLDecoder.decode(cookie.getValue(), "UTF-8"); 
					cartInfo = om.readValue(decode, CartInfo.class);
					break;
				}
			}
		}
		
		//if no cart in cookie create it
		if(null == cartInfo)
			cartInfo = new CartInfo();
		
		//add  product into cart to instead fronted product infor
		if(null != code) {
			paper = paperService.getPaper(code);
		}
		
		if(null != paper) {
			productInfo = new ProductInfo(paper);
			
			//remove existing productInfo
			cartInfo.removeProduct(productInfo);
			
			cartInfo.setQuantity(cartInfo.getQuantityTotal());
			cartInfo.setTotal(cartInfo.getAmountTotal());
		}
		
		
		//above login in or login out is the same
		//below need judge
		String userName = securityService.findLoggedUsername();
		if(null != userName) {
			//login in
			//delete line single to redis
			cartService.deleteProductInfoToRedis(productInfo, userName);;
			//clear cookie, set survive time=0 and destroy
			Cookie cookie = new Cookie("BUYER_CART", null);
			cookie.setPath("/");
			cookie.setMaxAge(-0);
			response.addCookie(cookie);
			
		} else {
			//not login in
			//save cart into cookie
			//convert object into json
			Writer writer = new StringWriter();
			om.writeValue(writer, cartInfo);
			String encode = URLEncoder.encode(writer.toString(), "UTF-8");
			Cookie cookie = new Cookie("BUYER_CART", encode);
			//set cookie is public share
			cookie.setPath("/");
			//max exist time is 24h
			cookie.setMaxAge(24*60*60);
			//cookie is writed into browser
			response.addCookie(cookie);
		}
		
		return "success";
		
	}
	
	
}

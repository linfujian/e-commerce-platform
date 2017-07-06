package git.com.postgraduate.bookstore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import git.com.postgraduate.bookstore.entity.Paper;
import git.com.postgraduate.bookstore.model.CartInfo;
import git.com.postgraduate.bookstore.model.CartLineInfo;
import git.com.postgraduate.bookstore.model.ProductInfo;
import git.com.postgraduate.bookstore.service.PaperService;
import git.com.postgraduate.bookstore.service.SecurityService;
import git.com.postgraduate.bookstore.utils.Utils;

@Controller
public class SearchController {

	@Autowired
	PaperService paperService;
	
	@Autowired
	SecurityService securityService;
	
	@RequestMapping(value={"/findbook"})
	public String searchView(HttpServletRequest request){
		if(securityService.findLoggedUsername() != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", securityService.findLoggedUsername());
		}
		return "searchPaper";
	}
	
	@RequestMapping("/query")
	public @ResponseBody Paper queryDetail(@RequestParam String province, @RequestParam String university,
							  @RequestParam String school, @RequestParam String major) {
		
		Paper paper = paperService.getPaper(province, university, school, major);
		
		return paper;
	}
	
	@RequestMapping("/querysingleincart/{code}")
	public @ResponseBody Paper querySingle(@PathVariable("code") Long code) {
	
		return paperService.getPaper(code);
	}
	
}


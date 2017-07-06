package git.com.postgraduate.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import git.com.postgraduate.bookstore.entity.Account;
import git.com.postgraduate.bookstore.service.AccountService;
import git.com.postgraduate.bookstore.service.SecurityService;
import git.com.postgraduate.bookstore.validator.AccountValidator;

@Controller
public class SecurityController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private AccountValidator accountValidator;
	
	@RequestMapping(value={"/registration"}, method= RequestMethod.GET)
	public String Registration(Model model) {
		model.addAttribute("accountForm", new Account());
		
		return "registration";
	}
	
	@RequestMapping(value={"/registration"}, method= RequestMethod.POST)
	public String registration(@ModelAttribute("accountForm") Account accountForm, BindingResult bindingResult, Model model) {
		accountValidator.validate(accountForm, bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "registration";
		}
		
		accountService.save(accountForm);
		
		//securityService.autologin(accountForm.getUserName(), accountForm.getPassword());
		
		return "redirect:login";
		
	}
	
	@RequestMapping(value={"/login"}, method= RequestMethod.GET)
	public String login(Model model,String error, String logout) {
		if(error != null) 
			model.addAttribute("error", "Your username and password is invalid");
		if(logout != null)
			model.addAttribute("message", "You have been logged out successfully");
		return "login";
	}
}

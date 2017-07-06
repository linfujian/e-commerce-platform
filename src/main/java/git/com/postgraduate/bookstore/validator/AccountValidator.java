package git.com.postgraduate.bookstore.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import git.com.postgraduate.bookstore.entity.Account;
import git.com.postgraduate.bookstore.service.AccountService;

@Component
public class AccountValidator implements Validator {
	@Autowired
	AccountService accountService;
	
	public boolean supports(Class<?> aClass) {
		return Account.class.equals(aClass);
	}

	public void validate(Object o, Errors errors) {
		Account account = (Account) o;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.accountForm.name");
		if(account.getUserName().length() < 6 || account.getUserName().length() > 32) {
			errors.rejectValue("userName", "Size.accountForm.username");
		}
		if(accountService.findByUsername(account.getUserName()) != null) {
			errors.rejectValue("userName", "Duplicate.accountForm.username");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.accountForm.password");
		if(account.getPassword().length() <  8 || account.getPassword().length() > 32) {
			errors.rejectValue("password", "Size.accountForm.password");
		}
		
		if(!account.getPasswordConfirm().equals(account.getPassword())) {
			errors.rejectValue("password", "Diff.accountForm.passwordConfirm");
		}
		
	}

}

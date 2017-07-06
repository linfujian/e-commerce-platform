package git.com.postgraduate.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import git.com.postgraduate.bookstore.dao.AccountDAO;
import git.com.postgraduate.bookstore.entity.Account;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private AccountDAO accountDAO;
	
	public void save(Account user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setPasswordConfirm(bCryptPasswordEncoder.encode(user.getPasswordConfirm()));
		
		accountDAO.save(user);
	}

	public Account findByUsername(String username) {
		
		return accountDAO.findAccount(username);
	}

}

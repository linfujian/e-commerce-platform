package git.com.postgraduate.bookstore.service;

import git.com.postgraduate.bookstore.entity.Account;

public interface AccountService {

	public void save(Account user);
	
	public Account findByUsername(String username);
}

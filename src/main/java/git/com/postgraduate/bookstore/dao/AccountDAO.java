package git.com.postgraduate.bookstore.dao;

import git.com.postgraduate.bookstore.entity.Account;

public interface AccountDAO {

	public Account findAccount(String name);
	
	public void save(Account account);
}

package git.com.postgraduate.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import git.com.postgraduate.bookstore.dao.AccountDAO;
import git.com.postgraduate.bookstore.entity.Account;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AccountDAO accountDAO;
	
	@Transactional(readOnly= true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountDAO.findAccount(username);
		System.out.println("Account=" + account);
		
		if(account == null) {
			throw new UsernameNotFoundException("User" + username + "was not found in the database");
		}
		
		//EMPLOYEE MANAGER
		String role = account.getUserRole();
		
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		
		// ROLE_EMPLOYEE ROLE_MANAGER
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
		grantList.add(authority);
		
		boolean enabled = account.isActive();
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		UserDetails userDetails = (UserDetails)new User(account.getUserName(), account.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantList);
		return userDetails;
	}

}

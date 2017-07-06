package git.com.postgraduate.bookstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/*授权相关的在security-context.xml中已配置
	 * 包括provider和password encoder
	 * */	
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			//TODO
			http.csrf().disable();
			
			//the pages requires login as EMPLOYEE or MANAGER.
			//if not login, it will redirect to /login page
			http.authorizeRequests().antMatchers("/orderList","/order","/accountInfo")
									.access("hasRole('ROLE_EMPLOYEE', 'ROLE_MANAGER')");
			
			http.authorizeRequests().antMatchers("/product").access("hasRole('RILE_MANAGER')");
			
			//when the user has logged in as XX.
			//But access a page that requires role YY,
			//AccessDeniedException will throw
			http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
			
			http
			.authorizeRequests()
			.and()
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/request_for_login")
			.defaultSuccessUrl("/findbook")
			.failureUrl("/login?error")
			.usernameParameter("userName")
			.passwordParameter("password")
			.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout");
		}
}

package br.com.tcc.testrest.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("rui").password("admin").roles("PESQUISAR", "CADASTRAR").and()
			.withUser("rosanne").password("12345").roles("PESQUISAR");
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring()
		
		   .antMatchers("/layout/**");
	
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
//				.antMatchers("/vinhos").hasRole("PESQUISAR")
//				.antMatchers("/vinhos/**").hasRole("CADASTRAR")
				.antMatchers("/testrest").hasRole("PESQUISAR")
				.antMatchers("/testrest/**").hasRole("CADASTRAR")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				/*.failureUrl("/?error")*/
			
                 //Método OPCIONAL			 
			  	//.defaultSuccessUrl("/vinhos")
				.loginPage("/")
				.permitAll()
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	
	}
	
	
}

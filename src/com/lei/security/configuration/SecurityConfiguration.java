package com.lei.security.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.lei.security.service.CoDUserDetailsService;


/**
 * 
 * @author Vinay.Kumar1
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("codUserDetailsService")
	UserDetailsService userDetailsService;
	
	@Autowired
	@Qualifier("requestAwareAuthenticationSuccessHandler")
	RequestAwareAuthenticationSuccessHandler requestAwareAuthenticationSuccessHandler;
	
	@Autowired
	@Qualifier("requestAwareAuthenticationFailureHandler")
	RequestAwareAuthenticationFailureHandler requestAwareAuthenticationFailureHandler;
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	
	/*@Autowired
	private DataSource dataSource;*/
	
	@Bean(name="authenticationManager")
	   @Override
	   public AuthenticationManager authenticationManagerBean() throws Exception {
	       return super.authenticationManagerBean();
	   }
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  http
	  
	  	//****
		  .csrf().disable()
	      .exceptionHandling()
	      .authenticationEntryPoint(restAuthenticationEntryPoint)
	      .and()
	      .authorizeRequests()
	      .antMatchers("/*").permitAll()
	      .and()
	      .formLogin()
	      .successHandler(requestAwareAuthenticationSuccessHandler)
	      .failureHandler(new SimpleUrlAuthenticationFailureHandler())
	      .and()
	      .logout()
	 
	  	;
	}
	
	
	
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		
	  http
	  
	  	//****
		  .csrf().disable()
	      .exceptionHandling()
	      .authenticationEntryPoint(restAuthenticationEntryPoint)
	      .and()
	      .authorizeRequests()
	       .antMatchers("/").permitAll()
	      .antMatchers("/projectservice/**").authenticated()
	      .and()
	      .formLogin().loginProcessingUrl("/")
	      .successHandler(requestAwareAuthenticationSuccessHandler)
	      .failureHandler(new SimpleUrlAuthenticationFailureHandler()).failureUrl("/")
	      .and()
	      .logout().logoutUrl("/");
	 
	  	;
	  
	 
	}*/

	
	/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
	  http
	  
	  	//****
		  .csrf().disable()
	      .exceptionHandling()
	      .authenticationEntryPoint(restAuthenticationEntryPoint)
	      .and()
	      .authorizeRequests()
	       .antMatchers("/CdiOnDemand").permitAll()
	      .antMatchers("/CdiOnDemand/projectservice/**").authenticated()
	      .and()
	      .formLogin().loginProcessingUrl("/CdiOnDemand")
	      .usernameParameter("email")
			.passwordParameter("password")
	      .successHandler(requestAwareAuthenticationSuccessHandler)
	      .failureHandler(new SimpleUrlAuthenticationFailureHandler()).failureUrl("/CdiOnDemand")
	      .and()
	      .logout().logoutUrl("/CdiOnDemand")
	 
	  	.and()
		.rememberMe().rememberMeServices(rememberMeServices()).tokenRepository(persistentTokenRepository())
		.tokenValiditySeconds(1209600);
	}
	
	
	/*
	  @Bean
      public RememberMeServices rememberMeServices(){
	          PersistentTokenBasedRememberMeServices rememberMeServices = new PersistentTokenBasedRememberMeServices(
	                          //environment.getProperty('application.key')
	        		  "ONEHnT9cfxChdSr+IUb1sipywA2ztHTDc6OIkEQ7GpY=", userDetailsService, persistentTokenRepository());
	          rememberMeServices.setAlwaysRemember(true);
//	          rememberMeServices.
	          return rememberMeServices;
	      }

	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setCreateTableOnStartup(false);
		db.setDataSource(dataSource);
		
		return db;
	}*/
	
}

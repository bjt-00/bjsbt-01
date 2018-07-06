package com.bitguiders.bjsbt_01;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// Authentication : User --> Roles
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance())
		.withUser("user").password("pwd").roles("USER").and()
		.withUser("admin").password("pwd").roles("USER", "ADMIN");
	}

	// Authorization : Role -> Access
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests()
		.antMatchers("/","/home").permitAll().anyRequest().authenticated()
		.antMatchers("/user/**").hasRole("USER")
		.antMatchers("/**").hasRole("ADMIN").and()
		.formLogin().loginPage("/login").permitAll().and()
		.logout().permitAll().and()
		.csrf().disable().headers().frameOptions().disable();
	}

}

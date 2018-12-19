package com.bitguiders.bjsbt_01.conf.security;


import static org.springframework.security.extensions.saml2.config.SAMLConfigurer.*;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.saml.websso.WebSSOProfileConsumerImpl;
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class HybridSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Value("${security.saml2.metadata-url}")
    String metadataUrl;
    @Value("${security.saml2.metadata-path}")
    String metadataPath;
    @Value("${server.ssl.key-alias}")
    String keyAlias;
    @Value("${server.ssl.key-store-password}")
    String password;
    @Value("${server.port}")
    String port;
    @Value("${server.ssl.key-store}")
    String keyStoreFilePath;
//    @Value("${security.saml2.responseSkew}")
//    int responseSkew = 0;

	// Authentication : User --> Roles
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance())
		.withUser("user").password("pwd").roles("USER").and()
		.withUser("admin").password("pwd").roles("USER", "ADMIN").and()
		.withUser("bitguiders@gmail.com").password("a->ktO01;").roles("USER", "ADMIN");
	}

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
    	//WebSSOProfileConsumerImpl ssoConsumer = new WebSSOProfileConsumerImpl();
    	//ssoConsumer.setResponseSkew(this.sso);
    	
        http
            .authorizeRequests()
            .antMatchers("/saml*").permitAll().anyRequest().authenticated()
            .antMatchers("/","/home").permitAll().anyRequest().authenticated()
            .antMatchers("/user/**").hasRole("USER")
    		.antMatchers("/**").hasAnyRole("ADMIN","USER")
    		.and()
    		//.formLogin().loginPage("/login").permitAll().and()
    		//.logout().permitAll().and()
            .apply(saml())
                .serviceProvider()
                	//.ssoProfileConsumer(ssoConsumer)
                    .keyStore()
                        .storeFilePath(this.keyStoreFilePath)
                        .password(this.password)
                        .keyname(this.keyAlias)
                        .keyPassword(this.password)
                        .and()
                    .protocol("https")
                    .hostname(String.format("%s:%s", "localhost", this.port))
                    .basePath("/bjsbt-01")
                    .and()
                    //.webSSOProfileConsumer(ssoConsumer) 
                .identityProvider()
                .metadataFilePath(this.metadataUrl);
      //.metadataFilePath(this.metadataPath);
    }
}
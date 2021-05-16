package com.example.demo.security;

import com.example.demo.auth.ApplicationUserService;
import com.example.demo.jwt.JwtConfig;
//import com.example.demo.jwt.JwtTokenVerifier;
import com.example.demo.jwt.JwtUserNameAndPasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

import static com.example.demo.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final PasswordEncoder passwordEncoder;
	private final ApplicationUserService applicationUserService;
	private final JwtConfig jwtConfig;
	private final SecretKey secretKey;

	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService, JwtConfig jwtConfig, SecretKey secretKey) {
		this.passwordEncoder=passwordEncoder;
		this.applicationUserService = applicationUserService;
		this.jwtConfig = jwtConfig;
		this.secretKey = secretKey;
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				//tokenlere dair confiqurasiyalar
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				//.addFilter(new JwtUserNameAndPasswordAuthenticationFilter(authenticationManager(),jwtConfig,secretKey))
				//.addFilterAfter(new JwtTokenVerifier(jwtConfig, secretKey),JwtUserNameAndPasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers("/","index","/css/*","/js/*").permitAll()
				.antMatchers("/api/*").hasRole(STUDENT.name())
/*
				.antMatchers(HttpMethod.DELETE,"/management/api/*").hasAuthority(COURSE_WRITE.getPermisson())
				.antMatchers(HttpMethod.PUT,"/management/api/*").hasAuthority(COURSE_WRITE.getPermisson())
				.antMatchers(HttpMethod.POST,"/management/api/*").hasAuthority(COURSE_WRITE.getPermisson())
				.antMatchers(HttpMethod.GET,"/management/api/*").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())
*/
				.anyRequest()
				.authenticated();
/*
				.and()
				.formLogin()
					.loginPage("/login")
					.permitAll()
					.defaultSuccessUrl("/courses",true)
					.passwordParameter("password")
					.usernameParameter("username")
				.and()
				.rememberMe()
					.tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(21))
					.key("somethingkey")
					.rememberMeParameter("remember-me")
				.and()
				.logout()
					.logoutUrl("/logout")
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
					.clearAuthentication(true)
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID","remember-me")
					.logoutSuccessUrl("/login");
*/
	}
/*

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {

		UserDetails anna=User.builder()
				.username("anna")
				.password(passwordEncoder.encode("1234"))
	//			.roles(STUDENT.name())
				.authorities(STUDENT.getGrantedAuthorities())
				.build();

		UserDetails linda=User.builder()
				.username("linda")
				.password(passwordEncoder.encode("1234"))
	//			.roles(ADMIN.name())
				.authorities(ADMIN.getGrantedAuthorities())
				.build();

		UserDetails tom=User.builder()
				.username("tom")
				.password(passwordEncoder.encode("1234"))
	//			.roles(ADMINTRAINEE.name())
				.authorities(ADMINTRAINEE.getGrantedAuthorities())
				.build();


		return new InMemoryUserDetailsManager(
				anna,
				linda,
				tom
		);

	}

*/

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider(){
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(applicationUserService);
		return provider;
	}

}


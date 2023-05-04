package by.bsuir.ticketbooking.config;

import by.bsuir.ticketbooking.entity.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

	private final AuthenticationProvider authenticationProvider;
	private final LogoutHandler logoutHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf()
					.disable()
				.authenticationProvider(authenticationProvider)
				.authorizeHttpRequests()
				.requestMatchers("/img/**", "/css/**", "/js/**")
					.permitAll()
				.requestMatchers("/", "/matches", "/login", "/register")
					.permitAll()
				.requestMatchers("/matches/**").hasAuthority(Role.CUSTOMER.name())
				.requestMatchers("/admin/**").hasAuthority(Role.ADMIN.name())
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/do-login")
				.failureUrl("/login?error=true")
				.successForwardUrl("/")
				.and()
				.logout()
				.addLogoutHandler(logoutHandler)
				.logoutSuccessUrl("/login");
		return http.build();
	}
}

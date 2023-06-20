package coms.CustomLoginApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SpringSecurityConfig {

	   @Bean
	    public static PasswordEncoder passwordEncoder(){
	        return new BCryptPasswordEncoder();
	    }
	   
	  @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http.csrf().disable()
	                .authorizeHttpRequests((authorize) ->
	                        authorize.anyRequest().authenticated()
	                ).formLogin(
	                        form -> form
	                                .loginPage("/login")
	                                .loginProcessingUrl("/login")
	                                .defaultSuccessUrl("/welcome")
	                                .permitAll()
	                ).logout(
	                        logout -> logout
	                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                                .permitAll()
	                );
	        return http.build();
	    }

	    @Bean
	    public UserDetailsService userDetailsService(){

	        UserDetails user = User.builder()
	                .username("tushar")
	                .password(passwordEncoder().encode("Test123@"))
	                .roles("USER")
	                .build();

	        UserDetails admin = User.builder()
	                .username("admin")
	                .password(passwordEncoder().encode("Test123@"))
	                .roles("ADMIN")
	                .build();

	        return new InMemoryUserDetailsManager(user, admin);
	    }
}

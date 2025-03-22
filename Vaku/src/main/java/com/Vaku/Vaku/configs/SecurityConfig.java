package com.Vaku.Vaku.configs;


import com.Vaku.Vaku.apiRest.repository.PersonsRepository;
import com.Vaku.Vaku.exception.NotFoundException;
import com.Vaku.Vaku.security.filter.JwtAuthenticationFilter;
import com.Vaku.Vaku.utils.Constants;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final PersonsRepository personsRepository;
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		logger.info("Configuring SecurityFilterChain");
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.cors(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests((authorize) -> {
					authorize.requestMatchers("/auth/**").permitAll();
					authorize.anyRequest().authenticated();
				})
				.exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
						httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(
								(request, response, authException) -> {
									response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
								}
						)
				)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new CorsFilter(corsConfigurationSource()), ChannelProcessingFilter.class)
				.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailService() {
		return email -> {
			return personsRepository.findByPersEmail(email).orElseThrow(() ->
					new NotFoundException(Constants.CREDENTIAL_INVALID.getMessage()));
        };
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("http://localhost:4200");
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}

	@PostConstruct
	public void generatePassword() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "123456"; // Aquí colocas la contraseña
		String encodedPassword = encoder.encode(rawPassword);
		System.out.println("🔥 Contraseña encriptada: " + encodedPassword);
	}
}
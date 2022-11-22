package tracking.system.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import tracking.system.auth.service.impl.UserDetailsIServiceImpl;


@Configuration
@RequiredArgsConstructor
public class AuthenticationManagerConfig extends GlobalAuthenticationConfigurerAdapter {

    private final UserDetailsIServiceImpl userDetailsIService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsIService).passwordEncoder(passwordEncoder());

    }
}

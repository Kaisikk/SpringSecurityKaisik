package com.kaisikk.java.springsecurity.springsecurity.config.security;

import com.kaisikk.java.springsecurity.springsecurity.services.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Kaisikk
 *
 * Конфигурация доступов
 */

@Configuration
// аннотация указывает что весь класс будет использоваться для глобальной аннотации
@EnableWebSecurity
// включаем аннотацию PreAuthorize (чтобы можно было описывать правила доступа к над контроллером)
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Интерфейс который предоставляет сведения о пользователе в контексте безопасности.
     * Будет вызываться провайдером  чтобы получить юзера и проверить его аккаунт при авторизации.
     *
     * @return UserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService(){

        return new MyUserDetailsService();
    }

    /**
     * Фильтр безопаности
     *
     * @param http
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // отключение защиту от csrf (в случае подмены пользователя)
        return http.csrf(AbstractHttpConfigurer::disable)
                // указываем контрольные точки
                // permitAll - выдаем доступ всем
                .authorizeHttpRequests(auth -> auth.requestMatchers("api/v1/apps/welcome", "api/v1/apps/new-user").permitAll()
                // по этому адресу разрешаем доступ только авторизованным
                .requestMatchers("api/v1/apps/**").authenticated())
                // даем доступ к форме авторизации всем желающим
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .build();

    }

    /**
     * Шифровальщик паролей
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }

    /**
     * Провайдер для авторизации (используется для проверки учетки пользователя)
     *
     * @return AuthenticationProvider
     */
    @Bean
    public AuthenticationProvider authenticationProvider(){
        // провайдер авторизации
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // сетим userDetailsService
        provider.setUserDetailsService(userDetailsService());
        // сеттим шифровальщик паролей
        // сеттим шифровальщик паролей
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

}

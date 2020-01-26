package me.study.springsecurity.configuration;

import lombok.RequiredArgsConstructor;
import me.study.springsecurity.core.auth.AuthService;
import me.study.springsecurity.core.auth.CustomAuthFilter;
import me.study.springsecurity.core.auth.CustomLoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthService authService;
    private final CustomAuthFilter customAuthFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()

//                .antMatchers("/v1/**", "/").permitAll()
//                .antMatchers("/home").hasRole("USER")
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .anyRequest().authenticated()

                //    ExpressionUrlAuthorizationConfigurer.hasRole 에서 ROLE_ prefix 붙여줌
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/info").hasRole("MEMBER")
                .antMatchers("/**").permitAll()
                .and()

                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/login/result")
                .usernameParameter("memberId")
                .passwordParameter("memberPassword")
                .successHandler(successHandler())
                .and()

                .rememberMe()
                .rememberMeParameter("remember-me")
                .rememberMeCookieName("remember-me-cookie")
                .tokenValiditySeconds(24 * 60 * 60)
                .and()

                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/logout/result")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "success-handler-cookie")
                .and()

                .addFilterBefore(customAuthFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling().accessDeniedPage("/denied");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new CustomLoginSuccessHandler("/");
    }
}
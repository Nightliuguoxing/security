package com.example.security.security.config;

import com.example.security.security.service.CustomAuthenticationFailureHandler;
import com.example.security.security.service.CustomAuthenticationFilter;
import com.example.security.security.service.CustomAuthenticationSuccessHandler;
import com.example.security.security.service.CustomExpiredSessionStrategy;
import com.example.security.security.service.CustomLogoutSuccessHandler;
import com.example.security.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-03-08 20:57
 * @Description:
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Autowired
    private CustomLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // ????????????????????????url???????????????
                .antMatchers("/login/invalid").permitAll()
                .anyRequest().authenticated().and()

                // ???????????????
                .formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/login")
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .permitAll().and()
                .logout().logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID").and()
                .sessionManagement()
                .invalidSessionUrl("/login/invalid")
                .maximumSessions(1)
                // ?????????????????????????????????????????????????????????
                .maxSessionsPreventsLogin(false)
                // ??????????????????????????????????????????????????????
                .expiredSessionStrategy(new CustomExpiredSessionStrategy())
                .sessionRegistry(sessionRegistry());
        // ??????CSRF??????
        http.csrf().disable();
        http.addFilterAt(customAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        //filter.setAuthenticationSuccessHandler(new SuccessHandler());
        //filter.setAuthenticationFailureHandler(new FailureHandler());
        filter.setFilterProcessesUrl("/login/self");

        //????????????????????????WebSecurityConfigurerAdapter?????????AuthenticationManager????????????????????????AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // ?????????????????????????????????????????????????????????
        web.ignoring().antMatchers("/css/**", "/js/**");
    }




}

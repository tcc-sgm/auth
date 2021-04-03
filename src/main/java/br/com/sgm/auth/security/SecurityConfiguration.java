package br.com.sgm.auth.security;

import br.com.sgm.auth.security.filter.JwtTokenFilterConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //todo estudar mais sobre
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Disable Cross Site Request Forgery
        http.csrf().disable();

        //No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //Entry points
        http.authorizeRequests()
                .antMatchers("/users/signin").permitAll()
                .antMatchers("/users/signoff").permitAll()
                .antMatchers("/h2-console/**/**").permitAll()
                .anyRequest().authenticated();

        //If try to access any resource without having enough permissions
        http.exceptionHandling().accessDeniedPage("/login");

        //Apply JWT token
        http.apply(new JwtTokenFilterConfiguration(jwtTokenProvider));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //Allows access to swagger without authentication
        web.ignoring().antMatchers("/v2/api-docs")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/swagger-ui.html")
                .antMatchers("/configuration/**")
                .antMatchers("/webjars/**")
                .antMatchers("/pubic")
                .and()
                .ignoring()
                .antMatchers("/h2-console/**/**");
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}

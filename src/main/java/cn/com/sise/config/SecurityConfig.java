package cn.com.sise.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.annotation.Resource;
import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private DataSource datasource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
            String userSQL="select account,password,status from tb_user where account=?";
            String authoritySQL="select u.account,r.role from tb_user u,tb_role r where u.role_id=r.role_id AND u.account=?";
            auth.jdbcAuthentication().passwordEncoder(encoder)
                    .dataSource(datasource)
                    .usersByUsernameQuery(userSQL)
                    .authoritiesByUsernameQuery(authoritySQL);
        auth.userDetailsService(userService).passwordEncoder(encoder);
    }
}

/**
 * Copyright (c) 2017 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2017/6/20
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.minesitex.base.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsUtils;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//允许进入页面方法前检验
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    UserDetailsService userDetailsService;
    
    @Autowired
    JWTParam jwtParam;
    
    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                // 设置UserDetailsService
                .userDetailsService(this.userDetailsService)
                // 使用BCrypt进行密码的hash
                .passwordEncoder(passwordEncoder());
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        
        // 由于使用的是JWT，我们这里不需要csrf
        httpSecurity.csrf().disable();
        //http.csrf().ignoringAntMatchers("/api/**");
        
        // 禁用缓存
        httpSecurity.headers().cacheControl().disable();
        
        // 基于token，所以不需要session
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        httpSecurity.authorizeRequests()
                
                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                
                //对于跨域的 Preflight 不做拦截
                .requestMatchers(MyCorsUtils::isPreFlightRequest).permitAll()
                
                // 允许对于网站静态资源的无授权访问
                .antMatchers(
                        "/*",
                        "/**/pub/**",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();
    
        Filter loginFilter = new JWTLoginFilter(authenticationManager()).setJwtParam(jwtParam);
        Filter authenticationFilter = new JWTAuthenticationFilter(authenticationManager()).setJwtParam(jwtParam);
        
        //添加 登录过滤器 和 校验过滤器
        httpSecurity
                .addFilter(loginFilter)
                .addFilter(authenticationFilter);
        
        //httpSecurity.authorizeRequests()
        //        .antMatchers("/**/pub/**").permitAll()
        //        .antMatchers("/api/**").permitAll()
        //        .antMatchers("/*").permitAll()
        //        .antMatchers("/articles/**").permitAll()
        //        .antMatchers("/articles/admin/**").authenticated()
        //        .antMatchers("/pages/**").permitAll()
        //        .antMatchers("/pages/admin/**").authenticated()
        //        .anyRequest().authenticated()
        //        .and().formLogin().loginPage("/login").permitAll()
        //        .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
        //        .and().sessionManagement().maximumSessions(1).expiredUrl("/expired")
        //        .and()
        //        .and().exceptionHandling().accessDeniedPage("/accessDenied")
        //;
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/static/**")
                .antMatchers("/css/**")
                .antMatchers("/trd/**")
                .antMatchers("/js/**", "/main.js")
                .antMatchers("/img/**", "/logo.png", "/**/favicon.ico")
        ;
    }
    
}

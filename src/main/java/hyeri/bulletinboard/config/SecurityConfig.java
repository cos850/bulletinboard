package hyeri.bulletinboard.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/post/list").permitAll()// 모든 사용자 열람 가능
                .antMatchers("/post/index").permitAll()
                .antMatchers("/post/register").hasRole("USER")
                .antMatchers("/post/modify").hasRole("USER")
                .antMatchers("/post/member").hasRole("USER");

        http.formLogin(); // 인증 문제시 로그인창으로 이동
        http.logout(); // logout url 호출하면 알아서

    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication().withUser("user1")
//                .password("$2a$10$SvxRaie4NEWx8pn3qeRwpe6FSaqHLnX7TiiaMfpyWzpte0nYkG73S")
//                .roles("STUDENT");
//    }
}

package kr.board.config;
//환경설정파일 만들기(상속받아야 환경설정파일이 됨)

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity // 웹에서 시큐리티를 사용
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	//오버라이드를하면 시큐리티를 사용할수 있음
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 요청에 대한 보안 설정
		// 시큐리티가 적용된상태면 시큐리티에 한글인코딩처리를 해줘야 안깨짐
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter,CsrfFilter.class);
		
	}
	
}

package jp.sobue.spring.security.preauth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

/**
 * Web Security Configurations.
 *
 * @author ssobue
 */
@RequiredArgsConstructor
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  /**
   * ユーザ情報を取得するための実装.
   */
  private final MyUserDetailsService userDetailsService;

  /**
   * PreAuthenticated認証用のProviderのBean.
   */
  @Bean
  public PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider() {
    PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
    provider.setPreAuthenticatedUserDetailsService(userDetailsService);
    return provider;
  }

  /**
   * AuthenticationProviderの設定.
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(preAuthenticatedAuthenticationProvider());
  }

  /**
   * Ignoreの設定.
   */
  @Override
  public void configure(WebSecurity webSecurity) {
    webSecurity.ignoring().mvcMatchers("/");
  }

  /**
   * セキュリティ設定.
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.addFilter(preAuthenticatedProcessingFilter());
    http.authorizeRequests().anyRequest().fullyAuthenticated();
  }

  /**
   * PreAuthenticated認証用のFilter.
   */
  private AbstractPreAuthenticatedProcessingFilter preAuthenticatedProcessingFilter()
      throws Exception {
    MyPreAuthenticatedFilter filter = new MyPreAuthenticatedFilter();
    filter.setCheckForPrincipalChanges(true);
    filter.setAuthenticationManager(authenticationManager());

    return filter;
  }
}

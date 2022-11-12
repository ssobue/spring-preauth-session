package jp.sobue.spring.security.preauth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

/**
 * Web Security Configurations.
 *
 * @author ssobue
 */
@RequiredArgsConstructor
@Configuration
public class WebSecurityConfiguration {

  @Bean
  public SecurityFilterChain securityFilterChain(
      final HttpSecurity http,
      final MyUserDetailsService userDetailsService) throws Exception {
    return http
        .addFilter(preAuthenticatedProcessingFilter(userDetailsService))
        .authorizeHttpRequests()
        // Ignoreの設定.
        .requestMatchers("", "/").permitAll()
        // セキュリティ設定.
        .requestMatchers("/auth", "/logout").authenticated()
        .and().build();
  }

  /**
   * PreAuthenticated認証用のFilter.
   */
  private AbstractPreAuthenticatedProcessingFilter preAuthenticatedProcessingFilter(
      final MyUserDetailsService userDetailsService
  ) {
    // AuthenticationProviderの設定.
    var filter = new MyPreAuthenticatedFilter();
    var authProvider = preAuthenticatedAuthenticationProvider(userDetailsService);
    filter.setCheckForPrincipalChanges(true);
    filter.setAuthenticationManager(authProvider::authenticate);
    return filter;
  }

  /**
   * PreAuthenticated認証用のProviderのBean.
   *
   * @param userDetailsService ユーザ情報を取得するための実装.
   */
  @Bean
  public PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider(
      final MyUserDetailsService userDetailsService
  ) {
    var provider = new PreAuthenticatedAuthenticationProvider();
    provider.setPreAuthenticatedUserDetailsService(userDetailsService);
    return provider;
  }
}

package jp.sobue.spring.security.preauth;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * Web Configurations.
 *
 * @author ssobue
 */
@RequiredArgsConstructor
@Configuration
public class WebConfiguration {

  /**
   * リクエストログを出力するFilter.
   */
  @Bean
  public FilterRegistrationBean<CommonsRequestLoggingFilter> requestLoggingFilter() {
    FilterRegistrationBean<CommonsRequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();
    CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
    filter.setIncludeClientInfo(true);
    filter.setIncludeQueryString(true);
    filter.setIncludeHeaders(true);
    filter.setIncludePayload(true);
    filter.setMaxPayloadLength(1024);

    registrationBean.setFilter(filter);
    registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);

    return registrationBean;
  }
}

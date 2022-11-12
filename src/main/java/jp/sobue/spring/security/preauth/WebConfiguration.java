package jp.sobue.spring.security.preauth;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * Web Configurations.
 *
 * @author ssobue
 */
@Configuration
public class WebConfiguration {

  /**
   * リクエストログを出力するFilter.
   */
  @Bean
  public FilterRegistrationBean<CommonsRequestLoggingFilter> requestLoggingFilter() {
    var registrationBean = new FilterRegistrationBean<CommonsRequestLoggingFilter>();
    var filter = new CommonsRequestLoggingFilter();
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

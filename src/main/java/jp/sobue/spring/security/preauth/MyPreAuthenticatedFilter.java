package jp.sobue.spring.security.preauth;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

/**
 * PrincipalとCredentialを取得する.
 *
 * @author ssobue
 */
public class MyPreAuthenticatedFilter extends AbstractPreAuthenticatedProcessingFilter {

  /**
   * {@link #principalChanged}をSkipするURL
   */
  private static final List<String> IGNORE_LIST = Arrays.asList("js", "css", "ico");

  /**
   * Principalが変わったことにして、UserDetailServiceを強制コールさせる.
   */
  @Override
  protected boolean principalChanged(
      final HttpServletRequest request,
      final Authentication currentAuthentication) {
    if (IGNORE_LIST.parallelStream().anyMatch(suffix -> request.getRequestURI().endsWith(suffix))) {
      return super.principalChanged(request, currentAuthentication);
    }
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
    return "Principal";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
    return "Password";
  }
}

package jp.sobue.spring.security.preauth;

import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * ユーザ情報を取得するための実装.
 *
 * @author ssobue
 */
@Slf4j
@Service
public class MyUserDetailsService implements
    AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

  /**
   * ユーザ情報を取得. 検証用のため固定値.
   */
  @Override
  public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) {
    var userDetails = new User(
        token.getPrincipal().toString(), // Principal
        token.getCredentials().toString(), // Password
        Set.of() // Empty-Set
    );

    log.info("user detail service called.");

    return userDetails;
  }
}

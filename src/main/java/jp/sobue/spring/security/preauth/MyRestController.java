package jp.sobue.spring.security.preauth;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 検証用のRestController.
 *
 * @author ssobue
 */
@Slf4j
@RestController
public class MyRestController {

  @PreAuthorize("isAnonymous()")
  @GetMapping(path = {"", "/"}, produces = {MediaType.TEXT_PLAIN_VALUE})
  public ResponseEntity<String> root(HttpSession session) {
    log.info("root endpoint. session id = {}", session.getId());
    return ResponseEntity
        .ok(LocalDateTime.now() + " Root-Endpoint. Session ID: " + session.getId());
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping(path = "/auth", produces = {MediaType.TEXT_PLAIN_VALUE})
  public ResponseEntity<String> auth(HttpSession session, Authentication authentication) {
    log.info("auth endpoint. session id = {} principal = {}",
        session.getId(),
        authentication.getPrincipal());
    return ResponseEntity
        .ok(LocalDateTime.now() + " Auth-Endpoint. Session ID: " + session.getId());
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping(path = "/logout", produces = {MediaType.TEXT_PLAIN_VALUE})
  public ResponseEntity<String> logout(HttpSession session, Authentication authentication) {
    session.invalidate(); // セッションを破棄する
    log.info("logout endpoint. session id = {}  principal = {}",
        session.getId(),
        authentication.getPrincipal());
    return ResponseEntity
        .ok(LocalDateTime.now() + " Logout-Endpoint. Session ID: " + session.getId());
  }
}

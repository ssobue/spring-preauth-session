package jp.sobue.spring.security.preauth;

import java.time.LocalDateTime;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
  @GetMapping(produces = {MediaType.TEXT_PLAIN_VALUE})
  public ResponseEntity<String> root(HttpSession session) {
    log.info("root endpoint. session id = {}", session.getId());
    return ResponseEntity
        .ok(LocalDateTime.now().toString() + " Root-Endpoint. Session ID: " + session.getId());
  }

  @PreAuthorize("isFullyAuthenticated()")
  @GetMapping(path = "/auth", produces = {MediaType.TEXT_PLAIN_VALUE})
  public ResponseEntity<String> auth(HttpSession session) {
    log.info("auth endpoint. session id = {}", session.getId());
    return ResponseEntity
        .ok(LocalDateTime.now().toString() + " Auth-Endpoint. Session ID: " + session.getId());
  }

  @PreAuthorize("isFullyAuthenticated()")
  @GetMapping(path = "/logout", produces = {MediaType.TEXT_PLAIN_VALUE})
  public ResponseEntity<String> logout(HttpSession session) {
    session.invalidate(); // セッションを
    log.info("logout endpoint. session id = {}", session.getId());
    return ResponseEntity
        .ok(LocalDateTime.now().toString() + " Logout-Endpoint. Session ID: " + session.getId());
  }
}

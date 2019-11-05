package jp.sobue.spring.security.preauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

@SpringBootApplication
public class SpringPreAuthSessionApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringPreAuthSessionApplication.class, args);
  }
}

package jp.sobue.spring.security.preauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 起動クラス.
 *
 * @author ssobue
 */
@SpringBootApplication
public class SpringPreAuthSessionApplication {

  /**
   * 起動メソッド.
   */
  public static void main(String[] args) {
    SpringApplication.run(SpringPreAuthSessionApplication.class, args);
  }
}

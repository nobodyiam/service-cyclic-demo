package service.cyclic.demo.service.a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import service.cyclic.demo.service.common.HttpUtil;

@SpringBootApplication(scanBasePackageClasses = {ApplicationA.class, HttpUtil.class})
public class ApplicationA {

  public static void main(String[] args) {
    System.setProperty("server.port", "9090");

    SpringApplication.run(ApplicationA.class);
  }
}

package service.cyclic.demo.service.b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import service.cyclic.demo.service.common.HttpUtil;

@SpringBootApplication(scanBasePackageClasses = {ApplicationB.class, HttpUtil.class})
public class ApplicationB {

  public static void main(String[] args) {
    System.setProperty("server.port", "9091");

    SpringApplication.run(ApplicationB.class);
  }
}

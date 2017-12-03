package service.cyclic.demo.service.c;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import service.cyclic.demo.service.common.HttpUtil;

@SpringBootApplication(scanBasePackageClasses = {ApplicationC.class, HttpUtil.class})
public class ApplicationC {

  public static void main(String[] args) {
    System.setProperty("server.port", "9092");

    SpringApplication.run(ApplicationC.class);
  }
}

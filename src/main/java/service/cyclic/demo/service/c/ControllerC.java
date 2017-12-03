package service.cyclic.demo.service.c;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.cyclic.demo.service.common.HttpUtil;

@RestController
public class ControllerC {

  @Autowired
  private HttpUtil httpUtil;

  @Value("${test.c1.latency}")
  private long c1LatencyInMilli;

  @Value("${test.c2.latency}")
  private long c2LatencyInMilli;

  @RequestMapping(method = RequestMethod.GET, value = "/c1")
  public String a1() {
    try {
      TimeUnit.MILLISECONDS.sleep(c1LatencyInMilli);
    } catch (InterruptedException e) {
      //ignore
    }

    return "result from c1";
  }

  @RequestMapping(method = RequestMethod.GET, value = "/c2")
  public String a2() {
    try {
      TimeUnit.MILLISECONDS.sleep(c2LatencyInMilli);
    } catch (InterruptedException e) {
      //ignore
    }

    return "result from c2";
  }

}

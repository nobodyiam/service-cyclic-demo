package service.cyclic.demo.service.a;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.cyclic.demo.service.common.HttpUtil;

@RestController
public class ControllerA {

  @Autowired
  private HttpUtil httpUtil;

  @Value("${test.a1.latency}")
  private long a1LatencyInMilli;

  @Value("${test.a2.latency}")
  private long a2LatencyInMilli;

  @Value("${test.enable-cyclic}")
  private boolean enableCyclic;

  @RequestMapping(method = RequestMethod.GET, value = "/a1")
  public String a1() {
    try {
      TimeUnit.MILLISECONDS.sleep(a1LatencyInMilli);
    } catch (InterruptedException e) {
      //ignore
    }

    if (enableCyclic) {
      return httpUtil.get("http://localhost:9091/b1");
    } else {
      return httpUtil.get("http://localhost:9092/c1");
    }
  }

  @RequestMapping(method = RequestMethod.GET, value = "/a2")
  public String a2() {
    try {
      TimeUnit.MILLISECONDS.sleep(a2LatencyInMilli);
    } catch (InterruptedException e) {
      //ignore
    }

    return "result from a2";
  }

}

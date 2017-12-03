package service.cyclic.demo.service.b;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.cyclic.demo.service.common.HttpUtil;

@RestController
public class ControllerB {

  @Autowired
  private HttpUtil httpUtil;

  @Value("${test.b1.latency}")
  private long b1LatencyInMilli;

  @Value("${test.b2.latency}")
  private long b2LatencyInMilli;

  @Value("${test.enable-cyclic}")
  private boolean enableCyclic;

  @RequestMapping(method = RequestMethod.GET, value = "/b1")
  public String a1() {
    try {
      TimeUnit.MILLISECONDS.sleep(b1LatencyInMilli);
    } catch (InterruptedException e) {
      //ignore
    }

    return "result from b1";
  }

  @RequestMapping(method = RequestMethod.GET, value = "/b2")
  public String a2() {
    try {
      TimeUnit.MILLISECONDS.sleep(b2LatencyInMilli);
    } catch (InterruptedException e) {
      //ignore
    }

    if (enableCyclic) {
      return httpUtil.get("http://localhost:9090/a2");
    } else {
      return httpUtil.get("http://localhost:9092/c2");
    }
  }

}

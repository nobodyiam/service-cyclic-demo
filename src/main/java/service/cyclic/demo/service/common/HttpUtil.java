package service.cyclic.demo.service.common;

import com.google.common.io.CharStreams;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HttpUtil {
  @Value("${test.connect-timeout}")
  private int connectTimeout;
  @Value("${test.read-timeout}")
  private int readTimeout;

  public String get(String url) {
    try {
      HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

      connection.setRequestMethod("GET");
      connection.setConnectTimeout(connectTimeout);
      connection.setReadTimeout(readTimeout);

      connection.connect();

      try (InputStreamReader isr = new InputStreamReader(connection.getInputStream(),
          StandardCharsets.UTF_8)) {
        return CharStreams.toString(isr);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

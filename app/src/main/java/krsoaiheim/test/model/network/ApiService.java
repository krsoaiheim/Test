package krsoaiheim.test.model.network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiService {
  public String auth(String login, String password) throws Exception {
    URL url = new URL(
          "http://condor.alarstudios.com/test/auth.cgi?username="+login+"&password="+password);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.connect();
    try {
      InputStream stream = conn.getInputStream();
      BufferedReader reader;
      StringBuilder response = new StringBuilder();
      reader = new BufferedReader(new InputStreamReader(stream));
      String line;
      while ((line = reader.readLine()) != null) {
        response.append(line);
      }
      return response.toString();
    } finally {
      conn.disconnect();
    }
  }


  public String getList(String code, int page) throws Exception {
    URL url = new URL("http://condor.alarstudios.com/test/data"+".cgi?code="+code+"&p="+page);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.connect();
    try {
      InputStream stream = conn.getInputStream();
      BufferedReader reader;
      StringBuilder response = new StringBuilder();
      reader = new BufferedReader(new InputStreamReader(stream));
      String line;
      while ((line = reader.readLine()) != null) {
        response.append(line);
      }
      return response.toString();
    } finally {
      conn.disconnect();
    }
  }
}

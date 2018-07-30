package krsoaiheim.test.model.network;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiService {
  public String auth(String login, String password) throws Exception {
    URL url = new URL("http://condor.alarstudios.com/test/auth"+""+""+""+""+""+""+""+
          ".cgi?username="+login+"&password="+password);
    Log.i("api", "auth: "+url.toString());
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.connect();
    int code = conn.getResponseCode();
    try {
      InputStream stream = conn.getInputStream();
      BufferedReader reader;
      StringBuffer response = new StringBuffer();
      reader = new BufferedReader(new InputStreamReader(stream));
      String line = "";
      while ((line = reader.readLine()) != null) {
        response.append(line);
      }
      return response.toString();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      conn.disconnect();
    }
    return "-1";
  }


  public String getList(String code, int page) throws Exception {
    URL url = new URL("http://condor.alarstudios.com/test/data"+".cgi?code="+code+"&p="+page);
    Log.i("api", "auth: "+url.toString());
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.connect();
    try {
      InputStream stream = conn.getInputStream();
      BufferedReader reader;
      StringBuffer response = new StringBuffer();
      reader = new BufferedReader(new InputStreamReader(stream));
      String line;
      while ((line = reader.readLine()) != null) {
        response.append(line);
      }
      return response.toString();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      conn.disconnect();
    }
    return "-1";
  }
}

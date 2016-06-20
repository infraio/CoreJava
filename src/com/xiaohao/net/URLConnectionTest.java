package com.xiaohao.net;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class URLConnectionTest {

  public static void main(String[] args) {
    try {
      String urlName = "http://www.mi.com";
      URL url = new URL(urlName);
      URLConnection connection = url.openConnection();
      connection.connect();
      
      Map<String, List<String>> headers = connection.getHeaderFields();
      for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
        String key = entry.getKey();
        for (String value : entry.getValue()) {
          System.out.println(key + " : " + value);
        }
      }
      
      Scanner in = new Scanner(connection.getInputStream());
      for (int i = 1; in.hasNextLine() && i <= 10; i++) {
        System.out.println(in.nextLine());
      }
      if (in.hasNextLine()) {
        System.out.println("......");
      }
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }
}

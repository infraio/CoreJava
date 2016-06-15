package com.xiaohao.net;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class SocketTest {

  public static void main(String[] args) throws Exception {
    String url;
    int port;
    if (args.length >= 2) {
      url = args[0];
      port = Integer.valueOf(args[1]);
    } else {
      url = "time.nist.gov";
      port = 13;
    }
    
    try (Socket s = new Socket()) {
      s.connect(new InetSocketAddress(url, port), 5000);
      InputStream input = s.getInputStream();
      Scanner in = new Scanner(input);
 
      System.out.println("Contect to " + url + ":" + port);
      while (in.hasNext()) {
        System.out.println(in.nextLine());
      }
    }
  }

}

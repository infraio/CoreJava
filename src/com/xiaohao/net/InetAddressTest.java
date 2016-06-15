package com.xiaohao.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

  public static void main(String[] args) throws Exception {
    if (args.length > 0) {
      String host = args[0];
      printAllAddressesByName(host);
    } else {
      InetAddress localHostAddress = InetAddress.getLocalHost();
      System.out.println("localhost : " + localHostAddress);
      InetAddress loopbackAddress = InetAddress.getLoopbackAddress();
      System.out.println("loopback : " + loopbackAddress);
      System.out.println();
      printAllAddressesByName("www.baidu.com");
      printAllAddressesByName("www.mi.com");
      printAllAddressesByName("www.taobao.com");
      printAllAddressesByName("www.jd.com");
    }
  }
  
  private static void printAllAddressesByName(String host) throws Exception {
    System.out.println("host : " + host);
    InetAddress[] addresses = InetAddress.getAllByName(host);
    for (InetAddress address : addresses) {
      System.out.println(address.getHostAddress());
    }
    System.out.println();
  }
}

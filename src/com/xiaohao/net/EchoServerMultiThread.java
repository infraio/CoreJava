package com.xiaohao.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServerMultiThread {

  public static void main(String[] args) throws IOException {
    try (ServerSocket s = new ServerSocket(8189)) {
      int i = 1;
      while (true) {
        Socket incoming = s.accept();
        EchoHandler handler = new EchoHandler(incoming);
        Thread t = new Thread(handler);
        t.start();
        System.out.println("Client " + i);
        i++;
      }
    }
  }

}

class EchoHandler implements Runnable {

  private Socket incoming;

  public EchoHandler(Socket incoming) {
    this.incoming = incoming;
  }
  
  @Override
  public void run() {
    try {
      InputStream input = incoming.getInputStream();
      OutputStream output = incoming.getOutputStream();

      try (Scanner in = new Scanner(input)) {
        PrintWriter out = new PrintWriter(output, true);
        out.println("Hello! Enter Bye to exit.");

        boolean done = false;
        while (!done && in.hasNext()) {
          String line = in.nextLine();
          out.println("Echo: " + line);
          if (line.trim().toLowerCase().equals("bye")) {
            done = true;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        incoming.close();
      } catch (Exception e){
        e.printStackTrace();
      }
    }
  }
}
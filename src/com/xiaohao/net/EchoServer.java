package com.xiaohao.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {

  public static void main(String[] args) throws Exception {
    
    try (ServerSocket s = new ServerSocket(8189)) {
      try (Socket incoming = s.accept()) {
        InputStream input = incoming.getInputStream();
        OutputStream output = incoming.getOutputStream();
        
        try (Scanner in = new Scanner(input)) {
          PrintWriter out = new PrintWriter(output, true);
          out.println("Hello! Entry Bye to exit.");
          
          boolean done = false;
          while (!done && in.hasNext()) {
            String line = in.nextLine();
            out.println("Echo: " + line);
            if (line.trim().toLowerCase().equals("bye")) {
              done = true;
            }
          }
        }
      }
    }
  }

}

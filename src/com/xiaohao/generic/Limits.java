package com.xiaohao.generic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Limits {

  public static void main(String[] args) {
    
    // don't need to catch FileNotFoundException
    new Block() {
      public void body() throws Exception {
        Scanner in = new Scanner(new File("fileNotFound"));
        while (in.hasNext()) {
          System.out.println(in.nextLine());
        }
      }
    }.toThread().start();

    try {
      Scanner in = new Scanner(new File("fileNotFound"));
      while (in.hasNext()) {
        System.out.println(in.nextLine());
      }
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}

abstract class Block {
  public abstract void body() throws Exception;
  
  public Thread toThread() {
    return new Thread() {
      @Override
      public void run() {
        try {
          body();
        } catch (Throwable t) {
          Block.<RuntimeException>throwAs(t);
        }
      }
    };
  }
  
  public static <T extends Throwable> void throwAs(Throwable e) throws T {
    throw (T) e;
  }
}

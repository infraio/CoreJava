package com.xiaohao.io;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

  public static void main(String[] args) {
    
    Scanner in = new Scanner(System.in);
    System.out.println("Enter pattern:");
    String patterString = in.nextLine();
    Pattern pattern = Pattern.compile(patterString);
    
    while (true) {
      System.out.println("Enter string to match:");
      String str = in.nextLine();
      if (str == null || str.length() == 0) {
        break;
      }
      Matcher matcher = pattern.matcher(str);
      if (matcher.matches()) {
        System.out.println("Match!");
        int g = matcher.groupCount();
        if (g > 0) {
          for (int i = 0; i < str.length(); i++) {
            for (int j = 1; j <= g; j++) {
              if (i == matcher.start(j) && i == matcher.end(j)) {
                System.out.print("()");
              } 
            }
            for (int j = 1; j <= g; j++) {
              if (i == matcher.start(j) && i != matcher.end(j)) {
                System.out.print("(");
              }
            }
            System.out.print(str.charAt(i));
            for (int j = 1; j <= g; j++) {
              if ((i + 1) != matcher.start(j) && (i + 1) == matcher.end(j)) {
                System.out.print(")");
              }
            }
          }
          System.out.println();
        }
      } else {
        System.out.println("Not match!");
      }
    }
  }

}

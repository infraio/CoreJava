package com.xiaohao.inheritance;

import java.util.Scanner;

public class EnumTest {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.println("Enter a size:");
    String input = in.next().toUpperCase();
    Size size = Size.valueOf(input);
    System.out.println("size = " + size);
    System.out.println("abbrevitation = " + size.getAbbreviation());
    System.out.println("ordinal = " + size.ordinal());
    if (size == Size.EXTRA_LARGE) {
      System.out.println("XL");
    }
    
    System.out.println("Enter a easy size:");
    input = in.next().toUpperCase();
    EasySize esize = EasySize.valueOf(input);
    System.out.println("size = " + esize);
    System.out.println("ordinal = " + esize.ordinal());
  }

}

enum Size {
  SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");
  
  private String abbreviation;
  
  private Size(String abbreviation) {
    this.abbreviation = abbreviation;
  }
  
  public String getAbbreviation() {
    return this.abbreviation;
  }
}

enum EasySize {
  SMALL, MEDIUM, LARGE, EXTRA_LARGE;
}

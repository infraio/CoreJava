package com.xiaohao.inheritance;

public class EqualsTest {

  public static void main(String[] args) {
    EqualEmployee alice1 = new EqualEmployee("Alice", 10000, 1990, 1, 1);
    EqualEmployee alice2 = alice1;
    EqualEmployee alice3 = new EqualEmployee("Alice", 10000, 1990, 1, 1);

    System.out.println("alice1 == alice2 ? " + (alice1 == alice2));
    System.out.println("alice1 == alice3 ? " + (alice1 == alice3));
    System.out.println("alice1.equals(alice2) ? " + (alice1.equals(alice2)));
    System.out.println("alice1.equals(alice3) ? " + (alice1.equals(alice3)));

    EqualEmployee bob = new EqualEmployee("Bob", 10000, 1980, 1, 1);
    System.out.println("alice1.euqals(bob) ? " + (alice1.equals(bob)));

    EqualManager carl1 = new EqualManager("Carl", 20000, 1970, 1, 1);
    EqualManager carl2 = new EqualManager("Carl", 20000, 1970, 1, 1);
    carl2.setBonus(10000);
    System.out.println("carl1.equals(carl2) ? " + (carl1.equals(carl2)));

    System.out.println("alice1.hashCode() = " + alice1.hashCode());
    System.out.println("alice2.hashCode() = " + alice2.hashCode());
    System.out.println("alice3.hashCode() = " + alice3.hashCode());
    System.out.println("bob.hashCode() = " + bob.hashCode());
    System.out.println("carl1.hashCode() = " + carl1.hashCode());
    System.out.println("carl2.hashCode() = " + carl2.hashCode());

    System.out.println("carl1 instanceof EqualManager ? " + (carl1 instanceof EqualEmployee));
    System.out.println("carl1 instanceof EqualEmployee ? " + (carl1 instanceof EqualEmployee));
    System.out.println("carl1 instanceof Object ? " + (carl1 instanceof EqualEmployee));
  }

}

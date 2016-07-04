package com.xiaohao.interfaces;

import java.util.Arrays;
import java.util.Date;

public class AnonymousInnerClassTest1 {

  public static void main(String[] args) {

    // Java 6,7 need counter be final variable, because it is used in Inner Class.
    // Java 8 don't need final. But it can't update, too.
    int[] counter = new int[1];
    int counterWrong = 0;
    Date[] dates = new Date[100];

    for (int i = 0; i < 100; i++) {
      dates[i] = new Date() {
        public int compareTo(Date other) {
          counter[0]++;
          // counterWrong++; // Local variable counterWrong defined in an enclosing scope must be
          // final or effectively final
          return super.compareTo(other);
        }
      };
    }

    Arrays.sort(dates);
    System.out.println(counter[0] + " comparisons");
  }

}

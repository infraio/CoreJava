package com.xiaohao.generic;

public class PairTest2 {

  public static void main(String[] args) {
    String[] words = {"hello", "word", "generic", "test", "program"};
    Pair<String> mm = ArrayAlg2.minmax(words);
    if (mm != null) {
      System.out.println("min = " + mm.getFirst());
      System.out.println("max = " + mm.getSecond());
    }
  }

}

class ArrayAlg2 {
  public static <T extends Comparable> Pair<T> minmax(T[] a) {
    if (a == null || a.length == 0) {
      return null;
    }
    T min = a[0];
    T max = a[0];
    
    for (int i = 1; i < a.length; i++) {
      if (a[i].compareTo(min) < 0) {
        min = a[i];
      }
      if (a[i].compareTo(max) > 0) {
        max = a[i];
      }
    }
    
    return new Pair<>(min, max);
  }
}
package com.xiaohao.interfaces;

public class StaticInnerClassTest {

  public static void main(String[] args) {
    double[] values = new double[100];
    for (int i = 0; i < values.length; i++) {
      values[i] = 100 * Math.random();
    }
    
    ArrayAlg.Pair pair = ArrayAlg.minmax(values);
    System.out.println("min = " + pair.getFirst());
    System.out.println("max = " + pair.getSecond());
  }

}

class ArrayAlg {
    
  public static class Pair {
    private double first;
    private double second;
    
    public Pair(double first, double second) {
      this.first = first;
      this.second = second;
    }
    
    public double getFirst() {
      return this.first;
    }
    
    public double getSecond() {
      return this.second;
    }
  }
  
  public static Pair minmax(double[] values) {
    double min = Double.MAX_VALUE;
    double max = Double.MIN_VALUE;
    for (double d : values) {
      if (d < min) {
        min = d;
      }
      if (d > max) {
        max = d;
      }
    }
    return new Pair(min, max);
  }
}

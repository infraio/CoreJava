package com.xiaohao.generic;

import com.xiaohao.io.Employee;
import com.xiaohao.io.Manager;

public class PairTest3 {

  public static void main(String[] args) {
    Manager ceo = new Manager("wang", 800000, 2003, 12, 1);
    Manager cfo = new Manager("li", 600000, 2005, 11, 1);
    Pair<Manager> buddies = new Pair<>(ceo, cfo);
    
    Manager[] managers = {ceo, cfo};
    Pair<Manager> result = new Pair<>();
    
    minmaxSalary(managers, result);
    System.out.println("first: " + result.getFirst().getName()
      + ", second: " + result.getSecond().getName());
    maxminSalary(managers, result);
    System.out.println("first: " + result.getFirst().getName()
      + ", second: " + result.getSecond().getName());
  }

  public static void printBuddies(Pair<? extends Employee> p) {
    Employee first = p.getFirst();
    Employee second = p.getSecond();
    System.out.println(first.getName() + " and " + second.getName());
  }
  
  public static void minmaxSalary(Manager[] a, Pair<? super Manager> result) {
    if (a == null || a.length == 0) {
      return;
    }
    Manager min = a[0];
    Manager max = a[0];
    for (int i = 1; i < a.length; i++) {
      if (a[i].getSalary() < min.getSalary()) {
        min = a[i];
      }
      if (a[i].getSalary() > max.getSalary()) {
        max = a[i];
      }
    }
    result.setFirst(min);
    result.setSecond(max);
  }
  
  public static void maxminSalary(Manager[] a, Pair<? super Manager> result) {
    minmaxSalary(a, result);
    PairAlg.swapHelper(result);
  }
}

class PairAlg {
  public static boolean hasNulls(Pair<?> p) {
    return p.getFirst() == null || p.getSecond() == null;
  }
  
  public static void swap(Pair<?> p) {
    swapHelper(p);
  }
  
  public static <T> void swapHelper(Pair<T> p) {
    T t = p.getFirst();
    p.setFirst(p.getSecond());
    p.setSecond(t);
  }
}
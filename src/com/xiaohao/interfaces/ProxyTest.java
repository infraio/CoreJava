package com.xiaohao.interfaces;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

public class ProxyTest {

  public static final int SIZE = 1000;
  
  public static void main(String[] args) {
    Object[] elements = new Object[SIZE];
    
    for (int i = 0; i < SIZE; i++) {
      Integer value = i;
      InvocationHandler handler = new TraceHandler(value);
      // proxy will proxy 4 method: compareTo, toString, equals, hashcode
      Object proxy = Proxy.newProxyInstance(null, new Class[] {Comparable.class}, handler);
      elements[i] = proxy;
    }
    
    Integer key = new Random().nextInt(elements.length);
    int result = Arrays.binarySearch(elements, key);
    
    System.out.println("key is " + key);
    if (result >= 0) {
      System.out.println("result is " + elements[result]);
      String equals = "false";
      if (elements[result].equals(key)) {
        equals = "true";
      }
      System.out.println("equals ? " + equals);
    }
  }

}

/**
 * An invocation handler that prints out the method name and parameters, 
 * then invokes the original method
 */
class TraceHandler implements InvocationHandler {
  private Object target;

  public TraceHandler(Object t) {
    target = t;
  }

  public Object invoke(Object proxy, Method m, Object[] args)
      throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    // print implicit argument
    System.out.print(target);
    // print method name
    System.out.print("." + m.getName() + "(");

    // print explicit arguments
    if (args != null) {
      for (int i = 0; i < args.length; i++) {
        System.out.print(args[i]);
        if (i < args.length - 1) {
          System.out.print(", ");
        }
      }
    }
    System.out.println(")");

    // invoke actual method
    return m.invoke(target, args);
  }
}

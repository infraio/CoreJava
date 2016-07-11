package com.xiaohao.inheritance;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class ReflectionTest {

  public static void main(String[] args) {

    String name;
    if (args.length > 0) {
      name = args[0];
    } else {
      Scanner in = new Scanner(System.in);
      System.out.println("Enter class name (e.g. java.util.Date): ");
      name = in.next();
      in.close();
    }

    try {
      Class cl = Class.forName(name);
      Class supercl = cl.getSuperclass();
      String modifiers = Modifier.toString(cl.getModifiers());

      if (modifiers.length() > 0) {
        System.out.print(modifiers + " ");
      }
      System.out.print("class " + name);
      if (supercl != null && supercl != Object.class) {
        System.out.print(" extends " + supercl.getName());
      }
      System.out.println(" {");

      printFields(cl);
      System.out.println();
      printConstructors(cl);
      System.out.println();
      printMethods(cl);
      
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    System.exit(0);
  }

  public static void printConstructors(Class cl) {
    Constructor[] constructors = cl.getDeclaredConstructors();
    for (Constructor constructor : constructors) {
      String name = constructor.getName();
      String modifiers = Modifier.toString(constructor.getModifiers());
      System.out.print("  ");
      if (modifiers.length() > 0) {
        System.out.print(modifiers + " ");
      }
      System.out.print(" " + name + "(");

      Class[] paramTypes = constructor.getParameterTypes();
      for (int i = 0; i < paramTypes.length; i++) {
        if (i != 0) {
          System.out.print(", ");
        }
        System.out.print(paramTypes[i].getName());
      }
      System.out.println(");");
    }
  }
  
  public static void printMethods(Class cl) {
    Method[] methods = cl.getMethods();
    for (Method method : methods) {
      Class returnType = method.getReturnType();
      String name = method.getName();
      String modifiers = Modifier.toString(method.getModifiers());
      System.out.print("  ");
      if (modifiers.length() > 0) {
        System.out.print(modifiers + " ");
      }
      System.out.print(returnType.getName() + " " + name + "(");
      
      Class[] paramTypes = method.getParameterTypes();
      for (int i = 0; i < paramTypes.length; i++) {
        if (i != 0) {
          System.out.print(", ");
        }
        System.out.print(paramTypes[i].getName());
      }
      System.out.println(");");
    }
  }
  
  public static void printFields(Class cl) {
    Field[] fields = cl.getDeclaredFields();
    for (Field field : fields) {
      Class type = field.getType();
      String name = field.getName();
      String modifiers = Modifier.toString(field.getModifiers());
      System.out.print("  ");
      if (modifiers.length() > 0) {
        System.out.print(modifiers + " ");
      }
      System.out.println(type.getName() + " " + name + ";");
    }
  }

}

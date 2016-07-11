package com.xiaohao.inheritance;

import java.util.ArrayList;
import java.util.List;

import com.xiaohao.io.Employee;

public class ArrayListTest {

  public static void main(String[] args) {
    List<Employee> staffs = new ArrayList<>();
    staffs.add(new Employee("wang", 10000, 1990, 1, 1));
    staffs.add(new Employee("li", 11000, 1990, 1, 1));
    staffs.add(new Employee("zhao", 12000, 1990, 1, 1));
    
    for (Employee e : staffs) {
      System.out.println(e);
    }
  }

}

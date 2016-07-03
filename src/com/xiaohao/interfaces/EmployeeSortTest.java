package com.xiaohao.interfaces;

import java.util.Arrays;

import com.xiaohao.io.Employee;

public class EmployeeSortTest {

  public static void main(String[] args) {
    ComparableEmployee[] staffs = new ComparableEmployee[4];
    
    staffs[0] = new ComparableEmployee("zhang", 13000);
    staffs[1] = new ComparableEmployee("wang", 11000);
    staffs[2] = new ComparableEmployee("li", 12000);
    staffs[3] = new ComparableEmployee("zhao", 14000);
    
    Arrays.sort(staffs);
    
    for (ComparableEmployee e : staffs) {
      System.out.println(e);
    }
  }

}

class ComparableEmployee extends Employee implements Comparable<Employee> {
  
  public ComparableEmployee(String name, double salary) {
    super(name, salary, 0, 0, 0);
  }
  
  @Override
  public int compareTo(Employee other) {
    return Double.compare(salary, other.getSalary());
  }
}

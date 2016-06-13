package com.xiaohao.io;

import java.util.Date;
import java.util.GregorianCalendar;

public class Employee {
  private String name;
  private double salary;
  private Date hireDay;

  public static final int NAME_SIZE = 10;
  public static final int RECORD_SIZE = NAME_SIZE * Character.BYTES + Double.BYTES + 3 * Integer.BYTES;

  public Employee(String name, double salary, int year, int month, int day) {
    this.name = name;
    this.salary = salary;
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.set(year, month, day);
    hireDay = calendar.getTime();
  }

  public String getName() {
    return name;
  }

  public double getSalary() {
    return salary;
  }

  public Date getHireDay() {
    return hireDay;
  }

  @Override
  public String toString() {
    return "Employee: " + name + ", " + salary + ", " +  hireDay;
  }
}

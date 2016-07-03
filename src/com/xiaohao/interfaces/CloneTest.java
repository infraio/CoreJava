package com.xiaohao.interfaces;

import java.util.Date;
import java.util.GregorianCalendar;

import com.xiaohao.io.Employee;

public class CloneTest {

  public static void main(String[] args) {
    try {
      CloneableEmployee original = new CloneableEmployee("wang", 10000);
      original.setHireDay(2010, 1, 1);
      CloneableEmployee copy = original.clone();
      copy.setHireDay(2012, 1, 1);
      System.out.println("original is " + original);
      System.out.println("copy is " + copy);
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
  }

}

class CloneableEmployee extends Employee implements Cloneable {

  public CloneableEmployee(String name, double salary) {
    super(name, salary, 0, 0, 0);
  }

  @Override
  public CloneableEmployee clone() throws CloneNotSupportedException {
    CloneableEmployee cloned = (CloneableEmployee) super.clone();

    cloned.hireDay = (Date) hireDay.clone();

    return cloned;
  }

  public void setHireDay(int year, int month, int day) {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.set(year, month, day);
    this.hireDay = calendar.getTime();
  }
}

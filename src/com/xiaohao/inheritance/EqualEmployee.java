package com.xiaohao.inheritance;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public class EqualEmployee {
  private String name;
  private double salary;
  private Date hireDay;

  public EqualEmployee(String name, double salary, int year, int month, int day) {
    this.name = name;
    this.salary = salary;
    GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
    hireDay = calendar.getTime();
  }

  public String getName() {
    return this.name;
  }

  public double getSalary() {
    return this.salary;
  }

  public Date getHireDay() {
    return this.hireDay;
  }

  @Override
  public boolean equals(Object otherObject) {
    if (this == otherObject) {
      return true;
    }

    if (otherObject == null) {
      return false;
    }
    if (getClass() != otherObject.getClass()) {
      return false;
    }

    EqualEmployee other = (EqualEmployee) otherObject;

    return name.equals(other.getName()) && salary == other.getSalary()
        && Objects.equals(hireDay, other.getHireDay());
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, salary, hireDay);
  }

  @Override
  public String toString() {
    return getClass().getName() + ", name=" + name + ", salary=" + salary + ", hireDay=" + hireDay;
  }

}

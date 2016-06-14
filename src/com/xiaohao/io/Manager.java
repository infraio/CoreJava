package com.xiaohao.io;

public class Manager extends Employee {

  private Employee secretary = null;

  public Manager(String name, double salary, int year, int month, int day) {
    super(name, salary, year, month, day);
  }

  public void setSecretary(Employee e) {
    this.secretary = e;
  }

  public void raiseSecretarySalary(double d) {
    this.secretary.raiseSalary(d);
  }

  @Override
  public String toString() {
    return "Manager: " + name + ", " + salary + ", " + hireDay + ", Secretary: "
        + secretary.getName() + ", " + secretary.getSalary();
  }
}

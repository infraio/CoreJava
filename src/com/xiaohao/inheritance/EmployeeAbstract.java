package com.xiaohao.inheritance;

public class EmployeeAbstract extends Person {

  private double salary;
  
  public EmployeeAbstract(String name, double salary) {
    super(name);
    this.salary = salary;
  }
  
  public double getSalary() {
    return this.salary;
  }
  
  @Override
  public String getDescription() {
    return String.format("an employee with a salary of %.2f", salary);
  }
}

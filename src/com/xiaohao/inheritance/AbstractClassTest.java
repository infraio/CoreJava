package com.xiaohao.inheritance;

public class AbstractClassTest {

  public static void main(String[] args) {
    Person[] persons = new Person[2];
    
    persons[0] = new EmployeeAbstract("wang", 10000.0);
    persons[1] = new Student("zhao", "computer science");
    
    for (Person person : persons) {
      System.out.println(person.getName() + ", " + person.getDescription());
    }
  }

}

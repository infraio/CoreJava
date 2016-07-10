package com.xiaohao.inheritance;

public abstract class Person {

  private String name;

  public Person(String name) {
    this.name = name;
  }
  
  public String getName() {
    return this.name;
  }
  
  public abstract String getDescription();
}

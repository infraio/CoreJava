package com.xiaohao.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerialClone {

  public static void main(String[] args) {
    Student s1 = new Student("zhang", 18);
    Student s2 = (Student) s1.clone();

    s1.setAge(20);

    System.out.println(s1);
    System.out.println(s2);
  }

}

class SerialCloneable implements Cloneable, Serializable {
  
  public Object clone() {
    try {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ObjectOutputStream out = new ObjectOutputStream(baos);
      out.writeObject(this);
      out.close();
      
      ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
      ObjectInputStream in = new ObjectInputStream(bais);
      Object object = in.readObject();
      in.close();

      return object;
    } catch (Exception e) {
      return null;
    }
  }
}

class Student extends SerialCloneable {
  private String name;
  private int age;
  
  public Student(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
  
  @Override
  public String toString() {
    return "Student: name=" + name + ", age=" + age;
  }
}
package com.xiaohao.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectStreamTest {

  public static final String FILE_NAME = "employee.tmp";

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    Employee zhang = new Employee("zhang", 10000, 1990, 1, 1);
    Manager wang = new Manager("wang", 11000, 1991, 1, 1);
    wang.setSecretary(zhang);
    Manager zhao = new Manager("zhao", 12000, 1992, 1, 1);
    zhao.setSecretary(zhang);

    Employee[] staff = new Employee[3];
    staff[0] = zhang;
    staff[1] = wang;
    staff[2] = zhao;

    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
      out.writeObject(staff);
    }

    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
      Employee[] newStaff = (Employee[]) in.readObject();

      // Verify Manager wang and zhao has same secretary
      newStaff[0].raiseSalary(1.1);
      
      for (Employee e : newStaff) {
        System.out.println(e);
      }
    }
  }
}

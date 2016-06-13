package com.xiaohao.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class TextFileTest {

  public static final String FILE_NAME = "employee.tmp";
  
  public static void main(String[] args) throws IOException {
    Employee[] staff = new Employee[3];

    staff[0] = new Employee("zhang", 10000, 1990, 1, 1);
    staff[1] = new Employee("wang", 11000, 1991, 1, 1);
    staff[2] = new Employee("zhao", 12000, 1992, 1, 1);
    
    try (PrintWriter out = new PrintWriter(FILE_NAME, "UTF-8")) {
      writeData(staff, out);
    }
    
    try (Scanner in = new Scanner(new FileInputStream(FILE_NAME), "UTF-8")) {
      Employee[] newStaff = readData(in);
      for (Employee e : newStaff) {
        System.out.println(e);
      }
    }
  }

  private static void writeData(Employee[] employees, PrintWriter out) {
    out.println(employees.length);
    for (Employee e : employees) {
      writeEmployee(e, out);
    }
  }

  private static void writeEmployee(Employee e, PrintWriter out) {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(e.getHireDay());
    out.println(e.getName() + "|" + e.getSalary() + "|" + calendar.get(Calendar.YEAR) + 
      "|" + calendar.get(Calendar.MONTH) + "|" + calendar.get(Calendar.DAY_OF_MONTH));
  }
  
  private static Employee[] readData(Scanner in) {
    int n = in.nextInt();
    in.nextLine();
    
    Employee[] employees = new Employee[n];
    for (int i = 0; i < n; i++) {
      employees[i] = readEmployee(in);
    }
    return employees;
  }
  
  private static Employee readEmployee(Scanner in) {
    String line = in.nextLine();
    String[] tokens = line.split("\\|");
    String name = tokens[0];
    double salary = Double.parseDouble(tokens[1]);
    int year = Integer.parseInt(tokens[2]);
    int month = Integer.parseInt(tokens[3]);
    int day = Integer.parseInt(tokens[4]);
    return new Employee(name, salary, year, month, day);
  }
}

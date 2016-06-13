package com.xiaohao.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RandomAccessTest {

  public static final String FILE_NAME = "employee.tmp";
  
  public static void main(String[] args) throws IOException {
    Employee[] staff = new Employee[3];

    staff[0] = new Employee("zhang", 10000, 1990, 1, 1);
    staff[1] = new Employee("wang", 11000, 1991, 1, 1);
    staff[2] = new Employee("zhao", 12000, 1992, 1, 1);
    
    try (DataOutputStream out = new DataOutputStream(new FileOutputStream(FILE_NAME))){
      for (Employee e : staff) {
        writeData(out, e);
      }
    }
    
    try (RandomAccessFile in = new RandomAccessFile(FILE_NAME, "r")) {
      int n = (int) (in.length() / Employee.RECORD_SIZE);
      Employee[] newStaff = new Employee[n];

      for (int i = n - 1; i >= 0; i--) {
        in.seek(i * Employee.RECORD_SIZE);
        newStaff[i] = readData(in);
      }
      
      for (Employee e : newStaff) {
        System.out.println(e);
      }
    }
  }

  public static void writeData(DataOutput out, Employee e) throws IOException {
    DataIO.writeFixedString(e.getName(), Employee.NAME_SIZE, out);
    out.writeDouble(e.getSalary());
    
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(e.getHireDay());
    out.writeInt(calendar.get(Calendar.YEAR));
    out.writeInt(calendar.get(Calendar.MONTH));
    out.writeInt(calendar.get(Calendar.DAY_OF_MONTH));
  }
  
  public static Employee readData(DataInput in) throws IOException {
    String name = DataIO.readFixedString(Employee.NAME_SIZE, in);
    double salary = in.readDouble();
    int year = in.readInt();
    int month = in.readInt();
    int day = in.readInt();
    return new Employee(name, salary, year, month, day);
  }
}

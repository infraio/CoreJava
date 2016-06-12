package com.xiaohao.set;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

	public static void main(String[] args) {
		Map<String, Employee> staffs = new HashMap<>();
		staffs.put("1", new Employee("zhang"));
		staffs.put("2", new Employee("wang"));
		staffs.put("3", new Employee("lei"));
		staffs.put("4", new Employee("wei"));
		
		System.out.println(staffs);
		
		staffs.remove("4");
		staffs.put("5", new Employee("zhou"));
		
		System.out.println(staffs);
		
		for (Map.Entry<String, Employee> entry : staffs.entrySet()) {
			System.out.println("key=" + entry.getKey() + ", value=" + entry.getValue());
		}
	}

}

class Employee {
	private String name;
	
	public Employee(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}

package com.xiaohao.set;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Comparator;

public class TreeSetTest {
	public static void main(String[] args) {
		SortedSet<Item> items = new TreeSet<>();
		items.add(new Item(123, "one"));
		items.add(new Item(234, "two"));
		items.add(new Item(345, "three"));
		items.add(new Item(456, "four"));
		items.add(new Item(567, "five"));
		
		System.out.println("Sort by partNumber:");
		System.out.println(items);
		
		SortedSet<Item> itemsSortByDescription = new TreeSet<>(new Comparator<Item>() {
			public int compare(Item a, Item b) {
				return a.getDescription().compareTo(b.getDescription());
			}
		});
		
		itemsSortByDescription.addAll(items);
		
		System.out.println("Sort by description:");
		System.out.println(itemsSortByDescription);
	}
}

class Item implements Comparable<Item> {

	private String description;
	private int partNumber;
	
	public Item(int partNumber, String description) {
		this.description = description;
		this.partNumber = partNumber;
	}
	
	@Override
	public int compareTo(Item o) {
		return Integer.compare(partNumber, o.partNumber);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(int partNumber) {
		this.partNumber = partNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + partNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (partNumber != other.partNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [description=" + description + ", partNumber=" + partNumber + "]";
	}
}

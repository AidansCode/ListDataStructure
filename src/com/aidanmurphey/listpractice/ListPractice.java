package com.aidanmurphey.listpractice;

public class ListPractice {

	public static void main(String[] args) {

		IndexedList<String> list = new IndexedList<>();
		list.add("Jack");
		list.add("Billy");
		list.add("John");
		list.add("Richard");
		list.add("Bob");

		System.out.println(list);
		System.out.println(list.size());
		System.out.println("--------------------");

		list.remove("John");
		System.out.println(list);
		System.out.println(list.size());
		System.out.println("--------------------");

		list.remove(0);
		System.out.println(list);
		System.out.println(list.size());
		System.out.println("--------------------");

		System.out.println(list.get(0));

	}

}

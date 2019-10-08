package com.aidanmurphey.listpractice;

public interface ListADT<T> {

	public boolean add(T element);

	public T get(int index);

	public boolean remove(int index);

	public boolean remove(T element);

	public int size();

}

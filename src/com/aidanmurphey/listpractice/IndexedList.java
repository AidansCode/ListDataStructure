package com.aidanmurphey.listpractice;

public class IndexedList<T> implements ListADT<T> {

	private Node[] arr;
	private int count;

	/**
	 * Constructor, initializes array to size of 10
	 */
	public IndexedList() {
		this(10);
	}

	/**
	 * Constructor, initializes array to a given size
	 * @param startingSize The starting size of the array
	 */
	public IndexedList(int startingSize) {
		arr = new Node[startingSize];
		count = 0;
	}

	/**
	 * Add a specific element to the list
	 * @param element The element to add to the array
	 * @return Whether the operation was successful or not
	 */
	@Override
	public boolean add(T element) {
		try {
			monitorArraySize(ListAction.ADD);

			arr[count++] = new Node<T>(element);
		} catch(Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * Returns the element at a specific position in the list
	 * @param index The index of the element
	 * @return The element at the given position of the list
	 */
	@Override
	public T get(int index) {
		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException(index + " is out of bounds for the list.");

		return (T) arr[index].getElement();
	}

	/**
	 * Removes the element at the given index of the list
	 * @param index The index of the element to remove
	 * @return Whether or not the operation was successful
	 */
	@Override
	public boolean remove(int index) {
		try {
			if (index < 0 || index >= size())
				throw new IndexOutOfBoundsException(index + " is out of bounds for the list.");

			arr[index] = null;
			compactListElements();
			monitorArraySize(ListAction.REMOVE);
		} catch(Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * Removes the first instance of an element which is .equals() to the given element
	 * @param element The element to be removed from the list
	 * @return Whether or not the operation was successful
	 */
	@Override
	public boolean remove(T element) {
		try {
			for(int i = 0; i < size(); i++) {
				T value = (T) arr[i].getElement();

				if (value.equals(element)) {
					arr[i] = null;
					compactListElements();
					monitorArraySize(ListAction.REMOVE);
				}
			}
		} catch(Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * Returns the current size of the list
	 * @return The current size of the list
	 */
	@Override
	public int size() {
		return count;
	}

	/**
	 * Watches the size of the internal array; called for every addition and removal to the list.
	 * Possible actions are doubling/halving the size of the internal array
	 * @param action The action being taken (adding or removing an element)
	 */
	private void monitorArraySize(ListAction action) {
		if (action == ListAction.ADD) { //If adding new element

			if ((size() + 1) == arr.length) { //If new size is = array's length

				resizeArray(arr.length * 2); //Double size of array

			}

		} else if (action == ListAction.REMOVE) { //If removing an element

			if (size() <= (arr.length) / 2) { //if new size is less than half of array's length

				if (arr.length / 2 >= 10) { //If half of array's length is at least 10

					resizeArray(arr.length / 2); //Cut size of array in half

				}

			}
		}
	}

	/**
	 * Resize the internal array to a specified size
	 * @param newSize The new size of the internal array
	 */
	private void resizeArray(int newSize) {
		Node[] newArr = new Node[newSize];
		System.arraycopy(arr, 0, newArr, 0, count);

		arr = newArr;
	}

	/**
	 * Removes null spaces from the list. Called after an element is removed from the list.
	 */
	private void compactListElements() {
		int nullIndex;

		while ((nullIndex = getNullIndex()) != -1) {

			for (int i = nullIndex + 1; i < size(); i++) {

				arr[i-1] = arr[i];

				if (i == size() - 1)
					arr[i] = null;

			}

			count--;

		}
	}

	/**
	 * Returns the first index of a null element in the list. Called by compactListElements
	 * @return int The first index of a null element in the list
	 */
	private int getNullIndex() {
		for (int i = 0; i < size(); i++) {

			if (arr[i] == null)
				return i;

		}

		return -1;
	}

	/**
	 * Returns the toString of each element in the list on their own line
	 * @return The toString of each element in the list on their own line
	 */
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < size(); i++) {
			Node node = arr[i];

			if (node == null)
				stringBuilder.append("null");
			else
				stringBuilder.append(node.getElement());

			if (i+1 < size())
				stringBuilder.append(System.lineSeparator());
		}

		return stringBuilder.toString();
	}

}

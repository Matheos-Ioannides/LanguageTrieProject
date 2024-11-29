package LanguageTrieProject;

public class MinHeap {

	String words[]; // Array of words
	int importance[]; // Parallel array for each words importance
	private int occupied; // how many words are in the heap
	private int capacity; // total capacity of the heap

	public MinHeap(int capacity) {
		this.capacity = capacity;
		this.occupied = 0;
		this.importance = new int[this.capacity];
		this.words = new String[this.capacity];
	}

	public int getOccupied() {
		return occupied;
	}

	public void setOccupied(int occupied) {
		this.occupied = occupied;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void heapPush(String newWord, int newImportance) {
		if (this.contains(newWord))
			return;
		
		
	}

	public boolean contains(String targetWord) {
		for (int i = 0; i < this.occupied; i++)
			if (this.words[i].equals(targetWord))
				return true;

		return false;
	}

	
	//Driver function to test MinHeaP
	public static void main(String[] args) {

	}

}

package LanguageTrieProject;

public class MinHeap {

	String words[]; // Array of words
	int importance[]; // Parallel array for each words importance
	private int occupied; // how many words are in the heap
	private int capacity; // total capacity of the heap

	public MinHeap(int k) {
		this.capacity = k;
		this.occupied = 0;
		this.words = new String[this.capacity];
		this.importance = new int[this.capacity];
	}

	// standard getters and setters
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

	// inserts a new word to the MinHeap, making the necessary adjustments to the
	// order of the words
	public void insert(String newWord, int newImp) {
		if (this.contains(newWord))
			return; // Word already exists in heap no need to re-insert

		if (occupied < capacity) { // Heap is not full, insert unconditionally
			words[occupied] = newWord;
			importance[occupied] = newImp;

			int current = occupied; // Restore heap property using up-heap bubbling
			occupied++;

			while (current > 0) {
				int parent = (current - 1) / 2;

				if (importance[current] < importance[parent]) {
					swap(current, parent);
					current = parent;
				} else {
					break;
				}
			}
		} else if (newImp > importance[0]) { // Heap is full, throw root and replace with new word if needed
			words[0] = newWord;
			importance[0] = newImp;

			heapify(0);
		}
	}

	private void heapify(int index) {
		int smallest = index;
		int left = 2 * index + 1;
		int right = 2 * index + 2;

		// Check if left child exists and is smaller than the current node
		if (left < this.occupied && this.importance[left] < this.importance[smallest]) {
			smallest = left;
		}

		// Check if right child exists and is smaller than the current smallest
		if (right < this.occupied && this.importance[right] < this.importance[smallest]) {
			smallest = right;
		}

		// If the smallest is not the current node, swap and continue heapifying
		if (smallest != index) {
			swap(index, smallest);
			heapify(smallest); // Recursively heapify the affected subtree
		}
	}

	// Helper method to swap elements in both arrays
	private void swap(int i, int j) {
		// Swap in the words array
		String tempWord = words[i];
		words[i] = words[j];
		words[j] = tempWord;

		// Swap in the importance array
		int tempImportance = importance[i];
		importance[i] = importance[j];
		importance[j] = tempImportance;
	}

	// Check if the heap already contains a given word
	public boolean contains(String targetWord) {
		for (int i = 0; i < this.occupied; i++)
			if (this.words[i].equals(targetWord))
				return true;

		return false;
	}

	// Prints the entire heap (not in order)
	public void printHeap() {
		for (int i = 0; i < this.occupied; i++) {
			System.out.println(this.words[i]);
		}
	}


}

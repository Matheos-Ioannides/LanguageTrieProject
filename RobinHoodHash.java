package LanguageTrieProject;

public class RobinHoodHash {

	private Element table[]; // table of elements
	private int maxProbeLength; // Max distance from initial position
	private int size; // number of elements in table
	private int capacity; // total capacity of the table

	public RobinHoodHash() {
		this(5);
	}

	public RobinHoodHash(int capacity) {

		this.size = 0;
		this.capacity = capacity;
		this.maxProbeLength = 0;

		table = new Element[this.capacity];
	}

	public static void main(String[] args) {

	}

}

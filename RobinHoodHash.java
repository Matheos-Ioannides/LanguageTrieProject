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

	//Placeholder (might not be void)
	public void insert(char newChar) {
		return;
	}
	
	//Return the pointer to the next node newChar points to (if it exists, else returns null)
	public Element search(char newChar) {
		return null;
	}
	
	//Placeholder. Checks if the array needs a rehash (is >90% full)
	public boolean needsRehash() {
		return false;
	}
	
	//Rehashes the table, pretty self explanatory. Idiot
	public void rehash() {
		return;
	}
	
	public static void main(String[] args) {

	}

}

package LanguageTrieProject;

public class RobinHoodHash {

	Element table[]; // table of elements
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

	public Element[] getTable() {
		return table;
	}

	public void setTable(Element[] table) {
		this.table = table;
	}

	public int getMaxProbeLength() {
		return maxProbeLength;
	}

	public void setMaxProbeLength(int maxProbeLength) {
		this.maxProbeLength = maxProbeLength;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	// Placeholder (might not be void)
	// NOTE: ONLY WORKS FOR LOWERCASE, UPDATE LATER
	public Element insert(char newChar) {
		int i = (newChar - 'a') % this.capacity; // hashing function

		Element newElement = new Element(newChar);

		Element currentElement = newElement;

		while (currentElement != null) {
			if (this.table[i] == null || currentElement.getProbeLength() > this.table[i].getProbeLength()) {
				Element temp = this.table[i];
				this.table[i] = currentElement;
				currentElement = temp;
			} else {
				i++;
				currentElement.setProbeLength(currentElement.getProbeLength() + 1);
				if (currentElement.getProbeLength() > this.maxProbeLength)
					this.maxProbeLength = currentElement.getProbeLength();
			}
		}

		return newElement;
	}

	// Return the pointer to the next node newChar points to (if it exists, else
	// returns null)
	public Element search(char newChar) {
		return null;
	}

	// Placeholder. Checks if the array needs a rehash (is >90% full)
	public boolean needsRehash() {
		return false;
	}

	// Rehashes the table, pretty self explanatory. Idiot
	public void rehash() {
		return;
	}

	public static void main(String[] args) {

	}

}

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

	// Placeholder
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
				i%=this.capacity;
				currentElement.setProbeLength(currentElement.getProbeLength() + 1);
				if (currentElement.getProbeLength() > this.maxProbeLength)
					this.maxProbeLength = currentElement.getProbeLength();
			}
		}
		this.size++;

		return newElement;
	}

	// Placeholder. Checks if the array needs a rehash (is >90% full)
	public boolean needsRehash() {
		if ((float) this.size / this.capacity > 0.9)
			return true;
		return false;
	}

	// Rehashes the table, pretty self explanatory. Idiot
	public void rehash() {
		int newCapacity = 0;

		switch (this.capacity) {
		case 5:
			newCapacity = 11;
			break;
		case 11:
			newCapacity = 19;
			break;
		case 19:
			newCapacity = 29;
			break;
		}

		Element newElements[] = new Element[newCapacity];
		Element tempTable[] = this.table;
		this.table = newElements;
		this.size=0;

		int tempCapacity = this.capacity;
		this.capacity = newCapacity;
		

		for (int i = 0; i < tempCapacity; i++) {
			if (tempTable[i] != null) {
				Element tempElem = this.insert(tempTable[i].getKey());
				tempElem.setNext(tempTable[i].getNext());
				tempElem.setWordLength(tempTable[i].getWordLength());
			}
		}
	}

	public static void main(String[] args) {

	}

}

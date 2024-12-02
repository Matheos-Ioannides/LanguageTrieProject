package LanguageTrieProject;

/**
 * This class essentially represents out hash table. In addition to the actual
 * array of elements, we also have some other helpful fields.
 */
public class RobinHoodHash {
	Element table[]; // table of elements
	private int maxProbeLength; // Max distance from initial position
	private int size; // number of elements in table
	private int capacity; // total capacity of the table

	// default size of the hash table is 5
	public RobinHoodHash() {
		this(5);
	}

	public RobinHoodHash(int capacity) {

		this.size = 0;
		this.capacity = capacity;
		this.maxProbeLength = 0;

		table = new Element[this.capacity];
	}

	// standard getters
	public Element[] getTable() {
		return table;
	}

	public int getMaxProbeLength() {
		return maxProbeLength;
	}

	public int getSize() {
		return size;
	}

	public int getCapacity() {
		return capacity;
	}

	// standard setters
	public void setTable(Element[] table) {
		this.table = table;
	}

	public void setMaxProbeLength(int maxProbeLength) {
		this.maxProbeLength = maxProbeLength;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	// Inserts a character into the hash table as a new element.
	// Uses the Robin Hood algorithm to place everything in the correct place
	public Element insert(char newChar) {
		int i = (newChar - 'a') % this.capacity; // hashing function

		Element newElement = new Element(newChar);

		Element currentElement = newElement; // makes a double pointer so we don't lose reference to our new element

		// Cycles through the array and moves elements forward
		// Stops when the new element has been inserted, and no other elements are
		// pending (being moved)
		while (currentElement != null) {
			if (this.table[i] == null || currentElement.getProbeLength() > this.table[i].getProbeLength()) {
				Element temp = this.table[i];
				this.table[i] = currentElement;
				currentElement = temp;
			} else {
				i++;
				i %= this.capacity;
				currentElement.setProbeLength(currentElement.getProbeLength() + 1);
				if (currentElement.getProbeLength() > this.maxProbeLength)
					this.maxProbeLength = currentElement.getProbeLength();
			}
		}
		this.size++;

		return newElement; // returns the newly placed element so it can be handled afterwards
	}

	// Checks if the array needs a rehashing (if the array is over 90% full)
	public boolean needsRehash() {
		if ((float) this.size / this.capacity > 0.9)
			return true;
		return false;
	}

	//Rehashes the array into a new larger one
	public void rehash() {
		int newCapacity = 0;

		// Decides the new capacity of the array
		// Picks the next prime number from these to resize to (11 ,19 ,29)
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

		// This part of the code basically moves everything from the old array into the
		// new array
		// The insertion happens like normal, that way all numbers are hashed again
		// This reduces clusters of characters
		Element newElements[] = new Element[newCapacity];
		Element tempTable[] = this.table;
		this.table = newElements;
		this.size = 0;

		int tempCapacity = this.capacity;
		this.capacity = newCapacity;

		for (int i = 0; i < tempCapacity; i++) {
			if (tempTable[i] != null) {
				Element tempElem = this.insert(tempTable[i].getKey());

				// moves all the characteristics from the old table to the new one
				tempElem.setNext(tempTable[i].getNext());
				tempElem.setWordLength(tempTable[i].getWordLength());
				tempElem.setImportance(tempTable[i].getImportance());
			}
		}
	}
}

package LanguageTrieProject;

public class HashTrieNode {

	RobinHoodHash hashTable;
	private int wordLength;
	private int importance;

	public HashTrieNode() {
		this.hashTable = new RobinHoodHash();
		this.wordLength = -1;
		this.importance = 0;
	}

	public int getWordLength() {
		return wordLength;
	}

	public void setWordLength(int wordLength) {
		this.wordLength = wordLength;
	}

	public int getImportance() {
		return importance;
	}

	public void incrementImportance(){
		this.importance++;
	}

	// Only takes lowercase characters, fix later
	// BUG: THE LAST CHARACTER OF A WORD WILL ALWAYS HAVE A POINTER TO AN EMPTY NODE
	public HashTrieNode insert(char newChar) {
		boolean charExists = false;
		Element newElement = null;

		for (int i = 0; i < this.hashTable.table.length; i++) {
			if (this.hashTable.table[i] != null && this.hashTable.table[i].getKey() == newChar) {
				charExists = true;
				newElement = this.hashTable.table[i];
				break;
			}
		}

		if (!charExists) {
			newElement = this.hashTable.insert(newChar);
			newElement.setNext(new HashTrieNode());
			if(this.hashTable.needsRehash())
				this.hashTable.rehash();
		}
		
		return newElement.getNext();
	}
	
	
	
	//Looks for a key in the hashtable. If it finds it, it return the next node. Returns null if it doesn't
	public HashTrieNode search(char targetChar, int wordLength) {
		for (int i = 0; i < this.hashTable.table.length; i++)
			if (this.hashTable.table[i] != null && this.hashTable.table[i].getKey() == targetChar /*&& this.wordLength == wordLength*/) {
				return this.hashTable.table[i].getNext();
			}

		return null;
	}

	public static void main(String args[]) {
		HashTrieNode myNode = new HashTrieNode();

		myNode.insert('a');
		myNode.insert('b');
		myNode.insert('f');
		myNode.insert('k');
		myNode.insert('g');
		
		
		
		
	}
}

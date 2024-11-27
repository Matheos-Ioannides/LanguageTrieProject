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

	// Only takes lowercase characters, fix later
	//BUG: THE LAST CHARACTER OF A WORD WILL ALWAYS HAVE A POINTER TO AN EMPTY NODE
	public HashTrieNode insert(char newChar) {
		boolean charExists = false;
		Element newElement = null;
		
		for (int i = 0; i < this.hashTable.table.length; i++) {
			if (this.hashTable.table[i]!=null && this.hashTable.table[i].getKey() == newChar) {
				charExists = true;
				newElement = this.hashTable.table[i];
			}
		}

		if (!charExists) {
			newElement = this.hashTable.insert(newChar);
			newElement.setNext(new HashTrieNode());
		}

		return newElement.getNext();
	}
	
	public static void main(String args[]) {
		HashTrieNode myNode = new HashTrieNode();
		
		myNode.insert('a').insert('b');
		
		myNode.insert('a').insert('c');
		
		myNode.insert('a').insert('c').insert('d'); 
	}
}

package LanguageTrieProject;

public class HashTrieNode {

	RobinHoodHash hashTable;
//	private int wordLength;
	private int importance;

	public HashTrieNode() {
		this.hashTable = new RobinHoodHash();
//		this.wordLength = -1;
		this.importance = 0;
	}

	public int getWordLength(char targetChar) {
		for (int i = 0; i < this.hashTable.table.length; i++)
			if (this.hashTable.table[i] != null && this.hashTable.table[i].getKey() == targetChar)
				return this.hashTable.table[i].getWordLength();
		return -1;
	}

	public void setWordLength(int wordLength) {
//		this.wordLength = wordLength;
	}

	public int getImportance() {
		return importance;
	}

	public void setImportance(int importance) {
		this.importance = importance;
	}

	// Only takes lowercase characters, fix later
	// BUG: THE LAST CHARACTER OF A WORD WILL ALWAYS HAVE A POINTER TO AN EMPTY NODE
	public HashTrieNode insert(char newChar, int newElementWordLength) {
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
			newElement.setWordLength(newElementWordLength);
			if (this.hashTable.needsRehash())
				this.hashTable.rehash();
		}

		return newElement.getNext();
	}

	// Looks for a key in the hashtable. If it finds it, it return the next node.
	// Returns null if it doesn't
	public HashTrieNode search(char targetChar) {
		for (int i = 0; i < this.hashTable.table.length; i++)
			if (this.hashTable.table[i] != null && this.hashTable.table[i].getKey() == targetChar) {
				return this.hashTable.table[i].getNext();
			}

		return null;
	}

	// searches the current tree (starting from the root), follows the characters of
	// the prefix and returns the subtree after the prefix.
	// If no word with that prefix exists it returns null.z
	public HashTrieNode startsWith(String prefix) {
		HashTrieNode current = this;
		for (int i = 0; i < prefix.length(); i++) {
			current = current.search(prefix.charAt(i));
			if (current == null)
				return null;
		}
		return current;
	}

//	public void collectWithPrefix(HashTrieNode current, String prefix, int k, MinHeap myHeap) {
//
//		if (current == null)
//			return;
//
//		for (char i = 'a'; i <= 'z'; i++) {
//			HashTrieNode next = current.search(i);
//			if (next != null) {
//				this.collectWithPrefix(next, prefix + i, k, myHeap);
////				if (current.wordLength != -1 && current.importance != 0) {
//				if (current.wordLength != -1) {
////				myHeap.heapPush(prefix, current.importance); not yet implemented
//					System.out.println(prefix + i);
//				}
//			}
//		}
//	}

//	public void collectWithSameLength(HashTrieNode current, String wordBuilder, String targetWord, int k,
//			MinHeap myHeap) {
//
//		if (current == null)
//			return;
//
////		if(current.wordLength == targetWord.length() && current.importance!= 0)
//		if (current.wordLength == targetWord.length() + 1)
////			myHeap.heapPush(wordBuilder, current.importance);
//			System.out.println(wordBuilder);
//
//		for (char i = 'a'; i <= 'z'; i++) {
//			HashTrieNode next = current.search(i);
//			if (next != null)
//				this.collectWithSameLength(next, wordBuilder + i, targetWord, k, myHeap);
//		}
//	}

	public static void main(String args[]) {
		HashTrieNode myNode = new HashTrieNode();

		myNode.insert('a', -1);
		myNode.insert('b', -1);
		myNode.insert('f', -1);
		myNode.insert('k', -1);
		myNode.insert('g', -1);

	}
}

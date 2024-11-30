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

	public int getImportance() {
		return importance;
	}

	public void incrementImportance(){
		this.importance++;
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

	public void collectWithPrefix(HashTrieNode current, String prefix, MinHeap myHeap) {

		if (current == null)
			return;

		for (char i = 'a'; i <= 'z'; i++) {
			HashTrieNode next = current.search(i);
			if (next != null) {
				this.collectWithPrefix(next, prefix + i, myHeap);
//				if (current.wordLength != -1 && current.importance != 0) {
				if (current.getWordLength(i) != -1) {
//				myHeap.heapPush(prefix, current.importance); not yet implemented
					System.out.println(prefix + i);
				}
			}
		}
	}

	public void collectWithSameLength(HashTrieNode current, String wordBuilder, String targetWord, MinHeap myHeap) {

		if (current == null || wordBuilder.length() > targetWord.length())
			return;

		for (int i = 0; i < current.hashTable.table.length; i++) {
//			if (current.hashTable.table[i] != null&&current.hashTable.table[i].getWordLength() == targetWord.length() && current.getImportance()!=0) {
			if (current.hashTable.table[i] != null
					&& current.hashTable.table[i].getWordLength() == targetWord.length()) {
				if (countDifferentLetters(targetWord, wordBuilder + current.hashTable.table[i].getKey()) <= 2)
//					myHeap.heapPush(wordBuilder, current.importance);
					System.out.println(wordBuilder + current.hashTable.table[i].getKey());
			}
		}

		for (char i = 'a'; i <= 'z'; i++) {
			HashTrieNode next = current.search(i);
			if (next != null)
				this.collectWithSameLength(next, wordBuilder + i, targetWord, myHeap);
		}
	}

	private int countDifferentLetters(String baseWord, String checkWord) {
		int count = 0;
		for (int i = 0; i < baseWord.length(); i++)
			if (baseWord.charAt(i) != checkWord.charAt(i))
				count++;
		return count;
	}

	public void collectWithSimilarLength(HashTrieNode current, String wordBuilder, String targetWord, MinHeap myHeap) {

		if (current == null)
			return;

		for (int i = 0; i < current.hashTable.table.length; i++) {
			if (current.hashTable.table[i] != null && current.hashTable.table[i].getWordLength() > 0) {
				if (validForSimilarLength(targetWord, wordBuilder + current.hashTable.table[i].getKey()))
//				myHeap.heapPush(wordBuilder, current.importance);
					System.out.println(wordBuilder + current.hashTable.table[i].getKey());
			}

		}

		for (char i = 'a'; i <= 'z'; i++) {
			HashTrieNode next = current.search(i);
			if (next != null)
				this.collectWithSimilarLength(next, wordBuilder + i, targetWord, myHeap);
		}

	}

	private boolean validForSimilarLength(String baseWord, String checkWord) {
		if (baseWord.length() - checkWord.length() == 1) {

			int currentIndex = 0;
			for (int i = 0; i < baseWord.length(); i++) {
				if (checkWord.charAt(currentIndex) == baseWord.charAt(i)) {
					currentIndex++;
				}
			}
			if (currentIndex == checkWord.length())
				return true;

		} else if (checkWord.length() - baseWord.length() <= 2) {
			int currentIndex = 0;

			for (int i = 0; i < checkWord.length(); i++) {
				if (checkWord.charAt(i) == baseWord.charAt(currentIndex)) {
					currentIndex++;
				}
				if (currentIndex == baseWord.length())
					return true;
			}
			
		}

		return false;
	}

	public static void main(String args[]) {
		HashTrieNode myNode = new HashTrieNode();

		myNode.insert('a', -1);
		myNode.insert('b', -1);
		myNode.insert('f', -1);
		myNode.insert('k', -1);
		myNode.insert('g', -1);

	}
}

package LanguageTrieProject;

/**
 * This class represents a node of our Trie. It consists of a Hash Table
 */
public class HashTrieNode {

	RobinHoodHash hashTable;

	public HashTrieNode() {
		this.hashTable = new RobinHoodHash();
	}

	// takes a character and searches for it in the hash table. If it finds it it
	// returns its corresponding WordLength. If not it returns -1
	public int getWordLength(char targetChar) {
		for (int i = 0; i < this.hashTable.table.length; i++)
			if (this.hashTable.table[i] != null && this.hashTable.table[i].getKey() == targetChar)
				return this.hashTable.table[i].getWordLength();
		return -1;
	}

	// takes a character and searches for it in the hash table. If it finds it it
	// returns its corresponding Importance. If not it returns -1
	public int getImportance(char targetChar) {
		for (int i = 0; i < this.hashTable.table.length; i++)
			if (this.hashTable.table[i] != null && this.hashTable.table[i].getKey() == targetChar)
				return this.hashTable.table[i].getImportance();
		return -1;
	}

	// takes a character and searches for it in the hash table. If it finds it it
	// increments its corresponding Importance by 1. This is used while going
	// through a text file, updating each words importance.
	public void incrementImportance(char targetChar) {
		for (int i = 0; i < this.hashTable.table.length; i++)
			if (this.hashTable.table[i] != null && this.hashTable.table[i].getKey() == targetChar)
				this.hashTable.table[i].setImportance(this.hashTable.table[i].getImportance() + 1);
	}

	// Inserts a new character to the hash table. The way this works is that it runs
	// through our hash table and inserts the character as a new elements, using the
	// Robing Hood hashing method. It then returns the next node in the chain, so it
	// can be processed by the caller.
	// if the character already exists, we just return the next node in the chain
	public HashTrieNode insert(char newChar, int newElementWordLength) {
		boolean charExists = false;
		Element newElement = null;

		// checks if the character already exists in the hash table
		for (int i = 0; i < this.hashTable.table.length; i++) {
			if (this.hashTable.table[i] != null && this.hashTable.table[i].getKey() == newChar) {
				charExists = true;
				newElement = this.hashTable.table[i];
				break;
			}
		}

		// if it doesn't exist, it gets added to the table (by calling our hashing
		// function) and we create a new element
		if (!charExists) {
			newElement = this.hashTable.insert(newChar);
			newElement.setNext(new HashTrieNode());
			newElement.setWordLength(newElementWordLength);

			// additionally, we rehash if required
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

	// Searches the current tree (starting from the root), follows the characters of
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

	// recursive function that runs through the current tree, and searches for words
	// that start with a given prefix
	// If such a word exists it gets added to the given heap
	public void collectWithPrefix(HashTrieNode current, String prefix, MinHeap myHeap) {

		if (current == null)
			return;

		// Explore all child nodes from a - z
		for (char i = 'a'; i <= 'z'; i++) {
			// And searches if its a a character sequence that exists in the dictionary
			HashTrieNode next = current.search(i);
			// If a word that contains that sequence of characters exists in the dictionary
			// We get the next node

			// Finally, if the node we're at now is an ending node, its importance is over 0
			// And it starts with the desired prefix
			// its a valid word to get added to our heap
			if (next != null) {

				this.collectWithPrefix(next, prefix + i, myHeap); // recursive call, follow the character chain
				if (current.getWordLength(i) != -1 && current.getImportance(i) != 0) {
					myHeap.insert(prefix + i, current.getImportance(i));
				}
			}
		}
	}

	// Recursive function that runs through the current tree, and searches for words
	// that have the same length as the target Word.
	// Additionally, up to 2 characters can differ for a word in the tree to be
	// considered valid
	public void collectWithSameLength(HashTrieNode current, String wordBuilder, String targetWord, MinHeap myHeap) {

		// base case
		if (current == null || wordBuilder.length() > targetWord.length())
			return;

		// Check if the current entry in the hash table is valid and matches conditions:
		// 1. It is not null.
		// 2. The word's length matches the target word's length.
		// 3. The word's importance is not zero.
		for (int i = 0; i < current.hashTable.table.length; i++) {
			if (current.hashTable.table[i] != null && current.hashTable.table[i].getWordLength() == targetWord.length()
					&& current.hashTable.table[i].getImportance() != 0) {

				if (countDifferentLetters(targetWord, wordBuilder + current.hashTable.table[i].getKey()) <= 2) {
					myHeap.insert(wordBuilder + current.hashTable.table[i].getKey(),
							current.hashTable.table[i].getImportance());
				}
			}
		}
		// If the number of different letters between the target word and
		// the newly built word is less than or equal to 2, add it to the MinHeap.
		for (char i = 'a'; i <= 'z'; i++) {
			HashTrieNode next = current.search(i);
			if (next != null)
				this.collectWithSameLength(next, wordBuilder + i, targetWord, myHeap);
		}
	}

	private int countDifferentLetters(String baseWord, String checkWord) {
		int count = 0; // initialize the counter of different letters
		for (int i = 0; i < baseWord.length(); i++)

			// Compare characters at the same position.
			// Increment the counter if the characters differ.
			if (baseWord.charAt(i) != checkWord.charAt(i))
				count++;
		return count;
	}

	public void collectWithSimilarLength(HashTrieNode current, String wordBuilder, String targetWord, MinHeap myHeap) {

		// base case
		if (current == null)
			return;

		// Check if the current entry in the hash table is valid:
		// 1. The entry is not null.
		// 2. The word length is greater than 0.
		// 3. The word's importance is not zero.
		for (int i = 0; i < current.hashTable.table.length; i++) {
			if (current.hashTable.table[i] != null && current.hashTable.table[i].getWordLength() > 0
					&& current.hashTable.table[i].getImportance() != 0) {
				if (validForSimilarLength(targetWord, wordBuilder + current.hashTable.table[i].getKey())) {
					myHeap.insert(wordBuilder + current.hashTable.table[i].getKey(),
							current.hashTable.table[i].getImportance());
				}
			}

		}

		// Explore all child nodes from a - z
		for (char i = 'a'; i <= 'z'; i++) {
			HashTrieNode next = current.search(i);
			if (next != null)
				this.collectWithSimilarLength(next, wordBuilder + i, targetWord, myHeap);
		}

	}

	private boolean validForSimilarLength(String baseWord, String checkWord) {
		// if checkWord is exactly 1 shorter than baseWord
		if (baseWord.length() - checkWord.length() == 1) {

			int currentIndex = 0;

			for (int i = 0; i < baseWord.length(); i++) {
				if (checkWord.charAt(currentIndex) == baseWord.charAt(i)) {
					currentIndex++;
				}
				if (currentIndex == checkWord.length() - 1)
					return true;
			}
		// If checkWord is up to 2 characters than baseWord
		} else if (checkWord.length() - baseWord.length() <= 2) {
			int currentIndex = 0;

			for (int i = 0; i < checkWord.length(); i++) {
				if (checkWord.charAt(i) == baseWord.charAt(currentIndex)) {
					currentIndex++;
				}
				if (currentIndex == baseWord.length() - 1)
					return true;
			}

		}

		return false;
	}

}

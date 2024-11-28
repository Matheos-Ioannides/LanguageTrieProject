package LanguageTrieProject;

public class HashTrie {

	private HashTrieNode root = new HashTrieNode();

	public HashTrie() {
	}

	// Recursive insert method
	private void insert(String newWord, int wordLength, HashTrieNode currentNode) {
		if (newWord == "") {
			return;
		}
		if (newWord.length() == 1) {
			currentNode.setWordLength(wordLength);
		}

		currentNode = currentNode.insert(newWord.charAt(0));

		this.insert(newWord.substring(1), wordLength, currentNode);
	}

	// Helper method for insert
	public void insert(String newWord) {
		insert(newWord, newWord.length(), this.root);
	}

	private boolean search(String targetWord, int wordLength, HashTrieNode currentNode) {

		if (currentNode == null)
			return false; // if the currentNode is null, that means the key was not found

		if (targetWord.length() == 1)
			if (currentNode.getWordLength() == wordLength
					&& currentNode.search(targetWord.charAt(0), wordLength) != null) {
				return true;
			} else {
				return false;
			}

		currentNode = currentNode.search(targetWord.charAt(0), wordLength);
		return this.search(targetWord.substring(1), wordLength, currentNode);
	}

	public boolean search(String targetWord) {
		return this.search(targetWord, targetWord.length(), this.root);
	}

	public static void main(String[] args) {
		HashTrie myTrie = new HashTrie();

	}

}

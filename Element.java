package LanguageTrieProject;

public class Element {

	private char key; // character that the element is identifiable by
	private int probeLength = 0; // distance from the elements original spot in the hash table
	private HashTrieNode next = null; // pointer to the next HashTrieNode
	private int wordLength = -1; // flag that signals that an existing word ends on this character
	private int importance = 0; // appearances of this word.

	// constructor, only takes the key as a parameter. All other fields have to be
	// initialized from the setters
	public Element(char key) {
		this.key = key;
	}

	// standard getters
	public char getKey() {
		return key;
	}

	public int getProbeLength() {
		return probeLength;
	}

	public HashTrieNode getNext() {
		return next;
	}

	public int getWordLength() {
		return this.wordLength;
	}

	public int getImportance() {
		return this.importance;
	}

	// standard setters
	public void setProbeLength(int probeLength) {
		this.probeLength = probeLength;
	}

	public void setNext(HashTrieNode next) {
		this.next = next;
	}

	public void setWordLength(int wordLength) {
		this.wordLength = wordLength;
	}

	public void setImportance(int importance) {
		this.importance = importance;
	}
}

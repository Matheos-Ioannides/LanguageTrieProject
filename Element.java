package LanguageTrieProject;

public class Element {

	private char key;
	private int probeLength = 0;
	private HashTrieNode next = null;

	public Element(char key) {
		this.key = key;
	}

	public char getKey() {
		return key;
	}

	public int getProbeLength() {
		return probeLength;
	}

	public void setProbeLength(int probeLength) {
		this.probeLength = probeLength;
	}

	public HashTrieNode getNext() {
		return next;
	}

	public void setNext(HashTrieNode next) {
		this.next = next;
	}

	public static void main(String[] args) {

	}

}

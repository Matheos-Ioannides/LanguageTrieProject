package LanguageTrieProject;

public class HashTrie {

	private HashTrieNode root = new HashTrieNode();

	public HashTrie() {
	}

	public void insert(String newWord) {
		HashTrieNode current = this.root;
		int wordLength = newWord.length();

		while (newWord != "") {
			current = current.insert(newWord.charAt(0));
			newWord = newWord.substring(1);
		}
	}
	
	

	public static void main(String[] args) {
		HashTrie myTrie = new HashTrie();

		myTrie.insert("car");
		myTrie.insert("cat");
		myTrie.insert("catnip");
	}

}

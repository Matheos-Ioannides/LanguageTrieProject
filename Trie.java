package LanguageTrieProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Trie {

	TrieNode root;

	public Trie() {
		this.root = new TrieNode();
	}

	public class TrieNode {

		private TrieNode[] alphabet;
		private int wordLength;

		public TrieNode() {
			this.alphabet = new TrieNode[26];
			this.wordLength = -1;
		}

	}

	public void insert(String newWord) {
		TrieNode p = this.root;
		this.insert(newWord, newWord.length(), p);
	}

	// BUG: WordLength has to be on the node of the last character, not the one
	// after
	private void insert(String newWord, int newLength, TrieNode p) {

		if (newWord == "") {
			p.wordLength = newLength;
			return;
		}

		if (p.alphabet[newWord.charAt(0) - 'a'] == null)
			p.alphabet[newWord.charAt(0) - 'a'] = new TrieNode();

		p = p.alphabet[newWord.charAt(0) - 'a'];

		insert(newWord.substring(1), newLength, p);
		return;
	}

	public boolean find(String myWord) {
		TrieNode p = this.root;
		return this.find(myWord, myWord.length(), p);
	}

	private boolean find(String myWord, int myLength, TrieNode p) {
		if (myWord == "") {
			if (myLength == p.wordLength)
				return true;
			else
				return false;
		}

		if (p.alphabet[myWord.charAt(0) - 'a'] == null)
			return false;
		p = p.alphabet[myWord.charAt(0) - 'a'];
		return find(myWord.substring(1), myLength, p);
	}

	public static void readDictionary(Trie myTrie, String filename) {
		try {
			File dictionary = new File(filename);
			Scanner input = new Scanner(dictionary);

			while (input.hasNextLine()) {
				myTrie.insert(input.nextLine().toLowerCase());
			}
			input.close();

		} catch (FileNotFoundException e) {
			System.out.println("Dictionary file not found: ");
		}
	}

	public static int memCalc(TrieNode current) {

		if (current == null)
			return 0;

		int memory = 0;

		memory = 4 + 4 + 26; // Fields of the node (2 integers and 26 characters)
		memory += 26 * 4; // as well as the array of pointers to the next nodes

		for (int i = 0; i < current.alphabet.length; i++) {
			memory += memCalc(current.alphabet[i]); // compute the same for each child
		}

		return memory;
	}

	public static void main(String[] args) {
		Trie myTrie = new Trie();

		readDictionary(myTrie,
				"D:\\Folders\\UNI STUFF\\WinterSemester24-25\\EPL231\\Project\\LanguageTrie\\src\\LanguageTrieProject\\TextFiles\\Static Dictionaries\\staticDictionary100000.txt");
		System.out.println(memCalc(myTrie.root));
	}

}

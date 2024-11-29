package LanguageTrieProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
			if (currentNode.getWordLength() == wordLength && currentNode.search(targetWord.charAt(0)) != null) {
				return true;
			} else {
				return false;
			}

		currentNode = currentNode.search(targetWord.charAt(0));
		return this.search(targetWord.substring(1), wordLength, currentNode);
	}

	public static void readDictionary(HashTrie myTrie, String filename) {
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

	public boolean search(String targetWord) {
		return this.search(targetWord, targetWord.length(), this.root);
	}

	private void findKWithPrefix(HashTrieNode node, String prefix, int k, MinHeap myHeap) {
		HashTrieNode current = node.startsWith(prefix);
		if (current == null)
			return;

		current.collectWithPrefix(current, prefix, k, myHeap);

	}

	// helper
	public void findKWithPrefix(String prefix, int k, MinHeap myHeap) {
		this.findKWithPrefix(this.root, prefix, k, myHeap);
	}

	// D:\Folders\UNI STUFF\WinterSemester24-25\EPL231\Project\LanguageTrie\src\LanguageTrieProject\Words.txt
	public static void main(String[] args) {

		HashTrie myTrie = new HashTrie();

		System.out.println("File name of dictionary");
		Scanner in = new Scanner(System.in);
		String dictionary = in.nextLine();
		readDictionary(myTrie, dictionary);

		System.out.println(myTrie.search("abandon"));
		System.out.println(myTrie.search("ability"));
		System.out.println(myTrie.search("abroad"));
		System.out.println(myTrie.search("abundant"));
		System.out.println(myTrie.search("access"));
		System.out.println(myTrie.search("achieve"));
		System.out.println(myTrie.search("adapt"));
		System.out.println(myTrie.search("admire"));
		System.out.println(myTrie.search("adventure"));
		System.out.println(myTrie.search("advice"));
		System.out.println(myTrie.search("advocate"));
		System.out.println(myTrie.search("afford"));
		System.out.println(myTrie.search("agree"));
		System.out.println(myTrie.search("alert"));
		System.out.println(myTrie.search("align"));
		System.out.println(myTrie.search("allow"));
		System.out.println(myTrie.search("alter"));
		System.out.println(myTrie.search("ambition"));
		System.out.println(myTrie.search("analyze"));
		System.out.println(myTrie.search("ancient"));
		System.out.println(myTrie.search("angle"));
		System.out.println(myTrie.search("animal"));
		System.out.println(myTrie.search("answer"));
		System.out.println(myTrie.search("appeal"));
		System.out.println(myTrie.search("apply"));
		System.out.println(myTrie.search("approve"));
		System.out.println(myTrie.search("area"));
		System.out.println(myTrie.search("argue"));
		System.out.println(myTrie.search("arrange"));
		System.out.println(myTrie.search("arrest"));
		System.out.println(myTrie.search("arrive"));
		System.out.println(myTrie.search("artist"));
		System.out.println(myTrie.search("assist"));
		System.out.println(myTrie.search("assume"));
		System.out.println(myTrie.search("athlete"));
		System.out.println(myTrie.search("attack"));
		System.out.println(myTrie.search("attempt"));
		System.out.println(myTrie.search("attend"));
		System.out.println(myTrie.search("attract"));
		System.out.println(myTrie.search("author"));
		System.out.println(myTrie.search("avoid"));
		System.out.println(myTrie.search("balance"));
		System.out.println(myTrie.search("beauty"));
		System.out.println(myTrie.search("believe"));
		System.out.println(myTrie.search("benefit"));
		System.out.println(myTrie.search("beyond"));
		System.out.println(myTrie.search("blend"));
		System.out.println(myTrie.search("border"));
		System.out.println(myTrie.search("borrow"));
		System.out.println(myTrie.search("break"));
		System.out.println(myTrie.search("bridge"));
		System.out.println(myTrie.search("bright"));
		System.out.println(myTrie.search("budget"));
		System.out.println(myTrie.search("build"));
		System.out.println(myTrie.search("burst"));
		System.out.println(myTrie.search("calm"));
		System.out.println(myTrie.search("cancel"));
		System.out.println(myTrie.search("capture"));
		System.out.println(myTrie.search("care"));
		System.out.println(myTrie.search("carry"));
		System.out.println(myTrie.search("cause"));
		System.out.println(myTrie.search("center"));
		System.out.println(myTrie.search("change"));
		System.out.println(myTrie.search("charge"));
		System.out.println(myTrie.search("choice"));
		System.out.println(myTrie.search("circle"));
		System.out.println(myTrie.search("citizen"));
		System.out.println(myTrie.search("claim"));

		MinHeap myHeap = new MinHeap(0);
		myTrie.findKWithPrefix("bo", 2, myHeap);
	}

}

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
			currentNode = currentNode.insert(newWord.charAt(0), wordLength);
		} else {
			currentNode = currentNode.insert(newWord.charAt(0), -1);
		}

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
			if (currentNode.getWordLength(targetWord.charAt(0)) == wordLength
					&& currentNode.search(targetWord.charAt(0)) != null) {
				return true;
			} else {
				return false;
			}

		currentNode = currentNode.search(targetWord.charAt(0));
		return this.search(targetWord.substring(1), wordLength, currentNode);
	}

	public boolean search(String targetWord) {
		return this.search(targetWord, targetWord.length(), this.root);
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

	private void findKWithPrefix(String prefix, MinHeap myHeap) {
		HashTrieNode current = this.root.startsWith(prefix);
		if (current == null)
			return;

		current.collectWithPrefix(current, prefix, myHeap);

	}

	private void findKWithSameLength(String targetWord, MinHeap myHeap) {

		if (this.root == null)
			return;

		this.root.collectWithSameLength(this.root, "", targetWord, myHeap);
	}

	private void findKWithSimilarLength(String targetWord, MinHeap myHeap) {
		if (this.root == null)
			return;

		this.root.collectWithSimilarLength(this.root, "", targetWord, myHeap);
	}

	public void findKWords(String prefix, MinHeap myHeap) {
		System.out.println("With the prefix " + prefix);
		this.findKWithPrefix(prefix, myHeap);
		System.out.println("\nWith the same length as " + prefix);
		this.findKWithSameLength(prefix, myHeap);
		System.out.println("\nWith similar length to " + prefix);
		this.findKWithSimilarLength(prefix, myHeap);
	}

	public static void main(String[] args) {

		HashTrie myTrie = new HashTrie();

		readDictionary(myTrie,
				"D:\\Folders\\UNI STUFF\\WinterSemester24-25\\EPL231\\Project\\LanguageTrie\\src\\LanguageTrieProject\\Words.txt");

//		System.out.println(myTrie.search("abandon"));
//		System.out.println(myTrie.search("ability"));
//		System.out.println(myTrie.search("abroad"));
//		System.out.println(myTrie.search("abundant"));
//		System.out.println(myTrie.search("access"));
//		System.out.println(myTrie.search("achieve"));
//		System.out.println(myTrie.search("adapt"));
//		System.out.println(myTrie.search("admire"));
//		System.out.println(myTrie.search("adventure"));
//		System.out.println(myTrie.search("advice"));
//		System.out.println(myTrie.search("advocate"));
//		System.out.println(myTrie.search("afford"));
//		System.out.println(myTrie.search("agree"));
//		System.out.println(myTrie.search("alert"));
//		System.out.println(myTrie.search("align"));
//		System.out.println(myTrie.search("allow"));
//		System.out.println(myTrie.search("alter"));
//		System.out.println(myTrie.search("ambition"));
//		System.out.println(myTrie.search("analyze"));
//		System.out.println(myTrie.search("ancient"));
//		System.out.println(myTrie.search("angle"));
//		System.out.println(myTrie.search("animal"));
//		System.out.println(myTrie.search("answer"));
//		System.out.println(myTrie.search("appeal"));
//		System.out.println(myTrie.search("apply"));
//		System.out.println(myTrie.search("approve"));
//		System.out.println(myTrie.search("area"));
//		System.out.println(myTrie.search("argue"));
//		System.out.println(myTrie.search("arrange"));
//		System.out.println(myTrie.search("arrest"));
//		System.out.println(myTrie.search("arrive"));
//		System.out.println(myTrie.search("artist"));
//		System.out.println(myTrie.search("assist"));
//		System.out.println(myTrie.search("assume"));
//		System.out.println(myTrie.search("athlete"));
//		System.out.println(myTrie.search("attack"));
//		System.out.println(myTrie.search("attempt"));
//		System.out.println(myTrie.search("attend"));
//		System.out.println(myTrie.search("attract"));
//		System.out.println(myTrie.search("author"));
//		System.out.println(myTrie.search("avoid"));
//		System.out.println(myTrie.search("balance"));
//		System.out.println(myTrie.search("beauty"));
//		System.out.println(myTrie.search("believe"));
//		System.out.println(myTrie.search("benefit"));
//		System.out.println(myTrie.search("beyond"));
//		System.out.println(myTrie.search("blend"));
//		System.out.println(myTrie.search("border"));
//		System.out.println(myTrie.search("borrow"));
//		System.out.println(myTrie.search("break"));
//		System.out.println(myTrie.search("bridge"));
//		System.out.println(myTrie.search("bright"));
//		System.out.println(myTrie.search("budget"));
//		System.out.println(myTrie.search("build"));
//		System.out.println(myTrie.search("burst"));
//		System.out.println(myTrie.search("calm"));
//		System.out.println(myTrie.search("cancel"));
//		System.out.println(myTrie.search("capture"));
//		System.out.println(myTrie.search("care"));
//		System.out.println(myTrie.search("carry"));
//		System.out.println(myTrie.search("cause"));
//		System.out.println(myTrie.search("center"));
//		System.out.println(myTrie.search("change"));
//		System.out.println(myTrie.search("charge"));
//		System.out.println(myTrie.search("choice"));
//		System.out.println(myTrie.search("circle"));
//		System.out.println(myTrie.search("citizen"));
//		System.out.println(myTrie.search("claim"));


		MinHeap myHeap = new MinHeap(2);

		myTrie.insert("plant");
		myTrie.insert("plane");
		myTrie.insert("plans");
		myTrie.insert("planet");
		myTrie.insert("plank");
		myTrie.insert("planning");
		myTrie.insert("pan");
		myTrie.insert("lan");
		myTrie.insert("planet");
		myTrie.insert("planer");
		myTrie.insert("aplans");
		myTrie.insert("plains");
		myTrie.insert("play");
		myTrie.insert("clan");
		myTrie.insert("plum");
		myTrie.insert("span");
		
		
		myTrie.findKWords("plan", myHeap);
	}

}

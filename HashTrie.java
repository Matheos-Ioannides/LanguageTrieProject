package LanguageTrieProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HashTrie {

	public HashTrieNode root = new HashTrieNode();

	public HashTrie() {
	}

	// Recursive insert method
	// Inserts each character of the newWord one by one.
	private void insert(String newWord, int wordLength, HashTrieNode currentNode) {
		if (newWord == "") {
			return;
		}

		// When we're at the final node, we make sure to add the wordLength, so that we
		// can locate the word later
		if (newWord.length() == 1) {
			currentNode = currentNode.insert(newWord.charAt(0), wordLength);
		} else { // otherwise we just set wordLength to the default (-1)
			currentNode = currentNode.insert(newWord.charAt(0), -1);
		}

		this.insert(newWord.substring(1), wordLength, currentNode); // recursive call. Reduces the word by 1
	}

	// Helper method for insert
	public void insert(String newWord) {
		insert(newWord, newWord.length(), this.root);
	}

	// Recursive function that searches for a word in the Trie. Same logic as insert
	private boolean search(String targetWord, int wordLength, HashTrieNode currentNode) {

		if (currentNode == null)
			return false; // if the currentNode is null, that means the key was not found

		// if we're at the last node, check if the word ends here
		if (targetWord.length() == 1)
			if (currentNode.getWordLength(targetWord.charAt(0)) == wordLength
					&& currentNode.search(targetWord.charAt(0)) != null) {
				return true;

			} else {// if it doesn't then the word doesn't exist
				return false;
			}

		// Get the next node in the sequence by searching for the current character in
		// the hash table
		currentNode = currentNode.search(targetWord.charAt(0));
		return this.search(targetWord.substring(1), wordLength, currentNode);
	}

	// Recursive function that searches for a word in the Trie. Returns a reference
	// to the ending node of the word we're looking for
	private HashTrieNode nodeSearch(String targetWord, int wordLength, HashTrieNode currentNode) {

		if (currentNode == null)
			return null; // if the currentNode is null, that means the key was not found

		// if we're at the last node, check if the word ends here
		if (targetWord.length() == 1)
			if (currentNode.getWordLength(targetWord.charAt(0)) == wordLength
					&& currentNode.search(targetWord.charAt(0)) != null) {
				return currentNode;
			} else {// if it doesn't then the word doesn't exist
				return null;
			}

		// Get the next node in the sequence by searching for the current character in
		// the hash table
		currentNode = currentNode.search(targetWord.charAt(0));
		return this.nodeSearch(targetWord.substring(1), wordLength, currentNode);
	}

	// Helper function for search
	public boolean search(String targetWord) {
		return this.search(targetWord, targetWord.length(), this.root);
	}

	// Reads a dictionary file and adds all the words to the Trie
	public static void readDictionary(HashTrie myTrie, String filename) {
		try {
			File dictionary = new File(filename);
			Scanner input = new Scanner(dictionary);

			// Inserts the entire file into the Trie
			while (input.hasNextLine()) {
				myTrie.insert(input.nextLine().toLowerCase());
			}
			input.close(); // close the stream we have to the file

			// exception handling
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary file not found: ");
		}
	}

	// Reading through a text file, we update the importance of every word that
	// makes an appearance
	public static void setImportance(HashTrie myTrie, String filename) {
		try {
			File importanceFile = new File(filename);
			Scanner input = new Scanner(importanceFile);

			// reads through the entire file
			while (input.hasNextLine()) {
				String word = input.next().toLowerCase();

				// If the word doesn't contain any invalid characters.
				// If it contains any special characters we ignore it
				if (checkWordValidity(word)) {
					// we find the final node of the new word we're looking for
					HashTrieNode finalNode = myTrie.nodeSearch(word, word.length(), myTrie.root);
					if (finalNode != null) {
						// increment the importance of the final node by 1
						finalNode.incrementImportance(word.charAt(word.length() - 1));
					}
				}
			}
			input.close(); // close the stream

		} catch (FileNotFoundException e) { // exception handling
			System.out.println("Dictionary file not found: ");
		}
	}

	// Check if the word contains any special characters.
	private static boolean checkWordValidity(String word) {
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (c < 'a' && c > 'z' && c < 'A' && c > 'Z') {
				return false;
			}
		}
		return true;
	}

	// Fills the MinHeap with words that start with the given prefix
	private void findKWithPrefix(String prefix, MinHeap myHeap) {
		HashTrieNode current = this.root.startsWith(prefix);
		if (current == null)
			return;

		current.collectWithPrefix(current, prefix, myHeap);

	}

	// Fills the MinHeap with words that have the same length as the given target
	// word
	private void findKWithSameLength(String targetWord, MinHeap myHeap) {

		if (this.root == null)
			return;

		this.root.collectWithSameLength(this.root, "", targetWord, myHeap);
	}

	// Fills the MinHeap with words that has a similar length as the given target
	// word.
	private void findKWithSimilarLength(String targetWord, MinHeap myHeap) {
		if (this.root == null)
			return;

		this.root.collectWithSimilarLength(this.root, "", targetWord, myHeap);
	}

	// Uses all the functions above to collect all the words w e're looking for
	public void findKWords(String prefix, MinHeap myHeap) {
		this.findKWithPrefix(prefix, myHeap);

		this.findKWithSameLength(prefix, myHeap);

		this.findKWithSimilarLength(prefix, myHeap);

		myHeap.printHeap();
	}

	// calculates the total theoretical memory the Trie is using up recursively
	public static int memCalc(HashTrieNode current) {
		if (current == null || current.hashTable == null)
			return 0;

		int memory = 12; // integers of our RobinHoodHash object
		memory += current.hashTable.table.length * 13; // variables in each Element object (1 character & 3 integers)

		memory += current.hashTable.table.length * 4; // Size of the reference to the next HashTrieNode (next)

		// for each child of the Trie, we initiate a self call
		for (int i = 0; i < current.hashTable.table.length; i++) {
			if (current.hashTable.table[i] != null)
				memory += memCalc(current.hashTable.table[i].getNext());
		}

		return memory;
	}

	// EXECUTABLE FUNCTION
	public static void main(String[] args) {
		HashTrie myHashTrie = new HashTrie();

		System.out.print("Reading dictionary from arguments");
		readDictionary(myHashTrie, args[0]);

		try {
			Thread.sleep(500);
			System.out.print(".");
			Thread.sleep(500);
			System.out.print(".");
			Thread.sleep(500);
			System.out.print(".");
		} catch (Exception e) {

		}
		System.out.print("Done!\nReading text from arguments");
		setImportance(myHashTrie, args[1]);

		try {
			Thread.sleep(500);
			System.out.print(".");
			Thread.sleep(500);
			System.out.print(".");
			Thread.sleep(500);
			System.out.print(".");
		} catch (Exception e) {

		}
		System.out.println("Done!\n");
		String input = "";
		Scanner scan = new Scanner(System.in);
		System.out.println("             HashTrie");
		do {

			System.out.println("\nEnter your prefix and k, or ~~ to exit");

			input = scan.next();

			if (input.equals("~~"))
				break;

			try {
				int k = scan.nextInt();
				MinHeap myHeap = new MinHeap(k);
				myHashTrie.findKWords(input, myHeap);

			} catch (InputMismatchException e) {
				System.out.println("k should be an integer!");
				break;
			}

		} while (!input.equals("~~"));

		System.out.println("-----------------------------------");

		System.out.println("EXITED");

		scan.close();
	}

}

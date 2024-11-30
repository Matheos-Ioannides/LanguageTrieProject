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


	private HashTrieNode nodeSearch(String targetWord, int wordLength, HashTrieNode currentNode) {

		if (currentNode == null)
			return null; // if the currentNode is null, that means the key was not found

		if (targetWord.length() == 1)
			if (currentNode.getWordLength() == wordLength
					&& currentNode.search(targetWord.charAt(0), wordLength) != null) {
				return currentNode;
			} else {
				return null;
			}

		currentNode = currentNode.search(targetWord.charAt(0), wordLength);
		return this.nodeSearch(targetWord.substring(1), wordLength, currentNode);
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


  
  
  
  
  
  
  
	public static void setImportance(HashTrie myTrie, String filename){
		try {
			File importanceFile = new File(filename);
			Scanner input = new Scanner(importanceFile);

			while (input.hasNextLine()) {
				String word = input.next().toLowerCase();
				if(checkWordValidity(word))	{
					HashTrieNode finalNode = myTrie.nodeSearch(word, word.length(), myTrie.root);		
					if(finalNode != null){
						finalNode.incrementImportance();
					}
				}
			}
			input.close();

		} catch (FileNotFoundException e) {
			System.out.println("Dictionary file not found: ");
		}
	}

	private static boolean checkWordValidity(String word){
		for(int i = 0; i < word.length(); i++){
			char c = word.charAt(i);
			if(c < 'a' && c > 'z' && c < 'A' && c > 'Z'){
				return false;
			}
		}
		return true;
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

	}

}

package LanguageTrieProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Random;

public class WordGenerator {

	private static String generateRandomString(int length) {

		StringBuilder word = new StringBuilder();
		Random rng = new Random();

		while (word.length() < length) {
			int characterIndex = (int) (rng.nextFloat() * 26);
			word.append((char) ('a' + characterIndex));

		}

		return word.toString();
	}

	private static void generateDictionary(int numOfWords) {
		String filename = "staticDictionary100000.txt"; // hardcoded file name

		try {
			FileWriter printer = new FileWriter(filename);

			for (int i = 0; i < numOfWords; i++) {
				printer.write(generateRandomString(5) + "\n"); // hardcodeded word length
			}
			printer.close();

		} catch (FileNotFoundException e) {
			System.out.println("Dictionary file not found: ");
		} catch (Exception e) {
			System.out.println("Printer Stream could not be closed ");
		}

	}

	private static String generateRandomLognormalString(double mean, double stddeviation) {
		StringBuilder word = new StringBuilder();
		Random rng = new Random();

		double y = mean + stddeviation * rng.nextGaussian();
		int length = (int) Math.pow(Math.E, y);

		while (word.length() < length) {
			int characterIndex = (int) (rng.nextFloat() * 26);
			word.append((char) ('a' + characterIndex));

		}

		return word.toString();
	}

	private static void generateLognormalDictionary(int numOfWords) {
		String filename = "distributionDictionary" + numOfWords + "_test5.txt"; // hardcoded file name

		double mean = 2; // hardcoded mean and standard deviation
		double stddeviation = 0.6;

		try {
			FileWriter printer = new FileWriter(filename);

			for (int i = 0; i < numOfWords; i++) {
				printer.write(generateRandomLognormalString(mean, stddeviation) + "\n");
			}
			printer.close();

		} catch (FileNotFoundException e) {
			System.out.println("Dictionary file not found: ");
		} catch (Exception e) {
			System.out.println("Printer Stream could not be closed ");
		}

	}

	public static void main(String[] args) {

		Trie myTrie = new Trie();
		HashTrie myHashTrie = new HashTrie();

		Trie.readDictionary(myTrie,
				"D:\\Folders\\UNI STUFF\\WinterSemester24-25\\EPL231\\Project\\LanguageTrie\\src\\LanguageTrieProject\\TextFiles\\Distribution Dictionaries\\Test5\\distributionDictionary1000_test5.txt");
		HashTrie.readDictionary(myHashTrie,
				"D:\\Folders\\UNI STUFF\\WinterSemester24-25\\EPL231\\Project\\LanguageTrie\\src\\LanguageTrieProject\\TextFiles\\Distribution Dictionaries\\Test5\\distributionDictionary1000_test5.txt");

		System.out.println("Trie memory: " + myTrie.memCalc(myTrie.root));
		System.out.println("HashTrie memory: " + myHashTrie.memCalc(myHashTrie.root));

		myTrie = new Trie();
		myHashTrie = new HashTrie();

		Trie.readDictionary(myTrie,
				"D:\\Folders\\UNI STUFF\\WinterSemester24-25\\EPL231\\Project\\LanguageTrie\\src\\LanguageTrieProject\\TextFiles\\Distribution Dictionaries\\Test5\\distributionDictionary5000_test5.txt");
		HashTrie.readDictionary(myHashTrie,
				"D:\\Folders\\UNI STUFF\\WinterSemester24-25\\EPL231\\Project\\LanguageTrie\\src\\LanguageTrieProject\\TextFiles\\Distribution Dictionaries\\Test5\\distributionDictionary5000_test5.txt");

		System.out.println("\n\nTrie memory: " + myTrie.memCalc(myTrie.root));
		System.out.println("HashTrie memory: " + myHashTrie.memCalc(myHashTrie.root));
		
		myTrie = new Trie();
		myHashTrie = new HashTrie();

		Trie.readDictionary(myTrie,
				"D:\\Folders\\UNI STUFF\\WinterSemester24-25\\EPL231\\Project\\LanguageTrie\\src\\LanguageTrieProject\\TextFiles\\Distribution Dictionaries\\Test5\\distributionDictionary10000_test5.txt");
		HashTrie.readDictionary(myHashTrie,
				"D:\\Folders\\UNI STUFF\\WinterSemester24-25\\EPL231\\Project\\LanguageTrie\\src\\LanguageTrieProject\\TextFiles\\Distribution Dictionaries\\Test5\\distributionDictionary10000_test5.txt");

		System.out.println("\n\nTrie memory: " + myTrie.memCalc(myTrie.root));
		System.out.println("HashTrie memory: " + myHashTrie.memCalc(myHashTrie.root));
		
		myTrie = new Trie();
		myHashTrie = new HashTrie();

		Trie.readDictionary(myTrie,
				"D:\\Folders\\UNI STUFF\\WinterSemester24-25\\EPL231\\Project\\LanguageTrie\\src\\LanguageTrieProject\\TextFiles\\Distribution Dictionaries\\Test5\\distributionDictionary100000_test5.txt");
		HashTrie.readDictionary(myHashTrie,
				"D:\\Folders\\UNI STUFF\\WinterSemester24-25\\EPL231\\Project\\LanguageTrie\\src\\LanguageTrieProject\\TextFiles\\Distribution Dictionaries\\Test5\\distributionDictionary100000_test5.txt");

		System.out.println("\n\nTrie memory: " + myTrie.memCalc(myTrie.root));
		System.out.println("HashTrie memory: " + myHashTrie.memCalc(myHashTrie.root));
	}

}

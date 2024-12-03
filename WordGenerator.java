package LanguageTrieProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Random;

/**
 * Extra function we used to generate random Strings, to eventually calculate
 * the memory of our heaps
 */
public class WordGenerator {

	// Generates a random String of characters.
	private static String generateRandomString(int length) {

		StringBuilder word = new StringBuilder();
		Random rng = new Random();

		while (word.length() < length) {
			int characterIndex = (int) (rng.nextFloat() * 26); // picks a number 1 -26
			word.append((char) ('a' + characterIndex)); // and appends the corresponding lowercase character

		}

		return word.toString();
	}

	// Generate a given number of words with a static word length
	private static void generateDictionary(int numOfWords) {
		String filename = "staticDictionary" + numOfWords + "_test1.txt"; // hardcoded file name

		try {
			FileWriter printer = new FileWriter(filename);

			for (int i = 0; i < numOfWords; i++) {
				printer.write(generateRandomString(17) + "\n"); // hardcodeded word length
			}
			printer.close();

		} catch (FileNotFoundException e) {
			System.out.println("Dictionary file not found: ");
		} catch (Exception e) {
			System.out.println("Printer Stream could not be closed ");
		}

	}

	// Generate a given number of words with a word length that follows a lognormal
	// distribution
	private static String generateRandomLognormalString(double mean, double stddeviation) {
		StringBuilder word = new StringBuilder();
		Random rng = new Random();

		// calculates the length of the word using the distribution
		double y = mean + stddeviation * rng.nextGaussian();
		int length = (int) Math.pow(Math.E, y);

		while (word.length() < length) {
			int characterIndex = (int) (rng.nextFloat() * 26); // picks a number 1 -26
			word.append((char) ('a' + characterIndex)); // and appends the corresponding lowercase character

		}

		return word.toString();
	}

	// Generate a given number of words with word lengths following a lognormal
	// distribution
	private static void generateLognormalDictionary(int numOfWords) {
		String filename = "distributionDictionary" + numOfWords + "_test1.txt"; // hardcoded file name

		double mean = 1.5; // hardcoded mean and standard deviation
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

	// Driver function used to calculate the memory of each dictionary generated
	// with this class
	public static void main(String[] args) {

//		for(int i=5000; i<=100000; i+=5000) {
//			generateDictionary(i);
//		}

//		System.out.println("Trie memory:");
		for (int i = 5; i <= 100; i += 5) {
			Trie myTrie = new Trie();
			HashTrie myHashTrie = new HashTrie();

			Trie.readDictionary(myTrie,
					"D:\\Folders\\UNI STUFF\\WinterSemester24-25\\EPL231\\Project\\LanguageTrie\\src\\LanguageTrieProject\\TextFiles\\Distribution Dictionaries\\Test5\\distributionDictionary"
							+ i + "000_test1.txt");
			HashTrie.readDictionary(myHashTrie,
					"D:\\Folders\\UNI STUFF\\WinterSemester24-25\\EPL231\\Project\\LanguageTrie\\src\\LanguageTrieProject\\TextFiles\\Distribution Dictionaries\\Test5\\distributionDictionary"
							+ i + "000_test1.txt");

			System.out.print(myTrie.memCalc(myTrie.root) + " ");

		}

//		System.out.println("\nHashTrie memory:");
		System.out.println();
		for (int i = 5; i <= 100; i += 5) {
			Trie myTrie = new Trie();
			HashTrie myHashTrie = new HashTrie();

			Trie.readDictionary(myTrie,
					"D:\\Folders\\UNI STUFF\\WinterSemester24-25\\EPL231\\Project\\LanguageTrie\\src\\LanguageTrieProject\\TextFiles\\Distribution Dictionaries\\Test5\\distributionDictionary"
							+ i + "000_test1.txt");
			HashTrie.readDictionary(myHashTrie,
					"D:\\Folders\\UNI STUFF\\WinterSemester24-25\\EPL231\\Project\\LanguageTrie\\src\\LanguageTrieProject\\TextFiles\\Distribution Dictionaries\\Test5\\distributionDictionary"
							+ i + "000_test1.txt");

			System.out.print(myHashTrie.memCalc(myHashTrie.root) + " ");

		}
	}

}

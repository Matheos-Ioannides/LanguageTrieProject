package LanguageTrieProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
			File dictionary = new File(filename);
			FileWriter printer = new FileWriter(filename);

			for(int i=0; i<numOfWords; i++) {
				printer.write(generateRandomString(5) + "\n"); //hardcodeded word length
			}
			printer.close();

		} catch (FileNotFoundException e) {
			System.out.println("Dictionary file not found: ");
		} catch (Exception e) {
			System.out.println("Printer Stream could not be closed ");
		}
	
	}

	public static void main(String[] args) {

		generateDictionary(100000);
	}

}

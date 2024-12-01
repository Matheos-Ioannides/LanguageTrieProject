package LanguageTrieProject;

import java.util.Random;

public class WordGenerator {
	private static String generateRandomString(int length) {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		StringBuilder word = new StringBuilder();
		Random rng = new Random();

		int desiredWordLength = 5;
		while (word.length() < desiredWordLength) {
			int alphabetIndex = (int) (rng.nextFloat() * alphabet.length());

			word.append(alphabet.charAt(alphabetIndex));

		}

		return word.toString();
	}

	public static void main(String[] args) {

		for(int i=0; i<10; i++)
			System.out.println(generateRandomString(10)); 
	}

}

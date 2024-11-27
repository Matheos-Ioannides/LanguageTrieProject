package LanguageTrieProject;
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
		TrieNode p= this.root;
		this.insert(newWord, newWord.length(), p);
	}

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
		TrieNode p= this.root;
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
	
	public static void main(String[] args) {
		Trie myTree = new Trie();
		
		myTree.insert("app");
		myTree.insert("cat");
		myTree.insert("catnip");
		myTree.insert("car");
		myTree.insert("carnip");
		myTree.insert("likeabigwordaswell");
		myTree.insert("necoarc");
		myTree.insert("burenyan");

		
		System.out.println("app included? " + myTree.find("app"));
		System.out.println("cat included? " + myTree.find("cat"));
		System.out.println("catnip included? " + myTree.find("catnip"));
		System.out.println("car included? " + myTree.find("car"));
		System.out.println("carnip included? " + myTree.find("carnip"));
		System.out.println("likeabigwordaswell included? " + myTree.find("likeabigwordaswell"));
		System.out.println("necoarc included? " + myTree.find("necoarc"));
		System.out.println("burenyan included? " + myTree.find("burenyan"));
		
		System.out.println("\n\nburunyan included? " + myTree.find("burunyan"));
	

	}

}

package LanguageTrieProject;

public class HashTrieNode {

	RobinHoodHash hashTable;
	private int wordLength;
	private int importance;

	public HashTrieNode() {
		this.hashTable = new RobinHoodHash();
		this.wordLength = -1;
		this.importance = 0;
	}

	//Only takes lowercase characters, fix later 
	public void insert(char newChar){
		boolean charExists=false;
		
		for(int i=0; i<this.hashTable.table.length; i++) 
			if(this.hashTable.table[i].getKey() == newChar)
				charExists=true;
				
//		if(!charExists) {
//			
//		}
		
		
	}
}

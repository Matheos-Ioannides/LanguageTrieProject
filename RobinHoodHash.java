package LanguageTrieProject;

public class RobinHoodHash {
	
	char elements[];
	int maxProbeLength;
	int size;
	int capacity;
	

	public RobinHoodHash() {
		this(7);
	}
	
	public RobinHoodHash(int capacity) {
		
		this.size=0;
		this.capacity=capacity;
		this.maxProbeLength=0;
		
		elements = new char[this.capacity];
	}
	
	//this will NOT work!!
	public static void main(String[] args) {
		
		
	}

}

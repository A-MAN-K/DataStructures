package ds;

// The X represents the key generic type and Y represents the value generic type
public class BasicHashTable<X, Y> {

	private HashEntry[] data;
	private int capacity; // It is different from the size and it tells that how big the hash table is
	private int size;
	
	
	
	// Constructor for BasicHashTable to determine its size	
	public BasicHashTable(int tableSize) {
		this.capacity = tableSize;
		this.data = new HashEntry[this.capacity];
		this.size = 0;
	}
	
	// It accepts a key (X)  and gives away a Y value
	public Y get(X key) {
		int hash = calculateHash(key);
		
		// if dont have anything for the given key, just return null
		
		if(data[hash] == null) {
			return null;
		}
		else {
			return (Y) data[hash].getValue();
		}
		
	}
	
	// implementation of Put method
	
	public void put(X key, Y value) {
		
		int hash = calculateHash(key);
		
		data[hash] = new HashEntry<X, Y>(key, value);
		size++;		
	}
	
	//Implementation of the delete method in the hash table
	// BIG(O) notation for this method is little trivial, because of rehashing.
	// In the best case scenarios it will be linear. But in the worst case scenarios it will quadratic.
	// We may have to perform the rehashing for whole the loop.
	
	public Y delete(X key) {
		// First get the value for this key and return the remove 
		
		Y value = get(key);
		
		// Clear out the hash slot for key and return the removed value
		
		if(value != null) {
			int hash = calculateHash(key);
			data[hash] = null;
			size--;
			hash = (hash + 1) % this.capacity;
		
		
			// If the next slot isn't empty we should readd it so we can keep the algorithm clean
			while(data[hash] != null) {
				HashEntry he = data[hash];
				data[hash] = null;
				
				System.out.println("Rehashing: " + he.getKey() + " - " + he.getValue());
				
				put((X)he.getKey(), (Y)he.getValue());
				
				// We just repositioned the hash item and didn't really add a new one so back off the size
				
				size--;
				hash = (hash + 1) % this.capacity;
		
			}
		}
		
			return value;
	}
	
	// Implementation of has key method
	public boolean hasKey(X key) {
		
		int hash = calculateHash(key); // Calculate hash method helps us to avoid the loop and BIG O is constant time thus
		
		// If we don't have anything for the key we can just return the false
		
		if(data[hash] == null) {
			return false;
		}
		else {
			if(data[hash].getKey().equals(key)) {
				return true;
			}
			
		}
		return false;
	}
	
	//Implementation of has Value method 
	// Since we can't use the calculateHash function we will have to iterate through all the values
	// Big(O) notation for this is linear.
	public boolean hasValue(Y value) { 
		
		// Can't iterate over the size because capacity will look into each slot available
		for(int i=0; i < this.capacity; i++) {
			
			HashEntry entry = data[i];
			
			// If the hash value is in the hash table return true else return false
			
			if(entry != null && entry.getValue().equals(value)) {
				
				return true;				
			}
			
		}
		return false;
		
	}
	
	
	
	
		
	//Size method that will tell how many hash keys and values the hash table has in it
	public int size() {
		return this.size;
	}
	
	// Calculate the hash value. It takes the incoming key and gets the hash code of it.
	private int calculateHash(X key) {
		
		int hash = (key.hashCode() % this.capacity);
		
		//this is necessary to deal with the collisions
		while( data[hash] != null && !data[hash].getKey().equals(key)) {
			hash = (hash + 1) % this.capacity;
		}
		return hash;
	}
	
	
	
	
	private class HashEntry<X, Y> {
		
		private X key;
		private Y value;
		
		public HashEntry(X key, Y value) {
			this.key = key;
			this.value = value;
		}
		
		
		public void setKey(X key) {
			this.key = key;
		}
		public X getKey() {
			return key;
		}
		
		public void setValue(Y value) {
			this.value = value;
		}
		
		public Y getValue() {
			return value;
		}
	}
	
}

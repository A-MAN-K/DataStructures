package ds;

public class BasicStack<X> {

	private X [] data;
	private int stackPointer;
	
	public BasicStack() {
		
		data = (X []) new Object[1000];
		stackPointer = 0;
	}
	
	// PUSH
	public void push(X newItem) {
		data[stackPointer++] = newItem;
		
	}
	
	// POP
	public X pop() {
		//Underflow condition
		if(stackPointer == 0) {
			throw new IllegalStateException("No more items on the stack");
		}
		
		return data[--stackPointer];
	}
	
	//SEARCH
	public boolean contains(X item) {
		
		boolean found = false;
		
		for( int i = 0; i < stackPointer; i++) {
			if(data[i].equals(item)) {
				found = true;
				break;
			}
		}
		return found;
	} 
	
	//ACCESS
	public X access(X item) {
		while(stackPointer > 0) {
			X tmpItem = pop();
			if(item.equals(tmpItem)) {
				return tmpItem;
			}
		}
		
		// If we don't find the item
		throw new IllegalArgumentException("Could not find the item on the Stack: "+ item);
	}
	
	
	// Return the size of the stack
	
	public int size() {
		return stackPointer;
	}
	
}

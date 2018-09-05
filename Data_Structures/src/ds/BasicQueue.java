package ds;

public class BasicQueue<X> {

	private X[] data;
	private int front;
	private int end;
	
	public BasicQueue() {
		this(1000);
	}
	
	public BasicQueue(int size) {
		this.front = -1; // To show that the queue is empty
		this.end = -1;   // To show that the queue is empty
		data = (X[])new Object[size];
		
	}
	
	public int size() {
		// if the queue is empty return 0
		
		if( front == -1 && end == -1) {
			return 0;
		}
		else {
			// one is added to consider the inclusive maths.
			return end - front +1;
		}
	}
	
	public void enQueue(X item) {
		// first see that the queue is full		
		if((end + 1)% data.length == front) {
			
			throw new IllegalStateException("The queue is already full");
		}
		
		// check that whether queue is empty
		else if (size() == 0) {
			front++;
			end++;
			data[end] = item;
		}
		else {
			end++;
			data[end] = item;
		}
	}
	
	public X deQueue() {
		
		X item = null;
		// If the queue is empty
		if(size() == 0) {
			
			throw new IllegalStateException("NO element in the queue");
		}
		// If there is only the last item in the queue
		else if(front == end) { 
			item = data[front];
			data[front] = null; // If we are considering the garbage collection
			front = -1;
			end = -1;
		}
		// All other cases
		else {
			item = data[front];
			data[front] = null;
			front++;
		}
		return item;
	}
	
	//contains method
	public boolean contains(X item) {
		boolean found = false;
		
		// if the queue is empty
		if(size() == 0) {
			return found;
		}
		else {
			
			for(int i= front; i< end; i++) {
				if(data[front].equals(item)) {
					found = true;
					break;
				}
					
			}
			
		}
		
		
		return found;
	}
	
	//access method
	
	public X access(int position) {
		
		
		if(size() == 0 || position > size()) {
			throw new IllegalArgumentException("No items in the queue or the position is grater than the size of the queue");
		}
		
		int trueIndex = 0;
		for(int i = front; i < end; i++) {
			if(trueIndex == position) {
				return data[i];
			}
			trueIndex++;
		}
		  	// if you didn't find the item throw an exception
			throw new IllegalArgumentException("Could not get queue item at position: "+ position);
		}
		
		
	}



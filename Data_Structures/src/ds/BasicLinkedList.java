package ds;

public class BasicLinkedList<X> {

	private Node first;
	private Node last;
	private int nodeCount;
	
	public BasicLinkedList() {
		first = null;
		last = null;
		nodeCount = 0;
	}
	
	public void add(X item) {

		// if we are adding something for the first time
		
		if(first == null) {
			first = new Node(item);
			last = first;
		}
		
		// otherwise, we want to grab the last node and update it's value.
		else {
			
			Node newLastNode = new Node(item);
			last.setNextNode(newLastNode);
			last = newLastNode;
		}
		nodeCount++;
	}
	
	public X remove() {
		
		if(first == null) {
			throw new IllegalStateException("There is no item in the list to be removed");
		}
		
		X nodeItem = first.getNodeItem();
		
		// now update the first pointer and throw away the old first
		
		first = first.getNextNode();
		nodeCount-- ;
		return nodeItem; 
	}
	
	//Insert method implementation
	
	public void insert(X item, int position) {
		if(size() < position) {
			throw new IllegalStateException("The linked list is smaller than the position you are inserting and element");
		}
		
		Node currentNode = first;
		
		//start at 1 because we are already on the first node.
		
		for(int i=1; i< position && currentNode != null; i++) {
			currentNode = currentNode.getNextNode();
		}
		Node newNode = new Node(item);
		Node nextNode = currentNode.getNextNode();
		currentNode.setNextNode(newNode);
		newNode.setNextNode(nextNode);
		nodeCount++;
		
	}
	
	
	// Remove at method
	
	public X removeAt(int position) {
		
		if(first == null) {
			throw new IllegalStateException("The LinkedList is empty and there are no items to remove");
		}
		
		Node currentNode = first;
		Node prevNode = first;
		
		
		//Start at one because we already on the first Node
		
		for(int i=1; i < position && currentNode != null; i++) {
			prevNode = currentNode;
			currentNode = currentNode.getNextNode();
		}
		
		// now update the pointers and throw away the old first
		
		X nodeItem = currentNode.getNodeItem();
		prevNode.setNextNode(currentNode.getNextNode());
		nodeCount--;
		return nodeItem;
	}
	
	// Get Method Implementation
	
	public X get(int position) {
		 
		if(first == null) {
			throw new IllegalStateException("The list is empty there are no element to search in the list");
		}
		
		Node currentNode = first;
		
		for(int i=0; i < size() && currentNode != null; i++) {
			if(i == position) {
				return currentNode.getNodeItem();
			}
			
			currentNode = currentNode.getNextNode();
			
		}
		
		// If we don't find any item
		return null;
	}
	
	// Method to find an element in the list. In this we pass the item to the list to check whether it is there or not.
	public int find(X item) {
		
		if(first == null) {
			throw new IllegalStateException("The List is empty, There are no elements on the list ");
		}
		
		Node currentNode = first;
		
		for(int i=0; i < size() && currentNode != null; i++) {
			
			if(currentNode.getNodeItem().equals(item)) {
				return i;
			}
			
			currentNode = currentNode.getNextNode();
		}
		
		// If element is not found on the list.
		return -1;
	}
	
	
	// Pretty Printing the Linked List
	
	public String toString() {
		
		StringBuffer contents = new StringBuffer();
		Node currNode = first;
		
		while(currNode != null) {
			contents.append(currNode.getNodeItem());
			currNode = currNode.getNextNode();
			
			if(currNode != null) {
				contents.append(", ");
			}
		}
		
		return contents.toString();
	}
	
	
	
	public int size() {
		return nodeCount;
	}
	
	// Class for the Node
	private class Node {
		
		private Node nextNode;
		private X nodeItem;
		
		// Constructor for the Node Class
		public Node(X item) {
			this.nextNode = null;
			this.nodeItem = item;
		}
		
		public void setNextNode(Node nextNode) {
			this.nextNode = nextNode;
		}
		
		public Node getNextNode() {
			return nextNode;
		}
		
		public void setNodeItem(X item) {
			this.nodeItem = item;
		}
		

		public X getNodeItem() {
			return nodeItem;
		}
		
	}
}

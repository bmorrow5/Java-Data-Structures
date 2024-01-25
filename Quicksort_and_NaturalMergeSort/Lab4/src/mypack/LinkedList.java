package mypack;

public class LinkedList {

	public class Node {

		Node next;
		Node previous;
		int value;

		public Node(int value) {
			this.value = value;
			this.next = null;
			this.previous = null;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}//End Node class()

	//Begin LinkedList Class
	Node head;
	Node tail;
	int listSize;
	
	public LinkedList(){
		Node head;
		Node tail;
	}
	
	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}
	
	public void add(int value) {

		Node temp = new Node(value);
		if (head == null) {
			head = temp;
			tail = temp;
		} else {
			tail.next = temp;
			temp.previous = tail;
			tail = temp;
			tail.next = null;
		}
		listSize++;
	}
	
	public Node getHead() {
		return head; 
	}

	public void setHead(Node head) {
		this.head = head;
	}

	public Node getTail() {
		return tail; 
	}
	
	public int getHeadValue() {
		return head.value;
	}

	public int getTailValue() {
		return tail.value;
	}

	public void printList() {

		Node temp = head;
		while (temp != null) {
			System.out.println(temp.value);
			temp = temp.next;
		}
	}

}

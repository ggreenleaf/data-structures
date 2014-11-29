//Geoffrey Greenleaf
//Project 1
//GenLinkedList.java
//September 27 2014


import java.util.List;
import java.util.ArrayList;



public class GenLinkedList <T>  {
	/*======================================
	=    members specific to list         =
	======================================*/
	/*=============================================
	=     Node Definition (element of List)      =
	=============================================*/
	private static class Node <T> {	
		/**
		* constructor for node with T type 
		* param d - val for storage
		* param n - node will point to
		**/
		public Node(T val, Node<T> n) {
			data = val;
			next = n;
		}
		public T data;
		public Node<T> next;
	
	}
	private Node<T> head;
	private Node<T> tail;
	private Integer length; 
	public Integer length() {return length;}
	/*=======================================
	=           List Methods               =
	=======================================*/
	/**
	*
	* Constructor clear list and set head, tail to node with null data 
	*
	**/
	public GenLinkedList () {
		head = null;
		tail = null;
		length = 0;	
	}
	private void checkBounds (Integer i) { 
		if (i < 0 || i >= length)
			throw new IndexOutOfBoundsException("for index, " + i);
	}
	/**
	*
	* Push an Item to end of list
	*
	**/
	public void addEnd(T val) {
		Node<T> newNode = new Node<>(val, null);
		//if tail is null first element added will be head and tail
		if (tail == null) {
			head = newNode;
		}
		else {
			tail.next = newNode;
		}
		tail = newNode;
		length++;
	}
	/**
	*
	* add item with val to front of list
	*
	**/
	public void addFront(T val) {
		Node<T> newNode = new Node<>(val, head);
		//if head is null no object is in the list
		//set newNode is now head/tail
		if (head == null) {
			tail = newNode;
		}
		head = newNode;
		length++;
	}
	/**
	*
	* remove front node from list
	*	possibly add later value for return of data removed. 
	**/	
	public T removeFront() {
		T d = head.data; 
		head = head.next;
		length--;

	return d;
	}
	/**
	*
	* remove from tail of list
	*	possible add later value for return of data removed. 
	**/
	public T removeEnd() {
		Node<T> p = head;
		T d = null;
		//transverse list until p.next is tail 
		//then remove tail and set currents to tail
		while(p.next != tail) 
			p = p.next;
		
		d = tail.data;
		tail = p;
		tail.next = null; 
		length--;
	
	return d;
	}
	/**
	*
	* set value T at index 
	*
	**/
	public void set(T value, int index ) {
		checkBounds(index);
		Node<T> ptr = head;
		while(index-- != 0) {
			ptr = ptr.next;
		}
		ptr.data = value;	
	}
	/**
	*
	* get value at index
	*
	**/
	public T get (int index) {
		checkBounds(index);
		
		Node<T> ptr = head;
			while(index-- != 0) {
				ptr = ptr.next;
			}

		return ptr.data;

	}
	/**
	*
	* convert list to string for printing
	*
	**/	
	public String toString( )	{
        StringBuilder sb = new StringBuilder( "[" );
        Node<T> ptr = head;
    	while(ptr != null) {

    		sb.append(ptr.data + " ");
    		ptr = ptr.next;
    	}
        ptr = null;	
        sb.append( "]" );
        return new String( sb );
    }

    /*
    *
    *Handles the actual swapping of nodes 
    *
    */
   	private void swap(Node<T> p1, Node<T> p2, Node<T> p3, Node<T> p4) {
   			Node<T> temp = null;

   			if (p2 == p3) {    //Nodes are next to each other
   				if (p2 == head) { //Nodes are next toeacher and p2 is head
	   				temp = p2;	
	   				head.next = p4;
	   				temp.next = p4.next;
	   				p4.next = temp;
					head = p4; 		
   				}
   				else {
	   				temp = p2;
		   			p1.next = p4;
		   			temp.next = p4.next;
		   			p4.next = temp;
	   			}
   			}
   			else {
	   			if (p2 == head) {
	   				temp = p4.next;		
	   				p4.next = p2.next;
	   				p2.next = temp;
	   				p3.next = p2;
					head = p4;   			
   				}
   				else {
	   			//swap the nodes nexts
	   			temp = p4.next; //temp node set to SecondIndex.next
				p4.next = p2.next; //secondIndex.next = firstIndex.next
				p2.next = temp;	//firstIndex.next = SecondIndex.next
				//swaps the nodes previous nexts
				p1.next = p4; //firstIndex Previous.next = SecondIndex;
				p3.next = p2; //secondIndex Previous.next = firstIndex;
   				}
   			}
   	}
    /**
    *
    * public swap nodes at index firstIndex, two positions 
    *	provided the index of the two positions are valid
    **/
   	public void swap (Integer firstIndex, Integer secondIndex) {
   		//swap integers positions if firstIndext>secondIndex
   		//will only require one walkthrough

   		checkBounds(firstIndex); //throws indexOutofBounds
   		checkBounds(secondIndex); 
   		
   		if (firstIndex > secondIndex) {
   			Integer temp = firstIndex;
			firstIndex = secondIndex; //swap index so firstIndex is always smaller
			secondIndex = temp;
		}
		//Getting the nodes for swapping
			Node<T> p1 = null; //previous of fNode
			Node<T> p2 = head; //first index to swap with sNode
			Node<T> p3 = null; //previous of sNode
			Node<T> p4 = head; //second index to swap with fNode

			Integer distance = secondIndex - firstIndex;
			boolean foundFirstIndex = false;
			while(secondIndex > 0 ) {
				if (secondIndex == distance)  //found firstIndex
					foundFirstIndex = true;
				
				if (!foundFirstIndex) {
					p1 = p2;
					p2 = p2.next;
				}

				p3 = p4;
				p4 = p4.next;

				secondIndex--;
			}

			//actual swapping nodes
			swap(p1,p2,p3,p4);
   	}


   	//In class!
	public void reverse() {
			Node<T> ptr1 = null;
			Node<T> ptr2 = head;
			Node<T> ptr3 = head.next;

			while (ptr3 != null) {
				ptr2.next = ptr1;
				ptr1 = ptr2;
				ptr2 = ptr3;
				ptr3 = ptr3.next;
			}
			ptr2.next = ptr1; //patch last node
			tail = head; 
			head = ptr2;
	} 
	//removes node at prev.next
	private void remove(Node<T> prev,Node<T> node) { 
		//point prev next to the one after p.next
		if (node == head)  //if prev is null then element removed is head
			head = node.next;
		else
			prev.next = node.next;
		length--;
		//node goes out of scope and is GC
	}


	//remove all occurances of T
	public void removeMatching(T val) {
		Node<T> p = head;
		Node<T> prev = null;

		while (p != null) {
			if (p.data == val) {
				if (p == head) 
					head = head.next;
				if (p == tail)
					tail = prev;

				prev.next = p.next;
				p = prev.next;
				length--;
			}
			else {
				prev = p;
				p = p.next;
			}

		}

	}
	//remove elements starting at index to numElements
	//if Numelemts + index > length remove all at index+ and make prev tail
	public void erase(Integer index, Integer numElements) {

		checkBounds(index);

		Node<T> p = head;
		Node<T> prev = null;
		//transveres to index
		while (index-- != 0) {
			prev = p;
			p = p.next;
		}
		// if (p == head)

		Node<T> temp = prev;
		while(numElements-- != 0 ) {
			if (temp == null)
				break;

			temp = temp.next;
			length--;
		}
		if (temp == null) {
			prev.next = null;
			tail = prev;
		}
		else
			prev.next = temp.next;
	}

	// insert new node between prev and p
	private void insert(Node<T> prev, Node<T> p, T val) {
		Node<T> newNode = new Node<>(val, p);
		prev.next = newNode;
		length++;
	}

	public void insertList (Integer index, List<T> list ) {
		checkBounds(index);
		Node<T> p = head;
		Node<T> prev = null;
		//move to index
		while (index -- != 0) {
			 	prev = p;
			 	p = p.next;
			 }

		if (p == head) {
			for (Integer i = list.size() - 1; i >= 0; i--) 
				addFront(list.get(i));			
		}
		else if (p == tail) {
			for (Integer i = 0; i < list.size(); i++)
				addEnd(list.get(i));
		}
		else {
			 for (T e : list) {
			 	insert(prev, p, e);
			 	prev = prev.next;
			 }
		}
	}

	public void shift(Integer n) {
		// -n % 3 should return a positve value
		n = Math.floorMod(n,length); // needed to get postive answer from mod
		while (n-- != 0) {
			addEnd(head.data);
			removeFront();
		}


	}

public static void main (String[] args) 
{
	GenLinkedList<Integer> myList = new GenLinkedList<>();
	System.out.println("before addFront: "+ myList.toString());
	for (Integer i=5; i>=0; i-- )
		myList.addFront(i);
	System.out.println("before addBack: "+ myList.toString());
	for (Integer i=6; i <= 10; i++)
		myList.addEnd(i);
	System.out.println("after addBack: "+ myList.toString());
	// Get index 7
	System.out.println("get(7): " + myList.get(7));
	/**
	*
	* Testing Swap
	*	Swap the 0 and 6 element 
	* 	also test swap with String Type
	**/
	//swapping head with any element
	System.out.println("before swap(0,2): " + myList.toString());
	myList.swap(0,2);
	System.out.println("after swap(0,2): " + myList.toString());
	//swapping head with head.next
	System.out.println("before swap(0,1): " + myList.toString());
	myList.swap(0,1);
	System.out.println("after swap(0,1): " + myList.toString());
	//swapping any element with e.next
	System.out.println("before swap(4,5): " + myList.toString());
	myList.swap(4,5);
	System.out.println("after swap(4,5): " + myList.toString());
	//swapping any elements
	System.out.println("before swap(4,6): " + myList.toString());
	myList.swap(4,6);
	System.out.println("after swap(4,6): " + myList.toString());
	//setting 100 at index 10
	System.out.println("before set(100,10): " + myList.toString());
	myList.set(100,10);
	System.out.println("after set(100,10): " + myList.toString());
	//removing from front
	System.out.println("removing front: "+ myList.removeFront());
	System.out.println("after removeFront: " + myList.toString());
	//removing from end
	System.out.println("removing End: " + myList.removeEnd());
	myList.removeEnd();
	System.out.println("after removeEnd: " + myList.toString());

	//removing all instance of T

	myList.set(5,1);
	myList.set(5,3);
	myList.set(5,4);

	System.out.println("before remove all 5: "+ myList.toString());
	myList.removeMatching(5);
	System.out.println("after remove: " + myList.toString());
	//erase starting at Index 3, 3 elements 
	myList.erase(2,2);
	System.out.println("after erase(2,1): "+myList.toString());

	List<Integer> iList = new ArrayList<>();
	for(int i=0; i<5; i++)
		iList.add(100+i);
	
	myList.insertList(1,iList);
	System.out.println("after inserList: "+myList.toString());
	myList.insertList(myList.length-1,iList);
	System.out.println("after inserList: "+myList.toString());

	//new List for shifting
	myList = new GenLinkedList<>();
	for (Integer i=5; i>=0; i-- )
		myList.addFront(i);

	System.out.println("before shift(3): " + myList.toString());
	myList.shift(3);
	System.out.println("after shift(3): " + myList.toString());
	myList.shift(-3);
	System.out.println("shift(-3) : " + myList.toString());
}	

}		





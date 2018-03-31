/**
 * COMP 410
 *See inline comment descriptions for methods not described in interface.
 *
*/
package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
	Node sentinel; // this will be the entry point to your linked list (the head)

	public LinkedListImpl() {// this constructor is needed for testing purposes. Please don't modify!
		sentinel = new Node(0); // Note that the root's data is not a true part of your data set!
	}

	// implement all methods in interface, and include the getRoot method we made
	// for testing purposes. Feel free to implement private helper methods!

	public Node getRoot() { // leave this method as is, used by the grader to grab your linkedList easily.
		return sentinel;
	}

	@Override
	public boolean insert(double elt, int index) {
		// TODO Auto-generated method stub
		Node n = new Node(elt);
		Node s = sentinel;

		if (index < 0 || index > size()) {
			return false;
		} else {
			if (s.next == null) {
				s.next = n;
				n.next = s;
				s.prev = n;
				n.prev = s;

			} else {
				for (int i = 0; i <= index; i++) {
					s = s.next;
				}
				n.prev = s.prev;
				n.next = s;
				n.prev.next = n;
				n.next.prev = n;
			}
			return true;
		}
	}

	@Override
	public boolean remove(int index) {
		// TODO Auto-generated method stub

		Node p = sentinel.next;
		Node n = new Node(0);

		if (index < 0 || index > size()) {
			return false;
		}

		else {
			if (p.next == sentinel) {
				sentinel.next = null;
			} else {
				p = sentinel;
				for (int i = 0; i <= index; i++) {
					p = p.next;
				}

				if (p != null) {
					n = p.prev;
					n.next = p.next;
					p.next.prev = n;
				}

			}
			return true;
		}

	}

	@Override
	public double get(int index) {
		// TODO Auto-generated method stub
		Node p = sentinel;
		if (p.next != null) {
			for (int i = 0; i <= index; i++)
				p = p.next;
			return p.data;
		} else
			return Double.NaN;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		int count = 0;
		Node s = sentinel.next;

		if (s != null) {
			while (s != sentinel) {
				count++;
				s = s.next;
			}
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		Node s = sentinel.next;

		if (s == null)
			return true;
		else
			return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		sentinel.next = null;
		sentinel.prev = sentinel;
	}
}
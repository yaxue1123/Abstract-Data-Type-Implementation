package BST_A2;

public class BST_Node {
	String data;
	BST_Node left;
	BST_Node right;

	BST_Node(String data) {
		this.data = data;
	}

	// --- used for testing ----------------------------------------------
	//
	// leave these 3 methods in, as is

	public String getData() {
		return data;
	}

	public BST_Node getLeft() {
		return left;
	}

	public BST_Node getRight() {
		return right;
	}

	// --- end used for testing -------------------------------------------

	// --- fill in these methods ------------------------------------------
	//
	// at the moment, they are stubs returning false
	// or some appropriate "fake" value
	//
	// you make them work properly
	// add the meat of correct implementation logic to them

	// you MAY change the signatures if you wish...
	// make the take more or different parameters
	// have them return different types
	//
	// you may use recursive or iterative implementations

	public boolean containsNode(BST_Node node, String s) {
		if (node == null)
			return false;
		// Use recursion to find whether a BST contains a String

		if (node.data.compareTo(s) == 0)
			return true;
		else {
			if (s.compareTo(node.data) < 0)
				return containsNode(node.left, s);
			else
				return containsNode(node.right, s);
		}

	}

	// insert Node
	public BST_Node insertNode(BST_Node node, String s) {
		// to check whether the element is already in the tree, if so, return null
		if (containsNode(node, s) == true)
			return null;

		// if the node is null, then create a node whose data is s and assign it to
		// node.left/right
		if (node == null)
			return new BST_Node(s);
		// Do the recursion until find the parent node for new node
		if (s.compareTo(node.data) < 0)
			node.left = insertNode(node.left, s);
		else if (s.compareTo(node.data) > 0)
			node.right = insertNode(node.right, s);
		return node;

	}

	public BST_Node removeNode(BST_Node node, String s) {
		if (node != null) {
			if (s.compareTo(node.data) < 0)
				node.left = removeNode(node.left, s);
			else if (s.compareTo(node.data) > 0)
				node.right = removeNode(node.right, s);
			// Then we find the target node
			else
			// The most important part: three conditions of removing
			{
				// if the element is a leaf node or has one child
				if (node.left == null)
					return node.right; // if node.left is null, use right to replace it
				else if (node.right == null)
					return node.left; // if node.right is null, use left to replace it
				// if the element has two children
				else {
					node.data = findMin(node.right).data; // to replace the data with the smallest data of the right
															// subtree
					node.right = removeNode(node.right, node.data);// recursively remove the min node
				}

			}
			return node;
		}
		return null;

	}

	public BST_Node findMin(BST_Node node) {
	
		if (node != null) {
			while (node.left != null)
				node = node.left;

			return node;
		} 
		else
			return null;
	}

	public BST_Node findMax(BST_Node node) {
	
		if (node != null) {
			while (node.right != null)
				node = node.right;

			return node;
		}

		else
			return null;
	}

	public int getHeight(BST_Node node) {
		if (node == null)
			return -1;
		else
			return (1 + Math.max(getHeight(node.left), getHeight(node.right)));
	}

	// --- end fill in these methods --------------------------------------

	// --------------------------------------------------------------------
	// you may add any other methods you want to get the job done
	// --------------------------------------------------------------------

	public String toString() {
		return "Data: " + this.data + ", Left: " + ((this.left != null) ? left.data : "null") + ",Right: "
				+ ((this.right != null) ? right.data : "null");
	}
}
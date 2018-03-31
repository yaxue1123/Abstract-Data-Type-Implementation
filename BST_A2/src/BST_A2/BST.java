package BST_A2;

public class BST implements BST_Interface {
	public BST_Node root;
	int size;

	public BST() {
		size = 0;
		root = null;
	}

	@Override
	// used for testing, please leave as is
	public BST_Node getRoot() {
		return root;
	}

	@Override
	public boolean insert(String s) {
		// TODO Auto-generated method stub
		if (root == null) {
			BST_Node node = new BST_Node(s);
			root = node;
			return true;
		} else 
			return (root.insertNode(root, s) != null);
	

	}

	@Override
	public boolean remove(String s) {
		// TODO Auto-generated method stub
		if (root != null && root.containsNode(root, s) == true) {
			root = root.removeNode(root, s);
			return true;
		} else
			return false;
	}

	@Override
	public String findMin() {
		// TODO Auto-generated method stub
		return root.findMin(root).data;
	}

	@Override
	public String findMax() {
		// TODO Auto-generated method stub
		return root.findMax(root).data;
	}

	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		if (root == null)
			return true;
		else
			return false;
	}

	@Override
	public boolean contains(String s) {
		// TODO Auto-generated method stub
		return root.containsNode(root, s);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size(root);
	}

	int size(BST_Node node) {
		if (node == null)
			return 0;
		else
			return (size(node.left) + 1 + size(node.right));
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		if (root != null)
			return root.getHeight(root);
		else
			return 0;
	}

}
package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
	private EntryPair[] array; // load this array
	private int size; // why size??
	private static final int arraySize = 10000; // Everything in the array will initially
												// be null. This is ok! Just build out
												// from array[1]

	public MinBinHeap() {
		this.array = new EntryPair[arraySize];
		array[0] = new EntryPair(null, -100000); // 0th will be unused for simplicity
													// of child/parent computations...
													// the book/animation page both do this.
	}

	// Please do not remove or modify this method! Used to test your entire Heap.
	@Override
	public EntryPair[] getHeap() {
		return this.array;
	}

	@Override
	public void insert(EntryPair entry) {
		// TODO Auto-generated method stub
	
		size = size();
		int n = size + 1;
				
		while (n >= 2) {
			if (entry.priority > array[n/2].priority) // just insert.
			{
				array[n] = entry;
				break;
			} else // swap the parent with child, continue comparing.
			{
				array[n] = array[n/2];
				n = n/2;
			}
		}
		
		if(n==1)
			array[1] = entry;
		
	}

	@Override
	public void delMin() {
		// TODO Auto-generated method stub
		size = size();
		array[1] = null;
		EntryPair temp = array[size];
		array[size] = null;
		size--;
		
		if(size==1)
			array[1] = temp;
		
		int n = 1;
		while((2*n+1) <= size)
		{
			if(array[2*n].priority<array[2*n+1].priority)
			{
				if(temp.priority<array[2*n].priority)
				{
					array[n] = temp;
					break;
				}
				else
				{
					array[n] = array[2*n];
					array[2*n] = null; //make a hole
					if(4*n<=size)
						n = 2*n;
					else
						{
						array[2*n] = temp;
						break;
						}
				}
			}
			else
			{
				if(temp.priority<array[2*n+1].priority)
				{
					array[n] = temp;
					break;
				}
				else
				{
					array[n] = array[2*n+1];
					array[2*n+1] = null;
					if(2*(2*n+1)<=size)
						n = 2*n+1;
					else
						{
						array[2*n+1] = temp;
						break;
						}
				}
			}
		}
		
		
		if(array[n]==null&&2*n==size)
			if(array[2*n].priority>temp.priority)
				array[n]=temp;
			else
				{
				array[n] = array[2*n];
				array[2*n] = temp;
				}
		
		


	}

	@Override
	public EntryPair getMin() {
		// TODO Auto-generated method stub
		// The min of a binary heap tree is the root of it, and the big o is O(1).
		size = size();

		if (size != 0)
			return array[1];
		else
			return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		int s = 0;
		while (array[s + 1] != null)
			s++;

		return s;
	}

	@Override
	public void build(EntryPair[] entries) {
		// TODO Auto-generated method stub

		for (int i = 0; i < entries.length; i++)
			array[i + 1] = entries[i]; // initialize the binary heap tree, notice that array starts from [1] while
										// entries starts from [0];

		// From down to top
		int n = size() / 2;
		int s = size();
		EntryPair temp = null; // define a variable for swap
		while (n >= 1) // compare parent with two children and make the smallest of the three be the new parent
		{
			if ((2 * n + 1) <= s) {
				if (array[2 * n].priority < array[2 * n + 1].priority) {
					if (array[2 * n].priority < array[n].priority) // swap
					{
						temp = array[2 * n];
						array[2 * n] = array[n];
						array[n] = temp;
					}
				} else // two children's comparison
				{
					if (array[2 * n + 1].priority < array[n].priority) // swap
					{
						temp = array[2 * n + 1];
						array[2 * n + 1] = array[n];
						array[n] = temp;
					}
				}
			} else if (array[2 * n].priority < array[n].priority) // swap
			{
				temp = array[2 * n];
				array[2 * n] = array[n];
				array[n] = temp;
			}

			n--;
		}

		// From top to down
		n = 2; // starts from node 2 because the comparison with root and two children has been
				// done in "down to top" process and the root is the min of the tree
		while (n <= s/2) // compare parent with two children and make the smallest of the three be the
								// new parent, and bubble-down
		{
			if ((2 * n + 1) <= s) // The situation need be categorized as the parent has/ has no right child,
										// because it is related to that the expression of 2*n+1 is valid or not.
			{
				if (array[2 * n].priority < array[2 * n + 1].priority) {
					if (array[2 * n].priority < array[n].priority) // swap
					{
						temp = array[2 * n];
						array[2 * n] = array[n];
						array[n] = temp;
					}
				} else // two children's comparison
				{
					if (array[2 * n + 1].priority < array[n].priority) // swap
					{
						temp = array[2 * n + 1];
						array[2 * n + 1] = array[n];
						array[n] = temp;
					}
				}
			} else if (array[2 * n].priority < array[n].priority) // swap
			{
				temp = array[2 * n];
				array[2 * n] = array[n];
				array[n] = temp;
			}
			n++;
		}

	}

}
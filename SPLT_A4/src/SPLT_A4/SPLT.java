package SPLT_A4;

public class SPLT implements SPLT_Interface {
  public BST_Node root;
  int size;
  
  public SPLT(){ size=0; root=null; }
  
  @Override
  //used for testing, please leave as is
  public BST_Node getRoot(){ return root;}
  
  public boolean insert(String s) {
	  if(s==null)
		  return false;
	  else 
	  {
		  BST_Node newNode = new BST_Node(s);	  
		  if(empty()==true)
		  {
			  root = newNode;
			  root.flag=2;//root 
			  return true;		  
		  }
		  else 
		  {	  
			  if( contains(s)==true)
				  return false;
			  else
			  {
				  root=root.insertNode(root,s);
				  root.parent=null;
				  root.flag=2;
				  return true;
			  }
		  }
	  }
  }
  
  public boolean remove(String s){
	  if(s==null)
		  return false;
	  else 
	  {  
		  if(empty()==true)
			  return false;
		  else //not empty
		  {	  
			  if( contains(s)!=true)
				  return false;
			  else
			  {				 
				  if(root.left!=null)
				  {
					  BST_Node Ltree = root.left;		
					  Ltree.parent=null;
					  Ltree.flag=2;
					  if(root.right!=null)
					  {
						  BST_Node Rtree = root.right;	
						  Rtree.parent=null;
						  Rtree.flag=2;
						  root=null;
						  root=Ltree.findMax(Ltree);
						  root.right=Rtree;
				          Rtree.parent=root;	  
						  Rtree.flag=0;
						  return true;		
					  }
					  else//root.right=null
					  {
						  root=Ltree.findMax(Ltree);
						  return true;
					  }
				  }
				  else //root.left=null
				  {	
					  if(root.right!=null)
					  {
					  	  BST_Node Rtree = root.right;	
					      Rtree.parent=null;
					      Rtree.flag=2;	
					      root=Rtree;
						  return true;		
					  }
					  else//left=null && right=null, just root
					  {
						  root=null;
					      return true;
					  }
				  }
					  
			  }
		  }		  
	  }
  }
  
  public String findMin()   
  {
	 if(empty())
		 return null;
	 else
	 {
		 root=root.findMin(root);
		 return root.data;
	 }
  }
  
  public String findMax()   
  {
	 if(empty())
		 return null;
	 else 
	 {
		 root=root.findMax(root);
		 return root.data;
	 }
  }
 
  public boolean empty()
  {
	  if(root==null)
		  return true;
	  else
		  return false;
  }
  
  public boolean contains(String s)
  {
	  if(s==null)
		  return false;
	  else 
	  {
		  if(empty())
			  return false;
		  else
		  {
			  BST_Node newNode = new BST_Node(s);
			  root=root.containsNode(newNode, root);
			  if (root.data==s)
				  return true;
			  else
				  return false;
		  } 
	  }
  }
  
  public int size()
  {
	  if(empty())
		  return 0;
	  else 	  
		  return root.getSize(root);
  }
  
  public int height()
  {
	  if(empty())
		  return -1;
	  else
	      return root.getHeight(root)-1;
  }
  

  
 
}
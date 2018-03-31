package SPLT_A4;

public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  BST_Node parent;
  int flag;//indicate whether a left child(1) or a right child(0) or a root(2)
  

  
  BST_Node(String data){ this.data=data; this.flag=3;}

  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }
  public BST_Node getParent(){ return parent; }
  public int getFlag(){ return flag; }

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

  
  public BST_Node containsNode(BST_Node newNode,BST_Node orgNode)
  {
		  int res = newNode.data.compareTo(orgNode.data);
		  if(res==0)
		  {
			  splay(orgNode);
			  return orgNode;
		  }
		  else if(res>0)//bigger, go right
		  {
			  if(orgNode.right==null)
			  {
				  splay(orgNode);
				  return orgNode;
			  }
			  else	
				  return containsNode(newNode,orgNode.right);
		  }
		  else//smaller, go left 
		  {
			  if(orgNode.left==null)
			  {
				  splay(orgNode);
				  return orgNode;
			  }
			  else	
				  return containsNode(newNode,orgNode.left); 
		  }
  }
  
  public BST_Node findNode(BST_Node root,String s)
  {
	  if(root==null)
		  return null;
	  else
	  {
		  int res = s.compareTo(root.data);
		  if(res==0)
			  return root;
		  else if(res>0)//bigger, go right
			  return findNode(root.right,s);
		  else//smaller, go left 
			  return findNode(root.left,s); 
	  }
  }
  
  
  public BST_Node insertNode(BST_Node root,String s)
  { 
	  int res = s.compareTo(root.data);
	  BST_Node newNode = new BST_Node(s);
	  if(res<0)
	  {
		  if(root.left!=null)
			 return insertNode(root.left,s);
		  else 
		  {
			  root.left=newNode;	
			  newNode.parent=root;
			  newNode.flag=1;//left child
			  //spaly newNode
			  splay(newNode);
		  }
	  }
	  else
	  {
		  if(root.right!=null)
			 return insertNode(root.right,s);
		  else 
		  {
			  root.right=newNode;	
			  newNode.parent=root;
			  newNode.flag=0;//right child
			  //splay newNode	
			  splay(newNode);
		  }
	  }	
	  return newNode;
  }
  
  public boolean removeNode(BST_Node root,String s)//not useful in this project
  {
	  BST_Node remNode = new BST_Node(null);
	  BST_Node minNode = new BST_Node(null);
	  remNode = findNode(root,s);
	  if(remNode.left==null&&remNode.right==null)//leaf
	  {
		  if(remNode.flag==1)//is a left child
			  remNode.parent.left=null;
		  else if(remNode.flag==0)//is a right child
			  remNode.parent.right=null;
		  return true;
	  }
	  if(remNode.left!=null&&remNode.right!=null)//has two child
	  {
		  minNode=findMin(remNode.right);
		  if(remNode.flag==1)
			  remNode.parent.left.data=minNode.data;
		  else if(remNode.flag==0)		  
			  remNode.parent.right.data=minNode.data;
		  else//remove root
			  remNode.data=minNode.data;
	      
		  //remove minNode
		  if(minNode.flag==1)//is a left child
		  {
			  if(minNode.right!=null)
			  {	  
				  minNode.right.flag=1;
				  minNode.parent.left=minNode.right;
				  minNode.right.parent=minNode.parent;
			  }
			  else
				  minNode.parent.left=null;	
		  }
		  else
		  {
			  if(minNode.right!=null)
			  {
				  minNode.parent.right=minNode.right;
				  minNode.right.parent=minNode.parent;
			  }
			  else
				  minNode.parent.right=null;
		  }
		  
		  return true;		  
	  }
	  else//has one child
	  {
		  if(remNode.flag==1)//is a left child
		  {
			  if(remNode.left!=null)//has left child
			  {
				  remNode.parent.left=remNode.left;
			  	  remNode.left.parent=remNode.parent;
			  }
			  else//has right child
			  {
				  remNode.right.flag=1;
				  remNode.parent.left=remNode.right;
			      remNode.right.parent=remNode.parent;
			  }
		  }
		  else if(remNode.flag==0)//is a right child
		  {
			  if(remNode.left!=null)//has left child
			  {
				  remNode.left.flag=0;
				  remNode.parent.right=remNode.left;
			      remNode.left.parent=remNode.parent;
			  }
			  else//has right child
			  {
				  remNode.parent.right=remNode.right;
				  remNode.right.parent=remNode.parent;
			  }
		  } 	  
		  return true; 
	  }
	 
  }
  
  public BST_Node findMin(BST_Node min)
  {
	  while(min.left!=null)
		  min = min.left;  
	  splay(min);
	  return min; 
  }
  
  public BST_Node findMax(BST_Node max)
  {  
	  while(max.right!=null)
		  max = max.right;
	  splay(max);
	  return max; 
  }
  

  public int getSize(BST_Node node)
  {
	  if(node!=null)
		  return getSize(node.left)+getSize(node.right)+1;
	  else
		  return 0;
  }
  
  public int Max(int x,int y)
  {
	  if (x>y)
		  return x;
	  else
		  return y;
  }
  
  public int getHeight(BST_Node node)
  {
	  if (node!=null) {
		  int left_h = getHeight(node.left);
		  int right_h = getHeight(node.right);
		  return Max(left_h,right_h)+1;
	  }	    
	  else
		  return 0;
  }
  
  private void splay(BST_Node toSplay)//sorry for writing it too long:( I just don't want to use the recursion
  {
	  BST_Node tmp = new BST_Node(null);
	  BST_Node tmp2 = new BST_Node(null);
	  int flag=0;
      while(toSplay.parent!=null)//splay until it is root
      {
	  if(toSplay.parent!=null)
	  {
		  if(toSplay.parent.parent==null)//has parent, no grandparent, zig
		  {
			  if(toSplay.flag==0)//is a right child, zig R
				  RotateL(toSplay);
			  else//is a left child, zig L
				  RotateR(toSplay);
			  toSplay.parent=null;
			  toSplay.flag=2;      
		      break; 
		  }
		  else//has grandparent, zigzig or zigzag
		  {
			  if(toSplay.flag==toSplay.parent.flag)//zigzig
			  {
				  if(toSplay.flag==0)//zigzig R
				  {
					  if(toSplay.parent.parent.parent==null)
					  {
						  RotateL(toSplay.parent);
						//  toSplay.parent.flag=1;
					  	  RotateL(toSplay);
					  	  toSplay.parent=null;
						  toSplay.flag=2;   
					  	  break;
					  }
					  else 
					  {						  
						  tmp=toSplay.parent.parent.parent;
						  flag=toSplay.parent.parent.flag;
						  RotateL(toSplay.parent);
						 // toSplay.parent.flag=1;
						  RotateL(toSplay);
						  if(flag==0)
							  tmp.right=toSplay;
						  else
							  tmp.left=toSplay;
						  toSplay.parent=tmp;					  
					  }
				  }		  
				  else//zigzig L
				  {
					  if(toSplay.parent.parent.parent==null)
					  {
						  RotateR(toSplay.parent);
						 // toSplay.parent.flag=0;
					  	  RotateR(toSplay);
					  	  toSplay.parent=null;
						  toSplay.flag=2;   
					  	  break;
					  }
					  else 
					  {						  
						  tmp=toSplay.parent.parent.parent;
						  flag=toSplay.parent.parent.flag;
						  RotateR(toSplay.parent);
						 // toSplay.parent.flag=0;
						  RotateR(toSplay);
						  if(flag==0)
							  tmp.right=toSplay;
						  else
							  tmp.left=toSplay;
						  toSplay.parent=tmp;					  
					  }
				  }
			  }//end zigzig		  
			  else//zig zag
			  {
				  if(toSplay.flag==0)//zigzag L
				  {
					  if(toSplay.parent.parent.parent==null)
					  {
						  tmp2=toSplay.parent.parent;
						  RotateL(toSplay);
					  	  toSplay.parent=tmp2;
					  	  tmp2.left=toSplay;
					  	  RotateR(toSplay);
					  	  toSplay.parent=null;
						  toSplay.flag=2;   
					  	  break;
					  }
					  else 
					  {						  
						  tmp=toSplay.parent.parent.parent;
						  tmp2=toSplay.parent.parent;
						  flag=tmp2.flag;
						  RotateL(toSplay);
					  	  toSplay.parent=tmp2;
					  	  tmp2.left=toSplay;
					  	  RotateR(toSplay);
						  if(flag==0)
						  {
							  tmp.right=toSplay;
							  toSplay.flag=0;
						  }
						  else
						  {
							  tmp.left=toSplay;
							  toSplay.flag=1;
						  }
						  toSplay.parent=tmp;					  
					  }
				  }	//end zigzag L		  
				  else//zigzag R
				  {
					  if(toSplay.parent.parent.parent==null)
					  {
						  tmp2=toSplay.parent.parent;
						  RotateR(toSplay);
					  	  toSplay.parent=tmp2;
					  	  tmp2.right=toSplay;
					  	  RotateL(toSplay);
						  toSplay.parent=null;
						  toSplay.flag=2;   
						  break;
					  }
					  else 
					  {						  
						  tmp=toSplay.parent.parent.parent;
						  tmp2=toSplay.parent.parent;
						  flag=tmp2.flag;
						  RotateR(toSplay);
					  	  toSplay.parent=tmp2;
					  	  tmp2.right=toSplay;
					  	  RotateL(toSplay);
						  if(flag==0)
						  {
							  tmp.right=toSplay;
							  toSplay.flag=0;
						  }
						  else
						  {
							  tmp.left=toSplay;
							  toSplay.flag=1;
						  }
						  toSplay.parent=tmp;					  
					  }
				  }//end zigzag R
			  }//end zigzag
		  }//end zigzig & zigzag
	  }//end one splay
	  }//end while determine whether it still need to splay

  }
  
  private void RotateL(BST_Node x)
  {
	  BST_Node p=x.parent;
	  if(x.left!=null)
	  {
		  p.right=x.left;
	  	  x.left.parent=p;
	      x.left.flag=0;//right child of new parent p
	  }
	  else
		  p.right=null;
	  x.left=p;
	  p.parent=x;
	  p.flag=1;//left child of new parent x
  }

  private void RotateR(BST_Node x)
  {
	  BST_Node p=x.parent;
	  if(x.right!=null)
	  {
		  p.left=x.right;
	  	  x.right.parent=p;
	      x.right.flag=1;//left child of new parent p
	  }
	  else
		  p.left=null;
	  x.right=p;
	  p.parent=x;
	  p.flag=0;//right child of new parent x
  }
  // --- end fill in these methods --------------------------------------


  // --------------------------------------------------------------------
  // you may add any other methods you want to get the job done
  // --------------------------------------------------------------------
  
  public String toString(){
    return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
            +",Right: "+((this.right!=null)?right.data:"null");
  }
}

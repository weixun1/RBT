/**
 * Lab 2: Debugging with Eclipse and Red Black Tree) <br />
 * The {@code RedBlackTree} class of integers only <br />
 * Reference: <a href="https://en.wikipedia.org/wiki/Red%E2%80%93black_tree">
 *              https://en.wikipedia.org/wiki/Red%E2%80%93black_tree
 *            </a>
 */



import java.util.*;

public class RedBlackTree {

    /**
     * Root node of the red black tree
     */
    private Node root = null;

    /**
     * Size of the tree
     */
    private int size = 0;

    private boolean found = false;

    /**
     * Search the tree to find if the value is contained
     * @param value     {@code int} the value to be checked
     * @return          {@code boolean} If contains, return {@code true}, otherwise return {@code false}
     */
    public boolean contains(int value) {
        // TODO: Lab 2 Part 2-1 -- find an integer from the tree
    	boolean check = search(root,value);
        return check;
    }

    // this function search for a value from the tree

    public boolean search(Node node, int value)
    {
    	if(node.value == null)
    	{
    		return false;
    	}
    	else
    	{

    		if(node.lChild.value == null && node.rChild.value == null)
    		{
    			return false;
    		}
    		else
    		{
    			if(node.value == value)
    			{
    				found = true;;
    			}
    			if(node.value > value)
    			{
    				search(node.lChild,value);
    			}
    			if(node.value < value)
    			{
    				search(node.rChild,value);
    			}
    		}
    	}
    	return found;
    }


    //  this function creates the red and black tree by inserting values in to the tree

    public void treebuilder(Node node, int value)
    {
    	if(node == null)
    	{
    		node = new Node(value);
    		node.color = false;
    		//node = color(node);

    		color(node);

    		root = node;
    		//return node;
    		return;
    	}
    	else
    	{
    		if(node.value >= value)
    		{
    			if(node.lChild.value == null)
    			{
    				node.lChild = new Node(value);
    				node.lChild.color = false;
    				node.lChild.parent = node;
    				//node.lChild = color(node.lChild);
    				color(node.lChild);
    				//return node;
    				return;
    			}
    			else
    			{
    				//node.lChild = treebuilder(node.lChild,value);
    				treebuilder(node.lChild,value);
    				//return node;
    				return;
    			}
    		}
    		else
    		{
    			if(node.rChild.value == null)
    			{

    				node.rChild = new Node(value);
    				node.rChild.color = false;
    				node.rChild.parent = node;

    				color(node.rChild);

    				//return node;
    				return;
    			}
    			else
    			{
    				//node.rChild = treebuilder(node.rChild,value);
    				treebuilder(node.rChild,value);
    				///return node;
    				return;
    			}
    		}
    	}
    }

    /**
     * Insert an integer to the tree
     * @param data      {@code int} New element to be inserted
     */
    public void insert(int value)
    {
        // TODO: Lab 2 Part 2-2 -- insert an integer into the tree
    	treebuilder(root,value);
    	//toString();





    }


    public void color(Node node)
    {
    	// set color to new nodes
    	// case 1
    	if(node.parent == null)
    	{
    		node.color = true;

    		//return node;
    		return;
    	}
    	// case 2
    	if(node.parent != null && node.parent.color == true)
    	{
    		node.color = false;
    		//return node;
    		return;
    	}





    	//case 3

    	if(node.parent != null)
    	{
    		if(node.parent.parent != null)
    		{
    			Node gpNode = node.parent.parent;
    			if(gpNode.lChild.color == false && gpNode.rChild.color == false)
    			{
    				gpNode.lChild.color = true;
    				gpNode.rChild.color = true;

    				color(gpNode);

    				return;

    			}

    		}
    	}

    	//case 4

    	if (node.parent != null)
    	{
    		if(node.parent.parent != null)
    		{
    			Node p = new Node(null),gp = new Node(null), uncle = new Node(null);
    			p = node.parent;
    			gp = node.parent.parent;
    			// determine uncle
    			if(p == gp.lChild)
    			{
    				uncle = gp.rChild;
    			}
    			else
    			{
    				uncle = gp.lChild;
    			}

    			// case 4 && 5
    			if(p.color == false && uncle.color == true)
    			{
    				// case 4
    				// left right
    				if(node == p.rChild && p == gp.lChild)
    				{
    					// rotate left


    					node.parent = gp;
    					gp.lChild = node;

    					p.parent = node;
    					node.lChild.parent = p;

    					p.rChild = node.lChild;


    					node.lChild = p;
    					node = gp.lChild.lChild;
    					p = gp.lChild;




    				}
    				// right left
    				if(node == p.lChild && p == gp.rChild)
    				{
    					// rotate right

    					node.parent = gp;
    					gp.rChild = node;

    					p.parent = node;
    					node.rChild.parent = p;


    					p.lChild = node.rChild;

    					node.rChild = p;
    					node = gp.rChild.rChild;
    					p = gp.rChild;



    				}

    				// case 5
    				if(p == gp.lChild && node == p.lChild)
    				{
    					gp = node.parent.parent;
    					p = node.parent;

    					gp.color = false;
    					gp.lChild.color = true; // parent



    					Node ggp = gp.parent;

    					if(ggp != null)
    					{
    						if(gp == ggp.lChild)
    						{
    							p.parent = ggp;
    							ggp.lChild = p;


    							p.rChild.parent = gp;
    							gp.lChild = p.rChild;

    							gp.parent = p;
    							p.rChild = gp;

    							node = p.lChild;

    							findRoot(node);


    						}
    						else if(gp == ggp.rChild)
    						{
    							p.parent = ggp;
    							ggp.rChild = p;


    							p.rChild.parent = gp;
    							gp.lChild = p.rChild;

    							gp.parent = p;
    							p.rChild = gp;

    							node = p.lChild;

    							findRoot(node);
    						}
    					}
    					else if(ggp == null)
    					{

        					p.parent = gp.parent;
        					gp.parent = p;

        					gp.lChild = p.rChild;
        					gp.lChild.parent = gp;
        					p.rChild = gp;

        					node = p.lChild;

        					if(p.parent == null)
        					{
        						root = p;
        						return;
        					}

    					}

    				}
    				if(p == gp.rChild && node == p.rChild)
    				{
    					/**/
    					node.parent.parent.color = false;
    					node.parent.color = true;

    					gp = node.parent.parent;
    					p = node.parent;

    					Node ggp = gp.parent;

    					if(ggp != null)
    					{
        					if(gp == ggp.lChild) // gp is on the left
        					{
        						p.parent = ggp;
        						ggp.lChild = p;


        						p.lChild.parent = gp;
        						gp.rChild = p.lChild;


        						gp.parent = p;
        						p.lChild = gp;

        						node = p.rChild;

        					}
        					else if(gp == ggp.rChild)//gp is on the right
        					{
        						p.parent = ggp;
        						ggp.rChild = p;

        						p.lChild.parent = gp;
        						gp.rChild = p.lChild;


        						gp.parent = p;
        						p.lChild = gp;

        						node = p.rChild;

        					}
    					}
    					else if (ggp == null)
    					{
        					gp = node.parent.parent;
        					p = node.parent;

        					gp.color = false;
        					gp.rChild.color = true;

        					p.parent = gp.parent;
        					gp.parent = p;

        					gp.rChild = p.lChild;

        					gp.rChild.parent = gp;
        					p.lChild = gp;
        					p.lChild.parent = p;

        					node = p.rChild;
        					node.parent = p;


        					if(p.parent == null)
        					{
        						root = p;

        						return;
        					}
    					}

    				}


    			}
    		}
    	}

    	//return node;
    	return;

    }



    // this function assigns the root of the tree
    public void findRoot(Node node)
    {
    	if(node.parent != null)
    	{
    		root = node.parent;
    		findRoot(root);
    	}
    }


    // This function calculates the size of the red and black tree
    public int sizeCalc(Node node, int counter)
    {

    	if(node == null)
    	{
    		return counter;
    	}
    	else
    	{
    		counter = counter + 1;

    		if(node.lChild.value == null && node.rChild.value == null)
    		{
    			return counter;
    		}
    		else
    		{
    			if(node.lChild.value != null)
    			{
    				counter = sizeCalc(node.lChild,counter);
    			}
    			if(node.rChild.value != null)
    			{
    				counter = sizeCalc(node.rChild,counter);
    			}
    		}
    	return counter;
    	}
}
    /**
     * Get the size of the tree
     * @return          {@code int} size of the tree
     */
    public int size()
    {
    	size = 0;
    	size = sizeCalc(root,size);
    	return size;
    }
    public void treeToString(Node node)
    {
    	Node lC,rC;
    	//root
    	if(node.parent == null)
    	{
    		System.out.println("["+node.value+"]");
    	}

		// left child
		if(node.lChild.value != null)
		{
			lC = node.lChild;
			if(node.lChild.color == true)
			{
				System.out.print("["+node.lChild.value+"]");
			}
			else
			{
				System.out.print("("+node.lChild.value+")");
			}

			treeToString(lC);
		}

		if(node.rChild.value != null)
		{
			rC = node.rChild;
			if(node.rChild.color == true)
			{
				System.out.print("["+node.rChild.value+"]");
			}
			else
			{
				System.out.print("("+node.rChild.value+")");
			}
			treeToString(rC);
		}


    }

    /**
     * Cast the tree into a string
     * @return          {@code String} Printed format of the tree
     */
    @Override public String toString() {
        // TODO: Lab 2 Part 2-3 -- print the tree, where each node contains both value and color
        // You can print it by in-order traversal
    	//treeToString(root);

    	treeToString(root);


        return null;
    }

    /**
     * Main entry
     * @param args      {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        RedBlackTree rbt = new RedBlackTree();

        int[] test = {199,10,70,12,143,46,108,76,91,100};




        int[] test1 = {86,162,160,189,93,175,129,146,33,95};// pass



        int[] test2 = {60,17,190,167,54,22,112,9,151,162};
        int[] test3 = {158,36,24,109,15,165,151};
        int[] test4 = {181,62,146,61,37,177,76,41,67,138};
        for (int i = 0; i < test.length; i++)
        {

            //rbt.insert((int) (Math.random() * 200));
        	//System.out.println((int)(Math.random()*200));


        	//int num = (int)(Math.random()*200);
           // rbt.insert(num);

        	//System.out.println(num);



        	rbt.insert(test[i]);
        }
        assert rbt.root.color == RedBlackTree.Node.BLACK;

        //System.out.print(rbt.size());
        rbt.toString();

        if(rbt.search(rbt.root, 86)== true)
        {
        	System.out.println("yes");
        }

        //System.out.println(rbt.root);           // This helps to figure out the tree structure
        //System.out.println(rbt);
    }

    /**
     * The {@code Node} class for {@code RedBlackTree}
     */
    private class Node {
        public static final boolean BLACK = true;
        public static final boolean RED = false;

        public Integer value;
        public boolean color = BLACK;
        public Node parent = null, lChild = null, rChild = null;

        public Node(Integer value) {             // By default, a new node is black with two NIL children
            this.value = value;
            if (value != null) {
                lChild = new Node(null);         // And the NIL children are both black
                lChild.parent = this;
                rChild = new Node(null);
                rChild.parent = this;
            }
        }

        /**
         * Print the tree node: red node wrapped by "<>"; black node by "[]"
         * @return          {@code String} The printed string of the tree node
         */
        @Override public String toString() {
            if (value == null)
                return "";
            return (color == RED) ? "<" + value + ">" : "[" + value + "]";
        }
    }




}

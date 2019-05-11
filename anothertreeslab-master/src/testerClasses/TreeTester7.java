package testerClasses;
import labUtils.Utils;
import treeClasses.LinkedBinaryTree;

public class TreeTester7 {
	public static void main(String[] args) { 
		LinkedBinaryTree<Integer> t = Utils.buildExampleTreeAsLinkedBinaryTree(); 

		// display content as a tree
		Utils.displayTree("The structure of the tree is: ", t);
		

		// the following displays those elements in nodes of t that have only one child
		ElementsIterator<Integer> iter = new ElementsIterator<>(t); 
		Utils.displayIter("Values in nodes having at most one child (in preorder).", iter);

		
		// the following removes those nodes from the tree t that have only one child
		iter = new ElementsIterator<>(t); 
		System.out.println("Removing values in nodes having at most one child."); 
		while (iter.hasNext()) {
			iter.next(); 
			iter.remove();
		}
		
		// display content of t after removing the nodes having only one child
		Utils.displayTree("The structure of the tree after removing nodes with only one child: ", t);
	}

}

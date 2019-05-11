package testerClasses;

import labUtils.Utils;
import treeClasses.LinkedTree;

public class TreeTester10 {
   public static void main(String[] args) { 
      LinkedTree<Character> t = Utils.buildTrieAsLinkedTree(); 

	// display content as a tree
	Utils.displayTree("The structure of the tree is: ", t);
		
	LevelsCountPairIterator<Character> iter =  new LevelsCountPairIterator<>(t);
	Utils.displayIter("Pairs (level, nodes at level): ", iter);
   }
}

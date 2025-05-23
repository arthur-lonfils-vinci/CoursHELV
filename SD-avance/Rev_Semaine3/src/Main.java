import java.util.*;

public class Main {
  public static void main(String[] args) {
    Tree l1 = new Tree(1);
    Tree l3 = new Tree(3);
    Tree l5 = new Tree(5);
    Tree l7 = new Tree(7);

    Tree t2 = new Tree(2, new Tree[]{l1, l3});
    Tree t6 = new Tree(6, new Tree[]{l7});

    Tree t4 = new Tree(4, new Tree[]{t2, l5, t6});
    Tree t5 = new Tree(3, new Tree[]{l5, t4});

    TreeDisplay.displayHeader("t4 tree");
    TreeDisplay.displayTree(t4);

    System.out.println("Number of leaves => " + Trees.nbrLeaves(t4));

    System.out.println("Number of nodes => " + Trees.nbrNode(t4));

    System.out.println("Minimum => " + Trees.min(t4));

    System.out.println("Sum => " + Trees.sum(t4));

    TreeDisplay.displayHeader("t4 tree");
    TreeDisplay.displayTree(t4);
    TreeDisplay.displayHeader("t5 tree");
    TreeDisplay.displayTree(t5);
    System.out.println("Is equal ? (t4 and t5) => " + Trees.equals(t4, t5));

    TreeDisplay.displayHeader("t4 tree");
    TreeDisplay.displayTree(t4);
    TreeDisplay.displayHeader("t4 tree");
    TreeDisplay.displayTree(t4);
    System.out.println("Is equal ? (t4 and t4) => " + Trees.equals(t4, t4));

    TreeDisplay.displayHeader("t4 tree");
    TreeDisplay.displayTree(t4);
    System.out.println("What is the depth of t4 => " + Trees.depth(t4));

    System.out.println();
    System.out.println();
    System.out.println("****************************************************************************************");
    System.out.println();
    System.out.println();

    Tree[] ls = Trees.flattenLeaves(t4);
    System.out.println("Les " + ls.length + " feuilles");
    int i = 0;
    while (i != ls.length) {
      System.out.println(ls[i].getValue());
      i++;
    }

    System.out.println("Path V1");
    Trees.pathV1(l7);

    System.out.println("Path V2");
    Trees.pathV2(l7);

    // Test cases for sameOne method
    TreeDisplay.displaySectionHeader("Testing sameOne method");

    // Create a separate tree for testing
    Tree separateL8 = new Tree(8);
    Tree separateL9 = new Tree(9);
    Tree separateT10 = new Tree(10, new Tree[]{separateL8, separateL9});

    TreeDisplay.displayHeader("Separate tree");
    TreeDisplay.displayTree(separateT10);

    // Test Case 1: Same nodes should return true
    boolean result1 = Trees.sameOne(l1, l1);
    TreeDisplay.displayTestResult("Test 1 - Same node (l1, l1)", result1, true, result1 == true);

    // Test Case 2: Different nodes in same tree should return true
    boolean result2 = Trees.sameOne(l1, l7);
    TreeDisplay.displayTestResult("Test 2 - Different nodes in same tree (l1, l7)", result2, true, result2 == true);

    // Test Case 3: Root and leaf in same tree should return true
    boolean result3 = Trees.sameOne(t4, l3);
    TreeDisplay.displayTestResult("Test 3 - Root and leaf in same tree (t4, l3)", result3, true, result3 == true);

    // Test Case 4: Nodes from different trees should return false
    boolean result4 = Trees.sameOne(l1, separateL8);
    TreeDisplay.displayTestResult("Test 4 - Nodes from different trees (l1, separateL8)", result4, false, result4 == false);

    // Test Case 5: Node from t4 tree and node from separate tree should return false
    boolean result5 = Trees.sameOne(t6, separateT10);
    TreeDisplay.displayTestResult("Test 5 - Different tree roots (t6, separateT10)", result5, false, result5 == false);

    // Test Case 6: Null tests
    boolean result6 = Trees.sameOne(null, l1);
    TreeDisplay.displayTestResult("Test 6 - Null and valid node (null, l1)", result6, false, result6 == false);

    boolean result7 = Trees.sameOne(l1, null);
    TreeDisplay.displayTestResult("Test 7 - Valid node and null (l1, null)", result7, false, result7 == false);

    boolean result8 = Trees.sameOne(null, null);
    TreeDisplay.displayTestResult("Test 8 - Both null (null, null)", result8, false, result8 == false);

    // Test Case 9: Internal nodes in same tree
    boolean result9 = Trees.sameOne(t2, t6);
    TreeDisplay.displayTestResult("Test 9 - Internal nodes in same tree (t2, t6)", result9, true, result9 == true);

    TreeDisplay.displaySeparator();
    System.out.println("All sameOne tests completed!");

    // Test cases for DFS and BFS traversal methods
    TreeDisplay.displaySectionHeader("Testing DFS and BFS traversal methods");

    // Create a more structured tree for better testing
    Tree testL11 = new Tree(11);
    Tree testL12 = new Tree(12);
    Tree testL13 = new Tree(13);
    Tree testL14 = new Tree(14);
    Tree testL15 = new Tree(15);

    Tree testT9 = new Tree(9, new Tree[]{testL11, testL12});
    Tree testT10 = new Tree(10, new Tree[]{testL13});
    Tree testT8 = new Tree(8, new Tree[]{testT9, testT10, testL14});
    Tree testRoot = new Tree(20, new Tree[]{testT8, testL15});

    TreeDisplay.displayHeader("Test tree for DFS/BFS");
    TreeDisplay.displayTree(testRoot);

    System.out.println("\n" + TreeDisplay.BOLD + "Expected DFS order (Pre-order): " + TreeDisplay.RESET + "20, 8, 9, 11, 12, 10, 13, 14, 15");
    System.out.print(TreeDisplay.CYAN + "Actual DFS output: " + TreeDisplay.RESET);
    Trees.dfsPrint(testRoot);

    System.out.println("\n" + TreeDisplay.BOLD + "Expected BFS order (Level-order): " + TreeDisplay.RESET + "20, 8, 15, 9, 10, 14, 11, 12, 13");
    System.out.print(TreeDisplay.CYAN + "Actual BFS output: " + TreeDisplay.RESET);
    Trees.bfsPrint(testRoot);

    TreeDisplay.displaySeparator();

    // Test with single node
    TreeDisplay.displayHeader("Single node test");
    Tree singleNode = new Tree(100);
    TreeDisplay.displayTree(singleNode);

    System.out.println(TreeDisplay.YELLOW + "DFS single node:" + TreeDisplay.RESET);
    Trees.dfsPrint(singleNode);

    System.out.println(TreeDisplay.YELLOW + "BFS single node:" + TreeDisplay.RESET);
    Trees.bfsPrint(singleNode);

    // Test with null (edge case)
    System.out.println(TreeDisplay.YELLOW + "\nTesting null tree:" + TreeDisplay.RESET);
    System.out.print("DFS null: ");
    Trees.dfsPrint(null);
    System.out.print("BFS null: ");
    Trees.bfsPrint(null);

    // Test with original t4 tree
    TreeDisplay.displayHeader("DFS/BFS on original t4 tree");
    TreeDisplay.displayTree(t4);

    System.out.println(TreeDisplay.YELLOW + "DFS traversal of t4:" + TreeDisplay.RESET);
    Trees.dfsPrint(t4);

    System.out.println(TreeDisplay.YELLOW + "BFS traversal of t4:" + TreeDisplay.RESET);
    Trees.bfsPrint(t4);

    TreeDisplay.displaySeparator();
    System.out.println("All DFS/BFS tests completed!");

    // Test cases for path printing methods
    TreeDisplay.displaySectionHeader("Testing Path Printing Methods");

    // Create a test tree for path testing
    Tree pathL16 = new Tree(16);
    Tree pathL17 = new Tree(17);
    Tree pathL18 = new Tree(18);
    Tree pathL19 = new Tree(19);
    Tree pathL20 = new Tree(20);

    Tree pathT14 = new Tree(14, new Tree[]{pathL16, pathL17});
    Tree pathT15 = new Tree(15, new Tree[]{pathL18});
    Tree pathT13 = new Tree(13, new Tree[]{pathT14, pathT15, pathL19});
    Tree pathRoot = new Tree(12, new Tree[]{pathT13, pathL20});

    TreeDisplay.displayHeader("Test tree for path methods");
    TreeDisplay.displayTree(pathRoot);

    // Test printPathV1 (recursive)
    TreeDisplay.displayHeader("Testing printPathV1 (recursive)");

    System.out.print("Path from root to leaf 16: ");
    Trees.printPathV1(pathL16);
    System.out.println(" (Expected: 12 -> 13 -> 14 -> 16)");

    System.out.print("Path from root to leaf 18: ");
    Trees.printPathV1(pathL18);
    System.out.println(" (Expected: 12 -> 13 -> 15 -> 18)");

    System.out.print("Path from root to root: ");
    Trees.printPathV1(pathRoot);
    System.out.println(" (Expected: 12)");

    System.out.print("Path from root to internal node 13: ");
    Trees.printPathV1(pathT13);
    System.out.println(" (Expected: 12 -> 13)");

    System.out.print("Path with null: ");
    Trees.printPathV1(null);
    System.out.println(" (Expected: nothing)");

    TreeDisplay.displaySeparator();

    // Test printPathV2 (non-recursive)
    TreeDisplay.displayHeader("Testing printPathV2 (non-recursive)");

    System.out.print("Path from root to leaf 17: ");
    Trees.printPathV2(pathL17);
    System.out.println(" (Expected: 12 -> 13 -> 14 -> 17)");

    System.out.print("Path from root to leaf 20: ");
    Trees.printPathV2(pathL20);
    System.out.println(" (Expected: 12 -> 20)");

    System.out.print("Path from root to root: ");
    Trees.printPathV2(pathRoot);
    System.out.println(" (Expected: 12)");

    System.out.print("Path from root to internal node 15: ");
    Trees.printPathV2(pathT15);
    System.out.println(" (Expected: 12 -> 13 -> 15)");

    System.out.print("Path with null: ");
    Trees.printPathV2(null);
    System.out.println(" (Expected: nothing)");

    TreeDisplay.displaySeparator();

    // Test printPathV3 (find path to value)
    TreeDisplay.displayHeader("Testing printPathV3 (find path to value)");

    System.out.print("Path to value 16: ");
    Trees.printPathV3(pathRoot, 16);
    System.out.println(" (Expected: 12 -> 13 -> 14 -> 16)");

    System.out.print("Path to value 18: ");
    Trees.printPathV3(pathRoot, 18);
    System.out.println(" (Expected: 12 -> 13 -> 15 -> 18)");

    System.out.print("Path to value 12 (root): ");
    Trees.printPathV3(pathRoot, 12);
    System.out.println(" (Expected: 12)");

    System.out.print("Path to value 13 (internal): ");
    Trees.printPathV3(pathRoot, 13);
    System.out.println(" (Expected: 12 -> 13)");

    System.out.print("Path to non-existent value 99: ");
    Trees.printPathV3(pathRoot, 99);
    System.out.println(" (Expected: Value 99 not found in tree)");

    System.out.print("Path in null tree: ");
    Trees.printPathV3(null, 5);
    System.out.println(" (Expected: Tree is null)");

    TreeDisplay.displaySeparator();

    // Test with original trees
    TreeDisplay.displayHeader("Testing with original t4 tree");
    TreeDisplay.displayTree(t4);

    System.out.print("printPathV1 to l7: ");
    Trees.printPathV1(l7);
    System.out.println(" (Expected: 4 -> 6 -> 7)");

    System.out.print("printPathV2 to l1: ");
    Trees.printPathV2(l1);
    System.out.println(" (Expected: 4 -> 2 -> 1)");

    System.out.print("printPathV3 to value 3: ");
    Trees.printPathV3(t4, 3);
    System.out.println(" (Expected: 4 -> 2 -> 3)");

    System.out.print("printPathV3 to value 5: ");
    Trees.printPathV3(t4, 5);
    System.out.println(" (Expected: 4 -> 5)");

    TreeDisplay.displaySeparator();
    System.out.println("All path printing tests completed!");
  }
}
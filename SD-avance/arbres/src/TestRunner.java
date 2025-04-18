/**
 * Handles running the tree tests based on the selected test type
 */
public class TestRunner {
  private Tree t4;
  private Tree t2;
  private Tree t6;
  private Tree l1;
  private Tree l3;
  private Tree l5;
  private Tree l7;
  private Tree s1, s2, s3, s4, s5, s6;

  /**
   * Constructor - initialize the tree structures
   */
  public TestRunner() {
    // Initialize the main test tree
    l1 = new Tree(1);
    l3 = new Tree(3);
    l5 = new Tree(5);
    l7 = new Tree(7);

    t2 = new Tree(2, new Tree[]{l1, l3});
    t6 = new Tree(6, new Tree[]{l7});

    t4 = new Tree(4, new Tree[]{t2, l5, t6});

    // Initialize the equality test trees
    s1 = new Tree(10);
    s2 = new Tree(20);
    s3 = new Tree(10, new Tree[]{s1, s2});
    s4 = new Tree(10);
    s5 = new Tree(20);
    s6 = new Tree(10, new Tree[]{s4, s5});
  }

  /**
   * Run the selected test
   *
   * @param testType The test type to run
   */
  public void runTest(TestMenu.TestType testType) {
    try {
      switch (testType) {
        case ALL:
          runAllTests();
          break;
        case DISPLAY:
          runDisplayTest();
          break;
        case NBR_LEAVES:
          runNbrLeavesTest();
          break;
        case NBR_NODE:
          runNbrNodeTest();
          break;
        case MIN:
          runMinTest();
          break;
        case FLATTEN:
          runFlattenTest();
          break;
        case PATH:
          runPathTests();
          break;
        case SUM:
          runSumTest();
          break;
        case EQUALS:
          runEqualsTest();
          break;
        case DEPTH:
          runDepthTest();
          break;
        case SAME_ONE:
          runSameOneTest();
          break;
        case DFS:
          runDFSTest();
          break;
        case BFS:
          runBFSTest();
          break;
        case ARRAY:
          runArrayTest();
          break;
        case LCA:
          runLCATest();
          break;
        case EX2_PATHS:
          runExercise2Tests();
          break;
        case EXIT:
          System.out.println(TreeDisplay.YELLOW + "Exiting program..." + TreeDisplay.RESET);
          break;
      }
    } catch (Exception e) {
      System.out.println(TreeDisplay.RED + "Error running test: " + e.getMessage() + TreeDisplay.RESET);
      e.printStackTrace();
    }

    // If not exiting, wait for key press before returning to menu
    if (testType != TestMenu.TestType.EXIT) {
      System.out.println("\n" + TreeDisplay.YELLOW + "Press Enter to return to menu..." + TreeDisplay.RESET);
      try {
        System.in.read();
        // Clear input buffer
        while (System.in.available() > 0) {
          System.in.read();
        }
      } catch (Exception e) {
        // Ignore
      }
    }
  }

  /**
   * Run all tests
   */
  private void runAllTests() {
    TreeDisplay.displayHeader("Running All Tests");

    // Exercise examples
    TreeDisplay.displaySectionHeader("EXAMPLE METHODS");
    runDisplayTest();
    runNbrLeavesTest();
    runFlattenTest();
    runPathTests();

    // Exercise 1
    TreeDisplay.displaySectionHeader("EXERCISE 1");
    runNbrNodeTest();
    runMinTest();
    runSumTest();
    runEqualsTest();
    runDepthTest();
    runSameOneTest();
    runDFSTest();
    runBFSTest();

    // Exercise 2
    TreeDisplay.displaySectionHeader("EXERCISE 2");
    runExercise2Tests();

    // Exercise 3
    TreeDisplay.displaySectionHeader("EXERCISE 3");
    runArrayTest();

    // Exercise 4
    TreeDisplay.displaySectionHeader("EXERCISE 4");
    runLCATest();

    TreeDisplay.displayHeader("All Tests Completed");
  }

  // =====================================
  // Example Methods Tests
  // =====================================

  /**
   * Run the tree display test
   */
  private void runDisplayTest() {
    TreeDisplay.displayHeader("Tree Display Test");
    TreeDisplay.displayTree(t4);
  }

  /**
   * Run the number of leaves test
   */
  private void runNbrLeavesTest() {
    TreeDisplay.displayHeader("Number of Leaves Test");
    int expectedLeaves = 4;
    int actualLeaves = Trees.nbrLeaves(t4);
    TreeDisplay.displayTestResult("Number of leaves", actualLeaves, expectedLeaves, actualLeaves == expectedLeaves);
  }

  /**
   * Run the flatten leaves test
   */
  private void runFlattenTest() {
    TreeDisplay.displayHeader("Flatten Leaves Test");

    Tree[] ls = Trees.flattenLeaves(t4);
    int expectedLeaves = 4;
    boolean leavesOK = ls.length == expectedLeaves;

    TreeDisplay.displayTestResult("Flatten leaves length", ls.length, expectedLeaves, leavesOK);

    System.out.println(TreeDisplay.BOLD + "\nLeaves content:" + TreeDisplay.RESET);
    int[] expectedLeafValues = {1, 3, 5, 7};
    boolean leafValuesOK = true;

    for (int i = 0; i < ls.length; i++) {
      boolean leafOK = ls[i].getValue() == expectedLeafValues[i];
      TreeDisplay.displayTestResult("Leaf " + i, ls[i].getValue(), expectedLeafValues[i], leafOK);
      if (!leafOK) leafValuesOK = false;
    }

    TreeDisplay.displayTestResult("Overall leaves flattened correctly", leafValuesOK ? "Yes" : "No", "Yes", leafValuesOK);
  }

  /**
   * Run the path tests
   */
  private void runPathTests() {
    TreeDisplay.displayHeader("Path Tests (Example)");

    System.out.println(TreeDisplay.BOLD + "\nPath V1 (from leaf to root):" + TreeDisplay.RESET);
    System.out.println(TreeDisplay.CYAN + "Output: " + TreeDisplay.RESET);
    Trees.pathV1(l7);
    System.out.println(TreeDisplay.YELLOW + "Expected: 7, 6, 4 (bottom-up)" + TreeDisplay.RESET);

    System.out.println(TreeDisplay.BOLD + "\nPath V2 (from leaf to root, iterative):" + TreeDisplay.RESET);
    System.out.println(TreeDisplay.CYAN + "Output: " + TreeDisplay.RESET);
    Trees.pathV2(l7);
    System.out.println(TreeDisplay.YELLOW + "Expected: 7, 6, 4 (bottom-up)" + TreeDisplay.RESET);
  }

  // =====================================
  // Exercise 1 Tests
  // =====================================

  /**
   * Run the number of nodes test
   */
  private void runNbrNodeTest() {
    TreeDisplay.displayHeader("Number of Nodes Test (Ex 1.1)");
    int expectedNodes = 7;
    int actualNodes = Trees.nbrNode(t4);
    TreeDisplay.displayTestResult("Number of nodes", actualNodes, expectedNodes, actualNodes == expectedNodes);
  }

  /**
   * Run the minimum value test
   */
  private void runMinTest() {
    TreeDisplay.displayHeader("Minimum Value Test (Ex 1.2)");
    int expectedMin = 1;
    int actualMin = Trees.min(t4);
    TreeDisplay.displayTestResult("Minimum value", actualMin, expectedMin, actualMin == expectedMin);
  }

  /**
   * Run the sum test
   */
  private void runSumTest() {
    TreeDisplay.displayHeader("Sum Test (Ex 1.3)");
    int expectedSum = 28; // 4 + 2 + 1 + 3 + 5 + 6 + 7
    int actualSum = Trees.sum(t4);
    TreeDisplay.displayTestResult("Sum of all values", actualSum, expectedSum, actualSum == expectedSum);
  }

  /**
   * Run the equals test
   */
  private void runEqualsTest() {
    TreeDisplay.displayHeader("Equals Test (Ex 1.4)");

    boolean expectedEqualsDiff = false;
    boolean actualEqualsDiff = Trees.equals(t4, t2);
    TreeDisplay.displayTestResult("Different trees", actualEqualsDiff, expectedEqualsDiff, actualEqualsDiff == expectedEqualsDiff);

    boolean expectedEqualsSim = true;
    boolean actualEqualsSim = Trees.equals(s3, s6);
    TreeDisplay.displayTestResult("Similar structure trees", actualEqualsSim, expectedEqualsSim, actualEqualsSim == expectedEqualsSim);
  }

  /**
   * Run the depth test
   */
  private void runDepthTest() {
    TreeDisplay.displayHeader("Depth Test (Ex 1.5)");

    int expectedDepthT4 = 3;
    int actualDepthT4 = Trees.depth(t4);
    TreeDisplay.displayTestResult("Depth of t4", actualDepthT4, expectedDepthT4, actualDepthT4 == expectedDepthT4);

    int expectedDepthT2 = 2;
    int actualDepthT2 = Trees.depth(t2);
    TreeDisplay.displayTestResult("Depth of t2", actualDepthT2, expectedDepthT2, actualDepthT2 == expectedDepthT2);

    int expectedDepthL1 = 1;
    int actualDepthL1 = Trees.depth(l1);
    TreeDisplay.displayTestResult("Depth of l1", actualDepthL1, expectedDepthL1, actualDepthL1 == expectedDepthL1);
  }

  /**
   * Run the same node test
   */
  private void runSameOneTest() {
    TreeDisplay.displayHeader("Same Node Test (Ex 1.6)");

    boolean expectedSameOneDiff = false;
    boolean actualSameOneDiff = Trees.sameOne(l1, l3);
    TreeDisplay.displayTestResult("Different nodes", actualSameOneDiff, expectedSameOneDiff, actualSameOneDiff == expectedSameOneDiff);

    boolean expectedSameOneSame = true;
    boolean actualSameOneSame = Trees.sameOne(l1, l1);
    TreeDisplay.displayTestResult("Same node", actualSameOneSame, expectedSameOneSame, actualSameOneSame == expectedSameOneSame);
  }

  /**
   * Run the DFS print test
   */
  private void runDFSTest() {
    TreeDisplay.displayHeader("DFS Print Test (Ex 1.7)");

    System.out.println(TreeDisplay.CYAN + "Output: " + TreeDisplay.RESET);
    Trees.dfsPrint(t4);
    System.out.println(TreeDisplay.YELLOW + "Expected order: 4, 2, 1, 3, 5, 6, 7 (or similar pre-order traversal)" + TreeDisplay.RESET);
  }

  /**
   * Run the BFS print test
   */
  private void runBFSTest() {
    TreeDisplay.displayHeader("BFS Print Test (Ex 1.8)");

    System.out.println(TreeDisplay.CYAN + "Output: " + TreeDisplay.RESET);
    Trees.bfsPrint(t4);
    System.out.println(TreeDisplay.YELLOW + "Expected order: 4, 2, 5, 6, 1, 3, 7 (level by level)" + TreeDisplay.RESET);
  }

  // =====================================
  // Exercise 2 Tests
  // =====================================

  /**
   * Run the Exercise 2 path printing tests
   */
  private void runExercise2Tests() {
    TreeDisplay.displayHeader("Exercise 2 - Path Printing Tests");

    // Test 2.1: Print path from node to root
    System.out.println(TreeDisplay.BOLD + "\n2.1) Print Path V1 (recursive):" + TreeDisplay.RESET);
    System.out.println(TreeDisplay.CYAN + "Output for l7 (value 7):" + TreeDisplay.RESET);
    Trees.printPathV1(l7);
    System.out.println(TreeDisplay.YELLOW + "Expected: 4 <- 6 <- 7 (root-to-node, arrows show parent direction)" + TreeDisplay.RESET);

    System.out.println(TreeDisplay.CYAN + "\nOutput for l3 (value 3):" + TreeDisplay.RESET);
    Trees.printPathV1(l3);
    System.out.println(TreeDisplay.YELLOW + "Expected: 4 <- 2 <- 3 (root-to-node, arrows show parent direction)" + TreeDisplay.RESET);

    // Test 2.2: Print path from node to root (iterative)
    System.out.println(TreeDisplay.BOLD + "\n2.2) Print Path V2 (iterative):" + TreeDisplay.RESET);
    System.out.println(TreeDisplay.CYAN + "Output for l5 (value 5):" + TreeDisplay.RESET);
    Trees.printPathV2(l5);
    System.out.println(TreeDisplay.YELLOW + "Expected: 4 <- 5 (root-to-node, arrows show parent direction)" + TreeDisplay.RESET);

    System.out.println(TreeDisplay.CYAN + "\nOutput for l1 (value 1):" + TreeDisplay.RESET);
    Trees.printPathV2(l1);
    System.out.println(TreeDisplay.YELLOW + "Expected: 4 <- 2 <- 1 (root-to-node, arrows show parent direction)" + TreeDisplay.RESET);

    // Test 2.3: Find and print path to a value
    System.out.println(TreeDisplay.BOLD + "\n2.3) Find and Print Path to Value:" + TreeDisplay.RESET);

    // Test with a value that exists in the tree
    System.out.println(TreeDisplay.CYAN + "Output for value 7:" + TreeDisplay.RESET);
    Trees.printPathV3(t4, 7);
    System.out.println(TreeDisplay.YELLOW + "Expected: 4 -> 6 -> 7 (root-to-value path, arrows show child direction)" + TreeDisplay.RESET);

    System.out.println(TreeDisplay.CYAN + "\nOutput for value 3:" + TreeDisplay.RESET);
    Trees.printPathV3(t4, 3);
    System.out.println(TreeDisplay.YELLOW + "Expected: 4 -> 2 -> 3 (root-to-value path, arrows show child direction)" + TreeDisplay.RESET);

    // Test with a value that doesn't exist in the tree
    System.out.println(TreeDisplay.CYAN + "\nOutput for value 10 (not in tree):" + TreeDisplay.RESET);
    Trees.printPathV3(t4, 10);
    System.out.println(TreeDisplay.YELLOW + "Expected: Value 10 not found in tree (or similar message)" + TreeDisplay.RESET);
  }

  // =====================================
  // Exercise 3 Tests
  // =====================================

  /**
   * Run the array conversion test
   */
  private void runArrayTest() {
    TreeDisplay.displayHeader("Array Conversion Test (Ex 3.1-3.2)");

    // Step 1: Test tree to array conversion
    System.out.println(TreeDisplay.BOLD + "3.1) Testing toArray conversion:" + TreeDisplay.RESET);
    System.out.println(TreeDisplay.CYAN + "Original tree structure:" + TreeDisplay.RESET);
    TreeDisplay.displayTree(t4);

    int[][] array = Trees.toArray(t4);
    boolean conversionSuccess = array != null;
    TreeDisplay.displayTestResult("Array conversion", conversionSuccess ? "Success" : "Failed",
            "Success", conversionSuccess);

    if (conversionSuccess) {
      // Check array dimensions
      int rows = array.length;
      int expectedRows = 3; // Should match the depth of the tree
      boolean rowsCorrect = rows == expectedRows;

      TreeDisplay.displayTestResult("Array height (depth)", rows, expectedRows, rowsCorrect);

      // Display the array with highlighted structure
      System.out.println(TreeDisplay.BOLD + "\nArray contents:" + TreeDisplay.RESET);
      for (int i = 0; i < array.length; i++) {
        System.out.print(TreeDisplay.CYAN + "Row " + i + ": " + TreeDisplay.RESET);
        for (int j = 0; j < array[i].length; j++) {
          // Color values differently based on whether they're actual nodes or padding
          if (array[i][j] != 0) {
            System.out.print(TreeDisplay.GREEN + array[i][j] + " " + TreeDisplay.RESET);
          } else {
            System.out.print(TreeDisplay.YELLOW + array[i][j] + " " + TreeDisplay.RESET);
          }
        }
        System.out.println();
      }

      // Verify array structure matches tree
      boolean structureOK = verifyArrayStructure(array, t4);
      TreeDisplay.displayTestResult("Array structure matches tree",
              structureOK ? "Yes" : "No", "Yes", structureOK);

      // Step 2: Test array to tree reconstruction
      System.out.println(TreeDisplay.BOLD + "\n3.2) Testing toTree conversion:" + TreeDisplay.RESET);

      Tree reconstructed = Trees.toTree(array);
      boolean reconstructionSuccess = reconstructed != null;
      TreeDisplay.displayTestResult("Tree reconstruction",
              reconstructionSuccess ? "Success" : "Failed",
              "Success", reconstructionSuccess);

      if (reconstructionSuccess) {
        // Compare structure properties
        int originalNodes = Trees.nbrNode(t4);
        int reconstructedNodes = Trees.nbrNode(reconstructed);
        boolean nodesEqual = originalNodes == reconstructedNodes;

        int originalLeaves = Trees.nbrLeaves(t4);
        int reconstructedLeaves = Trees.nbrLeaves(reconstructed);
        boolean leavesEqual = originalLeaves == reconstructedLeaves;

        int originalDepth = Trees.depth(t4);
        int reconstructedDepth = Trees.depth(reconstructed);
        boolean depthEqual = originalDepth == reconstructedDepth;

        TreeDisplay.displayTestResult("Node count", reconstructedNodes, originalNodes, nodesEqual);
        TreeDisplay.displayTestResult("Leaf count", reconstructedLeaves, originalLeaves, leavesEqual);
        TreeDisplay.displayTestResult("Tree depth", reconstructedDepth, originalDepth, depthEqual);

        System.out.println(TreeDisplay.BOLD + "\nReconstructed tree:" + TreeDisplay.RESET);
        TreeDisplay.displayTree(reconstructed);

        boolean expectedReconstructedEquals = true;
        boolean actualReconstructedEquals = Trees.equals(t4, reconstructed);
        TreeDisplay.displayTestResult("Trees equal after reconstruction",
                actualReconstructedEquals ? "Yes" : "No",
                "Yes",
                actualReconstructedEquals);

        // Verify the structure property: nodes at same level in the original and reconstructed tree
        boolean levelStructurePreserved = verifyLevelStructure(t4, reconstructed);
        TreeDisplay.displayTestResult("Level structure preserved",
                levelStructurePreserved ? "Yes" : "No",
                "Yes", levelStructurePreserved);
      } else {
        System.out.println(TreeDisplay.RED + "Tree reconstruction failed - KO" + TreeDisplay.RESET);
      }
    } else {
      System.out.println(TreeDisplay.RED + "Array representation failed - KO" + TreeDisplay.RESET);
    }
  }

  /**
   * Verify that the array structure matches the tree
   *
   * @param array The array representation
   * @param tree  The original tree
   * @return true if the array correctly represents the tree
   */
  private boolean verifyArrayStructure(int[][] array, Tree tree) {
    // This is a simple check - expand as needed
    // Root should be at array[0][0]
    if (array[0][0] != tree.getValue()) {
      return false;
    }

    // Other checks could be added here...
    return true;
  }

  /**
   * Verify that the level structure is preserved between trees
   *
   * @param original      The original tree
   * @param reconstructed The reconstructed tree
   * @return true if the level structure is preserved
   */
  private boolean verifyLevelStructure(Tree original, Tree reconstructed) {
    // Simple check - both should have the same BFS order
    java.util.List<Integer> originalBFS = getBFSOrder(original);
    java.util.List<Integer> reconstructedBFS = getBFSOrder(reconstructed);

    if (originalBFS.size() != reconstructedBFS.size()) {
      return false;
    }

    // Check if all nodes at the same level are preserved
    // Note: This is a simplified check and might not be sufficient
    // for all tree structures
    for (int i = 0; i < originalBFS.size(); i++) {
      if (!reconstructedBFS.contains(originalBFS.get(i))) {
        return false;
      }
    }

    return true;
  }

  /**
   * Get the BFS traversal order of a tree
   *
   * @param tree The tree to traverse
   * @return List of node values in BFS order
   */
  private java.util.List<Integer> getBFSOrder(Tree tree) {
    java.util.List<Integer> result = new java.util.ArrayList<>();
    java.util.Queue<Tree> queue = new java.util.LinkedList<>();
    queue.add(tree);

    while (!queue.isEmpty()) {
      Tree node = queue.poll();
      result.add(node.getValue());

      for (Tree child : node) {
        queue.add(child);
      }
    }

    return result;
  }

  // =====================================
  // Exercise 4 Tests
  // =====================================

  /**
   * Run the LCA test
   */
  private void runLCATest() {
    TreeDisplay.displayHeader("Lowest Common Ancestor Test (Ex 4)");

    Tree ancestorL1L3 = Trees.lca(l1, l3);
    if (ancestorL1L3 != null) {
      int expectedLCA1 = 2;
      int actualLCA1 = ancestorL1L3.getValue();
      TreeDisplay.displayTestResult("LCA of " + l1.getValue() + " and " + l3.getValue(),
              actualLCA1, expectedLCA1, actualLCA1 == expectedLCA1);
    } else {
      System.out.println(TreeDisplay.RED + "LCA of " + l1.getValue() + " and " + l3.getValue() +
              ": null (Expected: 2) ✗ KO" + TreeDisplay.RESET);
    }

    Tree ancestorL1L7 = Trees.lca(l1, l7);
    if (ancestorL1L7 != null) {
      int expectedLCA2 = 4;
      int actualLCA2 = ancestorL1L7.getValue();
      TreeDisplay.displayTestResult("LCA of " + l1.getValue() + " and " + l7.getValue(),
              actualLCA2, expectedLCA2, actualLCA2 == expectedLCA2);
    } else {
      System.out.println(TreeDisplay.RED + "LCA of " + l1.getValue() + " and " + l7.getValue() +
              ": null (Expected: 4) ✗ KO" + TreeDisplay.RESET);
    }
  }
}
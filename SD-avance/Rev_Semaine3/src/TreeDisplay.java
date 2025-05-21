/**
 * This class contains methods to display a tree in the terminal with colors
 */
public class TreeDisplay {
  // ANSI color codes
  public static final String RESET = "\u001B[0m";
  public static final String BLACK = "\u001B[30m";
  public static final String RED = "\u001B[31m";
  public static final String GREEN = "\u001B[32m";
  public static final String YELLOW = "\u001B[33m";
  public static final String BLUE = "\u001B[34m";
  public static final String PURPLE = "\u001B[35m";
  public static final String CYAN = "\u001B[36m";
  public static final String WHITE = "\u001B[37m";
  public static final String BOLD = "\u001B[1m";

  // Background colors
  public static final String BG_BLACK = "\u001B[40m";
  public static final String BG_RED = "\u001B[41m";
  public static final String BG_GREEN = "\u001B[42m";
  public static final String BG_YELLOW = "\u001B[43m";
  public static final String BG_BLUE = "\u001B[44m";
  public static final String BG_PURPLE = "\u001B[45m";
  public static final String BG_CYAN = "\u001B[46m";
  public static final String BG_WHITE = "\u001B[47m";

  /**
   * Display a tree in the terminal with colorful output
   *
   * @param root The root node of the tree
   */
  public static void displayTree(Tree root) {
    System.out.println(BOLD + BLUE + "Tree Structure:" + RESET);
    displayTreeHelper(root, "", true);
  }

  /**
   * Helper method for displayTree that handles the recursion and formatting
   *
   * @param node   The current node being processed
   * @param prefix The prefix string for the current line
   * @param isTail Whether the current node is a tail (last child of its parent)
   */
  private static void displayTreeHelper(Tree node, String prefix, boolean isTail) {
    // Display the current node with color depending on whether it's a leaf
    String nodeColor = node.isLeaf() ? GREEN : YELLOW;
    System.out.println(prefix + (isTail ? CYAN + "└── " : CYAN + "├── ") +
            nodeColor + node.getValue() + RESET);

    // If the node has children
    if (node.getChildren() != null && node.getChildren().length > 0) {
      Tree[] children = node.getChildren();

      // Process all children except the last one
      for (int i = 0; i < children.length - 1; i++) {
        displayTreeHelper(children[i], prefix + (isTail ? "    " : CYAN + "│   " + RESET), false);
      }

      // Process the last child
      if (children.length > 0) {
        displayTreeHelper(children[children.length - 1], prefix + (isTail ? "    " : CYAN + "│   " + RESET), true);
      }
    }
  }

  /**
   * Display colored test result
   *
   * @param description Test description
   * @param actual      Actual result
   * @param expected    Expected result
   * @param passed      Whether the test passed
   */
  public static void displayTestResult(String description, Object actual, Object expected, boolean passed) {
    System.out.println(BOLD + description + ": " + RESET +
            (passed ? GREEN : RED) + actual + RESET +
            " (Expected: " + expected + ") " +
            (passed ? GREEN + "✓ OK" : RED + "✗ KO") + RESET);
  }

  /**
   * Display a section header
   *
   * @param title The section title
   */
  public static void displayHeader(String title) {
    System.out.println("\n" + BOLD + PURPLE + "=== " + title + " ===" + RESET);
  }

  /**
   * Display a major section header
   *
   * @param title The section title
   */
  public static void displaySectionHeader(String title) {
    System.out.println("\n" + BOLD + BG_BLUE + WHITE + " " + title + " " + RESET);
    System.out.println(BLUE + "========================================" + RESET);
  }

  /**
   * Display a colorful separator line
   */
  public static void displaySeparator() {
    System.out.println(CYAN + "----------------------------------------" + RESET);
  }
}
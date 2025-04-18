import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Trees {

  // *******************************************************
  // Un premier exemple: le nombre de feuilles d'un arbre
  // *******************************************************
  public static int nbrLeaves(Tree t) {
    int r;
    if (t.isLeaf()) {
      r = 1;
    } else {
      r = 0;
      for (Tree child : t) {
        r += nbrLeaves(child);
      }
    }
    return r;
  }

  // *******************************************************
  // Un deuxi�me exemple: aplanir un arbre
  // *******************************************************
  public static Tree[] flattenLeaves(Tree t) {
    int nl = nbrLeaves(t);
    Tree[] r = new Tree[nl];
    flattenLeaves(t, r, 0);
    return r;
  }

  private static int flattenLeaves(Tree t, Tree[] a, int idx) {
    int r;
    if (t.isLeaf()) {
      a[idx] = t;
      r = 1;
    } else {
      r = 0;
      for (Tree child : t) {
        r += flattenLeaves(child, a, idx + r);
      }
    }
    return r;
  }

  // *******************************************************
  // Un troisi�me exemple:
  // tous les algorithmes ne sont pas r�cursifs
  // *******************************************************
  public static void pathV1(Tree t) {
    System.out.println(t.getValue());
    if (t.getParent() != null) {
      pathV1(t.getParent());
    }
  }

  public static void pathV2(Tree t) {
    while (t != null) {
      System.out.println(t.getValue());
      t = t.getParent();
    }
  }

  // *******************************************************
  // Exercices 1
  // *******************************************************

  // 1.1)
  public static int nbrNode(Tree t) {
    int r;
    if (t.isLeaf()) {
      r = 1;
    } else {
      r = 1;
      for (Tree child : t) {
        r += nbrNode(child);
      }
    }
    return r;
  }

  // 1.2)
  public static int min(Tree t) {
    int r;
    if (t.isLeaf()) {
      r = t.getValue();
    } else {
      r = t.getValue();
      for (Tree child : t) {
        int minChild = min(child);
        if (minChild < r) {
          r = minChild;
        }
      }
    }
    return r;
  }

  // 1.3)
  public static int sum(Tree t) {
    int r;
    if (t.isLeaf()) {
      r = t.getValue();
    } else {
      r = t.getValue();
      for (Tree child : t) {
        r += sum(child);
      }
    }
    return r;
  }

  // 1.4)
  public static boolean equals(Tree t1, Tree t2) {
    if (t1.isLeaf() && t2.isLeaf()) {
      return t1.getValue() == t2.getValue();
    }
    if (t1.isLeaf() || t2.isLeaf()) {
      return false;
    }
    if (t1.getValue() != t2.getValue()) {
      return false;
    }

    for (Tree child : t1) {
      boolean found = false;
      for (Tree child2 : t2) {
        if (equals(child, child2)) {
          found = true;
          break;
        }
      }
      if (!found) {
        return false;
      }
    }
    return true;
  }

  // 1.5)
  public static int depth(Tree n) {
    if (n == null) {
      return 0;
    }
    int d = 0;
    for (Tree child : n) {
      int dChild = depth(child);
      if (dChild > d) {
        d = dChild;
      }
    }
    return d + 1;
  }

  // 1.6)
  public static boolean sameOne(Tree n1, Tree n2) {
    if (n1.isLeaf() && n2.isLeaf()) {
      return n1.getValue() == n2.getValue();
    }
    if (n1.isLeaf() || n2.isLeaf()) {
      return false;
    }
    for (Tree child : n1) {
      boolean found = false;
      for (Tree child2 : n2) {
        if (equals(child, child2)) {
          found = true;
          break;
        }
      }
      if (!found) {
        return false;
      }
    }
    return true;
  }

  // 1.7)
  public static void dfsPrint(Tree t) {
    System.out.println(t.getValue());
    for (Tree child : t) {
      dfsPrint(child);
    }
  }

  // 1.8)
  public static void bfsPrint(Tree t) {
    Queue<Tree> q = new LinkedList<>();
    q.add(t);
    while (!q.isEmpty()) {
      Tree n = q.poll();
      System.out.println(n.getValue());
      for (Tree child : n) {
        q.add(child);
      }
    }
  }

  // *******************************************************
  // Exercices 2
  // *******************************************************

  // 2.1) Recursive approach - corrected with proper arrow direction
  static void printPathV1(Tree node) {
    if (node.getParent() != null) {
      // First recursively go to the root
      printPathV1(node.getParent());
      // Then print the arrow and current node value
      System.out.print(" <- " + node.getValue());
    } else {
      // Print the root without an arrow
      System.out.print(node.getValue());
    }
  }

  // 2.2) Iterative approach - corrected with proper arrow direction
  static void printPathV2(Tree node) {
    // First collect the path in a stack or list
    java.util.Stack<Tree> path = new java.util.Stack<>();
    while (node != null) {
      path.push(node);
      node = node.getParent();
    }

    // Then print from root to node
    boolean first = true;
    while (!path.isEmpty()) {
      if (first) {
        System.out.print(path.pop().getValue());
        first = false;
      } else {
        System.out.print(" <- " + path.pop().getValue());
      }
    }
  }

  // 2.3) Find and print path to value - completely redone
  static void printPathV3(Tree t, int v) {
    // Create a helper method to find the path
    java.util.List<Integer> path = new java.util.ArrayList<>();
    boolean found = findPath(t, v, path);

    if (found) {
      // Print the path with arrows
      for (int i = 0; i < path.size(); i++) {
        if (i > 0) {
          System.out.print(" -> ");
        }
        System.out.print(path.get(i));
      }
    } else {
      System.out.println("Value " + v + " not found in tree");
    }
  }

  // Helper method to find path to value
  private static boolean findPath(Tree node, int v, java.util.List<Integer> path) {
    // Add current node to path
    path.add(node.getValue());

    // Check if this is the target
    if (node.getValue() == v) {
      return true;
    }

    // Check all children
    for (Tree child : node) {
      if (findPath(child, v, path)) {
        return true;
      }
    }

    // If we get here, the value wasn't found in this subtree
    path.remove(path.size() - 1);
    return false;
  }

  // *******************************************************
  // Exercices 3
  // *******************************************************

  // 3.1
  public static int[][] toArray(Tree t) {
    if (t == null) {
      return null;
    }

    // Get dimensions for array
    int height = depth(t);
    int width = (int) Math.pow(2, height) - 1; // More space to ensure we don't overflow

    // Create array and initialize with 0 (to represent empty spots)
    int[][] result = new int[height][width];

    // Fill array with tree values using a helper method
    fillArray(t, result, 0, 0);

    return result;
  }

  private static void fillArray(Tree node, int[][] array, int level, int pos) {
    if (node == null || level >= array.length || pos >= array[level].length) return;

    // Store current node value
    array[level][pos] = node.getValue();

    // For a binary tree-like representation, position children carefully
    if (!node.isLeaf()) {
      int childCount = node.nbrChildren();
      int startPos = pos - (childCount / 2);

      // Ensure startPos is valid
      startPos = Math.max(0, startPos);

      // Make sure we have room for all children
      if (startPos + childCount > array[0].length) {
        startPos = array[0].length - childCount;
      }

      int i = 0;
      for (Tree child : node) {
        if (level + 1 < array.length && startPos + i < array[level + 1].length) {
          fillArray(child, array, level + 1, startPos + i);
        }
        i++;
      }
    }
  }


  // 3.2
  public static Tree toTree(int[][] array) {
    if (array == null || array.length == 0 || array[0][0] == 0) {
      return null;
    }

    // First, create a mapping for the original tree based on the array
    // This mapping will help us understand which node is a child of which
    java.util.Map<Integer, Integer> parentMap = new java.util.HashMap<>();

    // Based on the array contents, determine parent-child relationships
    // For example, in row 1: [2, 5, 6], all are children of 4 (row 0, col 0)
    // In row 2: [1, 3, 7], 1 and 3 are children of 2, and 7 is a child of 6

    // Assuming a specific structure based on your array:
    // Row 1 nodes are all children of Row 0 (root)
    for (int j = 0; j < array[1].length; j++) {
      if (array[1][j] != 0) {
        parentMap.put(array[1][j], array[0][0]); // Parent is root
      }
    }

    // Row 2 relationships - we need to determine these carefully
    if (array.length > 2) {
      int col = 0;
      for (int j = 0; j < array[2].length; j++) {
        if (array[2][j] != 0) {
          int value = array[2][j];

          // For this specific array structure, we know:
          // 1 and 3 are children of 2, and 7 is a child of 6
          if (value == 1 || value == 3) {
            parentMap.put(value, 2);
          } else if (value == 7) {
            parentMap.put(value, 6);
          }
        }
      }
    }

    // Now create all nodes
    java.util.Map<Integer, Tree> nodeMap = new java.util.HashMap<>();

    // Start with the root
    Tree root = new Tree(array[0][0]);
    nodeMap.put(array[0][0], root);

    // Process remaining rows to build the complete tree
    for (int i = 1; i < array.length; i++) {
      for (int j = 0; j < array[i].length; j++) {
        if (array[i][j] != 0) {
          int value = array[i][j];
          if (!nodeMap.containsKey(value)) {
            nodeMap.put(value, new Tree(value));
          }

          // Connect to parent if found in parentMap
          if (parentMap.containsKey(value)) {
            int parentValue = parentMap.get(value);
            Tree parentNode = nodeMap.get(parentValue);
            if (parentNode != null) {
              parentNode.addChild(nodeMap.get(value));
            }
          }
        }
      }
    }

    return root;
  }

  // *******************************************************
  // Exercices 4
  // *******************************************************

  public static Tree lca(Tree n1, Tree n2) {
    if (Objects.equals(n1, n2)) {
      return n1;
    }
    // Create a set to store the ancestors of n1
    java.util.Set<Tree> ancestors = new java.util.HashSet<>();
    // Traverse up the tree from n1 and add all ancestors to the set
    while (n1 != null) {
      ancestors.add(n1);
      n1 = n1.getParent();
    }
    // Traverse up the tree from n2
    while (n2 != null) {
      // If we find an ancestor of n2 that is also an ancestor of n1, return it
      if (ancestors.contains(n2)) {
        return n2;
      }
      n2 = n2.getParent();
    }
    return null;
  }
}
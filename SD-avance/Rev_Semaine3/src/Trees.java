import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
        int min = min(child);
        if (min < r) {
          r = min;
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
    boolean isEqual = false;
    for (Tree child : t1) {
      for (Tree child2 : t2) {
        if (equals(child, child2)) {
          isEqual = true;
          break;
        }
      }
    }
    return isEqual;
  }

  // 1.5)
  public static int depth(Tree n) {
    if (n == null)
      return 0;

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
    if (n1 == null || n2 == null) {
      return false;
    }

    Tree root1 = n1;
    while (root1.getParent() != null) {
      root1 = root1.getParent();
    }

    Tree root2 = n2;
    while (root2.getParent() != null) {
      root2 = root2.getParent();
    }

    return root1 == root2;
  }

  // 1.7)
  public static void dfsPrint(Tree t) {
    if (t == null) {
      return;
    }

    System.out.println(t.getValue());

    for (Tree child : t) {
      dfsPrint(child);
    }
  }

  // 1.8)
  public static void bfsPrint(Tree t) {
    if (t == null) {
      return;
    }

    // Use a queue for breadth-first traversal
    Queue<Tree> queue = new LinkedList<>();
    queue.offer(t);

    while (!queue.isEmpty()) {
      Tree current = queue.poll();
      System.out.println(current.getValue());

      // Add all children to the queue
      for (Tree child : current) {
        queue.offer(child);
      }
    }
  }

  // *******************************************************
  // Exercices 2
  // *******************************************************

  // 2.1) Recursive method to print path from root to node
  static void printPathV1(Tree node) {
    if (node == null) {
      return;
    }

    if (node.getParent() != null) {
      printPathV1(node.getParent());
      System.out.print(" -> " + node.getValue());
    } else {
      // This is the root node
      System.out.print(node.getValue());
    }
  }

  // 2.2) Non-recursive method to print path from root to node
  static void printPathV2(Tree node) {
    if (node == null) {
      return;
    }

    // Build the path by going up to the root
    List<Integer> path = new ArrayList<>();
    Tree current = node;

    while (current != null) {
      path.add(current.getValue());
      current = current.getParent();
    }

    // Print the path from root to node (reverse the collected path)
    for (int i = path.size() - 1; i >= 0; i--) {
      System.out.print(path.get(i));
      if (i > 0) {
        System.out.print(" -> ");
      }
    }
  }

  // 2.3) Method to print path from root to first node with value v
  static void printPathV3(Tree t, int v) {
    if (t == null) {
      System.out.println("Tree is null");
      return;
    }

    List<Integer> path = new ArrayList<>();
    boolean found = findPath(t, v, path);

    if (found) {
      for (int i = 0; i < path.size(); i++) {
        System.out.print(path.get(i));
        if (i < path.size() - 1) {
          System.out.print(" -> ");
        }
      }
    } else {
      System.out.print("Value " + v + " not found in tree");
    }
  }

  // Helper method for printPathV3
  private static boolean findPath(Tree node, int v, List<Integer> path) {
    if (node == null) {
      return false;
    }

    // Add current node to path
    path.add(node.getValue());

    // If we found the target value, return true
    if (node.getValue() == v) {
      return true;
    }

    // Search in all children
    for (Tree child : node) {
      if (findPath(child, v, path)) {
        return true;
      }
    }

    // If not found in this subtree, remove current node from path
    path.removeLast();
    return false;
  }

  // *******************************************************
  // Exercices 3
  // *******************************************************

  // 3.1
  public static int[][] toArray(Tree t) {
    return null;
  }

  // 3.2
  public static Tree toTree(int[][] t) {
    return null;
  }

  // *******************************************************
  // Exercices 4
  // *******************************************************

  public static Tree lca(Tree n1, Tree n2) {
    // Handle null cases
    if (n1 == null || n2 == null) {
      return null;
    }

    // If both nodes are the same, return that node
    if (n1 == n2) {
      return n1;
    }

    // Get the depths of both nodes
    int depth1 = getDepthFromRoot(n1);
    int depth2 = getDepthFromRoot(n2);

    // Bring both nodes to the same level
    while (depth1 > depth2) {
      n1 = n1.getParent();
      depth1--;
    }

    while (depth2 > depth1) {
      n2 = n2.getParent();
      depth2--;
    }

    // Now both nodes are at the same depth, move up until they meet
    while (n1 != n2) {
      n1 = n1.getParent();
      n2 = n2.getParent();
    }

    return n1;
  }

  // Helper method to get depth of a node from root
  private static int getDepthFromRoot(Tree node) {
    int depth = 0;
    Tree current = node;
    while (current.getParent() != null) {
      depth++;
      current = current.getParent();
    }
    return depth;
  }
}
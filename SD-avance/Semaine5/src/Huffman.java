import java.util.Map;
import java.util.PriorityQueue;


public class Huffman {

  static class Node {
    char ch;
    int freq;
    final Node left;
    final Node right;

    public Node(char ch, int freq, Node left, Node right) {
      this.ch = ch;
      this.freq = freq;
      this.left = left;
      this.right = right;
    }

    public boolean isLeaf() {
      return left == null && right == null;
    }

  }

  // renvoie une map qui a comme cl� les lettres de la chaine de
  // caract�re donn�e en param�tre et comme valeur la fr�quence de
  // ces lettres
  public static Map<Character, Integer> computeFreq(String s) {
    Map<Character, Integer> freq = new java.util.HashMap<>();
    for (char c : s.toCharArray()) {
      freq.put(c, freq.getOrDefault(c, 0) + 1);
    }
    return freq;
  }

  // renvoie l'arbre de Huffman obtenu � partir de la map des fr�quences des lettres
  public static Node buildTree(Map<Character, Integer> freq) {
    java.util.PriorityQueue<Node> pq = new java.util.PriorityQueue<>((a, b) -> a.freq - b.freq);
    for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
      pq.add(new Node(entry.getKey(), entry.getValue(), null, null));
    }

    while (pq.size() > 1) {
      Node left = pq.poll();
      Node right = pq.poll();
      Node parent = new Node('\0', left.freq + right.freq, left, right);
      pq.add(parent);
    }

    return pq.poll();
  }

  // renvoie une map qui associe chaque lettre � son code (suite de 0 et de 1).
  // Ce code est obtenu en parcourant l'arbre de Huffman donn� en param�tre
  public static Map<Character, String> buildCode(Node root) {
    Map<Character, String> codeMap = new java.util.HashMap<>();
    buildCodeHelper(root, "", codeMap);
    return codeMap;
  }

  private static void buildCodeHelper(Node root, String s, Map<Character, String> codeMap) {
    if (root.isLeaf()) {
      codeMap.put(root.ch, s);
      return;
    }
    buildCodeHelper(root.left, s + "0", codeMap);
    buildCodeHelper(root.right, s + "1", codeMap);
  }


  // encode la chaine de caract�re prise en param�tre en une chaine de
  // bit (0 et 1) en utilisant la map des codes codeMap
  public static String compress(String s, Map<Character, String> codeMap) {
    StringBuilder sb = new StringBuilder();
    for (char c : s.toCharArray()) {
      sb.append(codeMap.get(c));
    }
    return sb.toString();
  }

  // Cette m�thode d�code une chaine de 0 et de 1 cod� � l'aide de l'algorithme de Huffman
  // En param�tre, en plus de la chaine � d�coder, est sp�cifi� la racine de l'arbre de
  // Huffman
  public static String expand(Node root, String t) {
    StringBuilder sb = new StringBuilder();
    Node current = root;
    for (char c : t.toCharArray()) {
      if (c == '0') {
        current = current.left;
      } else {
        current = current.right;
      }
      if (current.isLeaf()) {
        sb.append(current.ch);
        current = root;
      }
    }
    return sb.toString();
  }

  public static int getFrequency(Object node) {
    return ((Node) node).freq;
  }

  public static int getLeftFrequency(Object node) {
    return ((Node) node).left.freq;
  }

  public static int getRightFrequency(Object node) {
    return ((Node) node).right.freq;
  }
}

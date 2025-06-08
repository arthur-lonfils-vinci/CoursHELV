import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Huffman {

	private static class Node implements Comparable<Node> {
		private char ch;
		private int freq;
		private final Node left, right;

		public Node(char ch, int freq, Node left, Node right) {
			this.ch = ch;
			this.freq = freq;
			this.left = left;
			this.right = right;
		}

		public boolean isLeaf() {
			return left == null && right == null;
		}

		@Override
		public int compareTo(Node that) {
			return this.freq - that.freq;
		}
	}
	
	// renvoie une map qui a comme clé les lettres de la chaine de 
	// caractère donnée en paramètre et comme valeur la fréquence de 
	// cette lettre dans cette chaine de caractère
	public static Map<Character, Integer> computeFreq(String s) {
		char[] input = s.toCharArray();
		Map<Character, Integer> freq = new HashMap<Character, Integer>();
		for (int i = 0; i < input.length; i++) {
			int oldFreq = 0;
			if (freq.get(input[i]) != null) {
				oldFreq = freq.get(input[i]);
			}
			freq.put(input[i], oldFreq + 1);
		}
		return freq;
	}	
	
	// renvoie l'arbre de Huffman obtenu grâce à la map des fréquences des lettres 
	public static Node buildTree(Map<Character, Integer> freq) {
		PriorityQueue<Node> p = new PriorityQueue<Node>();
		Set<Character> characters = freq.keySet();
		for (Character c : characters) {
			p.add(new Node(c, freq.get(c), null, null));
		}
		while (p.size() > 1) {
			Node x = p.poll();
			Node y = p.poll();
			Node parent = new Node('\0', x.freq + y.freq, x, y);
			p.add(parent);
		}
		return p.poll();
	}
	
	// renvoie une map qui associe chaque lettre à son code. Ce code est obtenu
	// en parcourant l'arbre de Huffman donné en paramètre
	public static Map<Character, String> buildCode(Node root) {
		//TODO
		return null;
	}
	
	// encode la chaine de caractère prise en paramètre en une chaine de 
	// bit 0 et 1 en utilisant la map des codes codeMap
	public static String compress(String s, Map<Character, String> codeMap) {
		char[] input = s.toCharArray();
		StringBuffer toReturn = new StringBuffer("");
		// encode
		for (int i = 0; i < input.length; i++) {
			String code = codeMap.get(input[i]);
			toReturn.append(code);
		}
		return toReturn.toString();
	}
	
	// Cette méthode décode une chaine de 0 et de 1 codé à l'aide de l'algorithme de Huffman
	// En paramètre, en plus de la chaine à décoder, est spécifié la racine de l'arbre de 
	// Huffman 
	public static String expand(Node root, String t) {
		StringBuffer s = new StringBuffer("");
		char[] b = t.toCharArray();
		int i=0;
		int length=root.freq;
		for (int j=0; j<length;j++) {
			Node x = root;
			while (!x.isLeaf()) {
				if (b[i] == '1') {
					x = x.right;
				} else {
					x = x.left;
				}
				i++;
			}
			s = s.append(x.ch);
		}
		return s.toString();
	}
	
	public static void main(String[] args) throws IOException {
		String s=HuffmanReadFile.loadFile(new File("11-0.txt"));
		Map<Character, Integer> freq = computeFreq(s);
		Node root = buildTree(freq);
		Map<Character, String> code= buildCode(root);
		String compress = compress(s, code);
		HuffmanWriteFile.write("fichier_compresse",compress);
		String texteOriginal =
		expand(root,HuffmanReadFile.read("fichier_compresse"));
		System.out.println(texteOriginal);
	}
}

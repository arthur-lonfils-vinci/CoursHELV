import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class Main {

  public static void main(String[] args) throws IOException {
    System.out.println("-----------------------------------------------------");
    System.out.println("Methode computeFreq");
    String s="Bonjour! Au revoir!";
    Map<Character, Integer> freq = Huffman.computeFreq(s);
    Object root = Huffman.buildTree(freq);
    System.out.println("Nbre de lettre dans la chaine de caractère à encoder: "+ Huffman.getFrequency(root) );
    System.out.println("Fréquence des lettres dans le sous-arbre de gauche: "+ Huffman.getLeftFrequency(root) );
    System.out.println("Fréquence des lettres dans le sous-arbre de droite: "+ Huffman.getRightFrequency(root) );

    System.out.println("-----------------------------------------------------");
    System.out.println("Methode buildTree");
    String s1="Bonjour! Au revoir!";
    Map<Character, Integer> freq1 = Huffman.computeFreq(s1);
    Huffman.Node root1 = Huffman.buildTree(freq1);
    Map<Character, String> code= Huffman.buildCode(root1);
    System.out.println(code);

    System.out.println("-----------------------------------------------------");
    System.out.println("Methode buildCode");
    String s2="Bonjour! Au revoir!";
    Map<Character, Integer> freq2 = Huffman.computeFreq(s);
    Huffman.Node root2 = Huffman.buildTree(freq2);
    Map<Character, String> code2= Huffman.buildCode(root2);
    String compress = Huffman.compress(s2, code2);
    System.out.println(compress);

    System.out.println("-----------------------------------------------------");
    System.out.println("Methode expand");
    String s3="Bonjour! Au revoir!";
    Map<Character, Integer> freq3 = Huffman.computeFreq(s3);
    Huffman.Node root3 = Huffman.buildTree(freq3);
    Map<Character, String> code3= Huffman.buildCode(root3);
    String compress1 = Huffman.compress(s3, code3);
    String texteOriginal = Huffman.expand(root3,compress1);
    System.out.println(texteOriginal);

    System.out.println("-----------------------------------------------------");
    System.out.println("Test avec le fichier Huffman.java");
    String s5=HuffmanReadFile.loadFile(new File("src/Huffman.java"));
    assert s5 != null;
    Map<Character, Integer> freq5 = Huffman.computeFreq(s5);
    Huffman.Node root5 = Huffman.buildTree(freq5);
    Map<Character, String> code5= Huffman.buildCode(root5);
    String compress5 = Huffman.compress(s5, code5);
    HuffmanWriteFile.write("fichier_compresse",compress5);
    String texteOriginal5 =
            Huffman.expand(root5, Objects.requireNonNull(HuffmanReadFile.read("fichier_compresse")));
    System.out.println("Fichier compressé: ");
    System.out.println(compress5);
    System.out.println();
    System.out.println("Texte original: ");
    System.out.println(texteOriginal5);

  }
}
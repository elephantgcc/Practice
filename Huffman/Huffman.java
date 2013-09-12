import java.util.*;
import java.io.*;

class Node implements Comparable<Node> {
	char ch;
	int freq;
	Node left, right;
	public Node(char ch, int freq, Node left, Node right) {
		this.ch = ch;
		this.freq = freq;
		this.left = left;
		this.right = right;
	}

	public boolean isLeaf() {
		return left == null && right == null;
	}

	public int compareTo(Node other) {
		return this.freq - other.freq;
	}
}

public class Huffman {

	private static int R = 256;

	public static void main(String args[]) throws Exception {
		new Huffman().compress("test.txt", "test.txt.bin");
		new Huffman().expand("test.txt.bin", "test.txt2");
	} 

	public void compress(String inputFile, String outputFile) throws Exception {
		// word count
		int[] freq = new int[R];
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		int c = 0;
		int N = 0;
		while ((c = br.read()) != -1) {
			++freq[c];
			++N;
		}
		br.close();

		// build trie
		Node root = buildTrie(freq);

		// build codeArray
		String[] codeArray = new String[R];
		buildCode(root, "", codeArray);

		// writeTrie
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(outputFile));
		writeTrie(root, dos);

		// output N, the total char count
		dos.writeInt(N);
		System.out.println();
		System.out.println(N);

		// encode the txt to bin data
		br = new BufferedReader(new FileReader(inputFile));
		encode(br, codeArray, dos);
		br.close();
		dos.close();

	}

	public void expand(String inputFile, String outputFile) throws Exception {
		DataInputStream dis = new DataInputStream(new FileInputStream(inputFile));
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
		decode(dis, bw);
		dis.close();
		bw.close();
	}

	public Node buildTrie(int[] freq) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		for (char c = 0; c < R; ++c) {
			if (freq[c] > 0) {
				pq.add(new Node(c, freq[c], null, null));
			}
		}	

		while (pq.size() > 1 ) {
			Node x = pq.poll();
			Node y = pq.poll();
			pq.add(new Node('_', x.freq + y.freq, x, y));
		}
		return pq.poll();
	}

	public void buildCode(Node root, String prefix, String[] codeArray) {
		if (root.isLeaf()) {
			codeArray[root.ch] = prefix;
			System.out.println(root.ch + "\t" + prefix);
			return;
		} else {
			buildCode(root.left, prefix + "0", codeArray);
			buildCode(root.right, prefix + "1", codeArray);
		}
	}

	public void writeTrie(Node node, DataOutputStream dos) throws Exception {
		if (node.isLeaf()) {
			dos.writeBoolean(true);
			dos.writeChar(node.ch);
			System.out.print(1);
			System.out.print(node.ch);
			return;
		} else {
			dos.writeBoolean(false);
			System.out.print(0);
			writeTrie(node.left, dos);
			writeTrie(node.right, dos);
		}
	}

	public void encode(BufferedReader br, String[] codeArray, DataOutputStream dos) throws Exception {
		int c = 0;
		while ((c = br.read()) != -1) { 
			for (int i = 0; i < codeArray[c].length(); ++i) {
				char codeBit = codeArray[c].charAt(i);
				if (codeBit == '1') {
					dos.writeBoolean(true);
					System.out.print(1);
				} else {
					dos.writeBoolean(false);
					System.out.print(0);
				}
			}
		}
	}

	public Node readTrie(DataInputStream dis) throws Exception {
		if (dis.readBoolean()) {
			return new Node(dis.readChar(), 0, null, null);
		} else {
			return new Node('_', 0, readTrie(dis), readTrie(dis));
		}
	}

	public void decode(DataInputStream dis, BufferedWriter bw) throws Exception {
		Node root = readTrie(dis);
		int N = dis.readInt();
		System.out.println("\nN = " + N);
		for (int i = 0; i < N; ++i) {
			Node x = root;
			while (!x.isLeaf()) {
				if (dis.readBoolean()) {
					x = x.right;
				} else {
					x = x.left;
				}
			}
			bw.write(x.ch);
		}
	}
}

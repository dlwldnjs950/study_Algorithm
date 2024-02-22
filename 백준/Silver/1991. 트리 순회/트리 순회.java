import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Node {
		int num;
		Node left;
		Node right;

		public Node(int num) {
			this.num = num;
			left = null;
			right = null;
		}
	}

	static Node[] tree;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int cnt = Integer.parseInt(br.readLine().trim());
		tree = new Node[cnt];

		for (int loop = 0; loop < cnt; loop++) {
			st = new StringTokenizer(br.readLine().trim());
			char parent = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			if (tree[parent - 'A'] == null)
				tree[parent - 'A'] = new Node(parent);
			if (left != '.') {
				tree[left - 'A'] = new Node(left);
				tree[parent - 'A'].left = tree[left - 'A'];
			}
			if (right != '.') {
				tree[right - 'A'] = new Node(right);
				tree[parent - 'A'].right = tree[right - 'A'];
			}
		}
		pre(tree[0]);
		sb.append("\n");
		in(tree[0]);
		sb.append("\n");
		post(tree[0]);

		System.out.println(sb);
	}

	static void pre(Node start) {
		if (start == null)
			return;
		sb.append((char) start.num);
		pre(start.left);
		pre(start.right);
	}

	static void in(Node start) {
		if (start == null)
			return;
		in(start.left);
		sb.append((char) start.num);
		in(start.right);

	}

	static void post(Node start) {
		if (start == null)
			return;
		post(start.left);
		post(start.right);
		sb.append((char) start.num);

	}

}

import java.util.*;

public class Iteration {
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> st = new Stack<TreeNode>();
		TreeNode node = root;
		while (st.size() > 0 || node != null) {
			if (node != null) {
				st.push(node);
				node = node.left;
			} else {
				node = st.pop();
				result.add(node.val);
				node = node.right;
			}
		}
		return result;
    }

	public ArrayList<Integer> preorderTraversal(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> st = new Stack<TreeNode>();
		if (root != null) {
			st.push(root);
		}
		while (st.size() > 0) {
			TreeNode node = st.pop();
			result.add(node.val);
			if (node.right != null) {
				st.push(node.right);
			}
			if (node.left != null) {
				st.push(node.left);
			}
		}
		return result;
	}

	public ArrayList<Integer> postorderTraversal(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}
		Stack<TreeNode> st = new Stack<TreeNode>();
		st.push(root);
		TreeNode prev = null;
		TreeNode curr = null;
		while (st.size() > 0) {
			curr = st.peek();
			if (prev == null || prev.left == curr || prev.right == curr) {
				if (curr.left != null) {
					st.push(curr.left);
				} else if (curr.right != null) {
					st.push(curr.right);
				}
			} else if (curr.left == prev) {
				if (curr.right != null) {
					st.push(curr.right);
				}
			} else {
				result.add(curr.val);
				st.pop();
			}
			prev = curr;
		}
		return result;
	}

	public static void main(String args[]) {

		int [] num = new int [] { 1, 2, 3, 4, 5, 6, 7};
		TreeNode [] nodes = new TreeNode[num.length];
		nodes[0] = new TreeNode(num[0]);
		for (int i = 1; i < num.length; ++i) {
			nodes[i] = new TreeNode(num[i]);
			if (i % 2 != 0) {
				nodes[(i - 1) / 2].left = nodes[i];
			} else {
				nodes[(i - 1) / 2].right = nodes[i];
			}
		}
        System.out.print("inorder   : ");
		System.out.println(new Iteration().inorderTraversal(nodes[0]));
        System.out.print("preorder  : ");
		System.out.println(new Iteration().preorderTraversal(nodes[0]));
        System.out.print("postorder : ");
		System.out.println(new Iteration().postorderTraversal(nodes[0]));

	}
}

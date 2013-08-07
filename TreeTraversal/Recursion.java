import java.util.*;

public class Recursion {
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }
        
        result.addAll(inorderTraversal(root.left));
        result.add(root.val);
        result.addAll(inorderTraversal(root.right));
        
        return result;
    }

	public ArrayList<Integer> preorderTraversal(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }
		result.add(root.val);
		result.addAll(preorderTraversal(root.left));
		result.addAll(preorderTraversal(root.right));
		
		return result;
	}

	
	public ArrayList<Integer> postorderTraversal(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }
		result.addAll(postorderTraversal(root.left));
		result.addAll(postorderTraversal(root.right));
		result.add(root.val);
		
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
        System.out.println(new Recursion().inorderTraversal(nodes[0]));
		System.out.print("preorder  : ");
        System.out.println(new Recursion().preorderTraversal(nodes[0]));
		System.out.print("postorder : ");
        System.out.println(new Recursion().postorderTraversal(nodes[0]));
    }

}

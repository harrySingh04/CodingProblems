package main.java.July2024_problems;


public class TreeNodeMain {

    public class TreeNode{
     int val;
      TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
}

public static void main(String[] args){

    int[] inorder = new int[]{9,3,15,20,7};
    int [] preorder = new int[]{3,9,20,15,7};

    TreeNodeMain t1 = new TreeNodeMain();

    t1.buildTree(inorder,preorder);

}

    private TreeNode buildTree(int[] inorder, int[] preorder) {

        return solve(inorder, preorder,0, preorder.length-1, 0);
    }


    public TreeNode solve(int[] inOrder, int[] preOrder, int start, int end, int idx){

        if(start > end)
             return null;


        int i = start; //2
        int rootVal = preOrder[idx]; //3

        for(;i<=end;i++){
        if(inOrder[i] == rootVal)
            break;
        }

        idx++;

        TreeNode root = new TreeNode(rootVal);
        root.left = solve(inOrder,preOrder,start, i-1, idx);
        root.right = solve(inOrder,preOrder, i+1, end, idx);

        return root;
        }
}


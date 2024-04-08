public class Tree {
    Node root;

    public void add(int val){
        root = addRecursive(root, val);
    }
    public boolean containsNode(int val){
        return containsNodeRecursive(root, val);
    }

    public void doInOder(){
        inOrder(root);
    }
    public void doPostOrder(){
        postOrder(root);
    }
    public void doPreOrder(){
        preOrder(root);
    }
    public void delete(int val){
        deleteRecursive(root, val);
    }

    private void preOrder(Node node){
        if(node!=null){
            System.out.print(" " + node.val);
            preOrder(node.left);
            preOrder(node.right);
        }

    }
    private void postOrder(Node node){
        if(node!=null){
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(" " + node.val);
        }
    }
    private void inOrder(Node node){
        if(node != null){
            inOrder(node.left);
            System.out.print(" " + node.val);
            inOrder(node.right);
        }
    }
    private Node addRecursive(Node current, int val){
        if(current == null){
            return new Node(val);
        }
        if(val > current.val){
            current.right = addRecursive(current.right, val);
        }
        else if(val < current.val){
            current.left = addRecursive(current.left, val);
        }
        else{
            return current;
        }
        return current;
    }
    private boolean containsNodeRecursive(Node current, int val){
        if(current == null){
            return false;
        }
        if(current.val == val){
            return true;
        }
        else if(val > current.val){
            return containsNodeRecursive(current.right, val);
        }
        else{
            return containsNodeRecursive(current.left, val);
        }
    }
    private Node deleteRecursive(Node current, int val){
        if(val == current.val) {
            if(current.left == null && current.right == null){
                return null;
            }
            else if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            } else {
                int smallestVal = findSmallestVal(current.right);
                current.val = smallestVal;
                current.right = deleteRecursive(current.right, smallestVal);
                return current;
            }
        }
        if(val < current.val){
            current.left = deleteRecursive(current.left, val);
        }
        else {
            current.right = deleteRecursive(current.right, val);
        }
        return current;
    }
    private int findSmallestVal(Node root){
        return root.left == null ? root.val:findSmallestVal(root.left);
    }

}

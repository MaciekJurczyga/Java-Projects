public class Tree {
    Node root;
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
    public void add(int val){
        root = addRecursive(root, val);
    }
    private void inOrder(Node node){
        if(node != null){
            inOrder(node.left);
            System.out.println(" " +node.val);
            inOrder(node.right);
        }
    }
    public void doInOder(){
        inOrder(root);
    }
    private void postOrder(Node node){
        if(node!=null){
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.val);
        }
    }
    public void doPostOrder(){
        postOrder(root);
    }
    private void preOrder(Node node){
        if(node!=null){
            System.out.println(node.val);
            preOrder(node.left);
            preOrder(node.right);
        }
    }
    public void doPreOrder(){
        preOrder(root);
    }

}

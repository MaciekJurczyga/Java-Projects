
public class Main {
    public static void main(String[] args) {
        Tree bt = new Tree();
        bt.add(6);
        bt.add(4);
        bt.add(8);
        bt.add(3);
        bt.add(5);
        bt.add(7);
        bt.add(9);
        System.out.println("In order traversal");
        bt.doInOder();
        System.out.println("Post order traversal");
        bt.doPostOrder();
        System.out.println("Pre order traversal");
        bt.doPreOrder();
    }
}
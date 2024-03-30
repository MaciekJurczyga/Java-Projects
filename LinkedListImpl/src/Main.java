public class Main {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.insert(5);
        linkedList.insert(2);
        linkedList.insert(6);
        linkedList.insert(11);
        linkedList.insert(6);

        linkedList.display();
        System.out.println();
        linkedList.remove(6);

        linkedList.display();
        linkedList.reverse();
        System.out.println();
        linkedList.display();
    }
}
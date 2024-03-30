public class LinkedList {
    Node head;

    private static class Node{
        int val;
        Node next;

        public Node(int val){
            this.val = val;
            next = null;
        }
    }
    public void insert(int val){
        if(head == null){
            head = new Node(val);
        }
        else{
            Node cur = head;
            while(cur.next != null){
                cur = cur.next;
            }
            cur.next = new Node(val);
        }
    }
    public void remove(int val){
        Node cur = head;
        Node prev = null;
        while(cur!=null){
            if(cur.val == val){
                if(prev == null){
                    head = cur.next;
                    cur = cur.next;
                }
                else{
                    prev.next = cur.next;
                    cur = cur.next;
                }
            }
            else{
                prev = cur;
                cur = cur.next;
            }
        }

    }

    public void display(){
        Node cur = head;
        while(cur != null){
            System.out.print(cur.val + " -> ");
            cur = cur.next;
        }
        System.out.print(" null");
    }
}

public class LinkList {
    public static class Node{
        private int value;
        private Node next;

        public Node(int data){
            this.value=data;
        }
    public static class reverseList{
            public static Node ReverseList(Node  head){
                Node pre=null;
                Node next=null;
                while(head.next!=null){
                    next=head.next;
                    head.next=pre;
                    pre=head;
                    head=next;
                }
                return  pre;
            }
        }

    }
    public static  class isPaLindromeList{

    }
}

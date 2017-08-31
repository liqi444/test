package exercise.danxianglianbiao;

/**
 * Created liqi on 2017/8/16.
 */
public class Test {

    public static void main(String[] args) {
        Node head = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        head.setNext(n1);
        n1.setNext(n2);
        n2.setNext(n3);

        Node headCopy = head;
        while (headCopy != null) {
            System.out.println("1==========" + headCopy.getData());
            headCopy = headCopy.getNext();
        }

        head = Reverse(head);


        while (head!= null) {
            System.out.println("2==========" + head.getData());
            head = head.getNext();
        }
    }

    /**
     * 将指针方向反转
     * @param head
     * @return
     */

    public static Node Reverse(Node head) {
        if (head == null) {
            return head;
        }

        Node pre = head;//设置第一位数据
        Node cur = head.getNext();  //设置第二位数据
        Node temp;//临时存储位置
        while (cur != null) {
            temp = cur.getNext(); //将第三位数据存储
            cur.setNext(pre);//将指针方向反转
            pre = cur;//翻转后将将数据后移一位
            cur = temp;//翻转后将将数据后移一位
        }
        head.setNext(null);//将第一个数据的指针删除
        return pre;
    }
}

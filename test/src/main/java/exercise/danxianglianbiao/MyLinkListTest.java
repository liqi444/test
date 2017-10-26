package exercise.danxianglianbiao;

/**
 * Created liqi on 2017/8/16.
 */
public class MyLinkListTest {
    public static void main(String[] args){
        MyLinkList<Object> linkList = new MyLinkList<>();
        linkList.add("1");
        linkList.add("2");
        linkList.add("3");
        linkList.add("4");
        linkList.add("5");
        linkList.add(1);
        System.out.print(linkList.toString());
        System.out.println(linkList.reverse().toString());
    }


}

class MyLinkList<E> {
    Node first;
    Node last;

    Node node = this.first;

    private static class Node<E>{
        E item;
        Node next;

        public Node(E item,Node next){
            this.item = item;
            this.next = next;
        }
    }

    public void add(E e){
        Node l = last;
        Node newNode = new Node(e,null);
        last = newNode;
        if(l == null){
            first = newNode;
            node = this.first;
        }else{
            l.next = newNode;
        }
    }
    public boolean hasNext(){
        if(node == null||node.next == null){
            return false;
        }
        return true;
    }

    public MyLinkList reverse(){
        Node f = this.first;
        Node l = this.last;


        if (l == null) {
            return this;
        }

        Node pre = f;//设置第一位数据
        Node cur = f.next;  //设置第二位数据
        Node temp;//临时存储位置
        while (cur != null) {
            temp = cur.next; //将第三位数据存储
            cur.next=pre;//将指针方向反转
            pre = cur;//翻转后将将数据后移一位
            cur = temp;//翻转后将将数据后移一位
        }
        f.next=null;//将第一个数据的指针删除
        return this;


    }

    public String toString(){
        String returnStr = "error";
        String str = "[";
        while(true){
            if("[".equals(str)){
                str += node.item;
            }else{
                str += ","+node.item;
            }
            if(!this.hasNext()){
                break;
            }
            node = node.next;
        }
        str +="]";
        if(!"[]".equals(str)){
            returnStr = str;
        }
        return returnStr;
    }
}


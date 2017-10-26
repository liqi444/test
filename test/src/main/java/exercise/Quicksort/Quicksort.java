package exercise.Quicksort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created liqi on 2017/8/17.
 */
public class Quicksort {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
//        list.add(9);
//        list.add(8);
//        list.add(7);
//        list.add(6);
//        list.add(5);
//        list.add(4);
//        list.add(3);
//        list.add(2);
//        list.add(1);
//        list.add(0);

        list.add(1);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(7);
        list.add(2);
        list.add(6);
        list.add(0);
        list.add(8);
        list.add(9);

        int[] array = new int[]{1, 3, 4, 5, 7, 2, 6, 0, 8, 9};

        System.out.println(list);
        Quicksort qs = new Quicksort();
//        qs.quickSort3(list, 0, list.size() - 1);
//        qs.quickSort5(list, 0, list.size() - 1);
        qs.exercise(list, 0, list.size() - 1);
//        qs.sort(array,0,list.toArray().length-1);


//        for (int i:
//             array) {
//            System.out.print(i+",");
//        }

        System.out.println(list);
    }


    void quickSort(List<Integer> list) {
        int key = list.get(0);
        int size = list.size();
        for (int ii = 0; ii < size; ii++) {
            int i = ii;
            int j = size - i - 1;
            int asc = list.get(i);
            int des = list.get(j);
            if (des < key) {
                int temp = asc;
                list.set(i, list.get(j));
                list.set(j, temp);
            }
            if (asc > key) {
                int temp = asc;
                list.set(i, list.get(j));
                list.set(j, temp);
            }
            if (i == j) {
                System.out.println("i == j");
                quickSort(list);
                return;
            }

        }
    }

    void quickSort2(List<Integer> list) {
        int key = list.get(0);
        int size = list.size();
        int i = 0;
        int j = size - 1;
        while (true) {
            int asc = list.get(i);
            int des = list.get(j);
            while (true) {
                if (des < key) {
                    int temp = asc;
                    list.set(i, list.get(j));
                    list.set(j, temp);
                    break;
                } else {
                    j = j - 1;
                    if (i == j) {
                        break;
                    }
                }
            }
            while (true) {
                if (asc > key) {
                    int temp = asc;
                    list.set(i, list.get(j));
                    list.set(j, temp);
                    break;
                } else {
                    i = i + 1;
                    if (i == j) {
                        break;
                    }
                }
            }
            if (i == j) {
                if (i > 0) {
                    j = i - 1;
                    i = 0;
                }
                if (j < size) {
                    i = j + 1;
                    j = size - 1;
                }
                if (i <= 0 || j >= size) {
                    return;
                }
                System.out.println("i == j " + list);
                quickSort2(list);
                return;
            }
        }
    }

    public void sort(int arr[], int low, int high) {
        int l = low;
        int h = high;
        int povit = arr[low];

        while (l < h) {
            while (l < h && arr[h] >= povit)
                h--;
            if (l < h) {
                int temp = arr[h];
                arr[h] = arr[l];
                arr[l] = temp;
                l++;
            }

            while (l < h && arr[l] <= povit)
                l++;

            if (l < h) {
                int temp = arr[h];
                arr[h] = arr[l];
                arr[l] = temp;
                h--;
            }
        }
        System.out.println(arr);
        System.out.println("l=" + (l + 1) + "h=" + (h + 1) + "povit=" + povit + "\n");
        if (l > low) sort(arr, low, l - 1);
        if (h < high) sort(arr, l + 1, high);
    }

    void quickSort3(List<Integer> list, int start, int end) {
        //设置游标
        int l = start;
        int h = end;
        //设置比较值
        int key = list.get(start);
        while (l < h) {
            //从后向前比较，当前值如果小于key，游标指向的对象交换位置
            while (l < h && list.get(h) >= key)
                h--;
            if (key > list.get(h)) {
                Integer temp = list.get(l);
                list.set(l, list.get(h));
                list.set(h, temp);
                System.out.println(list);
            }
            //从后向前比较，当前值如果大于key，游标指向的对象交换位置
            while (l < h && list.get(l) <= key)
                l++;
            if (key < list.get(l)) {
                Integer temp = list.get(l);
                list.set(l, list.get(h));
                list.set(h, temp);
                System.out.println(list);
            }
        }
        if (l > start) {
            quickSort3(list, start, l - 1);
        }
        if (h < end) {
            quickSort3(list, l + 1, end);
        }
    }


    void quickSort4(List<Integer> list) {
        Integer key = list.get(0);
        int start = 0;
        int end = list.size() - 1;
        int i = 0;
        int j = 0;
        while (start < end) {
            boolean b = false;
            if (list.get(end) < key) {
                j = end;
                b = true;
            } else {
                if (start < end) {
                    end--;
                    System.out.println(key + "end--" + end);
                    if (start == end/*&&end!=0*/) {
                        if (list.get(end) < list.get(0)) {
                            Integer temp = list.get(end);
                            list.set(end, list.get(0));
                            list.set(0, temp);
                            System.out.println(list);
                        }
                        quickSort4(list);
                    }
                    continue;
                }
            }
            if (list.get(start) > key) {
                i = start;
                b = true;
            } else {
                if (start < end) {
                    start++;
                    System.out.println(key + "start++" + start);
                    if (start == end/*&&start!=list.size()-1*/) {
                        if (list.get(end) < list.get(0)) {
                            Integer temp = list.get(end);
                            list.set(end, list.get(0));
                            list.set(0, temp);
                            System.out.println(list);
                        }
                        quickSort4(list);
                    }
                    continue;
                }
            }
            if (b) {
                Integer temp = list.get(j);
                list.set(j, list.get(i));
                list.set(i, temp);
                System.out.println(list);
            }
            System.out.println(start + "||" + end);
            if (start == end) {
                System.out.println("==");
                if (list.get(end) < list.get(0)) {
                    Integer temp = list.get(end);
                    list.set(end, list.get(0));
                    list.set(0, temp);
                    System.out.println(list);
                }
                quickSort4(list);
            }
        }

    }

    //定义全局变量，这两个变量需要在子函数中使用
    void quicksort(int[] a, int left, int right) {
        int i, j, t, temp;
        if (left > right)
            return;

        temp = a[left]; //temp中存的就是基准数
        i = left;
        j = right;
        while (i != j) {
            //顺序很重要，要先从右边开始找
            while (a[j] >= temp && i < j)
                j--;
            //再找右边的
            while (a[i] <= temp && i < j)
                i++;
            //交换两个数在数组中的位置
            if (i < j) {
                t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        //最终将基准数归位
        a[left] = a[i];
        a[i] = temp;

        quicksort(a, left, i - 1);//继续处理左边的，这里是一个递归的过程
        quicksort(a, i + 1, right);//继续处理右边的 ，这里是一个递归的过程
    }

    void quickSort5(List<Integer> list, int start, int end) {
        int i, j;
        if (start > end) return;
        Integer key = list.get(start);//基准数
        i = start;
        j = end;
        while (i != j) {
            //顺序很重要，要先从右边开始找
            while (list.get(j) >= key && i < j)
                j--;
            //再找左边的
            while (list.get(i) <= key && i < j)
                i++;
            //交换两个数在数组中的位置
            if (i < j) {
                Integer temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        //最终将基准数归位
        list.set(start, list.get(i));
        list.set(i, key);
        quickSort5(list, start, i - 1);//继续处理左边的，这里是一个递归的过程
        quickSort5(list, i + 1, end);//继续处理右边的 ，这里是一个递归的过程
    }





    void exercise(List<Integer> list,int start,int end){

        if(start>end) return;

        Integer key = list.get(start);
        int l = start;
        int h = end;
        while (l !=h){
            while(l<h&&list.get(h)>=key)
                h--;
            while(l<h&&list.get(l)<=key)
                l++;
            Integer temp = list.get(l);
            list.set(l,list.get(h));
            list.set(h,temp);
        }
        list.set(start,list.get(l));
        list.set(l,key);
        if(l>start) exercise(list,start,l-1);
        if(h<end) exercise(list,l+1,end);
    }








































}

class Single {
    static class SingleHolder {
        public static Single instance() {
            return new Single();
        }
    }

    private Single() {
    }

    public static void main(String[] args) {
        Single single = SingleHolder.instance();
    }
}

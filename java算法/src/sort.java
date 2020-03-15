import java.util.*;
public class sort{
    public static void swap(int i,int j){
        i=i^j;
        j=j^i;
        i=i^j;
    }

    public  static void swap(int[] num,int i,int j){
        num[i]=num[i]^num[j];
        num[j]=num[j]^num[i];
        num[i]=num[i]^num[j];
    }

    /**
     *  冒泡排序
     */
    public static void sort1(int[] num){
        for(int i=0;i<num.length-1;i++){
            for (int j=0;j<num.length-1-i;j++){
                if(num[j+1]<num[j])  swap(num,j,j+1);
            }
        }
    }

    /**
     * 选择排序
     */
    public  static  void sort2(int[] num){
        int min=num[0];
        for(int i=0;i<num.length-1;i++){
            for(int j=i;j<num.length-1;j++){
                if(min>num[i]) swap(num,min,i);
            }
        }
    }

    /**
     *  插入排序
     */
    public static void sort3(int[] num, int i, int r){
        for (int a=0;a<num.length-1;a++){
            for(int j=a;j>=0 && num[j]>num[j+1];j--){
                swap(num,j,j+1);
            }
        }
    }

    /**
     * 快速排序以及随机快排
     */
    public  static int partition(int num[],int l,int r){

        int temp=num[l];
        while (l<r){
            while (l<r&& num[r]>temp) r--;
            num[l]=num[r];
            while(l<r&& num[l]<=temp) l++;
            num[r]=num[l];
        }
        num[l]=temp;
        return l;
    }
        public static void sort1(int num[],int l,int r){
            if (l<r){
                int p=partition(num,l,r);
                sort1(num,0,p-1);
                sort1(num,p+1,r);
            }
        }

    /**
     * 归并排序
     */
    public static void mergeSort(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    public static void merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }

    /**
     *堆排序
     */
        public static void heapSort(int[]arr){
            for(int i=arr.length-1;i>=0;i--)
            {
                maxHeap(arr,arr.length,i);
            }
            //先把数组中的第0个和堆中的最后一个数交换位置，再把前面的处理为大顶堆
            for(int i=arr.length-1;i>0;i--)
            {
                swap(arr,0,i);
                maxHeap(arr,i,0);
            }
        }
        public static void maxHeap(int[]arr ,int size,int index){
            //左子节点
            int leftNode=2*index+1;
            //右子节点
            int rightNode=2*index+2;
            int max=index;
            //和两个子节点分别对比，找出最大的节点
            if(leftNode<size&&arr[leftNode]>arr[max])
            {
                max=leftNode;
            }
            if(rightNode<size&&arr[rightNode]>arr[max])
            {
                max=rightNode;
            }
            //交换位置
            if(max!=index)
            {
                swap(arr,index,max);
                //交换位置以后，可能会破坏之前排好的堆，所以，之前拍好的堆需要重新调整
                maxHeap(arr,size,max);
            }

        }

    /**
     *桶排序例题
     *若干数字组成的长度为n的数组中，求两个相邻的值差值最大的数字
     * @return
     */
    //需要返回元素是第几个桶
    public  static int gapNum(int[] num,int max,int min,int len,int i){
        //求距离最小的数字有多大，除以桶的最大差值乘以桶的长度
        return (num[i]-min)*len/(max-min);
    }

    public  static int Gap(int[] num){
        int Max=0;
        int Min=0;
        //先找出数组中的最大值和最小值来确定前后两个桶
        for (int i=0;i<num.length-1;i++){
            Max= Math.max(num[i], num[i+1]);
            Min=Math.min(num[i],num[i+1]);
        }
        //开始准备桶了，需要准备一个数组记录每个桶的最大值，最小值以及桶是否为空三个信息
        int[] maxGap=new int[num.length+1];
        int[] minGap=new int[num.length+1];
        boolean[] hasNum=new boolean[num.length+1];
        //将每个元素放入桶，需要注意辅助数组也要放进去，需要注意
        for (int i=0;i<num.length;i++){
            //先将元素放入桶中,所以呢需要先求出每个元素是第几个桶
            int id=gapNum(num,Max,Min,num.length,i);
            maxGap[id]=Math.max(num[i],maxGap[id]);
            minGap[id]=Math.min(num[i],minGap[id]);
            hasNum[id]=true;
        }
        //需要在桶中寻找空桶，根据鸽笼原理，必有空桶，注意未必有一个空桶哦
        int lastMax; // 需要记录上一个桶的最大值
        int j=0;
        int chazhi=0;
        while(j<num.length){
            while(hasNum[j]) j++;
            lastMax=maxGap[j-1];
            while(!hasNum[j]) j++;
            int a=Math.max(minGap[j]-lastMax,chazhi);
        }
        return chazhi;
    }
    //for text
    public static void main(String[] args) {
        int []arr=new int[] {9,6,8,7,0,1,10,4,2};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));

    }




}

import java.util.LinkedList;
import java.util.Queue;
import  java.util.Stack;
public class queueStack {
    /**
     *数组实现栈结构
     */
    public class MyStack{
        //Integer默认为null 是int类型的包装类
        private Integer size;
        private  Integer[] num;
        //每个数组都应该是独立的所以是prative
        public void myStack(int intsize){
            num=new Integer[intsize];
            size=0;
        }

        public int peek(){
            if(size==0){
                System.out.println("null");
            }
            return num[size-1];
        }

        public  void push(int pushint){
            num[++size]=pushint;
            System.out.println("push successful");
        }

        public  int pop(){
            if(size==0){
                System.out.println("empty");
            }
            return  num[--size];
        }

    }
    public class ArrayQueue {
            private Integer[] arr;
            private Integer size;
            private Integer first;
            private Integer last;

            public ArrayQueue(int initSize) {
                if (initSize < 0) {
                    throw new IllegalArgumentException("The init size is less than 0");
                }
                arr = new Integer[initSize];
                size = 0;
                first = 0;
                last = 0;
            }

            public Integer peek() {
                if (size == 0) {
                    return null;
                }
                return arr[first];
            }

            public void push(int obj) {
                if (size == arr.length) {
                    throw new ArrayIndexOutOfBoundsException("The queue is full");
                }
                size++;
                arr[last] = obj;
                last = last == arr.length - 1 ? 0 : last + 1;
            }

            public Integer poll() {
                if (size == 0) {
                    throw new ArrayIndexOutOfBoundsException("The queue is empty");
                }
                size--;
                int tmp = first;
                first = first == arr.length - 1 ? 0 : first + 1;
                return arr[tmp];
            }
        }
    public  class stackToQueue{
        private Stack<Integer> s1,s2;
        public void push(int newNum){
            s1.push(newNum);
        }
        public  int poll(){
            if (s1.empty()&&s2.empty()){
                System.out.println("err");
            }else if (s2.empty()){
                while (!s1.empty()){
                    s2.push(s1.pop());
                }
            }
            return s2.pop();
        }
    }
    public  class  queueTostack{

    }
    public static class  getMinStack{
        private Stack<Integer> s1,s2;
        //java实例化对象
        public getMinStack(){
            this.s1=new Stack<Integer>();
            this.s2=new Stack<Integer>();
        }

        public  void push(int newNum){
            if(s2.empty()){
                s2.push(newNum);
            }else if(s2.peek()>=newNum){
                s2.push(newNum);
            }
            s1.push(newNum);
        }
        //这里注意，出栈需要考虑这个元素是不是栈里面的最小值
        public int pop() {
            if (this.s1.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            int value = this.s1.pop();
            if (value == this.getmin()) {
                this.s2.pop();
            }
            return value;
        }

        public Integer getmin(){
            return s2.peek();
        }
    }
    public  static class catDogQueue {

        public static class Pet {
            private String type;

            public Pet(String type) {
                this.type = type;
            }

            public String getPetType() {
                return this.type;
            }
        }

        public static class Dog extends Pet {
            public Dog() {
                super("dog");
            }
        }

        public static class Cat extends Pet {
            public Cat() {
                super("cat");
            }
        }

        public static class PetEnterQueue {
            private Pet pet;
            private long count;

            public PetEnterQueue(Pet pet, long count) {
                this.pet = pet;
                this.count = count;
            }

            public Pet getPet() {
                return this.pet;
            }

            public long getCount() {
                return this.count;
            }

            public String getEnterPetType() {
                return this.pet.getPetType();
            }
        }

        public static class DogCatQueue {
            private Queue<PetEnterQueue> dogQ;
            private Queue<PetEnterQueue> catQ;
            private long count;

            public DogCatQueue() {
                this.dogQ = new LinkedList<PetEnterQueue>();
                this.catQ = new LinkedList<PetEnterQueue>();
                this.count = 0;
            }

            public void add(Pet pet) {
                if (pet.getPetType().equals("dog")) {
                    this.dogQ.add(new PetEnterQueue(pet, this.count++));
                } else if (pet.getPetType().equals("cat")) {
                    this.catQ.add(new PetEnterQueue(pet, this.count++));
                } else {
                    throw new RuntimeException("err, not dog or cat");
                }
            }

            public Pet pollAll() {
                if (!this.dogQ.isEmpty() && !this.catQ.isEmpty()) {
                    if (this.dogQ.peek().getCount() < this.catQ.peek().getCount()) {
                        return this.dogQ.poll().getPet();
                    } else {
                        return this.catQ.poll().getPet();
                    }
                } else if (!this.dogQ.isEmpty()) {
                    return this.dogQ.poll().getPet();
                } else if (!this.catQ.isEmpty()) {
                    return this.catQ.poll().getPet();
                } else {
                    throw new RuntimeException("err, queue is empty!");
                }
            }

            public Dog pollDog() {
                if (!this.isDogQueueEmpty()) {
                    return (Dog) this.dogQ.poll().getPet();
                } else {
                    throw new RuntimeException("Dog queue is empty!");
                }
            }

            public Cat pollCat() {
                if (!this.isCatQueueEmpty()) {
                    return (Cat) this.catQ.poll().getPet();
                } else
                    throw new RuntimeException("Cat queue is empty!");
            }

            public boolean isEmpty() {
                return this.dogQ.isEmpty() && this.catQ.isEmpty();
            }

            public boolean isDogQueueEmpty() {
                return this.dogQ.isEmpty();
            }

            public boolean isCatQueueEmpty() {
                return this.catQ.isEmpty();
            }

        }
    }
    //for text
    public static void main(String[] args) {
        getMinStack stack1 = new getMinStack();
        stack1.push(3);
        System.out.println(stack1.getmin());
        stack1.push(4);
        System.out.println(stack1.getmin());
        stack1.push(1);
        System.out.println(stack1.getmin());
        System.out.println(stack1.pop());
        System.out.println(stack1.getmin());
        System.out.println("=============");

    }
}
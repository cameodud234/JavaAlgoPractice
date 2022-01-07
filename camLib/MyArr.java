package camLib;
import java.util.ArrayList;
import java.util.Random;

public class MyArr {

    public MyArr(int size){
        this.size = size;
        this.usedSize = 0;
        this.arr = new long[size];
    }

    public MyArr(long[] arr){
        this.size = arr.length;
        this.usedSize = arr.length;
        this.arr = new long[arr.length];
        for(int i = 0; i < arr.length; i++){
            this.arr[i] = arr[i];
        }
    }

    public MyArr(ArrayList<Long> arr){
        this.size = arr.size();
        this.usedSize = arr.size();
        this.arr = new long[arr.size()];
        for(int i = 0; i < arr.size(); i++){
            this.arr[i] = arr.get(i);
        }
    }

    public void fillRandom(long range){
        Random rand = new Random();
        for(int i = 0; i < this.getMaxSize(); i++){
            this.arr[i] = rand.nextLong(range);
            this.usedSize++;
        }
    }

    public long add(long value) {
        if(this.usedSize == this.size){
            int newSize = 2 * this.size;
            long[] newArr = new long[newSize];
            for(int i = 0; i < this.usedSize; i++){
                newArr[i] = this.arr[i];
            }
            this.arr = newArr;
            this.size = newSize;
        }

        this.arr[this.usedSize] = value;

        this.usedSize++;
        
        return value;
    }

    public long[] add(long[] arr) {
        int addSize = arr.length;
        if(this.usedSize + addSize >= this.size){
            int newSize = 2 * (this.size + addSize);
            long[] newArr = new long[newSize];
            for(int i = 0; i < this.usedSize; i++){
                newArr[i] = this.arr[i];
            }
            for(int i = 0; i < addSize; i++){
                newArr[i + this.usedSize] = arr[i];
            }
            this.arr = newArr;
            this.size = newSize;
        }
        else{
            for(int i = 0; i < addSize; i++){
                this.arr[i + this.usedSize] = arr[i];
            }
        }
        this.usedSize += addSize;
        return arr;
    }

    public ArrayList<Long> add(ArrayList<Long> arr){
        int addSize = arr.size();
        if(this.usedSize + addSize >= this.size){
            int newSize = 2 * (this.size + addSize);
            long[] newArr = new long[newSize];
            for(int i = 0; i < this.usedSize; i++){
                newArr[i] = this.arr[i];
            }
            for(int i = 0; i < addSize; i++){
                newArr[i + this.usedSize] = arr.get(i);
            }
            this.arr = newArr;
            this.size = newSize;
        }
        else{
            for(int i = 0; i < addSize; i++){
                this.arr[i + this.usedSize] = arr.get(i);
            }
        }
        this.usedSize += addSize;
        return arr;
    }

    public MyArr add(MyArr arr){
        int addSize = arr.getSize();
        if(this.usedSize + addSize >= this.size){
            int newSize = 2 * (this.size + addSize);
            long[] newArr = new long[newSize];
            for(int i = 0; i < this.usedSize; i++){
                newArr[i] = this.arr[i];
            }
            for(int i = 0; i < addSize; i++){
                newArr[i + this.usedSize] = arr.getAt(i);
            }
            this.arr = newArr;
            this.size = newSize;
        }
        else{
            for(int i = 0; i < addSize; i++){
                this.arr[i + this.usedSize] = arr.getAt(i);
            }
        }
        this.usedSize += addSize;
        return arr;
    }

    public long getAt(int index){
        if(index > this.usedSize || index < 0){
            System.err.println("index must be within MyArr object [0, getSize]");
            return Long.MIN_VALUE;
        }
        return this.arr[index];
    }

    // slow as hell 
    public void bubbleSort(){
        if(this.isSorted(false) || this.isSorted(true)){
            System.out.println("Already sorted!");
            return;
        }
        for(int i = 0; i < this.usedSize; i++){
            for(int j = this.usedSize - 1; j > i; j--){
                if(this.arr[j] < this.arr[j - 1]){
                    swap(j, j - 1);
                }
            }
        }
    }

    // still slow 
    public void selectionSort(){
        if(this.isSorted(false) || this.isSorted(true)){
            System.out.println("Already sorted!");
            return;
        }
        for(int i = 0; i < this.usedSize; i++){
            int pos = this.minPos(i, this.usedSize);
            if(this.arr[pos] < this.arr[i]) this.swap(pos, i);
        }
    }

    public void insertionSort(){
        if(this.isSorted(false) || this.isSorted(true)){
            System.out.println("Already sorted!");
        }
        int j = this.usedSize - 1;
        for(int i = 0 ; i < this.usedSize; i++){
            while(j > i && this.arr[j] > this.arr[i]){

            }
        }
    }

    final private int minPos(int start, int end){
        int minP = start;
        for(int i = start + 1; i < end; i++){
            if(this.arr[i] < this.arr[minP]){
                minP = i;
            }
        }
        return minP;
    }

    private void swap(int pos1, int pos2){
        long tmp = this.arr[pos1];
        this.arr[pos1] = this.arr[pos2];
        this.arr[pos2] = tmp;
    }

    public boolean contains(long value) {
        for(int i = 0; i < this.usedSize; i++){
            if(this.arr[i] == value) return true;
        }
        return false;
    }

    public boolean isSorted(boolean reverse){
        if(!reverse){
            for(int i = 0; i < this.usedSize - 1; i++){
                if(this.arr[i] > this.arr[i+1]) return false;
            }
        }
        else if(reverse){
            for(int i = 0; i < this.usedSize - 1; i++){
                if(this.arr[i] < this.arr[i+1]) return false;
            }
        }
        
        return true;
    }

    // 

    public int binarySearch(long value){
        if((!this.isSorted(true)) || (!this.isSorted(false))){
            System.err.println("Array must be sorted to do binary search");
            return -1;
        }
        int start = 0;
        int end = this.usedSize;
        while(start < end){
            int mid = (start + end)/2;
            if(value == this.arr[mid]){
                return mid;
            }
            else if(value < this.arr[mid]){
                end = mid - 1;
            }
            else if(value > this.arr[mid]){
                start = mid + 1;
            }
        }

        return -1;
        // int mid = (this.usedSize)
    }

    public int getSize(){
        return this.usedSize;
    }

    public int getMaxSize(){
        return this.size;
    }

    public void print(){
        StringBuilder logger = new StringBuilder();
        if(this.usedSize == 0){
            System.out.print("no items to print");
        }
        else if(this.usedSize == 1){
            String tmp = Long.toString(this.arr[0]);
            logger.append(tmp);
        }
        else if(this.usedSize >= 2){
            for(int i = 0; i < this.usedSize - 1; i++){
                String tmp = Long.toString(this.arr[i]);
                logger.append(tmp);
                logger.append(", ");
            }
            long lastElem = this.arr[this.usedSize - 1];
            logger.append(lastElem);
        }

        String out = logger.toString();
        
        System.out.print(out);
    }

    protected int size;
    protected int usedSize;
    protected long[] arr;
    protected boolean sorted;
}
import java.io.*;

public class Main {

    static int[] inputArray;
    static int[] blockArray;

    static void build(){
        int x =0;
        int num =0;
        boolean bool = true;
        do {
            int sum =0;
            for (int i = 0; i < blockArray.length; i++) {
                if (x== (inputArray.length)){
                    bool = false;
                    break;
                }
                sum+=inputArray[x];
                x++;
            }
            if (num == blockArray.length)break;
            blockArray[num] = sum;
            num++;
        }while (bool);
    }

    static void update(int previousNumber,int nextNumber){
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] == previousNumber){
                inputArray[i] = nextNumber;
                blockArray[i/(int) Math.ceil(Math.sqrt(inputArray.length))] = blockArray[i/(int) Math.ceil(Math.sqrt(inputArray.length))]-previousNumber+nextNumber;
            }
        }
    }

    static int query(int startIndex,int endIndex){
        int num = 0;
        startIndex = startIndex-1;
        endIndex = endIndex -1;
        int startBlock = startIndex/(int) Math.ceil(Math.sqrt(inputArray.length));
        int endBlock = endIndex/(int) Math.ceil(Math.sqrt(inputArray.length));

        int sum = 0;
        for (int i = startIndex; i < (startBlock+1)*(int) Math.ceil(Math.sqrt(inputArray.length)); i++) {
            sum+=inputArray[i];
            num++;
        }

        for (int i = (endBlock)*((int) Math.ceil(Math.sqrt(inputArray.length))); i <= endIndex; i++) {
            sum+=inputArray[i];
            num++;
        }

        for (int i = startBlock+1; i < endBlock; i++) {
            sum+=blockArray[i];
            num++;
        }
        return num;
    }

    public static void main(String[] args) throws IOException {
        InputStream in = System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(in));
        inputArray = new int[Integer.parseInt(input.readLine())];
        blockArray = new int[(int) Math.ceil(Math.sqrt(inputArray.length))];
        for (int i = 0; i < inputArray.length; i++) {
            inputArray[i] = i+1;
        }
        build();
        for (int num:blockArray) {
            System.out.println(num);
        }

        update(Integer.parseInt(input.readLine()),Integer.parseInt(input.readLine()));
        for (int num:blockArray) {
            System.out.println(num);
        }

        int num = query(Integer.parseInt(input.readLine()),Integer.parseInt(input.readLine()));
        System.out.println(num);
    }
}

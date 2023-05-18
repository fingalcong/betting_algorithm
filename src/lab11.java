import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class lab11 {
    public int readFile(){
        ArrayList<String> nums = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("data.txt"));
            while (scanner.hasNextLine()) {
                nums.add(scanner.nextLine());
            }
            scanner.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

        int sum = 0;
        for(String s : nums){
            int i = Integer.parseInt(s);
            sum += i;
        }
        int len = nums.size();
        return len;
        //System.out.println(sum / len);
        //return nums;
    }
    /*
    public int sum(ArrayList<String> nums){
        int sum = 0;
        for(String s : nums){
            int i = Integer.parseInt(s);
            sum += i;
        }
        return sum;
    }
    public void main(String [] args){
        //File myfile = new File("data.txt");
        ArrayList<String> ans = readFile();
        int sumOfNum = sum(ans);
        int len = ans.size();
        System.out.println(sumOfNum / len);
    }*/
}

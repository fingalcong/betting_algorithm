import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class lab1 {
    public static ArrayList<String> readFile(){
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
        return nums;
    }

    public static double sum(ArrayList<String> nums){
        double sum = 0;
        for(String s : nums){
            double i = Double.parseDouble(s); //if there is nums that can not be transfered to double, the method parsedouble will return
            //error by itself.
            sum += i;
        }
        return sum;
    }


    public static void main(String [] args){
        //File myfile = new File("data.txt");
        ArrayList<String> ans = readFile();
        double sumOfNum = sum(ans);
        double len = ans.size();
        System.out.println(sumOfNum / len);
    }
}

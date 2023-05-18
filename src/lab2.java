import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class lab2 {
    public static ArrayList<String> readFile(){
        ArrayList<String> strings = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("strings.txt"));
            if(!scanner.hasNextLine()){
                strings.add("empty file");
                return strings;
            }
            while (scanner.hasNextLine()) {
                strings.add(scanner.nextLine());
            }
            scanner.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return strings;
    }

    public static ArrayList<String> copyString(ArrayList<String> strings){
        ArrayList<String> ans = new ArrayList<>();
        for(int i = 0; i < strings.size(); i++){
            String s = strings.get(i);
            ans.add(s + ":");
        }
        return ans;
    }
    public static ArrayList<String> substring(ArrayList<String> strings, ArrayList<String> ans){
        int i = 0;
        //<String> ans = new ArrayList<>();
        while(i < strings.size()){
            String s = strings.get(i);
            //ans.add(s + ":");
            int j = 0;
            while(j < strings.size()){
                if(i == j){
                    j += 1;
                }
                else{
                    if(strings.get(j).contains(s)){
                        ans.set(i,ans.get(i) + " " + strings.get(j));
                    }
                }
                j++;
            }
            i++;
        }
        return ans;
    }

    public static void main(String[] args){
        ArrayList<String> strings = readFile();
        ArrayList<String> res = copyString(strings);
        ArrayList<String> ans = substring(strings, res);
        for(int i = 0; i < ans.size(); i++){
            System.out.println(ans.get(i));
        }
        //System.out.println(ans);
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class lab3 {
    public static String readFile(){
        String text = "";
        try {
            Scanner scanner = new Scanner(new File("strings.txt"));
            //if the file is empty, the expected result would be "|Error: empty file"
            if(!scanner.hasNextLine()){
                return "Error: empty file";
            }
            else {
                text = scanner.nextLine();
                scanner.close();
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return text;
    }

    public static String[] ansMake (String text){
        String[] ans = new String[text.length()];
        for(int i = 0; i < ans.length; i++){
            ans[i] = "";
        }
        return ans;
    }

    public static void ansSpace (HashMap<Integer,Integer> map, Stack<Character> stack, int i, String text, String[] ans){
        if(!map.containsKey(stack.size())){
            map.put(stack.size(), i);
        }
        else{
            if(i - map.get(stack.size()) > 1) {
                ans[stack.size()] += " ";
            }
            map.put(stack.size(), i);
        }
        int num = stack.size();
        ans[num] += text.charAt(i);
    }

    public static String[] ParaIdentify (String text, Stack<Character> stack, int i){
        if(text.charAt(i) == ')') {
            if (stack.peek() == '(') {
                stack.pop();
            } else {
                return new String[]{"mismatched groups!"};
            }
        }
        else if(text.charAt(i) == ']'){
            if(stack.peek() == '['){
                stack.pop();
            }
            else{
                return new String[]{"mismatched groups!"};
            }
        }
        else{
            if(stack.peek() == '{'){
                stack.pop();
            }
            else{
                return new String[]{"mismatched groups!"};
            }
        }
        return new String[1];
    }

    public static String[] s (String text, String[] ans){
        int len = text.length();
        Stack<Character> stack = new Stack<>();
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < len; i++){
            if(text.charAt(i) == '[' || text.charAt(i) == '(' || text.charAt(i) == '{' ) {
                stack.push(text.charAt(i));
            }
            else if(text.charAt(i) == ']' || text.charAt(i) == '}' || text.charAt(i) == ')' ){
                String[] error = ParaIdentify(text, stack, i);
                if(error[0] != null){
                    return error;
                }
            }
            else{
                ansSpace(map, stack, i, text, ans);
            }
        }
        if(!stack.empty()){
            return new String[]{"mismatched groups!"};
        }
        return ans;
    }

    public static void main(String args[]){
        int i = 0;
        String text = readFile();
        String[] ansM = ansMake(text);
        String[] ans = s(text, ansM);
        String addon = "  ";
        String space = "";
        for(String n : ans){
            if(!n.isEmpty()){
                if(i == 0) {
                    System.out.println("|" + n);
                }
                else{
                    space += addon;
                    System.out.println("|" + space + n);
                }
                i++;
            }
        }
    }
}

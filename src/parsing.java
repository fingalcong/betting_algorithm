import java.util.HashMap;
import java.util.Stack;

public class parsing {
    public static boolean isBalanced(String s){
        Stack<Character> stack = new Stack();
        //HashMap<Character,Character> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{'){
                stack.push(s.charAt(i));
            }
            else{
                if(s.charAt(i) == ')') {
                    if (stack.peek() == '(') {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
                else if(s.charAt(i) == ']'){
                    if(stack.peek() == '['){
                        stack.pop();
                    }
                    else{
                        return false;
                    }
                }
                else{
                    if(stack.peek() == '{'){
                        stack.pop();
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        if(!stack.empty()){
            return false;
        }
        return true;
    }
    public static void main(String args[]){
        String s = "[{(}])";
        boolean b = isBalanced(s);
        System.out.println(b);
    }
}

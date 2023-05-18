public class quiz1 {
    public static String divisible(String str1, String str2){
        int len1 = str1.length();
        int len2 = str2.length();
        if(len1 <= len2){
            if(str1 == str2.substring(0,len1)){
                divisible(str1, str2.substring(len1 + 1, len2));
            }
            else{

            }
        }
    /*public static String divisible(String str1, String str2){
        return str1.substring(0,str2.length());*/
        return str1;
    }

    public static void main(String [] args){
        String str1 = "abababab";
        String str2 = "abab";
        String ans = divisible(str1, str2);
        System.out.println(ans);
    }
}

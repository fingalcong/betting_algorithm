public class lab8 {
    public static int minDistance(String word1, String word2) {
        int m=word1.length();
        int n=word2.length();
        int dp[][]=new int[m+1][n+1];
        for(int i=0;i<=m;i++) {
            for(int j=0;j<=n;j++) {
                if(i==0 && j==0) {
                    dp[i][j]=0;
                } else if(i==0) {
                    dp[0][j]=j;
                } else if(j==0) {
                    dp[i][0]=i;
                } else {
                    if(word1.charAt(i-1)==word2.charAt(j-1)) {
                        dp[i][j]=dp[i-1][j-1];
                    } else{
                        dp[i][j]=1+Math.min(dp[i-1][j] ,Math.min(dp[i-1][j-1] ,dp[i][j-1] ));
                    }
                }
            }
        }
        return dp[m][n];
    }
    public static void main(String args[]){
        String word1 = "intention";
        String word2 = "execution";
        int ans = minDistance(word1, word2);
        System.out.print(ans);
    }
}

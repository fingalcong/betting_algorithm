public class lab9 {
    public static int uniquePaths(int m, int n) {
        if(m == 0 || n == 0) return 0;
        int [][] arr = new int[m][n];
        for(int i=0;i<arr[0].length;i++){
            arr[0][i]=1;
        }
        for(int j=0;j< arr.length;j++){
            arr[j][0]=1;
        }
        for(int a=1; a<m;a++){
            for(int b=1;b<n;b++){
                arr[a][b]=arr[a-1][b]+arr[a][b-1];
            }
        }
        return arr[m-1][n-1];
    }
    public static void main(String[] args){
        int m = 33;
        int n = 72;
        int ans = uniquePaths(m,n);
        System.out.print(ans);
    }
}

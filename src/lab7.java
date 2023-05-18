import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;

public class lab7 {
    static HashMap<Integer, ArrayList<Integer>> map;
    public static int[] distanceToCycle(int n, int[][] edges) {
        map = new HashMap();
        int i = 0;
        while(i < edges.length){
            map.putIfAbsent(edges[i][0], new ArrayList<>());
            map.get(edges[i][0]).add(edges[i][1]);
            map.putIfAbsent(edges[i][1], new ArrayList<>());
            map.get(edges[i][1]).add(edges[i][0]);
            i++;
        }
        ArrayList<Integer> path = new ArrayList();
        path.add(0);
        boolean[] visited = new boolean[n];
        visited[0] = true;
        dfs(0,path,-1,visited);
        int start_point = path.indexOf(path.get(path.size() - 1));
        HashSet<Integer> set = new HashSet<>();
        while(start_point < path.size()){
            set.add(path.get(start_point));

            start_point ++;
        }
        return bfs(n, set);
    }
    public static boolean dfs(int a, ArrayList<Integer> path, int b, boolean[] visited){
        for(int num : map.getOrDefault(a, new ArrayList<>())) {
            if(num == b){
                continue;
            }
            path.add(num);
            if(visited[num]){
                return true;
            }
            visited[num] = true;
            if(dfs(num, path, a, visited)){
                return true;
            }
            path.remove(path.size() - 1);
        }
        return false;
    }
    public static int[] bfs(int n, HashSet<Integer> set){
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        for(int u : set) {
            queue.add(u);
            visited[u] = true;
        }

        int[] res = new int[n];

        int step = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int u = queue.poll();
                res[u] = step;

                for(int v : map.getOrDefault(u, new ArrayList<>())) {
                    if(visited[v]){
                        continue;
                    }
                    queue.add(v);
                    visited[v] = true;

                }
            }

            step += 1;
        }

        return res;
    }
    public static void main (String[] args){
        int n = 9;
        int[][] edges = {{0,1},{1,2},{0,2},{2,6},{6,7},{6,8},{1,3},{3,4},{3,5}};
        if(n == 0 || edges.length == 0){
            System.out.print("empty input");
        }
        else {
            int[] ans = distanceToCycle(n, edges);
            int count = 0;
            for (int ans1 : ans) {
                if (count == 0) {
                    System.out.print("[" + ans1);
                    count++;
                    continue;
                }
                System.out.print("," + ans1);
                if (count == ans.length - 1) {
                    System.out.print("]");
                }
                count++;
            }
        }
    }
}

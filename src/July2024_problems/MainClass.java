package July2024_problems;

import java.util.*;

public class MainClass {

    public static void main(String[] args){

        MainClass m1 = new MainClass();

       /* DailyQuestion_July3 solution1 = new DailyQuestion_July3();

        int[][] grid = {{1,1,1,1,1},{1,0,0,0,1},{1,0,1,0,1},{1,0,0,0,1},{1,1,1,1,1}};

        System.out.println(solution1.shortestBridge(grid));*/

        /*int[][] isConnected ={{1,1,0},{1,1,0},{0,0,1}};
        MainClass m1  = new MainClass();
        System.out.println(m1.findCircleNum(isConnected));*/

       /* int[][] network = {{2,1,1},{2,3,1},{3,4,1}};
        MainClass m1  = new MainClass();
        System.out.println(m1.networkDelayTime(network,4,2));*/

        /*int[][] grid= {{0,1},{1,0}};
        System.out.println(m1.shortestPathBinaryMatrix(grid));*/

        System.out.println(m1.wordBreak("catsdog", List.of(new String[]{"cat", "cats","dog"})));




    }


    public int networkDelayTime(int[][] times, int n, int k) {

        List<List<int[]>> adjLists = new ArrayList<>();

        for(int i = 0;i<=n;i++){
            adjLists.add(new ArrayList<>());
        }

        for(int u=0;u<times.length;u++){

            int source = times[u][0];
            int destination = times[u][1];
            int weight = times[u][2];

            adjLists.get(source).add(new int[]{destination,weight});
        }

        int[] time = new int[n+1];
        Arrays.fill(time,Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0] != 0 ? a[0] - b[0] : a[1]-b[1]));

        time[k] = 0;

        pq.add(new int[]{0,k});

        while(pq.size() != 0){

            int[] nextNode = pq.remove();
            int node = nextNode[1];
            int distance = nextNode[0];

            for(int[] v: adjLists.get(node)){

                if(v[1] + distance < time[v[0]])
                {
                    time[v[0]] = v[1] + distance;
                    pq.add(new int[]{v[1] + distance,v[0]});
                }
            }
        }

        int minValue = Integer.MIN_VALUE;

        for(int i = 1;i<=n;i++){
            if(time[i] > minValue)
                minValue = time[i];
        }

        return minValue == Integer.MAX_VALUE ? -1 : minValue;

    }

    public int findCircleNum(int[][] isConnected) {

        int v = isConnected.length;
        boolean[] visited = new boolean[v];
        int count = 0;

        for(int i = 0;i<v;i++){
            if(!visited[i])
            {
                bfsVisit(isConnected, visited, i);
                count++;
            }
        }

        return count;
    }

    public void bfsVisit(int[][] isConnected, boolean[] visited, int u){
        Queue<Integer> queue = new LinkedList<>();

        if(visited[u])
            return;

        visited[u] = true;

        queue.add(u);

        while(!queue.isEmpty()){
            int source = queue.remove();
            for(int i = 0;i<isConnected[source].length;i++){
                if(isConnected[source][i] == 1 && !visited[i] && source != i)
                    queue.add(i);
            }
        }
    }

    int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { 1, 1 }, { -1, 1 }, { 1, -1 }, { -1, -1 } };
    int m, n;

    public boolean isSafe(int x, int y) {

        if (x >= 0 && x < m && y >= 0 && y < n)
            return true;

        return false;
    }


    public int shortestPathBinaryMatrix(int[][] grid) {

        m = grid.length;
        n = grid[0].length;

        if (m == 0 || n == 0 || grid[0][0] != 0)
            return -1;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)-> a[0] - b[0] > 0
                ? a[1] - b[1] > 0 ? a[2] - b[2] : a[1] - b[1] : a[0] - b[0]);


        int[][] result = new int[m][n];
        for(int[] row: result)
            Arrays.fill(row, Integer.MAX_VALUE);

        pq.add(new int[]{0,0,0});
        result[0][0] = 0;

        while(pq.size() != 0){

            int[] nextNode = pq.remove();
            int d = nextNode[0];
            int x = nextNode[1];
            int y = nextNode[2];

            for(int[] dir: directions){
                int x_ = x + dir[0];
                int y_ = y + dir[1];

                int dist = 1;

                if(isSafe(x_,y_) && grid[x_][y_] == 0 && d + dist < result[x_][y_]){
                    pq.add(new int[]{d + dist,x_,y_});
                    result[x_][y_] = d + dist;
                }
            }

        }

        return grid[m-1][n-1] != Integer.MAX_VALUE ?  grid[m-1][n-1] : -1;

    }

    Set<String> wordSet;

    public boolean solve(String s, int idx){

        if(idx == s.length())
            return true;

        if(wordSet.contains(s))
            return true;

        for(int i = idx+1;i <=s.length();i++){

            String temp = s.substring(idx,i);
            if(wordSet.contains(temp) && solve(s,i))
                return true;

        }

        return false;
    }

    public boolean wordBreak(String s, List<String> wordDict) {

        wordSet = new HashSet<>(wordDict);

        return solve(s,0);

    }
}

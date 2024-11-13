package October2024Problems.LeetCodeProblem;

import java.util.*;

class SafestPath {

    int[][] directions = new int[][]{{0,1},{1,0}, {0,-1},{-1,0}};

    public boolean checkSafe(int[][] distance, int safe){

        System.out.println("safe is " + safe);

        if(distance[0][0] < safe)
            return false;

        int n = distance.length;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        queue.add(new int[]{0,0});
        visited[0][0] = true;

        while(!queue.isEmpty()){

            int[] curr = queue.poll();

            int currX = curr[0];
            int currY = curr[1];

            if(currX == n-1 && currY == n- 1)
                return true;

            for(int[] dir: directions){

                int x = currX + dir[0];
                int y = currY + dir[1];

                if(x >=0 && x < n && y>=0 && y <n && !visited[x][y]){
                    if(distance[x][y] < safe)
                        continue;

                    queue.add(new int[]{x,y});
                    visited[x][y] = true;
                }

            }
        }

        return false;
    }

    public int maximumSafenessFactor(List<List<Integer>> grid) {

        int n = grid.size();

        int[][] distanceToNearestThief = new int[n][n];

        for(int i = 0;i<n;i++){
            Arrays.fill(distanceToNearestThief[i], -1); // no one visited
        }

        boolean[][] visited = new boolean[n][n];

        Queue<int[]> queue = new LinkedList<>();

        for(int i=0;i<n;i++){

            for(int j = 0;j<n;j++){
                if(grid.get(i).get(j) == 1){
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                }

            }
        }

        // Apply multi BFS for each theif that we have

        int level = 0;

        while(queue.size() > 0){

            int size = queue.size();

            while(size > 0){

                int[] currPair = queue.poll();
                int currX = currPair[0];
                int currY = currPair[1];
                size--;

                distanceToNearestThief[currX][currY] = level;

                for(int[] dir: directions){

                    int x = currX + dir[0];
                    int y = currY + dir[1];



                    if(x < 0 || x >= n || y < 0 || y >= n || visited[x][y])
                        continue;
                    queue.add(new int[]{x,y});
                    visited[x][y] = true;

                }

            }

            level++;
        }


        // Apply Binary Search to find Minium Safest Path

        int l = 0;
        int r = 400;
        int result = 0;

        while(l < r){

            int mid = l + (r - l)/2;

            if(checkSafe(distanceToNearestThief, mid)){
                result = mid;
                l = mid + 1;
            }
            else{
                r = mid - 1;
            }
        }

        return result;

    }

    public static void main(String[] args){

        int[][] grid = new int[][]{{0,1,1},{0,1,1},{0,0,0}};

        List<List<Integer>> gridList = new ArrayList<>();
        for(int i = 0;i<grid.length;i++){
            List<Integer> list = new ArrayList<>();

            for(int j = 0;j<grid[0].length;j++){
                list.add(grid[i][j]);
            }
            gridList.add(list);
        }
        SafestPath solution = new SafestPath();
        System.out.println(solution.maximumSafenessFactor(gridList));
    }
}

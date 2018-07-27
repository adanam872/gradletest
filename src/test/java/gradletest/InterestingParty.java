package gradletest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class InterestingParty {

    public int hobby(String[] first, String[] second) {
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < first.length; i++) {
            map.put(first[i], 0);
            map.put(second[i], 0);
        }

        for (int i = 0; i < first.length; i++) {
            map.put(first[i], map.get(first[i]) + 1);
            map.put(second[i], map.get(second[i]) + 1);
        }

        int max = 0;
        for (Integer n : map.values()) {
            if (n > max) {
                max = n;
            }
        }

        return max;
    }

    /*
     * 어렵지 않은 문제이지만 이중반복문을 돌리는 것 보다 Map객체를 활용하는 것이 깔끔한 코드를 얻을 수 있다.
     * 
     */

    public long maxProd(int[] numbers) {

        long ret = 1;
        int min = numbers[0];

        for (int n : numbers) {
            ret *= n;
            if (min > n)
                min = n;
        }

        ret /= min;
        ret *= min + 1;

        return ret;
    }

    /*
     * 가장 먼 출구 찾기 (너비우선탐색)
     * 
     * 당신은 미로 제작을 의뢰 받았다. 당신이 제작할 미로는 조금 특이한데 바로 미로에 도전하는 사람의 점프력이 상당히 좋기 때문이다.
     * 이 도전자는 초기에 주어진 int[] moveRow, int[] moveCol에 따라 점프할 수 있는데 미로의 밖으로 나가거나 
     * 미로의 막힌 부분 위에 올라갈 수는 없다.
     * 이 도전자는 항상 최단거리로 움직이는데 당신은 미로의 출구를 가장 오랜시간이 걸리는 곳에 정해야 한다. String 배열로 주어지는
     * maze에는 "."과 "X"로 구분된 미로 지도가 주어진다. 
     * 가장 먼 거리를 움직여야 찾을 수 있는 미로의 출구까지 간 거리를 리턴하는 함수를 만들어라
     */

    public int dist(String[] maze, int startRow, int startCol, int[] moveRow, int[] moveCol) {
        int max = -1;
        int height = maze.length;
        int width = maze[0].length();
        int[][] chk = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                chk[i][j] = -1;
            }
        }
        chk[startRow][startCol] = 0;

        Queue<Integer> queueX = new LinkedList<>();
        Queue<Integer> queueY = new LinkedList<>();
        queueX.add(startRow);
        queueY.add(startCol);

        
        int cnt = 0;
        while (cnt < 10) {
            int x = queueX.poll();
            int y = queueY.poll();
            System.out.println(queueX.poll()+" "+queueY.poll());
            for (int i = 0; i < moveRow.length; i++) {
                int nextX = x + moveRow[i];
                int nextY = y + moveCol[i];
                if (nextX < 0 || nextX >= height) continue;
                if (nextY < 0 || nextY >= width) continue;
                if (maze[nextX].charAt(nextY) == 'X') continue;
                if (chk[nextX][nextY] != -1) continue;
                queueX.add(nextX);
                queueX.add(nextY);
                chk[nextX][nextY] = chk[x][y]+1;
            }
            System.out.println(cnt++);
        }
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(chk[i][j]);
//                if (maze[i].charAt(j) == '.' && chk[i][j] == -1) return -1;
//                if (max < chk[i][j]) max = chk[i][j];
            }
            System.out.println();
        }

        return max;
    }
    
    /*
     * 미로를 찾는 사람은 최단거리를 가지만 리턴해야 하는 값은 미로를 찾는데 걸린 최대 거리이다 
     * 때문에 모든 경로를 다 탐색해 봐야 하는데 한 번 지나온 곳은 다시 통과할 수 없다는 규칙이 있다
     * 최단거리를 가게 되면 같은 장소에 도착했을 때 지나온 경로가 더 길면 다시 방문할 필요가 없게된다
     * 깊이 우선탐색을 사용할 경우 이동 거리를 저장하는 배열을 사용해 가지치기를 한다고 해도 
     * 너비우선 탐색을 이용하는데 비해 시간이 더 오래 걸린다. 
     * 너비우선 탐색을 사용하면 이전에 큐에 저장된 경로는 다시 방문할 필요가 없기 때문이다.
     * 나는 chk 배열을 사용하여 한 번 통과한 곳은 지나지 않게 하는 동시에 이동 거리를 카운트 했다.
     * 자세한 코드는 아래와 같다.
     */

    public static void main(String[] args) {

        String[] maze = {"......","X.X.X..","XXX...X","....X..","X....X.","......."};
        int startRow = 5;
        int startCol = 0;
        int[] moveRow = {1,0,-1,0,-2,1};
        int[] moveCol = {0,-1,0,1,3,0};
        //System.out.println(new InterestingParty().dist(maze, startRow, startCol, moveRow, moveCol));
        
        Queue<Integer> a = new LinkedList<>();
        
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        
        while(!a.isEmpty()) {
            System.out.println(a.poll());
        }
    }
}

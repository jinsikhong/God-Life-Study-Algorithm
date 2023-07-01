import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_6593 {
    static int l, r, c;
    static char map[][][];
    static boolean visited[][][];
    static int[] dl = {0,0,0,0,-1,1};
    static int[] dx = {0,0,1,-1,0,0};
    static int[] dy = {1,-1,0,0,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if(l == 0 && r == 0 && c == 0) {
                break;
            }

            map = new char[l][r][c];
            visited = new boolean[l][r][c];
            Node startNode = null;
            for(int i = 0; i < l; i++){
                for(int j = 0; j < r; j++){
                    char[] arr = br.readLine().toCharArray();
                    for(int z = 0; z < c; z++){
                        if(arr[z] == 'S'){
                            startNode = new Node(i,j,z, 0);
                        }
                        map[i][j][z] = arr[z];
                    }
                }
                br.readLine();
            }
            int res = bfs(startNode);
            if(res == -1){
                System.out.println("Trapped!");
            }else{
                System.out.println("Escaped in " + res + " minute(s).");
            }

        }

    }
    static class Node{
        int l, x, y, d;

        public Node(int l, int x, int y, int d) {
            this.l = l;
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }


    static int bfs(Node s){
        Queue<Node> q = new ArrayDeque<>();
        q.offer(s);
        visited[s.l][s.x][s.y] = true;
        while(!q.isEmpty()){
            Node cur = q.poll();
            if(map[cur.l][cur.x][cur.y] == 'E'){
                return cur.d;
            }
            for(int i = 0; i < 6; i++){
                int nl = cur.l + dl[i];
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nl < 0 || nl >= l || nx < 0 || nx >= r || ny < 0 || ny >= c ){
                    continue;
                }
                if(visited[nl][nx][ny]){
                    continue;
                }
                if(map[nl][nx][ny] == '#'){
                    continue;
                }
                q.add(new Node(nl, nx, ny, cur.d + 1));
                visited[nl][nx][ny] = true;
            }
        }
        return -1;
    }
}

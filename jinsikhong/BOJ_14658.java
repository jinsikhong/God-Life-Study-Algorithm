import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 백준 14658 완전탐색
 * https://www.acmicpc.net/problem/14658
 *
 * 솔루션 참고
 * 이전 풀이 : 별똥별의 최소 x,y 좌표를 알아내서 완전탐색하는 범위를 좁힘
 * -> 반례 만약 별똥별이 0,0 / 50만, 50만 떨어지면 범위를 좁히는 효과가 하나도 없음
 *
 * 솔루션 풀이 참고 : 별을 2중으로 돎
 * ex)1 -> 첫번째 별에서 x좌표를 따고, 두번쪠~ 마지막 별에서 y좌표를 딴 후 그 좌표를 기준으로 L * L 만큼 탐색
 *
 *
 */
public class BOJ_14658 {
    static int N; // 가로길이
    static int M; // 세로길이
    static int L; // 트램펄린 한 변의 길이
    static int K ; // 별똥별 개수
    static ArrayList<Node> lst = new ArrayList(); // 별똥별 좌표를 담을 list

    static int ans;
    static int maxStar;
//    static int[][] map = new int[50001][50001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            lst.add(new Node(x,y));
        }
//        System.out.println(minX + " " + minY + " " + maxX +  " "+maxY);
        for(int i = 0; i < lst.size(); i++){
            for(int j = 0; j < lst.size(); j++) {
                check(lst.get(i).x, lst.get(j).y);
            }
        }
        ans = K - maxStar;
        System.out.println(ans);
    }
    static void check(int x, int y){
        int max = 0;
        for(int i = 0; i < lst.size(); i++){
            Node tempNode = lst.get(i);
            if((x <= tempNode.x && tempNode.x <= x + L) && (y <= tempNode.y && tempNode.y <= y + L)) {
                max++;
            }
        }
        if(max > maxStar){
            maxStar = max;
        }
    }
    static class Node{
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

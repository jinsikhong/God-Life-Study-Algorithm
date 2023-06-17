import java.io.*;

// 메모리초과
public class BOJ_14658 {
    static int N, M, L, K;
    static int[][] map;
    static int minR = Integer.MAX_VALUE;
    static int minC = Integer.MAX_VALUE;
    static int maxR = Integer.MIN_VALUE;
    static int maxC = Integer.MIN_VALUE;
    static int minStarCnt = Integer.MAX_VALUE;


    //i, j 에서 튕겨내고 남은 별
    static int countingStar(int r, int c) {
        // System.out.println("여기서 튕겨낼게 R !:"+r);
        // System.out.println("여기서 튕겨낼게 C !:"+c);
        int cnt = 0;

        for (int i = r; i < r + L; i++) {
            for (int j = c; j < c + L; j++) {
                if (map[i][j] == 1) {
                    cnt++;
                }
            }
        }
        // System.out.println("cnt : " + cnt);
        // System.out.println("this min : " + (K-cnt));
        return K - cnt;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]); // 맵의 가로
        M = Integer.parseInt(input[1]); // 맵의 세로
        L = Integer.parseInt(input[2]) + 1; // 트램펄린의 크기 L*L
        K = Integer.parseInt(input[3]); // 별똥별 수 (K )

        map = new int[N+1][M+1];

        for (int i = 0; i < K; i++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            map[a][b] = 1;
            minR = Math.min(minR, a);
            minC = Math.min(minC, b);
            maxR = Math.max(maxR, a);
            maxC = Math.max(maxC, b);
        }

        for (int i = minR; i <= maxR + 1 - L; i++) {
            for (int j = minC; j <= maxC + 1 - L; j++) {
                minStarCnt = Math.min(minStarCnt, countingStar(i, j));
            }
        }
        
        // print();
        System.out.println(minStarCnt);
    }
    
    public static void print() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}

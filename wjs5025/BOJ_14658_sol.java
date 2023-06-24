import java.io.*;

public class BOJ_14658_sol {
    static int N, M, L, K;
    static int minCnt = Integer.MAX_VALUE;


    public static boolean isBound(int tx, int ty, int sx, int sy, int l) {
        return sx >= tx && sx <= tx + l && sy >= ty && sy <= ty + l;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        L = Integer.parseInt(input[2]);
        K = Integer.parseInt(input[3]);

        int[][] stars = new int[K][2];
        for (int i = 0; i < K; i++) {
            input = br.readLine().split(" ");
            stars[i] = new int[]{Integer.parseInt(input[0]), Integer.parseInt(input[1])};
        }

        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                int cnt = 0;
                int tx = stars[i][0];
                int ty = stars[j][1];

                for (int[] star : stars) {
                    if (!isBound(tx, ty, star[0], star[1], L)) {
                        cnt++;
                    }
                }

                minCnt = Math.min(cnt, minCnt);
            }
        }

        System.out.println(minCnt);
    }


}
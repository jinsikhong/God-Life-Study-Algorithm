import java.io.*;
import java.util.*;

// 초기 코드에선 터졌는데,
// count를 그때그때 세어주니까 안터짐.
// 이유를 모르겠음.
public class BOJ_2531 {
    static int N, d, k, c;
    static int[] sushi;
    static int[] eaten;
    static int cnt = 0;
    static int maxCnt = Integer.MIN_VALUE;

    static int getCnt() {
        int cnt = 0;

        for (int i = 0; i <= d; i++) {
            if (eaten[i] > 0)
                cnt++;
        }

        return cnt;
    }
    static void eat() {
        // 초기 초밥 세팅
        for (int i = 0; i < k; i++) {
            eaten[sushi[i]]++;
        }
        // System.out.println(Arrays.toString(eaten));

        maxCnt = Math.max(maxCnt, getCnt());

        for (int i = k; i < N + k; i++) {
            int before = sushi[(i - k) % N];
            int after = sushi[i % N];

            // System.out.println("빠질거 : " + before + " / 들어올거 : " + after);

            if (eaten[before] >= 1) {
                eaten[before]--;
            }
            eaten[after]++;

            // System.out.println("현재 스시 : " + Arrays.toString(eaten));

            // if (eaten[before] == 0)
            //     cnt--;
            // if (eaten[after] == 1)
            //     cnt++;

            maxCnt = Math.max(maxCnt, getCnt());

            // System.out.println("현재 카운트" + cnt);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        d = Integer.parseInt(input[1]);
        k = Integer.parseInt(input[2]);
        c = Integer.parseInt(input[3]);

        sushi = new int[N];
        eaten = new int[d + 1];
        
        // 쿠폰 멕이기
        eaten[c]++;

        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        eat();
        System.out.println(maxCnt);
    }    
    
}

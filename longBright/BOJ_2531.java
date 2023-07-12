package day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 회전 초밥
 * 솔루션 한번 보고 한 두번 세번 푼듯
 */
public class BOJ_2531 {
    static int N, D, K, C, maxCnt, cnt;
    static int[] belt, sushi;

    public static void main(String[] args) throws IOException {
        init();
        // 1. 처음부터 k개 만큼 먹었을 때의 초기화
        initCnt();
        // 2. 1번부터 n-1 번까지 start 만 이동 시킬경우 K 는 고정
        process();
        print();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken()) - 1;

        belt = new int[N];
        sushi = new int[D];
        for (int i = 0; i < N; i++) {
            belt[i] = Integer.parseInt(br.readLine()) - 1;
        }
    }

    private static void initCnt() {
        for (int i = 0; i < K; i++) {
            if (sushi[belt[i]] == 0) {
                cnt++;
            }
            sushi[belt[i]]++;
        }
    }

    private static void process() {
        for (int start = 0; start < N; start++) {
            updateMax();
            moveEndPointer(start);
            moveStartPointer(start);
        }
    }

    private static void updateMax() {
        if (maxCnt <= cnt) {
            if (sushi[C] == 0) {
                maxCnt = cnt + 1;
            } else {
                maxCnt = cnt;
            }
        }
    }

    private static void moveEndPointer(int start) {
        int end = (start + K) % N;
        if (sushi[belt[end]] == 0) {
            cnt++;
        }
        sushi[belt[end]]++;
    }

    private static void moveStartPointer(int start) {
        sushi[belt[start]]--;
        if (sushi[belt[start]] == 0) {
            cnt--;
        }
    }

    private static void print() {
        System.out.println(maxCnt);
    }
}
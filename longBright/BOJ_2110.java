package day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 2100 공유기 설치
 */
public class BOJ_2110 {
    static int n, c, ans;
    static int[] house;

    public static void main(String[] args) throws IOException {
        init();
        process();
        print();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        house = new int[n];
        for (int i = 0; i < n; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(house);
    }

    private static void process() {
        int low = 1;   // 설치 가능한 최소 거리
        int high = house[n - 1] - house[0] + 1;    // 설치 가능한 최대 거리
        while (low <= high) {
            int mid = (low + high) / 2; // 현재 설치할 거리 (중간값)
            int cnt = getCnt(mid);
            // 현재 설치된 공유기가 총 공유기 개수보다 많으면 결과 갱신 후 더 긴 거리 탐색
            if (cnt >= c) {
                low = mid + 1;
                ans = mid;
            }
            // 현재 설치된 공유기가 총 공유기 개수보다 작으면 더 짧은 거리 탐색
            else {
                high = mid - 1;
            }
        }
    }

    private static int getCnt(int distance) {
        // 첫번째 집은 무조건 설치한다고 가정
        int cnt = 1;
        int before = house[0];
        // 설치 가능한 집 확인 (현재 거리 이상인 경우 cnt 증가)
        for (int i = 0; i < n; i++) {
            int current = house[i];
            if (current - before >= distance) {
                before = house[i];
                cnt++;
            }
        }
        return cnt;
    }

    private static void print() {
        System.out.println(ans);
    }
}

package day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 소수의 연속합
 * 에라토스테네스의 체 알고리즘 솔루션
 */
public class BOJ_1644 {
    static int N, cnt;
    static ArrayList<Integer> primes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        getPrimes(N);
        getCnt();
        print();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
    }

    /* 에라토스테네스의 체를 이용하여 소수 구하기 */
    static void getPrimes(int n) {
        int[] arr = new int[n + 1];
        int rootN = (int) Math.sqrt(n);
        for (int i = 2; i <= n; i++) {
            arr[i] = i;
        }
        for (int i = 2; i <= rootN; i++) {
            if (arr[i] != 0) {
                for (int j = i + i; j <= n; j += i) {
                    arr[j] = 0;
                }
            }
        }
        for (int i = 2; i <= n; i++) {
            if (arr[i] != 0) {
                primes.add(arr[i]);
            }
        }
    }

    private static void getCnt() {
        int start = 0;
        int end = 0;
        int sum = 2;
        int size = primes.size();
        while (start < size) {
            if (sum == N) {
                cnt++;
                sum -= primes.get(start);
                start++;
            } else if (sum > N) {
                sum -= primes.get(start);
                start++;
            } else {
                end++;
                if (end >= size) {
                    break;
                }
                sum += primes.get(end);
            }

        }
    }

    private static void print() {
        System.out.println(cnt);
    }
}

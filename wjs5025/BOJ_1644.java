import java.io.*;
import java.util.*;

public class BOJ_1644  {
    static int N;
    static ArrayList<Integer> primes;
    static int cnt = 0;


    static void getPrimes() {
        boolean[] arr = new boolean[N + 1];

        for (int i = 2; i < Math.sqrt(N) + 1; i++) {
            if (!arr[i]) {
                int j = 2;
                while (i * j <= N) {
                    arr[i * j] = true;
                    j += 1;
                }
            }
        }

        for (int i = 2; i < N + 1; i++) {
            if (!arr[i])
                primes.add(i);
        }
    }
    
    static void getCnt() {
        int end = primes.size() - 1;
        int sum = 0;

        for (int start = primes.size() - 1; start >= 0; start--) {
            while (sum < N && end >= 0) {
                sum += primes.get(end);
                end--;
            }
            if (sum == N) cnt++;
            
            sum -= primes.get(start);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        primes = new ArrayList<>();

        getPrimes();
        getCnt();

        System.out.println(cnt);
    }
}   

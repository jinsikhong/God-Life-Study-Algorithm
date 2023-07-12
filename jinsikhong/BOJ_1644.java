
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;



/*
    솔루션 참고 :
        1. 에라토스테네스의 체
        2. sum을 0으로 잡고 시작함
 */
public class BOJ_1644 {
    static int n;
    static ArrayList<Integer> prime = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        getPrime(n);

        int left = 0;
        int right = 0;
        int size = prime.size();
        int sum = 2;
        int cnt = 0;



        while(left < size && right < size){
            if(sum == n){
                cnt++;
                sum -= prime.get(left);
                left++;
            }else if(sum > n){
                sum -= prime.get(left);
                left++;
            }else{
                right++;
                if(right >= size) break;
                sum += prime.get(right);
            }
        }
        System.out.println(cnt);
    }


    //에라토스테네스의 체
    static void getPrime(int n) {
        int temp[] = new int[n+1];
        int rootN = (int)Math.sqrt(n);
        for(int i=2; i<=n; i++) {
            temp[i] = i;
        }
        for(int i=2; i<=rootN; i++) {
            if(temp[i] != 0 ) {
                for(int j=i+i; j<=n; j+=i) {
                    temp[j] = 0;
                }
            }
        }
        for(int i=2; i<=n; i++) {
            if(temp[i] != 0) {
                prime.add(temp[i]);
            }
        }
    }
}

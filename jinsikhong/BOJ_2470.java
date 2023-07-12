import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine()); //용액 개수
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int left = 0;
        int right = arr.length-1;

        int minLeft = 0;
        int minRight = 0;


        int min = 2000000001;
        while(left < right) {
            int sum = arr[left] + arr[right]; //한쪽이 양수니 +
            int mix = Math.abs(sum);
            if(mix == 0) {
                min = mix;
                minLeft = left;
                minRight = right;
                break;
            }
            if(min > mix) {
                min = mix;
                minLeft = left;
                minRight = right;
            }
            if(sum > 0) { // idx2(양수 부)가 더 높은 상태 idx2를 줄여야함
                right--;
            }else { //0 일때 위에서 break sum이 더 낮으면 idx1 높여야됨
                left++;
            }
        }
        if(arr[minLeft] > arr[minRight]) {
            int temp = minRight;
            minRight = minLeft;
            minLeft = temp;
        }else {
            System.out.println(arr[minLeft] + " " + arr[minRight]);
        }
    }
}

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1062 {
    static int N, K;
    static boolean[] visited;
    static String[] word;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[26]; // 알파벳 26개
        word = new String[N];
        if(K < 5){
            System.out.println(0);
        }
        if(K > 26){
            System.out.println(N);
        }

        visited['a' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;

        for(int i = 0; i < N; i++){
            String w = br.readLine();
            String str = w.substring(4, w.length()-4);
            word[i] = str;
        }
        dfs(0, 0);
        System.out.println(ans);



    }

    static void dfs(int start, int cnt){
        if(cnt == K - 5){
            int temp = 0;
            for(int i = 0; i < N; i++){
                boolean flag = true;
                for(int j = 0; j < word[i].length(); j++){
                    if(!visited[word[i].charAt(j) - 'a']){
                        flag = false;
                        break;
                    }
                }
                if(flag) temp++;
            }
            ans = Math.max(temp, ans);
            return;
        }
        for(int i = start; i < 26; i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(i, cnt + 1);
                visited[i] = false;
            }
        }
    }
}

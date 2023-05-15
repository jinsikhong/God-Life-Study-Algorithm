import java.io.*;
import java.util.*;

/*
 * 단순한 구현문제인데, 너무 오랜만에 풀어서 그런지 머리가 안돌아가유..
 * 그래서 난이도에 비해 풀이가 상당히 오래걸림 ㅋ
 */

public class BOJ_2853 {
    static int N;
    // 이기고 있던 시간 기록 [00, 00]
    static int[] team_one = new int[2];
    static int[] team_two = new int[2];

    // 마지막 득점 시간
    static int beforeState = 0; // 0 이 무승부, 1 이 1팀 이기는중, 2가 2팀이긴느중
    static int state = 0; // 0 이 무승부,
    static int[] score = { 0, 0, 0 };
    static int[] startTime = { 0, 0 }; // [00, 00]
    static int[] currentTime = { 0, 0 }; // [00, 00]

    static String convert(int[] time) {
        boolean flag = true;

        while (flag) {
            if (time[0] < 0 || time[1] < 0 || time[1] >= 60) {
                flag = true;
            } else {
                flag = false;
            }

            if (time[1] < 0) {
                time[0] -= 1;
                time[1] += 60;
            }

            if (time[1] >= 60) {
                time[1] -= 60;
                time[0] += 1;
            }
        }

        String bun = Integer.toString(time[0]);
        String cho = Integer.toString(time[1]);
        if (bun.length() == 1) {
            bun = "0" + bun;
        }
        if (cho.length() == 1) {
            cho = "0" + cho;
        }

        return bun + ":" + cho;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] tmp;

        for (int i = 0; i < N; i++) {
            tmp = br.readLine().split(" ");

            int team = Integer.parseInt(tmp[0]);
            score[team] += 1;

            String[] time = tmp[1].split(":");

            int bun = Integer.parseInt(time[0]);
            int cho = Integer.parseInt(time[1]);

            if (score[1] > score[2]) {
                state = 1;
            } else if (score[1] < score[2]) {
                state = 2;
            } else {
                state = 0;
            }

            if (beforeState != state) {
                if (beforeState == 1) {
                    // 1팀 시간점수증가시켜
                    team_one[0] += (bun - startTime[0]);
                    team_one[1] += (cho - startTime[1]);

                } else if (beforeState == 2) {
                    // 2팀 시간점수증가시켜
                    team_two[0] += (bun - startTime[0]);
                    team_two[1] += (cho - startTime[1]);
                }

                beforeState = state;
                startTime[0] = bun;
                startTime[1] = cho;
            }
        }

        if (beforeState == 1) {
            // 1팀 시간점수증가시켜
            team_one[0] += (48 - startTime[0]);
            team_one[1] += (0 - startTime[1]);

        } else if (beforeState == 2) {
            // 2팀 시간점수증가시켜
            team_two[0] += (48 - startTime[0]);
            team_two[1] += (0 - startTime[1]);
        }

        // 갱신
        String score_team_one = convert(team_one);
        String score_team_two = convert(team_two);

        System.out.println(score_team_one);
        System.out.println(score_team_two);
    }
}

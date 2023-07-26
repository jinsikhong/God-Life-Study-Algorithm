package day7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 웜홀
 * 벨만포드 알고리즘 사용
 * 솔루션
 */
public class BOJ_1865 {
    static int N, M, W;
    static int[] dist;
    static ArrayList<ArrayList<Edge>> graph;
    static final int INF = 9999;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            dist = new int[N+1];
            graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < M+W; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                if (i < M) {    // 도로는 양방향 그래프
                    graph.get(start).add(new Edge(end, weight));
                    graph.get(end).add(new Edge(start, weight));
                } else {    // 웜홀을 단방향 그래프
                    graph.get(start).add(new Edge(end, weight));
                }
            }

            boolean isMinusCycle = false;
            for (int i = 0; i <= N; i++) {
                if (bellmanFord(i)){
                    isMinusCycle = true;
                    sb.append("YES\n");
                    break;
                }
            }

            if (!isMinusCycle) {
                sb.append("NO\n");
            }
        }

        System.out.println(sb);
    }

    private static boolean bellmanFord(int start) {
        Arrays.fill(dist, INF);
        dist[start] = 0; // 시작점은 0으로 초기화.
        boolean update = false;

        // (정점의 개수 - 1)번 동안 최단거리 초기화 작업을 반복함.
        for (int i = 1; i < N; i++) {
            update = false;

            // 최단거리 초기화.
            for (int j = 1; j <= N; j++) {
                for (Edge edge : graph.get(j)) {
                    if (dist[j] != INF && dist[edge.end] > dist[j] + edge.weight) {
                        dist[edge.end] = dist[j] + edge.weight;
                        update = true;
                    }
                }
            }

            // 더 이상 최단거리 초기화가 일어나지 않았을 경우 반복문을 종료.
            if (!update) {
                break;
            }
        }

        // (정점의 개수 - 1)번까지 계속 업데이트가 발생했을 경우
        // (정점의 개수)번도 업데이트 발생하면 음수 사이클이 일어난 것을 의미함.
        if (update) {
            for (int i = 1; i <= N; i++) {
                for (Edge road : graph.get(i)) {
                    if (dist[i] != INF && dist[road.end] > dist[i] + road.weight) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    static class Edge {
        int end;
        int weight;

        public Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
}

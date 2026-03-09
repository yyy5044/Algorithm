import java.io.*;
import java.util.*;

public class Solution {
	static int N, M;
	static int[] parents;
	
	private static void makeSet(int x) {
		parents[x] = x;
	}
	
	private static int findSet(int x) {
		if (parents[x] == x) return x;
		return parents[x] = findSet(parents[x]);
	}
	
	private static boolean unionSet(int x, int y) {
		boolean result = true;
		
		int xRoot = findSet(x);
		int yRoot = findSet(y);
		
		if (xRoot == yRoot) {
			result = false;
		} else {
			parents[yRoot] = xRoot;
		}
		
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 정점 개수
			M = Integer.parseInt(st.nextToken()); // 간선 개수
			
			parents = new int[N+1]; // 인덱스 1부터
			for (int i = 1; i <= N; i++) {
				makeSet(i);
			}
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				unionSet(x, y);
			}
			
			// 여기에 집합의 개수를 세는 로직
			boolean[] visited = new boolean[N+1];
			for (int i = 1; i <= N; i++) {
				if (!visited[findSet(i)]) {
					visited[findSet(i)] = true;
				}
			}
			
			int ans = 0;
			for (int i = 1; i <= N; i++) {
				if (visited[i]) ans++;
			}
			
			sb.append("#").append(t).append(" ");
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
}
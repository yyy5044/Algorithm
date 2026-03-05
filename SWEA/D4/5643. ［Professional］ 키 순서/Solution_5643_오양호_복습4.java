import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();	
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine()); // 학생 수
			int M = Integer.parseInt(br.readLine()); // 비교 횟수 M
			
			ArrayList<Integer>[] adj = new ArrayList[N+1];
			ArrayList<Integer>[] revAdj = new ArrayList[N+1];
			
			for (int i = 1; i <= N; i++) {
				adj[i] = new ArrayList<>();
				revAdj[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				adj[from].add(to);
				revAdj[to].add(from);
			}
			
			int known = 0;
			for (int i = 1; i <= N; i++) {
				int cnt = bfs(adj, N, i) + bfs(revAdj, N, i);
				
				if (cnt == N-1) known++;
			}
			
			sb.append("#").append(t).append(" ");
			sb.append(known).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static int bfs(ArrayList<Integer>[] graph, int N, int startVertex) {
		boolean[] visited = new boolean[N+1]; // 인덱스 1부터
		
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		visited[startVertex] = true;
		dq.add(startVertex);
		
		int count = 0; 
		while(!dq.isEmpty()) {
			int cur = dq.poll();
			
			for (int next:graph[cur]) {
				if (!visited[next]) {
					visited[next] = true;
					dq.add(next);
					count++;
				}
			}
		}
		
		return count;
	}
}


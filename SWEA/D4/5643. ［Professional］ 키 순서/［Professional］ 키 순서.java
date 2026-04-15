import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Solution {
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			ArrayList<Integer>[] adjList = new ArrayList[N+1];
			ArrayList<Integer>[] revAdjList = new ArrayList[N+1];
			
			for (int i = 1; i <= N; i++) {
				adjList[i] = new ArrayList<>();
				revAdjList[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				adjList[from].add(to);
				revAdjList[to].add(from);
			}
			
			int ans = 0;
			for (int i = 1; i <= N; i++) {
				boolean[] visited = new boolean[N+1];
				ArrayDeque<Integer> dq = new ArrayDeque<>();
				
				visited[i] = true;
				dq.add(i);
				
				int count = 0;
				while(!dq.isEmpty()) {
					int cur = dq.poll();
					
					for (int next: adjList[cur]) {
						if (!visited[next]) {
							visited[next] = true;
							dq.add(next);
							count++;
						}
					}
				}
				
				visited[i] = true;
				dq.add(i);
				
				while(!dq.isEmpty()) {
					int cur = dq.poll();
					
					for (int next: revAdjList[cur]) {
						if (!visited[next]) {
							visited[next] = true;
							dq.add(next);
							count++;
						}
					}
				}
				
				if (count == N-1) ans++;
			}
			
			sb.append("#").append(t).append(" ");
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
	
}

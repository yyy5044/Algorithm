import java.io.*;
import java.util.*;

public class Solution {
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			
			visited = new boolean[N+1];
			
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
				int start = i;
				ArrayDeque<Integer> dq = new ArrayDeque<>();
				visited[start] = true;
				dq.addLast(start);
				
				int cnt = 0;
				while(!dq.isEmpty()) {
					int cur = dq.pollFirst();
					
					for (int next:adj[cur]) {
						if (visited[next]) continue;
						visited[next] = true;
						dq.addLast(next);
						cnt++;
					}
				}
				
				Arrays.fill(visited, false);
				visited[start] = true;
				dq.addLast(start);
				
				while(!dq.isEmpty()) {
					int cur = dq.pollFirst();
					
					for (int next:revAdj[cur]) {
						if(visited[next]) continue;
						visited[next] = true;
						dq.addLast(next);
						cnt++;
					}
				}
				
				if(cnt == N-1) {
					known++;
				}
			}
			
			sb.append("#").append(t).append(" ")
				.append(known).append("\n");
		}
		
		System.out.println(sb);
	}

}

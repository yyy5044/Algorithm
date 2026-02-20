import java.io.*;
import java.util.*;

public class Solution {
	static int N, M;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
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
				int count = 0;
				ArrayDeque<Integer> dq = new ArrayDeque<>();
				visited[i] = true;
				dq.add(i);
				
				while(!dq.isEmpty()) {
					int cur = dq.pollFirst();
					
					for (int j = 0; j < adj[cur].size(); j++) {
						int next = adj[cur].get(j);
						if (visited[next]) continue;
						visited[next] = true;
						dq.add(next);
						count++;
					}
				}
				
				Arrays.fill(visited, false);
				visited[i] = true;
				dq.add(i);
				
				while(!dq.isEmpty()) {
					int cur = dq.pollFirst();
					
					for (int j = 0; j < revAdj[cur].size(); j++) {
						int next = revAdj[cur].get(j);
						if(visited[next]) continue;
						visited[next] = true;
						dq.add(next);
						count++;
					}
				}
				
				if (count == N-1) {
					known++;
				}
			}
			
			sb.append("#").append(t+1).append(" ");
			sb.append(known).append("\n");
		}	
		
		System.out.println(sb);
	}
	
}

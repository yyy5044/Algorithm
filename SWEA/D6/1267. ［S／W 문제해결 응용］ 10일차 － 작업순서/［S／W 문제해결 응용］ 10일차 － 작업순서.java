import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= 10; t++) {
			sb.append("#").append(t).append(" ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken()); // 정점 개수
			int E = Integer.parseInt(st.nextToken()); // 간선 개수
			
			ArrayList<Integer>[] adj = new ArrayList[V+1];
			ArrayList<Integer>[] revAdj = new ArrayList[V+1];
			
			for (int i = 1; i <= V; i++) {
				adj[i] = new ArrayList<Integer>();
				revAdj[i] = new ArrayList<Integer>();
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				adj[from].add(to);
				revAdj[to].add(from);
			}
			
			boolean[] visited = new boolean[V+1];
			ArrayDeque<Integer> dq = new ArrayDeque<>(); // 큐 준비
			
			int[] counter = new int[V+1]; // cnt 기록용 배열
			for (int i = 1; i <= V; i++) { // 기록
				counter[i] = revAdj[i].size();
				if(counter[i] == 0) { // cnt가 0인 정점이 있으면
					visited[i] = true;
					dq.add(i); // 큐 삽입
				}
			}
			
			while(!dq.isEmpty()) {
				int cur = dq.poll();
				sb.append(cur).append(" ");
				
				for (int next : adj[cur]) {
					counter[next] -= 1; // cnt에 1 차감
					
					if (counter[next] == 0 && !visited[next]) { // cnt가 0이 되었으면
						visited[next] = true;
						dq.add(next);
					}
				}
			}
			
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
}


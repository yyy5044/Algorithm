import java.util.*;
import java.io.*;

public class Solution {
	static ArrayDeque<Integer> dq = new ArrayDeque<>();
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
 		
		int T = Integer.parseInt(br.readLine());	
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			ArrayList<Integer>[] adj = new ArrayList[N+1];
			ArrayList<Integer>[] revAdj = new ArrayList[N+1];
			
			visited = new boolean[N+1];
			
			for (int i = 1; i <= N; i++) {
				adj[i] = new ArrayList<>();
				revAdj[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				
				adj[start].add(end);
				revAdj[end].add(start);
			}
			
			
			int known = 0; // 자기 위치를 아는 학생 수
			for (int i = 1; i <= N; i++) {
				int count = 0; // i번 정점과 관련된 정점 개수
				
				Arrays.fill(visited, false);
				dq.addLast(i); // 일단 1번부터.
				visited[i] = true;
				
				while(!dq.isEmpty()) {
					int s = dq.pollFirst();
					
					for (int next : adj[s]) {
						if (!visited[next]) {
							visited[next] = true;
							dq.add(next);
							count++;
						}
					}
				}
				
				Arrays.fill(visited, false);
				dq.addLast(i);
				visited[i] = true;
				
				while(!dq.isEmpty()) {
					int s = dq.pollFirst();
					
					for (int next : revAdj[s]) {
						if (!visited[next]) {
							visited[next] = true;
							dq.add(next);
							count++;
						}
					}
				}
				
				if (count == N-1) { // 자기 빼고 다 이어져 있으면 위치를 정확히 아는 것
					known++;
				}
			}
			
			sb.append("#").append(t+1).append(" ")
				.append(known).append("\n");
		}
		
		System.out.println(sb);
	}

}


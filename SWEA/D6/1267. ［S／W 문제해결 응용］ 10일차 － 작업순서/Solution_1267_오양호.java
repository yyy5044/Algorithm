

import java.io.*;
import java.util.*;

public class Solution {
	static int N = 100;
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		

		
		
		// int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < 10; t++) {
			sb.append("#").append(t+1).append(" ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer>[] adj = new ArrayList[V+1];
			ArrayList<Integer>[] revAdj = new ArrayList[V+1];
			for (int i = 1; i <= V; i++) {
				adj[i] = new ArrayList<>();
				revAdj[i] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				adj[from].add(to);
				revAdj[to].add(from);
			}
			
			int[] count = new int[V+1]; // 진입 차수 기록용
			for (int i = 1; i < adj.length; i++) { // 진입 차수 기록
				count[i] = revAdj[i].size();
			}
			
			ArrayDeque<Integer> dq = new ArrayDeque<>();
			
			for (int i = 1; i < adj.length; i++) {
				if (revAdj[i].size() == 0) { // 진입 차수가 0인 애들 먼저 큐에 삽입
					dq.add(i);
				}
			}
			
			
			
			while(!dq.isEmpty()) {
				int task = dq.pollFirst();
				sb.append(task +" ");
				for (int i = 0; i < adj[task].size(); i++) {
					count[adj[task].get(i)] -= 1; // 진출 정점의 cnt를 1 깎는다 
					if(count[adj[task].get(i)] == 0) { // 진출 정점의 cnt가 0이면 큐에 삽입
						dq.add(adj[task].get(i));
					}
				}

			}
	
			sb.append("\n");
		}	
		
		System.out.println(sb);
	}
	
}

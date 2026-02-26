import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] population = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		ArrayList<Integer>[] adj = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int to = i;
			for (int j = 0; j < M; j++) {
				int from = Integer.parseInt(st.nextToken());
				adj[to].add(from);
			}
		}
		
		int[] 구역 = new int[N];
		for(int i = 1; i <= N; i++) {
			구역[0] = i;
		}
		
		int min_population_diff = Integer.MAX_VALUE;
		for (int i = 1; i < (1<<N)-1; i++) {
			// System.out.println("경우의 수: "+i);
			int[][] AB = new int[2][N]; 
			for (int j = 0; j < N; j++) {
				if ((i&(1<<j)) != 0) {
					AB[1][j] = j+1;
				} else {
					AB[0][j] = j+1;
				}
			}
			
			// A구역이 서로 연결됐는지 확인
			// System.out.println("A구역: "+Arrays.toString(AB[0]));
			// 시작 정점 선택
			int A_start = 0;
			int A_cnt = 0;
			int A_visit_cnt = 1;
			for(int j = 0; j < N; j++) {
				if(AB[0][j] != 0) {
					A_start = AB[0][j];
					A_cnt++;
				}
			}
			
			boolean[] A_visited = new boolean[N+1];
			boolean[] B_visited = new boolean[N+1];
			
			ArrayDeque<Integer> dq = new ArrayDeque<>();
			A_visited[A_start] = true;
			dq.addLast(A_start);
			
			while(!dq.isEmpty()) {
				int cur = dq.pollFirst();
				
				for (int next:adj[cur]) {
					if (A_visited[next] || AB[0][next-1] == 0) continue;
					A_visited[next] = true;
					dq.addLast(next);
					A_visit_cnt++;
				}
			}
			
			if (A_cnt != A_visit_cnt) { // 연결됐니?
				continue; // 안 됐으면 이 경우의 수는 폐기
			}
			
			// B구역이 서로 연결됐는지 확인
			// System.out.println("B구역: "+Arrays.toString(AB[1]));
			// 시작 정점 선택
			int B_start = 0;
			int B_cnt = 0;
			int B_visit_cnt = 1;
			for(int j = 0; j < N; j++) {
				if(AB[1][j] != 0) {
					B_start = AB[1][j];
					B_cnt++;
				}
			}
			
			B_visited[B_start] = true;
			dq.addLast(B_start);
			
			while(!dq.isEmpty()) {
				int cur = dq.pollFirst();
				
				for (int next:adj[cur]) {
					if(B_visited[next] || AB[1][next-1] == 0) continue;
					B_visited[next] = true;
					dq.addLast(next);
					B_visit_cnt++;
				}
			}
			
			if (B_cnt != B_visit_cnt) continue;
			
			int A_population = 0;
			int B_population = 0;
			for (int j = 0; j < N; j++) {
				if (AB[0][j] != 0) A_population += population[AB[0][j]];
				if (AB[1][j] != 0) B_population += population[AB[1][j]];
			}
			
			int population_diff = Math.abs(A_population - B_population);
			min_population_diff = Math.min(population_diff, min_population_diff);
		}
		
		if (min_population_diff == Integer.MAX_VALUE) {
			min_population_diff = -1;
		}
		
		System.out.println(min_population_diff);
		
		// System.out.println(sb);
	}
	

}

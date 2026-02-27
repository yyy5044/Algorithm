import java.io.*;
import java.util.*;

// 기록: 48분
public class Main {
	static int N;
	static int minDiff = Integer.MAX_VALUE; // 인구수 차이
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine()); // 구역 수
		
		// 인구 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] population = new int[N+1];
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		// 그래프 입력
		ArrayList<Integer>[] graph = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int from = i;
			for (int j = 0; j < M; j++) {
				int to = Integer.parseInt(st.nextToken());
				graph[from].add(to);
			}
		}
		
		// 모든 선거구의 경우를 확인하며 인구 계산
		for (int i = 1; i < (1<<N)-1; i++) {
			int[] A = new int[N+1];
			int[] B = new int[N+1];
			A[0] = -1; B[0] = -1; // 첫 번째 칸에 쓰레기 값 -> 인덱스 그대로 쓰기 위해서
			for (int j = 0; j < N; j++) {
				if ((i&(1<<j)) != 0) {
					A[j+1] = j+1;
				} else {
					B[j+1] = j+1;
				}
			}
			
			// 이 밑부터는 A와 B의 구역이 나뉜 상태
			
			if (!isConnected(A, graph)) continue; // A 선거구가 연결됐는지 확인
			if (!isConnected(B, graph)) continue; // B 선거구가 연결됐는지 확인
			
			// A, B가 정상적인 선거구라면 인구 계산
			int Apop = 0;
			int Bpop = 0;
			
			// 인구 계산 로직
			for (int j = 1; j <= N; j++) {
				if (A[j] != 0) Apop += population[j];
				else if (B[j] != 0) Bpop += population[j];
			}
			
			// 최소 인구 차이 갱신
			int diff = Math.abs(Apop-Bpop);
			minDiff = Math.min(diff, minDiff);
		}
		
		if (minDiff == Integer.MAX_VALUE) minDiff = -1;
		
		System.out.println(minDiff);
		
		// System.out.println(sb);
	}
	
	private static boolean isConnected(int[] section, ArrayList<Integer>[] graph) {
		boolean[] visited = new boolean[N+1];
		
		int sCnt = 0; // 구역의 수
		int vCnt = 1; // 방문 수: 첫 노드 방문 개수를 주고 시작
		int firstNode = -1; // 임의의 노드
		
		for (int i = 1; i <= N; i++) {
			if (section[i] != 0) {
				firstNode = section[i];
				sCnt++;
			} 
		}
		
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		visited[firstNode] = true;
		dq.addLast(firstNode);
		
		while(!dq.isEmpty()) {
			int cur = dq.pollFirst();
			
			for (int next:graph[cur]) {
				if (section[next] == 0 || visited[next]) continue;
				
				visited[next] = true;
				dq.addLast(next);
				vCnt++;
			}
		}
		
		if (vCnt == sCnt) {
			return true;
		} else {
			return false;
		}
	}
}

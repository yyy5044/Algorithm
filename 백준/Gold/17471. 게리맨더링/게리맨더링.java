import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] population;
	static int minPopulation;
	
	static class Node {
		int vertex;
		Node next;
		public Node(int vertex, Node next) {
			super();
			this.vertex = vertex;
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 그래프 입력
		N = Integer.parseInt(br.readLine());
		population = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		Node[] adjList = new Node[N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = i;
			int M = Integer.parseInt(st.nextToken());
			for (int j = 0; j < M; j++) {
				int to = Integer.parseInt(st.nextToken());
				adjList[from] = new Node(to, adjList[from]);
			}
		}
		
//		for (int i = 1; i <= N; i++) {
//			System.out.print(i+"-> ");
//			for (Node temp = adjList[i]; temp != null; temp = temp.next) {
//				System.out.print(temp.vertex + "-> ");
//			}
//			System.out.println();
//		}
		
		// 2. 부분집합으로 가능한 조합 찾기
		minPopulation = Integer.MAX_VALUE;
		
		for (int i = 1; i < (1<<N)-1; i++) {
			int[] A = new int[N+1];
			int[] B = new int[N+1];
			for (int j = 0; j < N; j++) {
				if ((i&(1<<j)) != 0) {
					A[j+1] = 1;
				} else {
					B[j+1] = 1;
				}
			}
			
			// System.out.println(Arrays.toString(A));
			// System.out.println(Arrays.toString(B));
			
			// 3. 해당 조합에서 두 선거구의 유효성 확인
			if (!isValid(A, adjList)) continue;
			
			if (!isValid(B, adjList)) continue;
			
			// 4. 두 선거구가 모두 유효하면 인구 차이 계산 후 최소 인구차이 갱신 시도
			int A_population = 0;
			int B_population = 0;
			for (int j = 1; j <= N; j++) {
				if (A[j] != 0) A_population += population[j];
				else B_population += population[j];
			}
			
			int population_diff = Math.abs(A_population - B_population);
			minPopulation = Math.min(population_diff, minPopulation);
		}
		
		if (minPopulation == Integer.MAX_VALUE) minPopulation = -1;
		
		System.out.println(minPopulation);
	}
	
	private static boolean isValid(int[] section, Node[] graph) {
		boolean[] visited = new boolean[N+1];
		
		int startVertext = -1;
		int visitCnt = 0;
		int sectionCnt = 0;
		for (int i = 1; i <= N; i++) {
			if (section[i] == 1) {
				sectionCnt++;
				startVertext = i;
			}
		}
		
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		dq.add(startVertext);
		visited[startVertext] = true;
		visitCnt++;
		
		while(!dq.isEmpty()) {
			int cur = dq.poll();
			for (Node temp = graph[cur]; temp != null; temp = temp.next) {
				if (!visited[temp.vertex] && section[temp.vertex] == 1) { // <- 여기 선거구에 포함된 구역인지 확인하는 로직이 빠졌었음
					visited[temp.vertex] = true;
					visitCnt++;
					dq.add(temp.vertex);
				}
			}
		}
		
		if (sectionCnt == visitCnt) {
			return true;
		} else {
			return false;
		}
	}
}

import java.io.*;
import java.util.*;

public class Main {
	static int N; // 구역 개수
	static int[] population; // 인구 정보
	
	static class Node {
		int vertex;
		Node next;
		public Node(int vertex, Node next) {
			super();
			this.vertex = vertex;
			this.next = next;
		}
	}
	
	static boolean isValid(int[] temp_s, Node[] adjList) {
		int visitCnt = 1; // 실제 방문한 정점 수
		int sectionCnt = 0; // 구역 개수
		int sVertex = -1; // 시작 정점
		
		int[] section = new int[N+1];
		for (int i = 0; i < N; i++) {
			section[i+1] = temp_s[i];
			if (section[i+1] == 1) {
				sectionCnt++;
				sVertex = i+1;
			}
		}
		
		boolean[] visited = new boolean[N+1];
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		visited[sVertex] = true;
		dq.add(sVertex);
		
		while(!dq.isEmpty()) {
			int cur = dq.poll();
			
			for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
				if (!visited[temp.vertex] && section[temp.vertex] == 1) {
					visited[temp.vertex] = true;
					dq.add(temp.vertex);
					visitCnt++;
				}
			}
		}
		
		if (visitCnt == sectionCnt) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		population = new int[N+1]; // 1번 인덱스부터
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		// System.out.println(Arrays.toString(population));
		
		Node[] adjList = new Node[N+1]; // 1번 인덱스부터
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); // i와 연결된 정점 개수
			int from = i;
			for (int j = 0; j < M; j++) {
				int to = Integer.parseInt(st.nextToken());
				adjList[from] = new Node(to, adjList[from]);
				adjList[to] = new Node(from, adjList[to]);
			}
		}
		
		
//		for (int i = 1; i <= N; i++) {
//			System.out.print(i + "-> ");
//			for (Node temp = adjList[i]; temp != null; temp = temp.next) {
//				System.out.print(temp.vertex + "-> ");
//			}
//			System.out.print("null");
//			System.out.println();
//		}
		
		int minPopDiff = Integer.MAX_VALUE;
		
		for (int i = 1; i < (1<<N)-1; i++) { // 선거구 나누는 경우의 수 -> 부분집합
			int[] A = new int[N];
			int[] B = new int[N];
			
			for (int j = 0; j < N; j++) { // 선거구 나누기
				if ((i&(1<<j)) != 0) {
					A[j] = 1;
				} else {
					B[j] = 1;
				}
			}
			
//			System.out.println(Arrays.toString(A));
//			System.out.println(Arrays.toString(B));
			
			// A 유효?
			if (!isValid(A, adjList)) continue;
			// B 유효?
			if (!isValid(B, adjList)) continue;
			// 둘 다 유효하면 인구 계산
			int A_pop = 0;
			for (int j = 0; j < N; j++) {
				if (A[j] == 1) A_pop += population[j+1];
			}
			
			int B_pop = 0;
			for (int j = 0; j < N; j++) {
				if (B[j] == 1) B_pop += population[j+1];
			}
			
			int diff = Math.abs(A_pop - B_pop);
			minPopDiff = Math.min(diff, minPopDiff);
		}
		
		if (minPopDiff == Integer.MAX_VALUE) minPopDiff = -1;
		
		System.out.println(minPopDiff);
	}
}

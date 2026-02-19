import java.io.BufferedReader;
import java.io.*;
import java.util.*;

public class AdjMatrixTest {
	static int V, E;
	static int adjMatrix[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(in.readLine()); // 정점수
		E = Integer.parseInt(in.readLine()); // 간선수
		
		adjMatrix = new int[V][V];
		
		for (int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine()," ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			// 무향 그래프
			adjMatrix[to][from] = adjMatrix[from][to];
		}
		
		bfs(0);
		
//		for (int i = 0; i < V; i++) {
//			System.out.println(adjList[i]);
//			for (Node temp=adjList[i]; temp != null; temp = temp.next) {
//				
//			}
//		}
		
	}
	
	private static void bfs(int start) {
		// 큐 생성
		Queue<Integer> queue = new ArrayDeque<>();
		// 방문관리 배열 생성
		boolean[] visited = new boolean[V];
		
		// 시작정점(너비0) 방문처리 후 큐에 넣기
		visited[start] = true;
		queue.offer(start);
		
		// 큐에 탐색 정점이 있을때까지 반복
		while(!queue.isEmpty()) {
			// 탐색 대상 큐에서 꺼내기
			int cur = queue.poll();
			System.out.println((char)(cur+65));
			// 탐색 대상의 인접정점 처리
			for (int i = 0; i<V; i++) {
				// 미방문 인접정점 방문처리 후 큐에 넣기
				if (adjMatrix[cur][i] == 0 && visited[i]) continue;
				
				visited[i] = true;
				queue.offer(i);
			}
			
		}
		
	}

}

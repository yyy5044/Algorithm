import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int vertex, weight;
		Node next;
		
		public Node(int vertex, int weight, Main.Node next) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}	
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int start = Integer.parseInt(br.readLine());
		
		Node[] adjList = new Node[V+1]; // 인덱스 그대로 쓰기
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[from] = new Node(to, weight, adjList[from]);
		}
		
//		// 인접 리스트 출력용 코드
//		for (int i = 1; i <= V; i++) {
//			System.out.print(i+ " ->");
//			for (Node temp = adjList[i]; temp != null; temp = temp.next) {
//				System.out.print(" [" + temp.vertex + ", " + temp.weight + "] ->");
//			}
//			System.out.println(" null");
//		}
		
		
		boolean[] visited = new boolean[V+1];
		int[] minDist = new int[V+1];
		
		final int INF = Integer.MAX_VALUE;
		
		Arrays.fill(minDist, INF);
		minDist[0] = -1; // 안 쓰는 칸
		
		minDist[start] = 0;
		
		for (int v = 1; v <= V; v++) { // 모든 정점에 대하여
			int cur = -1, min = INF;
			
			// [step 1] 출발지에서 v까지 오는데 가장 가까운 정점 선택
			for (int w = 1; w <= V; w++) { 
				if (!visited[w] && min > minDist[w]) {
					cur = w;
					min = minDist[w];
				}
			}
			
			if (cur == -1) break; // 연결된 정점이 없으면 종료
			
			visited[cur] = true;
			
			// [step 2] 선택된 정점과 연결된 정점들의 minDist 갱신
			for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
				if (!visited[temp.vertex] && minDist[temp.vertex] > min + temp.weight) {
					minDist[temp.vertex] = min + temp.weight;
				}
			}
		}
		
		for (int i = 1; i <= V; i++) {
			if (minDist[i] == INF) {
				sb.append("INF");
			} else {
				sb.append(minDist[i]);
			}
			sb.append("\n");
		}

		
		System.out.println(sb);
	}
	
}
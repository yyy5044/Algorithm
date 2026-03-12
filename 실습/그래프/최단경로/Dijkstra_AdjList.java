import java.io.*;
import java.util.*;

public class Dijkstra_AdjList {
	
	static class Node implements Comparable<Node>{
		int vertex, weight;
		Node next;

		public Node(int vertex, int weight, Node next) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		Node[] adjList = new Node[V]; // 인접 리스트
		boolean[] visited = new boolean[V]; // 트리 정점 체크
		int[] minDist = new int[V]; // 출발지에서 자신으로의 최소 거리(비용)
		
		for (int i = 0; i < E; i++) {
		    st = new StringTokenizer(br.readLine());
		    int from = Integer.parseInt(st.nextToken());
		    int to = Integer.parseInt(st.nextToken());
		    int weight = Integer.parseInt(st.nextToken());
		    adjList[from] = new Node(to, weight, adjList[from]);
		    adjList[to] = new Node(from, weight, adjList[to]); 
		}
		
		final int INF = Integer.MAX_VALUE;
		
		Arrays.fill(minDist, INF);
		
		minDist[start] = 0;
		
		for (int i = 0; i < V; i++) {
			// step1: 출발지에서 가까운 정점으로 탐색되지 않은 정점 중에 가장 가까운 정점 찾기
			int cur = -1, min = INF;
			for (int j = 0; j < V; j++) {
				if (!visited[j] && min > minDist[j]) {
					cur = j;
					min = minDist[j];
				}
			}
			
			if (cur == -1) break; // 출발지에서 가까운 미탐색 정점이 없으면 진행 불가
			
			visited[cur] = true;
			
			// step2 : 찾은 cur 정점을 경유해서 갈 수 있는 다른 미탐색 정점의 최소비용 비교하여 갱신
			for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
				if (!visited[temp.vertex] && minDist[temp.vertex] > min + temp.weight) {
					minDist[temp.vertex] = min + temp.weight;
				}
			}
		}
		
		System.out.println(Arrays.toString(minDist));
		System.out.println(minDist[end] != INF ? minDist[end] : -1); // 다익스트라 후에도 end가 INF면 start에서 end로 갈 수 있는 경로가 없다는 것
	}
}

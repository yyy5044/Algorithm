import java.io.*;
import java.util.*;

public class MST_Prim_AdjList {
	
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
		Node[] adjList = new Node[V]; // 인접 리스트
		boolean[] visited = new boolean[V]; // 트리 정점 체크
		PriorityQueue<int[]> minEdge = new PriorityQueue<>((e1,e2)->{
			return Integer.compare(e1[1], e2[1]);
		}); // 트리 정점들과 자신과의 간선 비용 최소값
		
		for (int i = 0; i < E; i++) {
		    st = new StringTokenizer(br.readLine());
		    int from = Integer.parseInt(st.nextToken());
		    int to = Integer.parseInt(st.nextToken());
		    int weight = Integer.parseInt(st.nextToken());
		    adjList[from] = new Node(to, weight, adjList[from]);
		    adjList[to] = new Node(from, weight, adjList[to]); 
		} // 인접리스트 완성

		// 전처리 2: 임의의 시작 정점의 minEdge 간선비용을 0으로 만듦 : 첫 번째 반복에서 선택될 트리의 시작정점을 만들기 위해
		minEdge.add(new int[] {0, 0});
		
		int result = 0; // MST 비용
		int c;
		for (c = 0; c < V; c++) {
			// step1 : 비트리 정점 중 최소 간선 비용의 정점 선택
			int minVertex = -1;
			
			while(!minEdge.isEmpty()){
				int[] cur = minEdge.poll();
				int vertex = cur[0];
				int weight = cur[1];
				if (!visited[vertex]) {
					minVertex = vertex;
					result += weight;
					break;
				}
			}
			
			if (minVertex == -1) break; // 신장 트리가 될 수 없다
			
			// 될 수 있는 애들은 여기로
			visited[minVertex] = true;
			
			// step2: 트리에 새롭게 추가된 정점과 비트리 인접 정점의 간선 비용 비교해서 업데이트
			for (Node temp = adjList[minVertex]; temp != null; temp = temp.next) {
				if (!visited[temp.vertex]) {
					minEdge.add(new int[] {temp.vertex, temp.weight});
				}
			}
		}
		
		System.out.println( c==V ? result : -1);
	}
}

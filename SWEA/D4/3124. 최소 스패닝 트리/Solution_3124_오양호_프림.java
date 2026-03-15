import java.io.*;
import java.util.*;

public class Solution {
	static int V, E;
	
	static class Node {
		int vertex, weight;
		Node next;
		public Node(int vertex, int weight, Node next) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> {
				return Integer.compare(e1[1], e2[1]);
			});
			
			Node[] adjList = new Node[V+1];
			boolean[] visited = new boolean[V+1]; // 인덱스 1번부터
			int[] minEdge = new int[V+1];
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				adjList[from] = new Node(to, weight, adjList[from]);
				adjList[to] = new Node(from, weight, adjList[to]);
			}
			
			final int INF = Integer.MAX_VALUE;
			long result = 0;
			int count = 0;
			
			Arrays.fill(minEdge, INF);
			
			minEdge[1] = 0;
			pq.add(new int[] {1, minEdge[1]});
			
			while(!pq.isEmpty()) {
				int[] cur = pq.poll();
				int curVertex = cur[0];
				int min = cur[1];
				
				if (visited[curVertex]) continue;
				
				visited[curVertex] = true;
				result += min;
				if (++count == V) break;
				
				for (Node temp = adjList[curVertex]; temp != null; temp = temp.next) {
					if (!visited[temp.vertex] && minEdge[temp.vertex] > temp.weight) {
						minEdge[temp.vertex] = temp.weight;
						pq.add(new int[] {temp.vertex, minEdge[temp.vertex]});
					}
				}
			}
			
			sb.append("#").append(t).append(" ");
			sb.append(result).append("\n");
			
		}

		System.out.println(sb);

	}
}

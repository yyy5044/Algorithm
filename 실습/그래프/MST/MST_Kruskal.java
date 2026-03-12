import java.io.*;
import java.util.*;

// 그래프 표현
// - 정점 중심 : 인접행렬, 인접리스트
// - 간선 중심 : 간선리스트

public class MST_Kruskal {

	static class Edge implements Comparable<Edge> {
		int start, end, weight; // 시작

		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static Edge[] edgeList;
	static int[] parents;
	static int V, E;
	
	private static void makeSets() {
		parents = new int[V];
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}
	}
	
	private static int findSet(int a) {
		if (parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	private static boolean unionSet(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		edgeList = new Edge[E];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(start, end, weight);
		} // 간선리스트 생성
		
		Arrays.sort(edgeList); // 간선 가중치 기준 오름차순 정렬
		
		makeSets(); // V개의 단위 서로소 집합(트리)로 만듦
		
		// 사용하지 않은 간선 중 가장 비용이 작은 간선들 사용하면서 처리
		int count = 0, result = 0; // 선택한 간선 개수, 간선비용 누적
		for (Edge edge : edgeList) {
			if (unionSet(edge.start, edge.end)) { // 선택한 간선이 사이클을 발생시키지 않았다면
				result += edge.weight;
				if(++count == V-1) break;
			}
		}
		
		System.out.println(result);
	}

}

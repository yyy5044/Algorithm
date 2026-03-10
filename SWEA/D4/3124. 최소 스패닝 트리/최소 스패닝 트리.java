import java.io.*;
import java.util.*;

public class Solution {
	static int V,E;
	static Edge[] edges;
	static int[] parents;
	
	static class Edge implements Comparable<Edge>{
		int start, end, weight;

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
	
	static void makeSets() {
		parents = new int[V+1];
		for (int i = 1; i <= V; i++) {
			parents[i] = -1;
		}
	}
	
	static int findSet(int x) {
		if (parents[x] < 0) return x;
		return parents[x] = findSet(parents[x]);
	}
	
	static boolean unionSet(int x, int y) {
		int xRoot = findSet(x);
		int yRoot = findSet(y);
		
		if (xRoot == yRoot) return false;
		
		// 집합의 크기가 큰쪽에 작은 집합을 붙인다.
		if (parents[xRoot] <= parents[yRoot]) { // 음수 크기 비교
			parents[xRoot] += parents[yRoot]; // a집합의 크기에 b집합의 크기를 더한다.
			parents[yRoot] = xRoot;
		} else {
			parents[yRoot] += parents[xRoot]; // a집합의 크기에 b집합의 크기를 더한다.
			parents[xRoot] = yRoot;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
			
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			edges = new Edge[E];
			
			// 1. 간선 리스트 생성
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				edges[i] = new Edge(start, end, weight);
			}
			
			// 2. 간선 정렬
			Arrays.sort(edges);
			
			makeSets();
			// 3. 간선 선택
			int count = 0;
			long result = 0;
			for (int i = 0; i < E; i++) {
				Edge cur = edges[i];
				// 고른 간선으로 unionSet시에 사이클 형성 판단, 사이클 형성 시 해당 간선은 버림
				if (unionSet(cur.start, cur.end)) {
					result += cur.weight;
					count++;
					
					if (count == V-1) break;
				}
			}
			
			sb.append("#").append(t).append(" ");
			sb.append(result).append("\n");
			
		}
		
		System.out.println(sb);
	}
}
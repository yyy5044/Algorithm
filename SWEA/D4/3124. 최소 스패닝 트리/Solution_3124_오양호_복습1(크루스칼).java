import java.io.*;
import java.util.*;

public class Solution {
	static int V, E;
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
		
		if (parents[xRoot] < parents[yRoot]) { // xRoot의 사이즈가 더 크다면
			parents[xRoot] += parents[yRoot];
			parents[yRoot] = xRoot;
		} else {
			parents[yRoot] += parents[xRoot];
			parents[xRoot] = yRoot;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken()); // 정점 수
			E = Integer.parseInt(st.nextToken()); // 간선 수
			
			Edge[] edges = new Edge[E];
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				edges[i] = new Edge(start, end, weight);
			}
			
			makeSets();
			
			Arrays.sort(edges);
			
			long result = 0;
			int count = 0;
			for (int i = 0; i < E; i++) {
				int start = edges[i].start;
				int end = edges[i].end;
				int weight = edges[i].weight;
				if (unionSet(start, end)) {
					result += weight;
					count++;
				}
				if (count == V-1) break;
			}
			
			sb.append("#").append(t).append(" ");
			sb.append(result).append("\n");
			
		}

		System.out.println(sb);

	}
}

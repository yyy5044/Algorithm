import java.io.*;
import java.util.*;

public class Solution {
	static int V, E;
	static int[] parents;
	static Edge[] edgeList;
	
	static void makeSets() {
		parents = new int[V];
		for (int i = 0; i < V; i++) {
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
		
		if (parents[xRoot] <= parents[yRoot]) {
			parents[xRoot] += parents[yRoot];
			parents[yRoot] = xRoot;
		} else {
			parents[yRoot] += parents[xRoot];
			parents[xRoot] = yRoot;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
 		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			V = Integer.parseInt(br.readLine());
			int[] islands_x = new int[V];
			int[] islands_y = new int[V];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < V; i++) {
				islands_x[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < V; i++) {
				islands_y[i] = Integer.parseInt(st.nextToken());
			}
			
			double taxRates = Double.parseDouble(br.readLine());
			
			edgeList = new Edge[(V*(V-1))/2]; // 모든 간선의 개수만큼
			
			// 가능한 모든 간선 생성
			int idx = 0;
			for (int i = 0; i < V; i++) {
				for (int j = i+1; j < V; j++) {
					double x1 = islands_x[i], y1 = islands_y[i];
					double x2 = islands_x[j], y2 = islands_y[j];
					
					double distSq = (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
					edgeList[idx++] = new Edge(i, j, taxRates*distSq);
				}
			}
			
			makeSets();
			
			Arrays.sort(edgeList); // 간선의 가중치에 대해 정렬
			
			// 비용이 낮은 것부터 고르기
			int count = 0;
			double result = 0;
			for (Edge edge: edgeList) {
				if (unionSet(edge.start, edge.end)) {
					result += edge.weight;
					count++;
					
					if (count == V-1) break;
				}
			}
			
			long ans = Math.round(result);
			
			sb.append("#").append(t).append(" ");
			sb.append(ans).append("\n");
			
		}

		System.out.println(sb);

	}
}

class Edge implements Comparable<Edge> {
	int start, end;
	double weight;

	public Edge(int start, int end, double weight) {
		super();
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		return Double.compare(this.weight, o.weight);

	}
}
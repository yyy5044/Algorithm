import java.io.*;
import java.util.*;

public class Solution {
	static int V;

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
			
			// 인접 행렬 구성
			double[][] adjMatrix = new double[V][V];
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					long x1 = islands_x[i];
					long y1 = islands_y[i];
					long x2 = islands_x[j];
					long y2 = islands_y[j];
					double distSq = (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
					adjMatrix[i][j] = taxRates*distSq;
				}
			}
			
			boolean[] visited = new boolean[V]; // MST
			
			// 우선순위 큐 준비
			PriorityQueue<double[]> minEdge = new PriorityQueue<>((e1, e2) -> {
				return Double.compare(e1[1], e2[1]);
			});
			
			minEdge.add(new double[] {0,0}); // 임의의 점 선택
			
			double result = 0;
			int c;
			
			for (c = 0; c < V; c++) { // 모든 정점에 대하여
				int minVertex = -1;
				
				while(!minEdge.isEmpty()) {
					double[] cur = minEdge.poll();
					int vertex = (int) cur[0];
					double weight = cur[1];
					
					if (!visited[vertex]) {
						minVertex = vertex;
						result += weight; // 결과 더하고
						break;
					}
				}
				
				if (minVertex == -1) break;
				
				visited[minVertex] = true; // 합류시키고
				for (int i = 0; i < V; i++) {
					if (!visited[i] && adjMatrix[minVertex][i] != 0) {
						minEdge.add(new double[] {i, adjMatrix[minVertex][i]});
					}
				}
			}
			
			long ans = Math.round(result);
			
			sb.append("#").append(t).append(" ");
			sb.append(ans).append("\n");
			
		}

		System.out.println(sb);

	}
}
import java.io.*;
import java.util.*;

public class Solution {
	static int N, M;
	static int[] parents;
	
	static void makeSets() {
		parents = new int[N+1]; // 인덱스 1부터 시작
		for (int i = 1; i <= N; i++) {
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
		
		if (parents[xRoot] < parents[yRoot]) {
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
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			makeSets();
			
			int count = 0;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				unionSet(x, y);
			}
			
			for (int i = 1; i < parents.length; i++) {
				if (parents[i] < 0) count++;
			}
			
			sb.append("#").append(t).append(" ");
			sb.append(count);
			sb.append("\n");
		}

		System.out.println(sb);

	}

}
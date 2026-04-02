import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
	static int[] parent;
	static int N, M;
	
	static void makeSets() {
		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = -1;
		}
	}
	
	static int findSet(int x) {
		if (parent[x] < 0) return x;
		return parent[x] = findSet(parent[x]);
	}
	
	static boolean union(int x, int y) {
		int xRoot = findSet(x);
		int yRoot = findSet(y);
		
		if (xRoot == yRoot) return false;
		
		if (parent[xRoot] < parent[yRoot]) {
			parent[xRoot] += parent[yRoot];
			parent[yRoot] = xRoot;
		} else {
			parent[yRoot] += parent[xRoot];
			parent[xRoot] = yRoot;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		makeSets();
		
		int ans = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if (!union(x, y)) {
				ans = i+1;
				break;
			}
		}
		
		System.out.println(ans);
	}

}

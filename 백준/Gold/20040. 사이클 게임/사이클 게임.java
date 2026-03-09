import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] parents;
	
	private static void makeSets() {
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	private static int findSet(int x) {
		if (parents[x] == x) return x;
		return parents[x] = findSet(parents[x]);
	}
	
	private static boolean unionSet(int x, int y) {
		boolean result = true;
		
		int xRoot = findSet(x);
		int yRoot = findSet(y);
		
		if (xRoot == yRoot) {
			result = false;
		} else {
			parents[yRoot] = xRoot;
		}
		
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		makeSets();
		
		boolean flag = false;
		int ans = 0;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			boolean nonCycle = unionSet(x, y);
			if (!flag) {
				if (!nonCycle) {
					flag = true;
					ans = i;
				}
			}
		}
		
		System.out.println(ans);
		
		System.out.println(sb);
	}
	
}
import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] parents;
	
	static void makeSets() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
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
		
		if (parents[xRoot] < parents[yRoot]) { // x가 더 큰 트리면
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
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		makeSets();
		
		// System.out.println(Arrays.toString(parents));
	
		int result = 0;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if (!unionSet(x, y)) {
				result = i;
				break;
			}
		}
		
		System.out.println(result);
	}

}

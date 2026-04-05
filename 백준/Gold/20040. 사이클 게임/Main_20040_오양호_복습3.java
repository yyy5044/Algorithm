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
		if (parents[x] < 0) return x; // 루트면 자기자신 리턴
		return parents[x] = findSet(parents[x]); // 경로 압축, 타고 올라가서 루트 찾으면 그쪽으로 붙이기
	}
	
	static boolean union(int x, int y) {
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

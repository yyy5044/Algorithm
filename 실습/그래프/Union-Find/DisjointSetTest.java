import java.util.Arrays;
import java.util.Iterator;

public class DisjointSetTest {

	static int N;
	static int[] parents;
	
	public static void makeSets() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i; // 자신의 부모를 자신으로하는 형태로 단위집합 생성
		}
	}
	
	public static int findSet(int a) {
		if (a == parents[a]) return a; // 자신이 자신의 부모라면, 즉 루트노드라면 집합의 대표자이므로 자신을 리턴
		return parents[a] = findSet(parents[a]); // path compression
	}
	
	public static boolean union(int a, int b) {
		
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if (aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
		
	}
	
	public static void main(String[] args) {
		
		N = 5;
		
		makeSets();
		System.out.println(Arrays.toString(parents));
		System.out.println(union(0,1));
		System.out.println(Arrays.toString(parents));
		System.out.println(union(2,1));
		System.out.println(Arrays.toString(parents));
		System.out.println(union(3,2));
		System.out.println(Arrays.toString(parents));
		System.out.println(union(4,3));
		System.out.println(Arrays.toString(parents));
		
		System.out.println(findSet(1));
		System.out.println(Arrays.toString(parents));
		
		// 지금처럼하면 skewed tree가 되어서 비효율적 -> 최적화 필요

	}

}

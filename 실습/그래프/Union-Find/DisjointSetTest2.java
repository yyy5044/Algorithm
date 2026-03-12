import java.util.Arrays;
import java.util.Iterator;

// 집합이 총 몇 개인가?
// a b가 같은 스터디
// b c가 같은 스터디
// ....
// 스터디 그룹이 총 몇개인가? : 집합의 수 == 대표자의 수
// 각 스터디 그룹의 구성원은 몇명인가요? 각 집합의 크기 (원소의 수)
public class DisjointSetTest2 {

	static int N;
	static int[] parents;
	
	public static void makeSets() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = -1; // 자신의 부모를 자신으로하는 형태로 단위집합 생성
		}
	}
	
	public static int findSet(int a) {
		if (parents[a] < 0) return a; // 자신이 자신의 부모라면, 즉 루트노드라면 집합의 대표자이므로 자신을 리턴
		return parents[a] = findSet(parents[a]); // path compression
	}
	
	public static boolean union(int a, int b) {
		
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if (aRoot == bRoot) return false;
		
		// 집합의 크기가 큰쪽에 작은 집합을 붙인다.
		if (parents[aRoot] <= parents[bRoot]) { // 음수 크기 비교
			parents[aRoot] += parents[bRoot]; // a집합의 크기에 b집합의 크기를 더한다.
			parents[bRoot] = aRoot;
		} else {
			parents[bRoot] += parents[aRoot]; // a집합의 크기에 b집합의 크기를 더한다.
			parents[aRoot] = bRoot;
		}
		
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

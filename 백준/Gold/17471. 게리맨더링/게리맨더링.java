import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
	static int N;
	static int[] population;
	
	static class Node {
		int vertex;
		Node next;
		public Node(int vertex, Node next) {
			this.vertex = vertex; 
			this.next = next;
		}
	}
	
	static boolean isValid(int[] party, Node[] adjList) {
		int sectionCnt = 0;
		int visitCnt = 1;
		
		boolean[] visited = new boolean[N+1];
		
		int randomV = -1;
		for (int i = 1; i <= N; i++) {
			if (party[i] != 0) {
				sectionCnt++;
				randomV = i;
			}
		}
		
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		visited[randomV] = true;
		dq.add(randomV);
		
		while(!dq.isEmpty()) {
			int cur = dq.poll();
			
			for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
				if (!visited[temp.vertex] && party[temp.vertex] != 0) {
					visited[temp.vertex] = true;
					dq.add(temp.vertex);
					visitCnt++;
				}
			}
		}
		
		if (visitCnt == sectionCnt) return true;
		else return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 입력부
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		population = new int[N+1];
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		Node[] adjList = new Node[N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int from = i;
			for (int j = 0; j < M; j++) {
				int to = Integer.parseInt(st.nextToken());
				adjList[from] = new Node(to, adjList[from]);
			}
		}
		
		int minDiff = Integer.MAX_VALUE;
		
		// 2. 경우의 수 출력
		for (int i = 1; i < (1<<N)-1; i++) {
			int[] aParty = new int[N+1];
			int[] bParty = new int[N+1];
			
			for (int j = 0; j < N; j++) {
				if ((i&(1<<j)) != 0) {
					aParty[j+1] = 1;
				} else {
					bParty[j+1] = 1;
				}
			}
			
			// 3. 유효성 검증
			if (!isValid(aParty, adjList) || !isValid(bParty, adjList)) continue;
			
			// 4. 되는 경우만 인구 계산 후 갱신
			int aPopulation = 0;
			int bPopulation = 0;
			for (int j = 1; j <= N; j++) {
				if (aParty[j] != 0) aPopulation += population[j];
				else bPopulation += population[j];
			}
			
			int diff = Math.abs(aPopulation - bPopulation);
			minDiff = Math.min(diff, minDiff);
		}
		
		if (minDiff == Integer.MAX_VALUE) minDiff = -1;
		
		System.out.println(minDiff);
	}

	
}

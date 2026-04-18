import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Solution {
	static int N;
	static boolean[] visited;
	static int[] x, y;
	static long minVector;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			minVector = Long.MAX_VALUE;
			
			N = Integer.parseInt(br.readLine());
			
			x = new int[N];
			y = new int[N];
			
			visited = new boolean[N];
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				x[i] = Integer.parseInt(st.nextToken());
				y[i] = Integer.parseInt(st.nextToken());
			}
			
			combination(0, 0);

			sb.append("#").append(t).append(" ");
			sb.append(minVector).append("\n");
			
		}

		System.out.println(sb);

	}
	
	static void combination(int depth, int start) {
	    // 1. 종료 조건: 절반(N/2)을 모두 골랐을 때
	    if (depth == N / 2) {
	        calculateVectorSum(); // 벡터 합을 계산하고 최솟값을 갱신하는 메서드 호출
	        return;
	    }

	    // 2. 재귀 확장: start부터 N까지 탐색하며 도착점 그룹 고르기
	    for (int i = start; i < N; i++) {
	        if (!visited[i]) {
	            visited[i] = true;   // i번째 지렁이를 도착점(+) 그룹으로 선택
	            combination(depth + 1, i + 1);
	            visited[i] = false;  // 백트래킹: 선택을 취소하고 다음 경우의 수 탐색
	        }
	    }
	}
	
	static void calculateVectorSum() {
	    long sumX = 0;
	    long sumY = 0;

	    // N마리 전체를 순회하면서 그룹에 따라 더하거나 뺌
	    for (int i = 0; i < N; i++) {
	        if (visited[i]) {
	            // visited가 true면 도착점 그룹 -> 좌표를 더함 (+)
	            sumX += x[i];
	            sumY += y[i];
	        } else {
	            // visited가 false면 자동으로 출발점 그룹 -> 좌표를 뺌 (-)
	            sumX -= x[i];
	            sumY -= y[i];
	        }
	    }

	    long currentVector = (sumX * sumX) + (sumY * sumY);
	    minVector = Math.min(minVector, currentVector);
	}
}

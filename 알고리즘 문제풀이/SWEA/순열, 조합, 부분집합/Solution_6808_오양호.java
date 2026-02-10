package com.ssafy.algorithm.month2;

import java.io.*;
import java.util.*;

public class Solution_6808_오양호 {
	static ArrayList<Integer> q0 = new ArrayList<>();
	static ArrayList<Integer> in0 = new ArrayList<>();
	static int win = 0;
	static int lose = 0;
	static int[] perm = new int[9];
	static boolean[] visited = new boolean[9];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			String line = br.readLine();
			StringTokenizer st = new StringTokenizer(line);
			
			for (int i = 0; i < 9; i++) {
				q0.add(Integer.parseInt(st.nextToken()));
			}
			ArrayList<Integer> cards = new ArrayList<>();
			for (int i = 0; i < 18; i++) {
				cards.add(i+1);
				if (!q0.contains(i + 1)) {
					in0.add(i+1);
				}
			}
			
			dfs(0);
			sb.append("#"+(t+1)+" "+win+" "+lose+"\n");
			win = 0;
			lose = 0;
			q0.clear();
			in0.clear();
		}
		

		System.out.println(sb);
		
	}
	
	public static void dfs(int depth) {
		// base case
		if (depth == 9) {
//			for(int i = 0; i < 9; i++) sb.append(perm[i] + " ");
//			sb.append("\n");
			int q0Score = 0, in0Score = 0;
			// 순열 생성
			for(int i = 0; i < 9; i++) {
				if (q0.get(i) < in0.get(perm[i])) {
					in0Score += q0.get(i) + in0.get(perm[i]);
				} else if (q0.get(i) > in0.get(perm[i])) {
					q0Score += q0.get(i) + in0.get(perm[i]);
				}
			}
			
			if (q0Score > in0Score) {
				win++;
			} else if (q0Score < in0Score) {
				lose++;
			}
			
			return;
		}
		
		for (int i = 0; i < 9; i++) {
			if (visited[i]) continue; // 이미 고른 수면 건너 뛰기
			visited[i] = true; // 다른 dfs 호출 전에 자기 자리 마킹
			perm[depth] = i; // 순열 자리수에 고른 숫자 넣어주기
			dfs(depth + 1); // 다른 dfs 호출
			visited[i] = false; // 다른 dfs 호출이 끝났으면 마킹 지워주기
		}
	} 

}
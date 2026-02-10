package com.ssafy.algorithm.month2;

import java.io.*;
import java.util.*;

public class Solution_4130_오양호 {
	static ArrayList<ArrayList<Integer>> mag = new ArrayList<>();
	static int numOfMag = 4;
	static int numOfBlade = 8;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			
			String line = br.readLine();
			StringTokenizer st = new StringTokenizer(line);
			int K = Integer.parseInt(st.nextToken()); // 회전 횟수
			
			for (int i = 0; i < numOfMag; i++) {
				mag.add(new ArrayList<>());
			}
			
			for (int i = 0; i < numOfMag; i++) {
				line = br.readLine();
				st = new StringTokenizer(line);
				for (int j = 0; j < numOfBlade; j++) {
					mag.get(i).add(Integer.parseInt(st.nextToken()));
				}
			}		

			for (int i = 0; i < K; i++) {
				line = br.readLine();
				st = new StringTokenizer(line);
				int idx = Integer.parseInt(st.nextToken());
				int rotateDir = Integer.parseInt(st.nextToken());
				rotate(idx - 1, rotateDir, 0, true);
			}
			
			int score = 0;
			int[] scoreBoard = new int[] {1, 2, 4, 8};
			for (int i = 0; i < numOfMag; i++) {
				if (mag.get(i).get(0) == 1) {
					score += scoreBoard[i];
				}
			}
			sb.append("#"+(t+1)+" "+score).append("\n");
			mag.clear();
			
//			for(int i = 0; i < numOfMag; i++) {
//				for (int j = 0; j < numOfBlade; j++) {
//					System.out.print(mag.get(i).get(j) + " ");
//				}
//				System.out.println();
//			}
		}
		System.out.println(sb);
	}
	
	public static void rotate(int idx, int rotateDir, int dx, boolean isFirst) {
		if (idx >= 0 && idx <= 3) { // 1~4번 자석 범위 안에서만 재귀 호출
			if (isFirst) { // 첫 번째 회전하는 자석만 양방향 확인
				if (idx < 3 &&(mag.get(idx).get(2) != mag.get(idx + 1).get(6))) { // 오른쪽 자석과 맞닿은 부분 극이 다르면
					rotate(idx + 1, rotateDir*(-1), 1, false); // 오른쪽 자석 호출
				}
				if (idx > 0 &&(mag.get(idx).get(6) != mag.get(idx - 1).get(2))) { // 왼쪽 자석과 맞닿은 부분 극이 다르면
					rotate(idx - 1, rotateDir*(-1), -1, false); // 왼쪽 자석 호출
				}
				
			} else { // 첫 번째 회전하는 자석이 아니면 한쪽 방향만 확인
				if (dx > 0) { // dx가 양수일 때
					if (idx < 3 && (mag.get(idx).get(2) != mag.get(idx + 1).get(6))) { // dx 방향 자석과 맞닿은 부분 극이 다르면
						rotate(idx + dx, rotateDir*(-1), dx, false); // dx 방향 자석 호출 
					}	
				} else if (dx < 0) { // dx가 음수일 
					if (idx > 0 && (mag.get(idx).get(6) != mag.get(idx - 1).get(2))) { // dx 방향 자석과 맞닿은 부분 극이 다르면
						rotate(idx + dx, rotateDir*(-1), dx, false); // dx 방향 자석 호출 
					}	
				}
			}
			Collections.rotate(mag.get(idx), rotateDir);// 다 끝나면 자기 회전 (rotateDir 방향으로)
		} else {
			return;
		}
	}
}
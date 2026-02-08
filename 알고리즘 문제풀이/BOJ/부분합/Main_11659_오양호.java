package com.ssafy.algorithm.month2;

import java.io.*;
import java.util.*;

public class Main_11659_오양호 {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine(); // 첫 번째 줄
		StringTokenizer st = new StringTokenizer(line);
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		
		line = br.readLine(); // 두 번째 줄
		st = new StringTokenizer(line);
		int[] arr = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
		}
		
		int[] psum = new int[N + 1];// 누적 합
		for (int i = 1; i < N + 1; i++) {
			psum[i] = psum[i - 1] + arr[i];
			//System.out.print(psum[i]);
		}
		
		for (int i = 0; i < M; i++) {
			line = br.readLine();
			st = new StringTokenizer(line);
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			int result = psum[y] - psum[x - 1];
			sb.append(result).append("\n");
		}
		
		System.out.println(sb);
	}

}

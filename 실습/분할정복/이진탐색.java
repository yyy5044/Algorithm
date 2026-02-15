import java.util.*;
import java.io.*;

// 입력
// 7
// 2 4 7 9 11 19 23
// 4

public class 이진탐색 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		int find = Integer.parseInt(br.readLine());
		
		Arrays.sort(input); // 이진 탐색은 반드시 정렬된 상태에 시작해야 함.
		
		int ans = iterativeBS(input, find);
		System.out.println("iterative Binary Search: " + ans);
		
		ans = recursiveBS(input, find, 0, N-1);
		System.out.println("recursive Binary Search: " + ans);
		
		System.out.println(sb);
	}
	
	public static int iterativeBS(int[] arr, int key) {
		int s = 0;
		int e = arr.length - 1;
		
		while(s<=e) {
			int mid = (s+e) / 2;
			if(key == arr[mid]) return mid;
			else if (key > arr[mid]) {
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}
		
		return -1; // 못 찾으면 -1 반환
	}
	
	public static int recursiveBS(int[] arr, int key, int s, int e) {
		if (s > e) return -1;
		
		int mid = (s+e)/2;
		if (key == arr[mid]) return mid;
		else if(key > arr[mid]) {
			return recursiveBS(arr, key, mid+1, e);
		} else {
			return recursiveBS(arr, key, s, mid-1);
		}
	}
		
}

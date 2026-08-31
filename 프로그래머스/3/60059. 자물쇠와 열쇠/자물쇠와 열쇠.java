import java.io.*;
import java.util.*;

class Solution {
    static int N, M;
    
    public boolean solution(int[][] key, int[][] lock) {
        // 1. 열쇠의 왼쪽 끝 기준으로
        // 2. 회전을 통한 4가지 경우의 수로 시작
        // 3. 각 경우의 수에서 평행이동 시켜보고 맞으면 true
        N = lock.length;
        M = key.length;
        
        // 평행이동 범위: -(M-1) ~ (N-1)
        // -> 0 ~ N+M-2
        
        for (int count = 0; count < 4; count++) {
            int[][] rotatedKey = rotation(key, count);
            for (int i = 0; i < N+M-1; i++) {
                for (int j = 0; j < N+M-1; j++) {
                    int[][] padding = new int[N+2*(M-1)][N+2*(M-1)];
                    for (int r = 0; r < M; r++) {
                        for (int c = 0; c < M; c++) {
                            padding[i+r][j+c] = rotatedKey[r][c];
                        }
                    }
                    
                    for (int r = 0; r < N; r++) {
                        for (int c = 0; c < N; c++) {
                            padding[M-1+r][M-1+c] += lock[r][c];
                        }
                    }
                    
                    boolean result = true;
                    for (int r = 0; r < N; r++) {
                        for (int c = 0; c < N; c++) {
                            if (padding[M-1+r][M-1+c] != 1) {
                                result = false;
                                break;
                            }
                        }
                        if (!result) break;
                    }
                    
                    if (result) return result;
                    
                    // for (int r = 0; r < N+2*(M-1); r++) {
                    //     System.out.println(Arrays.toString(padding[r]));
                    // }
                    // System.out.println();
                }
            }
        }
        
        return false;
    }
    
    static boolean check(int[][] arr) {
        boolean result = true;
        
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] != 1) {
                    return false;
                }
            }
        }
        
        return result;
    }
    
    static int[][] rotation(int[][] key, int count) {
        int[][] cur = key;
        for (int c = 0; c < count; c++) {
            int[][] next = new int[M][M];
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < M; j++) {
                    next[i][j] = cur[M-1-j][i];
                }
            }
            cur = next;
        }
        
        return cur;
    }
}
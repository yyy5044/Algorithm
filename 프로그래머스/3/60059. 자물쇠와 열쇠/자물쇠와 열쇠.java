import java.io.*;
import java.util.*;

class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int N = lock.length;
        int M = key.length;
        // 1. 기준은 열쇠의 왼쪽 끝부분
        // 2. 평행이동의 범위는 어떻게 되는가?
        // -(M-1) ~ N-1까지 -> 0 ~ N+M-2
        // 3. 회전 함수 만들기 -> 회전은 [i][j] = [M-1-j][i]
        
        boolean ans = false;
        for (int count = 0; count < 4; count++) {
            int[][] rotatedKey = rotation(key, count);
            for (int r = 0; r < N+M-1; r++) {
                for (int c = 0; c < N+M-1; c++) {
                    int[][] padding = new int[N+2*(M-1)][N+2*(M-1)];
                    // (r,c)가 왼쪽 끝
                    for (int i = 0; i < M; i++) {
                        for (int j = 0; j < M; j++) {
                           padding[r+i][c+j] = rotatedKey[i][j];
                        }
                    }
                    
                    // 중앙에 자물쇠 그리기
                    for (int i = 0; i < N; i++) {
                        for (int j = 0; j < N; j++) {
                            padding[M-1+i][M-1+j] += lock[i][j];
                        }
                    }
                    
                    // 딱 맞는지 검사
                    int sum = 0;
                    for (int i = 0; i < N; i++) {
                        for (int j = 0; j < N; j++) {
                            if (padding[M-1+i][M-1+j] == 1) sum++;
                        }
                    }
                    
                    if (sum == N*N) return true;
                }
            }
        }
        
        return false;
    }
    
    static int[][] rotation(int[][] key, int count){
        int M = key.length;
        int[][] result = key;
        int[][] tmp = key;
        for (int c = 0; c < count; c++) {
            result = new int[M][M];
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < M; j++) {
                    result[i][j] = tmp[M-1-j][i];
                }
            }
            tmp = result;
        }
        
        
        return result;
    }
}
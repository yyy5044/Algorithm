import java.io.*;
import java.util.*;
// 문은 자물쇠로 잠겨있다.
// 문 앞에는 열쇠와 자물쇠 푸는 방법이 써 있는 종이가 있다.
// 자물쇠는 N*N 크기의 정사각 격자 형태다.
// 열쇠는 M*M 크기인 정사각 격자 형태다.

class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int N = lock.length;
        int M = key.length;
        int P = 2*M+N-3;
        
        boolean ans = false;
        for (int count = 0; count < 4; count++) {
            int[][] rotatedKey = rotateClockwise(key, count);

            for (int n = 0; n < N+M-1; n++) {
                for (int m = 0; m < N+M-1; m++) {
                    int[][] board = new int[P+1][P+1];
                    
                    for (int r = M-1; r < M-1+N; r++) {
                        for (int c = M-1; c < M-1+N; c++) {
                            board[r][c] = lock[r-M+1][c-M+1];
                        }
                    }
                    
                    for (int r = 0; r < M; r++) {
                        for (int c = 0; c < M; c++) {
                            board[n+r][m+c] += rotatedKey[r][c];
                        }
                    }
                    
                    boolean flag = true;
                    for (int r = M-1; r < M-1+N; r++) {
                        for (int c = M-1; c < M-1+N; c++) {
                            if (board[r][c] != 1) {
                                flag = false;
                                break;
                            }
                        }
                        if (!flag) break;
                    }
                    
                    if (flag) {
                        ans = true;
                        break;
                    } 
                }
                if (ans) break;
            }
            
        }
        
        return ans;
    }
    
    
    static int[][] rotateClockwise(int[][] arr, int count) {
        int n = arr.length;
        int[][] cur = arr;
        for (int c = 0; c < count; c++) {
            int[][] next = new int[n][n];
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    next[i][j] = cur[n-1-j][i];
                }
            }
            cur = next;
        }

        return cur;
    }
}
import java.io.*;
import java.util.*;

public class Solution {
    static int[] dh = {1,-1,0,0};
    static int[] dw = {0,0,1,-1};
    static int[] curPos = {-1, -1};
    static char[][] map;
    static int H;
    static int W;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            map = new char[H][W];

            for (int h = 0; h < H; h++) {
                String line = br.readLine();
                for (int w = 0; w < W; w++) {
                    map[h][w] = line.charAt(w);
                    if (map[h][w] == '^' || map[h][w] == 'v' || map[h][w] == '>' || map[h][w] == '<') {
                        curPos[0] = h;
                        curPos[1] = w;
                    }
                }
            }

            int n = Integer.parseInt(br.readLine());
            String line = br.readLine();

            for (int i = 0; i < n; i++) {
                act(line.charAt(i));
            }

            sb.append("#").append(t).append(" ");
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }

        }

        System.out.println(sb);

    }

    static boolean isIn (int nh, int nw) {
        return (nh >= 0 && nw >= 0 && nh < H && nw < W);
    }
    static void act(char action) {
        int nh = curPos[0];
        int nw = curPos[1];

        switch (action) {
            case 'S':
                shoot();
                break;
            case 'D':
                turn(0);

                nh += dh[0];
                nw += dw[0];

                if (isIn(nh, nw) && map[nh][nw] == '.') {
                    map[nh][nw] = map[curPos[0]][curPos[1]];
                    map[curPos[0]][curPos[1]] = '.';
                    curPos[0] = nh;
                    curPos[1] = nw;
                }

                break;
            case 'U':
                turn(1);

                nh += dh[1];
                nw += dw[1];

                if (isIn(nh, nw) && map[nh][nw] == '.') {
                    map[nh][nw] = map[curPos[0]][curPos[1]];
                    map[curPos[0]][curPos[1]] = '.';
                    curPos[0] = nh;
                    curPos[1] = nw;
                }
                break;
            case 'R':
                turn(2);

                nh += dh[2];
                nw += dw[2];

                if (isIn(nh, nw) && map[nh][nw] == '.') {
                    map[nh][nw] = map[curPos[0]][curPos[1]];
                    map[curPos[0]][curPos[1]] = '.';
                    curPos[0] = nh;
                    curPos[1] = nw;
                }
                break;
            case 'L':
                turn(3);

                nh += dh[3];
                nw += dw[3];

                if (isIn(nh, nw) && map[nh][nw] == '.') {
                    map[nh][nw] = map[curPos[0]][curPos[1]];
                    map[curPos[0]][curPos[1]] = '.';
                    curPos[0] = nh;
                    curPos[1] = nw;
                }
                break;
        }

    }

    static void shoot() {
        int d = -1;
        switch (map[curPos[0]][curPos[1]]) {
            case 'v':
                d = 0;
                break;
            case '^':
                d = 1;
                break;
            case '>':
                d = 2;
                break;
            case '<':
                d = 3;
                break;
        }

        int nh = curPos[0];
        int nw = curPos[1];

        while (true) {
            nh += dh[d];
            nw += dw[d];

            if (!isIn(nh, nw)) break;

            if (map[nh][nw] == '*') {
                map[nh][nw] = '.';
                break;
            } else if (map[nh][nw] == '#') {
                break;
            }

        }
    }

    static void turn(int dir) {
        switch (dir) {
            case 0:
                map[curPos[0]][curPos[1]] = 'v';
                break;
            case 1:
                map[curPos[0]][curPos[1]] = '^';
                break;
            case 2:
                map[curPos[0]][curPos[1]] = '>';
                break;
            case 3:
                map[curPos[0]][curPos[1]] = '<';
                break;
        }
    }

}


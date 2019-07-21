import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

// 백트래킹을 잘 사용하면 풀 수 있는 문제
// 디저트 중복여부를 HashSet으로 해주었는데 이것보다는 배열로 중복제거 해주는게 메모리, 시간 절반...
// 배열로 중복제거. 아래쪽에 HashSet 버전 잇음.
public class Solution디저트카페 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		StringTokenizer token;

		for (int t = 1; t <= T; t++) {

			N = Integer.parseInt(br.readLine().trim());

			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				token = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());
				}
			}

//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}

			ans = 0;
			// 시작할 수 있는 점이 (0,1)부터 (N-3,N-2)까지임..
			for (int i = 0; i < N - 2; i++) {
				for (int j = 1; j < N - 1; j++) {
					visited = new int[N][N];
          // 디저트 번호 1~100 중복 확인.
					check = new boolean[101];
					sx = i;
					sy = j;
					dfs(i, j, 0, 0);
				}
			}

			// 먹은 디저트 없으면 -1 출력
			if (ans == 0) {
				ans = -1;
			}
			bw.write("#" + t + " " + ans + "\n");

			// 다시 시작해야 하므로 디저트 안에 있는거 비워줌.

		}
		bw.flush();
		bw.close();

	}

	static int N, ans, sx, sy;
	static int[][] visited, map;
	static boolean[] check;

	private static void dfs(int x, int y,int result, int cnt) {
		// 처음 시작.
		if (cnt == 0) {
			locChange(visited, x + 1, y + 1,result, cnt + 1);
		}
		// 방향 1번 바꿈
		else if (cnt == 1) {
			NoChange(visited, x + 1, y + 1, result,cnt);
			locChange(visited, x + 1, y - 1,result, cnt + 1);
		}
		// 방향 2번 바꿈
		else if (cnt == 2) {
			NoChange(visited, x + 1, y - 1,result, cnt);
			locChange(visited, x - 1, y - 1,result, cnt + 1);
		}
		// 방향 3번 바꿈
		else if (cnt == 3) {
			NoChange(visited, x - 1, y - 1,result, cnt);
			locChange(visited, x - 1, y + 1,result, cnt + 1);
		}
		// 방향 4번 바꿈
		else if (cnt == 4) {
			// 만약 시작점이랑 같으면 먹은 디저트 갯수 세고 종료
			if (x == sx && y == sy) {
				ans = Math.max(ans, result);
				return;
			}
			// 시작점에 아직 도달 못했으면 직진
			else {
				NoChange(visited, x - 1, y + 1,result, cnt);
			}
		}

	}

	// 방향안바꾸고 직진
	private static void NoChange(int[][] visited, int x, int y, int result, int cnt) {
		// 범위 확인
		if (x < 0 || y < 0 || x >= N || y >= N) {
			return;
		}

		// 디저트 중복된거 있거나, 방문했던 곳이면 리턴
		if (check[map[x][y]] || visited[x][y] == 1) {
			return;
		} 
		else {
			// 디저트 먹고, 방문체크
			check[map[x][y]] = true;
			visited[x][y] = 1;
			dfs(x, y,result+1, cnt);
			// 방문체크, 디저트 묵은거 다시 원상태로 돌려줌.
			check[map[x][y]] = false;
			visited[x][y] = 0;
		}

	}

	// 방향 바꿈.
	static void locChange(int[][] visited, int x, int y, int result, int cnt) {
		// 범위 확인
		if (x < 0 || y < 0 || x >= N || y >= N) {
			return;
		}

		// 디저트 중복된거 있거나, 방문했던 곳이면 리턴
		if (check[map[x][y]] || visited[x][y] == 1) {
			return;
		} 
		else {
			// 디저트 먹고, 방문체크
			check[map[x][y]] = true;
			visited[x][y] = 1;
			dfs(x, y,result+1, cnt);
			// 방문체크, 디저트 묵은거 다시 원상태로 돌려줌.
			check[map[x][y]] = false;
			visited[x][y] = 0;
		}
	}
	
}

// =========================================================================================
// HashSet으로 중복제거.

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution디저트카페 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		StringTokenizer token;

		for (int t = 1; t <= T; t++) {

			N = Integer.parseInt(br.readLine().trim());

			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				token = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());
				}
			}

//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			ans=0;
			// 시작할 수 있는 점이 (0,1)부터 (N-3,N-2)까지임..
			for (int i = 0; i < N - 2; i++) {
				for (int j = 1; j < N - 1; j++) {
					visited = new int[N][N];
					sx = i; sy=j;
					dfs(i, j, 0);
				}
			}
			
			// 먹은 디저트 없으면 -1 출력
			if(ans==0) {
				ans=-1;
			}
			bw.write("#"+t+" "+ans+"\n");
			
			// 다시 시작해야 하므로 디저트 안에 있는거 비워줌.
			dessert.clear();
			
		}
		bw.flush();
		bw.close();

	}

	static int N, ans, sx, sy;
	static int[][] visited, map;
	static HashSet<Integer> dessert = new HashSet<>();

	private static void dfs(int x, int y, int cnt) {
		// 처음 시작.
		if (cnt == 0) {
			locChange(visited, x+1, y+1 , cnt+1);
		} 
		// 방향 1번 바꿈
		else if (cnt == 1) {
			NoChange(visited, x+1, y+1, cnt);
			locChange(visited, x+1, y-1, cnt+1);
		}
		// 방향 2번 바꿈
		else if (cnt == 2) {
			NoChange( visited, x+1, y-1, cnt);
			locChange(visited, x-1, y-1, cnt+1);
		}
		// 방향 3번 바꿈
		else if (cnt == 3) {
			NoChange(visited, x-1, y-1, cnt);
			locChange(visited, x-1, y+1, cnt+1);
		}
		// 방향 4번 바꿈
		else if (cnt == 4) {
			// 만약 시작점이랑 같으면 먹은 디저트 갯수 세고 종료
			if(x==sx && y==sy) {
				ans = Math.max(ans,dessert.size());
				return;
			}
			// 시작점에 아직 도달 못했으면 직진
			else {
				NoChange(visited, x-1, y+1, cnt);
			}
		}

	}
	
	// 방향안바꾸고 직진
	private static void NoChange(int[][] visited, int x, int y, int cnt) {
		// 범위 확인
		if (x < 0 || y < 0 || x >= N || y >= N) {
			return;
		}
		
		// 디저트 중복된거 있거나, 방문했던 곳이면 리턴
		if (dessert.contains(map[x][y]) || visited[x][y] == 1) {
			return;
		} 
		else {
			// 디저트 먹고, 방문체크
			dessert.add(map[x][y]);
			visited[x][y] = 1;
			dfs(x, y, cnt);
			// 방문체크, 디저트 묵은거 다시 원상태로 돌려줌.
			dessert.remove(map[x][y]);
			visited[x][y] = 0;
		}
		
	}
	
	// 방향 바꿈.
	static void locChange(int[][] visited, int x, int y, int cnt) {
		// 범위 확인
		if (x < 0 || y < 0 || x >= N || y >= N) {
			return;
		}

		if (dessert.contains(map[x][y]) || visited[x][y] == 1) {
			return;
		} 
		else {
			// 디저트 먹고, 방문체크
			dessert.add(map[x][y]);
			visited[x][y] = 1;
			dfs(x, y, cnt);
			// 방문체크, 디저트 묵은거 다시 원상태로 돌려줌.
			dessert.remove(map[x][y]);
			visited[x][y] = 0;
		}
	}

}


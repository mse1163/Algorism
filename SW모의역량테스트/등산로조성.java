import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
// 백트래킹..완탐문제..
// 시작점에 방문체크 안해줘서 틀렷었음..조심하자..ㅠㅠ

public class Solution등산로조성 {
	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());

		StringTokenizer token;

		for (int t = 1; t <= T; t++) {
			token = new StringTokenizer(br.readLine().trim());

			N = Integer.parseInt(token.nextToken());
			K = Integer.parseInt(token.nextToken());

			int[][] map = new int[N][N];

			int max = -1;
			for (int i = 0; i < N; i++) {
				token = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());
					
					// 최대 봉우리 저장.
					if (max < map[i][j]) {
						max = map[i][j];
						max_mount.clear();
						max_mount.add(new Point(i, j));
					} else if (max == map[i][j]) {
						max_mount.add(new Point(i, j));
					}
				}
			}

//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			ans=-1;
			// 최대봉우리에서 시작.
			for (int i = 0; i < max_mount.size(); i++) {
				int x = max_mount.get(i).x;
				int y = max_mount.get(i).y;
				
				// 방문체크 배열
				visited = new boolean[N][N];
				// 시작점..방문체크 안해줘가지고...틀렷었음..ㅠㅠ
				visited[x][y]=true;
				//시작점부터 카운트 세므로 cnt=1
				dfs(map, x, y, 1, false);
			}

			bw.write("#"+t+" "+ans+"\n");
		}
		bw.flush();
		bw.close();

	}

	static int N, K, ans;
	static boolean[][] visited;
	static ArrayList<Point> max_mount = new ArrayList<>();
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	
	// 4방향 확인, isok -> 산 깎았는지 체크여부
	static void dfs(int[][] map, int x, int y, int cnt, boolean isok) {
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			// 범위 확인.
			if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
				continue;
			}
			
			// 갈 수 있는 봉우리 -> 아직 방문 안햇음.
			if (map[nx][ny] < map[x][y] && !visited[nx][ny]) {
				// 방문체크.
				visited[nx][ny] = true;
				dfs(map, nx, ny, cnt + 1, isok);
				// 방문 풀어주기.
				visited[nx][ny] = false;
			} 
			// 갈 수 없는 봉우리 -> 아직 방문안했고 봉우리 안깎음.
			else if (!visited[nx][ny] && !isok) {
				// 최대 K깊이까지 반복.
				for (int k = 1; k <= K; k++) {
					// k만큼 깍은 높이가 0보다 크거나 같아야되, k만큼 봉우리 깎으면 지나갈 수 있음? 
					if (map[nx][ny] - k>=0 && map[nx][ny] - k < map[x][y]) {
						// 방문체크
						visited[nx][ny] = true;
						// 맵에서 봉우리 깎음.
						map[nx][ny] = map[nx][ny] - k;
						// 봉우리 깎은거 표시.
						isok = true;
						
						dfs(map, nx, ny, cnt + 1, isok);
						
						// 봉우리 깎은거 다시 돌려놓음. 
						isok = false;
						map[nx][ny] = map[nx][ny] + k;
						// 방문도 풀어줌.
						visited[nx][ny] = false;
					}
				}
			}
		}
		
		// 갈 수 있는 최댓값
		ans = Math.max(ans, cnt);
	}

}

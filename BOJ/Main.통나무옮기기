import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

// 통나무 옮기기 
// 어려운 문제엿음...
// 처음에 코드는 잘 짰으나 방문체크를 2차배열하나에 해서 방향이 다를때 방문체크에 문제 있엇음
// ->가로, 세로 나눠서 방문체크함.
// 종료조건에 들어오지 못할때 계속 무한 루프돔..
// -> 회전할때 방문체크여부 확인하고 방문체크 해줬어야함. 
// -> 가로방향일때는 세로방문체크, 세로 방향일때는 가로방문체크 <-- 요게 핵심.
// 방문체크를 어떻게 하냐에 따라 달라짐...
public class Main통나무옮기기 {
	
	static class Point {
		int x, y, cnt;
		char loc;

		public Point(int x, int y, int cnt, char loc) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.loc = loc;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", cnt=" + cnt + ", loc=" + loc + "]";
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		map = new char[N][N];
		
		// B,E가 2번째 나온 위치 찾기 위한 카운트변수
		int B_cnt = 0, E_cnt = 0;
		// B,E 위치 기억하기 위한 변수
		int bx = -1, by = -1, ex = -1, ey = -1;
		
		// 맵 입력 받기.
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
				
				// 'B'길이가 항상 3이므로 그중 가운데를 기준으로 움직일거임.
				if (map[i][j] == 'B') {
					B_cnt++;
					// B가 2번째 나왔으면
					if (B_cnt == 2) {
						//기억해둔 시작위치랑 같은지 비교해서 모양 기억함.
						// x좌표가 같으면 '-' 모양
						if (bx == i) {
							q.add(new Point(i, j, 0, '-'));
						}
						// y좌표가 같으면 '|' 모양
						else if (by == j) {
							q.add(new Point(i, j, 0, '|'));
						}
					}
					// B가 처음 시작한 위치 기억.
					bx = i;
					by = j;
				}
				// B와 마찬가지로 E의 가운데 위치랑 모양 기억함.
				else if (map[i][j] == 'E') {
					E_cnt++;
					if (E_cnt == 2) {
						if (ex == i) {
							end = new Point(i, j, 0, '-');
						} else if (ey == j) {
							end = new Point(i, j, 0, '|');
						}
					}
					ex = i;
					ey = j;
				}

			}
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		ans = 0;
		move();

		System.out.println(ans);
	}

	static int N, ans;
	static char[][] map;
	static Point end;
	// 90도 회전할때 3*3방향 확인.
	static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };
	static Queue<Point> q = new LinkedList<>();
	
	// 통나무 움직이는 함수
	static void move() {
		// 가로(Horizontal) 방향일때 방문체크 배열
		int[][] H_sel = new int[N][N];
		// 세로(Vertical) 방향일 때 방문체크 배열
		int[][] V_sel = new int[N][N];
		
		while (!q.isEmpty()) {

			Point node = q.poll();
			int x = node.x;
			int y = node.y;
			int cnt = node.cnt;
			char loc = node.loc;
			
			// 끝나는 위치랑 방향 같은지 확인, 같으면 종료
			if (x == end.x && y == end.y && loc == end.loc) {
				// bfs는 최단거리를 찾아주므로 궅이 최소값 안찾아줘도 되는듯..?
				ans = cnt;
				return;
			}
			
			// 가로 방향 상,하,좌,우,90도회전 돌기.
			if (loc == '-') {
				Hup(H_sel, x, y, cnt);
				Hdown(H_sel, x, y, cnt);
				Hleft(H_sel, x, y, cnt);
				Hright(H_sel, x, y, cnt);
				Htrun(V_sel, x, y, cnt);
			}
			// 세로 방향 상,하,좌,우,90회전 돌기
			else if (loc == '|') {
				Vup(V_sel, x, y, cnt);
				Vdown(V_sel, x, y, cnt);
				Vleft(V_sel, x, y, cnt);
				Vright(V_sel, x, y, cnt);
				Vtrun(H_sel, x, y, cnt);
			}
		}

	}
	
	// 가로 위
	private static void Hup(int[][] H_sel, int x, int y, int cnt) {
		x = x - 1;
		
		// 범위 체크
		if (x < 0) {
			return;
		}
		
		// 가로방향 방문체크확인. 가로 방향이 위로 올라가면 3개 동시에 위로 올라가므로 좌우도 같이 확인.
		if (H_sel[x][y] == 0 && map[x][y - 1] != '1' && map[x][y] != '1' && map[x][y + 1] != '1') {
			// 방문체크
			H_sel[x][y] = 1;
			// 횟수 증가하고 큐에 담음.
			q.add(new Point(x, y, cnt + 1, '-'));
		}

	}
	
	// 가로 아래
	private static void Hdown(int[][] H_sel, int x, int y, int cnt) {
		x = x + 1;
		
		// 범위 체크
		if (x >= N) {
			return;
		}
		
		if (H_sel[x][y] == 0 && map[x][y - 1] != '1' && map[x][y] != '1' && map[x][y + 1] != '1') {
			H_sel[x][y] = 1;
			q.add(new Point(x, y, cnt + 1, '-'));
		}

	}
	
	// 가로 왼쪽
	private static void Hleft(int[][] H_sel, int x, int y, int cnt) {
		y = y - 1;
		
		// 범위 체크-> 왼쪽으로 이동하므로 제일 왼쪽애만 확인하면 됨.
		if (y - 1 < 0) {
			return;
		}
		
		// 가로 방문확인, 제일 왼쪽애만 확인.
		if (H_sel[x][y] == 0 && map[x][y - 1] != '1') {
			H_sel[x][y] = 1;
			q.add(new Point(x, y, cnt + 1, '-'));
		}

	}
	
	// 가로 오른쪽
	private static void Hright(int[][] H_sel, int x, int y, int cnt) {
		y = y + 1;
		
		// 범위 체크-> 오른쪽으로 이동하므로 제일 오른쪽애만 확인하면 됨.
		if (y + 1 >= N) {
			return;
		}
		
		// 제일 오른쪽애만 확인
		if (H_sel[x][y] == 0 && map[x][y + 1] != '1') {
			H_sel[x][y] = 1;
			q.add(new Point(x, y, cnt + 1, '-'));
		}

	}
	
	// 가로 -> 세로 90도 회전.
	private static void Htrun(int[][] V_sel, int x, int y, int cnt) {
		// 3*3방향 회전할 수 있는지 확인.
		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			//범위 확인
			if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
				return;
			}
			
			// 주변에 1있으면 회전 못하므로 리턴. 
			if (map[nx][ny] == '1') {
				return;
			}
		}
		
		// 8방향 다 확인해서 회전 가능
		// 회전하면 세로가 되므로 세로 방문체크함.
		// 이거 안하면 종료조건에 못들어올때 계속 무한으로 회전하면서 안끝남.
		if (V_sel[x][y] == 0) {
			V_sel[x][y] = 1;
			// 방향을 세로로 해서 큐에 담아줌.
			q.add(new Point(x, y, cnt + 1, '|'));
		}
	}
	
	//***** 위에랑 설명은 같음  ***** 
	// 세로 위
	private static void Vup(int[][] V_sel, int x, int y, int cnt) {
		x = x - 1;

		if (x - 1 < 0) {
			return;
		}

		if (V_sel[x][y] == 0 && map[x - 1][y] != '1') {
			V_sel[x][y] = 1;
			q.add(new Point(x, y, cnt + 1, '|'));
		}
	}
	
	// 세로 아래
	private static void Vdown(int[][] V_sel, int x, int y, int cnt) {
		x = x + 1;

		if (x + 1 >= N) {
			return;
		}

		if (V_sel[x][y] == 0 && map[x + 1][y] != '1') {
			V_sel[x][y] = 1;
			q.add(new Point(x, y, cnt + 1, '|'));
		}
	}
	
	// 세로 왼쪽
	private static void Vleft(int[][] V_sel, int x, int y, int cnt) {
		y = y - 1;

		if (y < 0) {
			return;
		}

		if (V_sel[x][y] == 0 && map[x - 1][y] != '1' && map[x][y] != '1' && map[x + 1][y] != '1') {
			V_sel[x][y] = 1;
			q.add(new Point(x, y, cnt + 1, '|'));
		}

	}
	
	// 세로 오른쪽
	private static void Vright(int[][] V_sel, int x, int y, int cnt) {
		y = y + 1;

		// 범위확인
		if (y >= N) {
			return;
		}

		if (V_sel[x][y] == 0 && map[x - 1][y] != '1' && map[x][y] != '1' && map[x + 1][y] != '1') {
			V_sel[x][y] = 1;
			q.add(new Point(x, y, cnt + 1, '|'));
		}

	}
	
	// 세로 -> 가로 90도 회전
	private static void Vtrun(int[][] H_sel, int x, int y, int cnt) {
		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
				return;
			}

			if (map[nx][ny] == '1') {
				return;
			}
		}

		if (H_sel[x][y] == 0) {
			H_sel[x][y] = 1;
			q.add(new Point(x, y, cnt + 1, '-'));
		}
	}

}

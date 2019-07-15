import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
// 단순한 bfs문제.
// 도착점인 3도 지나갈수 있어야 하는데 이 조건을 빠트려서 조금 고민함.
public class Solution미로2 {
	static class Point{
		int x,y;

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
		
		
		for(int t=1; t<=10; t++) {
			
			Integer.parseInt(br.readLine());
			
			map = new int[100][100];
			
			for(int i=0; i<100; i++) {
				String str = br.readLine();
				for(int j=0; j<100; j++) {
					map[i][j] = str.charAt(j)-'0';
					// 시작점
					if(map[i][j]==2) {
						q.add(new Point(i, j));
					}
          // 도착점
					else if(map[i][j]==3) {
						ex=i; ey=j;
					}
				}
			}
			
//			for(int i=0; i<100; i++) {
//				for(int j=0; j<100; j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
			
			ans = 0;
			bfs();
			System.out.println(t);
			bw.write("#"+t+" "+ans+"\n");
			q.clear();
			
		}
		bw.flush();
		bw.close();
	}
	
	static int[][] map;
	static int ex,ey,ans;
	static Queue<Point> q = new LinkedList<>();
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static void bfs() {
		while(!q.isEmpty()) {
			
			Point node = q.poll();
			
			int x = node.x;
			int y = node.y;
			
			if(x==ex && y==ey) {
				ans = 1;
				return;
			}
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx<0 || ny<0 || nx>=100 || ny>=100) {
					continue;
				}
				
				if(map[nx][ny]==0 || map[nx][ny]==3) {
					map[nx][ny]=9;
					q.add(new Point(nx, ny));
				}
			}
		}
	}
}

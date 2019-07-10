import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 조합으로 풀수 있는 문제
// 백트래킹으로도 풀어봐야하는뎅...
public class Main로또 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 0이 나올때까지 반복
		while(true) {
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(token.nextToken());
		
		// 0이나오면 끝
		if(N==0) {
			return;
		}
		
		int[] num = new int[N];
		
		for(int i=0; i<N;i ++) {
			num[i] = Integer.parseInt(token.nextToken());
		}
		
		// 로또는 6자리로 고정
		int[] sel = new int[6];
		combi(num, sel, 0, 0);
		System.out.println();
		}
	}
	static int N;
	// 조합으로 뽑음.
	static void combi(int[] num, int[] sel, int cnt, int idx) {
		if(cnt==6) {
			for(int i=0; i<6; i++) {
				System.out.print(sel[i]+" ");
			}
			System.out.println();
			return;
		}
		
		if(idx==N) {
			return;
		}
		
		sel[cnt] = num[idx];
		combi(num, sel, cnt+1, idx+1);
		combi(num, sel, cnt, idx+1);
	}

}

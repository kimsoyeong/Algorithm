import java.util.ArrayList;
import java.util.Scanner;

class Node {
	int row;
	int col;
	char type;

	Node(int i, int j, char type){
		this.row = i;
		this.col = j;
		this.type = type;
	}
}

public class P3055 {

	static int R, C;
	static ArrayList<Node> q;
	
	static int[][] dp;
	static boolean foundAnswer;
	
	static final int[] DX = {-1, 1, 0, 0};
	static final int[] DY = {0, 0, -1, 1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		R = sc.nextInt();
		C = sc.nextInt();

		char[][] map = new char[R][C];   // ����
		dp = new int[R][C]; // �湮 ����) �湮x -> 0
		
		q = new ArrayList<>();
		
		int sx = 0, sy = 0; // start location
		
		for(int i=0; i < R; i++) {
			String line = sc.next();
			
			for(int j=0; j < C; j++) {
				map[i][j] = line.charAt(j);
				switch(map[i][j]) {
				case '*': // ��
					q.add(new Node(i,j, '*'));
					break;
				case 'S': // ����ġ�� ��ġ
					sy = i;
					sx = j;
					break;
				}
			}
		}
		
		q.add(new Node(sy, sx, 'S'));
		
		while(!q.isEmpty()) {
			// 1. ť���� ������ -> S, ., D, *
			Node p = q.remove(0);
			// 2. �������ΰ�? -> D
			if(p.type == 'D') {
				System.out.println(dp[p.row][p.col]);
				foundAnswer = true;
				break;
			}
			// 3. ����� ���� ��ȸ -> ��, ��, ��, �Ʒ�
			for(int i=0; i<4; i++) {
				int ty = p.row + DY[i];
				int tx = p.col + DX[i];
				// 4. �� �� �ִ� ��? ( ���� ) -> ���� ����� �ʰ�
				if(0 <= ty && ty < R && 0 <= tx && tx < C) {
					if(p.type == '.' || p.type == 'S') { 
						// 4. �� �� �ִ� ��? ( ����ġ ) -> ���� ����� �ʰ�, . or D, �湮���� ���� ��
						if((map[ty][tx] == '.' || map[ty][tx] == 'D') && dp[ty][tx] == 0) {
							// 5. üũ�� -> dp�� ���� + 1�� ���
							dp[ty][tx] = dp[p.row][p.col] + 1;
							// 6. ť�� ����
							q.add(new Node(ty, tx, map[ty][tx]));
						}
						
					} else if(p.type == '*') { 
						// 4. �� �� �ִ� ��? ( �� ) .
						if(map[ty][tx] == '.' || map[ty][tx] == 'S') {
							// 5. üũ�� -> ������ * ǥ��
							map[ty][tx] ='*';
							// 6. ť�� ����
							q.add(new Node(ty, tx, map[ty][tx]));
						}
						
					}
					
				}
			}
		}
		
		if(!foundAnswer) {
			System.out.println("KAKTUS");
		}
		
	}
}

//备忘录（动态规划变形）求解找零问题
//2014-10-24

import java.util.Scanner;

public class CoinDesign {
	int des[][]; // 分配方式

	int total; // 总额

	int kind;
	int value[]; // 面额

	void input() {
		Scanner sc = new Scanner(System.in);
		System.out.println("输入总额");
		total = sc.nextInt();

		System.out.println("输入硬币种数");
		kind = sc.nextInt();

		System.out.println("输入各硬币面额，确保最小值为1");
		value = new int[kind + 1];
		for (int i = 1; i <= kind; i++) {
			value[i] = sc.nextInt();
		}

		sc.close();
	}

	// 找零方式初始化，D[0][K] = 0, D[1][K] = 1; D[K][1] = K; D[K][0]无意义
	void initDes() {
		des = new int[total + 1][kind + 1];
		for (int i = 0; i <= total; i++) {
			for (int j = 0; j <= kind; j++) {
				des[i][j] = -1;
			}
		}

		for (int i = 1; i <= kind; i++) {
			des[0][i] = 0;
			des[1][i] = 1;
		}
		for (int j = 0; j <= total; j++) {
			des[j][1] = j;
		}
	}

	int coinDesign() {
		initDes();
		return getDesign(total, kind);
	}

	int getDesign(int t, int k) {
		if (des[t][k] != -1) {
			return des[t][k];
		} else {
			if (t < value[k]) {
				des[t][k] = getDesign(t, k - 1);
				return des[t][k];
			} else {
				int r1 = getDesign(t, k - 1);
				int r2 = getDesign(t - value[k], k) + 1;

				des[t][k] = Math.min(r1, r2);
				return des[t][k];
			}
		}
	}

	void print() {
		int t = total, k = kind;
		while (t > 0) {
			if (t < value[k]) {
				k = k - 1;
			} else {
				if (des[t][k - 1] <= des[t - value[k]][k] + 1
						&& des[t][k - 1] != -1) {
					k = k - 1;
				} else {
					t = t - value[k];
					System.out.println(value[k] + "一张");
				}
			}
		}
	}

	public static void main(String args[]) {
		CoinDesign cd = new CoinDesign();
		cd.input();
		int r = cd.coinDesign();
		System.out.println(r);
		cd.print();
	}
}

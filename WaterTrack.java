//深度优先搜索，递归
// 2014-10-24

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class WaterTrack {
	int CA = 11; // 两个桶的容量
	int CB = 4;
	int dest = 3; // 目标

	// 6个操作
	String opName[] = { "装满A桶    ", "装满B桶    ", "清空A桶    ", "清空B桶     ",
			"A中水倒入B", "B中水倒入A", };

	// 结点
	class Node {
		int qa = 0;
		int qb = 0;

		@Override
		public int hashCode() {
			int ret = qa * 1024 + qb;
			return ret;
		}

		@Override
		public boolean equals(Object n) {
			Node nd = (Node) n;
			return nd.qa == qa && nd.qb == qb;
		}
	}

	// 带路径的结点
	class PathNode extends Node {
		List<Integer> path = new ArrayList<Integer>();
	}

	PathNode pnResult;
	// 已访问结点
	Set<Node> visited = new HashSet<Node>();

	// 根据操作码进行操作
	void operate(PathNode nd, int code) {
		switch (code) {
		case 0:
			nd.qa = CA;
			break;
		case 1:
			nd.qb = CB;
			break;
		case 2:
			nd.qa = 0;
			break;
		case 3:
			nd.qb = 0;
			break;
		case 4:
			if (nd.qa <= CB - nd.qb) {
				nd.qb += nd.qa;
				nd.qa = 0;
			} else {
				nd.qa -= CB - nd.qb;
				nd.qb = CB;
			}
			break;
		case 5:
			if (nd.qb <= CA - nd.qa) {
				nd.qa += nd.qb;
				nd.qb = 0;
			} else {
				nd.qb -= CA - nd.qa;
				nd.qa = CA;
			}
			break;
		}
		nd.path.add(code);
	}

	// 成功
	boolean success(Node nd) {
		return nd.qa == dest || nd.qb == dest
				|| (dest > nd.qa && dest > nd.qb && dest == nd.qa + nd.qb);
	}

	// 已访问
	boolean visited(Node nd) {
		return visited.contains(nd);
	}

	void searchTrack(PathNode pn) {
		if (pnResult != null) {
			return;
		} else if (success(pn)) {
			pnResult = pn;
			return;
		} else {
			for (int i = 0; i <= 5; i++) {
				PathNode pnNew = new PathNode();
				pnNew.qa = pn.qa;
				pnNew.qb = pn.qb;
				pnNew.path.addAll(pn.path);
				operate(pnNew, i);
				
				if (!visited(pnNew)){
					visited.add(pnNew);
					searchTrack(pnNew);
				}
			}
		}
	}

	void search() {
		PathNode pn = new PathNode();
		visited.add(pn);
		
		searchTrack(pn);

		if (pnResult != null) {
			print(pnResult);
		} else {
			System.out.println("无解");
		}
	}

	void print(PathNode pn) {
		PathNode pathResult = new PathNode();
		for (Integer i : pn.path) {
			this.operate(pathResult, i);
			System.out.println(opName[i] + "\t\t\t--->" + pathResult.qa + ' '
					+ pathResult.qb);
		}
	}

	void input() {
		System.out.println("依次输入A B桶的容量和目标水量，如6 5 3");
		Scanner sc = new Scanner(System.in);
		CA = sc.nextInt();
		CB = sc.nextInt();
		dest = sc.nextInt();
		sc.close();
	}

	public static void main(String args[]) {
		WaterTrack w = new WaterTrack();
		w.input();
		w.search();
	}
}

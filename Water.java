//广度优先搜索
// 2014-10-24
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Water {
	int CA = 11; // 两个桶的容量
	int CB = 4;
	int dest = 3; // 目标

	// 6个操作
	String opName[] = { "装满A桶    ", "装满B桶    ", "清空A桶    ", "清空B桶     ",
			"A中水倒入B", "B中水倒入A", };

	// 搜索队列
	Queue<PathNode> queue = new LinkedList<PathNode>();

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
		public boolean equals(Object n){
			Node nd = (Node)n;
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

	void search() {
		PathNode pn = new PathNode();
		queue.offer(pn);
		visited.add(pn);

		int status = 0; // 0搜索，1成功，2失败
		while (status == 0) {
			if (queue.isEmpty()) {
				status = 2;
			} else {
				PathNode pnOld = queue.poll();

				for (int i = 0; i <= 5; i++) {
					PathNode pnNew = new PathNode();
					pnNew.qa = pnOld.qa;
					pnNew.qb = pnOld.qb;
					pnNew.path.addAll(pnOld.path);

					this.operate(pnNew, i);
					if (success(pnNew)) {
						status = 1;
						pnResult = pnNew;
						break;
					} else if (!visited(pnNew)) {
						visited.add(pnNew);

						queue.offer(pnNew);
					}
				} // end of for
			}
		} // end of while

		if (status == 1) {
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
		Water w = new Water();
		w.input();
		w.search();
	}
}

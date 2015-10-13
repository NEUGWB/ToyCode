//�����������
// 2014-10-24
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Water {
	int CA = 11; // ����Ͱ������
	int CB = 4;
	int dest = 3; // Ŀ��

	// 6������
	String opName[] = { "װ��AͰ    ", "װ��BͰ    ", "���AͰ    ", "���BͰ     ",
			"A��ˮ����B", "B��ˮ����A", };

	// ��������
	Queue<PathNode> queue = new LinkedList<PathNode>();

	// ���
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

	// ��·���Ľ��
	class PathNode extends Node {
		List<Integer> path = new ArrayList<Integer>();
	}

	PathNode pnResult;
	// �ѷ��ʽ��
	Set<Node> visited = new HashSet<Node>();

	// ���ݲ�������в���
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

	// �ɹ�
	boolean success(Node nd) {
		return nd.qa == dest || nd.qb == dest
				|| (dest > nd.qa && dest > nd.qb && dest == nd.qa + nd.qb);
	}

	// �ѷ���
	boolean visited(Node nd) {

		return visited.contains(nd);
	}

	void search() {
		PathNode pn = new PathNode();
		queue.offer(pn);
		visited.add(pn);

		int status = 0; // 0������1�ɹ���2ʧ��
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
			System.out.println("�޽�");
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
		System.out.println("��������A BͰ��������Ŀ��ˮ������6 5 3");
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

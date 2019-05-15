import java.util.Scanner;

public class StudentScore {

	private static Scanner myScanner;
	private static int[] scores;
	private static String[] studentnames;
	private static int total_student;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		myScanner = new Scanner(System.in);

		int menu = menu();
		boolean exe = true;

		while (exe == true) {
			switch (menu) {
			case 1:
				inputScore();
				menu = menu();
				break;
			case 2:
				totalScore_output();
				menu = menu();
				break;
			case 3:
				search_student();
				menu = menu();
				break;
			case 4:
				exe = false;
				System.out.println("���α׷��� �����մϴ�.");
				break;
			default:
				System.out.println("Error");
			}
		}
	}

	public static int menu() {
		int answer = 0;
		System.out.println("\n------------------menu(���ڷ� �Է�)------------------");
		System.out.println("1.�����Է�(������� �ʱ�ȭ)\n2.��ü����Ȯ��\n3.�˻�\n4.����");
		while (answer < 1 || answer > 4) {
			System.out.print(">�Է� : ");
			answer = myScanner.nextInt();
			System.out.println();
		}
		return answer;
	}

	public static void inputScore() {

		total_student = 0;
		int score = 0;
		int numberOfStudent = 0;

		System.out.print(">�л� ���� �Է��ϼ���.: ");
		total_student = myScanner.nextInt();

		while (total_student <= 0) {
			System.out.print(">�ùٸ� �Է��� �ƴմϴ�. �ٽ� �л� ���� �Է��ϼ���.: ");
			total_student = myScanner.nextInt();
			System.out.print("");
		}

		scores = new int[total_student];
		studentnames = new String[total_student];

		while (numberOfStudent < total_student) {
			System.out.print("");
			System.out.print(">" + (numberOfStudent + 1) + "��° �л� �̸��� �Է��Ͻÿ�.: ");
			studentnames[numberOfStudent] = myScanner.next();

			System.out.print("");
			System.out.print(">������ �Է��Ͻÿ�.: ");
			score = myScanner.nextInt();

			while (score > 100 || score < 0) {
				System.out.println("���� :�������� ������ �ƴմϴ�.");
				System.out.print(">�ٽ� ������ �Է��Ͻÿ�.: ");
				score = myScanner.nextInt();
			}

			scores[numberOfStudent] = score;
			numberOfStudent++;
		}
	}

	public static void totalScore_output() {

		if (total_student <= 0) {
			System.out.println("Error: ������ �Է����� �ʾҽ��ϴ�.\n\n");
			return;
		}

		double average = calcAverage(scores, total_student);
		int Max = calcMax(scores, total_student);
		int Min = calcMin(scores, total_student);

		System.out.println();
		System.out.println("���" + total_student + "���� ������ �ԷµǾ����ϴ�.");
		System.out.println("�����" + average + "�Դϴ�.");
		System.out.println();

		System.out.println();
		System.out.println("�ְ����� " + Max + " �� �Դϴ�.");
		System.out.println("�������� " + Min + " �� �Դϴ�.");
		System.out.println();

		System.out.println("�������� ������ �����ϴ�.");
		selectionSort(studentnames, scores, total_student);
	}

	public static void search_student() {

		if (total_student <= 0) {
			System.out.println("Error: ������ �Է����� �ʾҽ��ϴ�.\n\n");
			return;
		}

		String student_name;

		System.out.print("");
		System.out.print(">�л��� �̸��� �Է��ϼ���.: ");
		student_name = myScanner.next();

		int num = foundStudent_arrayNum(student_name);
		if (num == -1) {
			System.out.println("�Է��� �л� �߿��� ã�� �� �����ϴ�.");
			return;
		}

		if (scores[num] > calcAverage(scores, total_student)) {
			System.out.println(studentnames[num] + " �л��� ������ " + scores[num] + "������ ��� �̻��Դϴ�.");
		} else if (scores[num] < calcAverage(scores, total_student)) {
			System.out.println(studentnames[num] + " �л��� ������ " + scores[num] + "������ ��� �̸��Դϴ�.");
		} else {
			System.out.println(studentnames[num] + " �л��� ������ " + scores[num] + "������ ��� �Դϴ�.");
		}

	}

	public static int foundStudent_arrayNum(String studentname) {
		for (int i = 0; i < studentnames.length; i++) {
			if (studentnames[i].equals(studentname)) {
				return i;
			}
		}
		return -1;
	}

	private static double calcAverage(int[] elements, int aSize) {
		int i = 0;
		int sum = 0;
		double average;

		while (i < aSize) {
			sum = sum + elements[i];
			i++;
		}

		average = (double) sum / aSize;
		return average;

	}

	private static int calcMax(int[] elements, int aSize) {
		int j = 0;
		int max = elements[0];

		while (j < aSize) {
			if (elements[j] >= max) {
				max = elements[j];
			} else {
			}
			j++;
		}
		return max;

	}

	private static int calcMin(int[] elements, int aSize) {
		int z = 0;
		int min = elements[0];

		while (z < aSize) {
			if (elements[z] <= min) {
				min = elements[z];
			} else {
			}
			z++;
		}
		return min;

	}

	private static void selectionSort(String[] students, int[] elements, int aSize) {

		int lastLoc = aSize - 1;

		int maxLoc;
		int maxValue;
		int selectionLoc = 0;
		int curLoc;
		String name;

		while (selectionLoc < lastLoc) {
			maxLoc = selectionLoc;
			maxValue = elements[maxLoc];
			curLoc = selectionLoc + 1;

			while (curLoc <= lastLoc) {
				if (elements[curLoc] >= maxValue) {
					maxLoc = curLoc;
					maxValue = elements[maxLoc];

				} else {
				}
				curLoc++;
			}

			name = students[maxLoc];

			elements[maxLoc] = elements[selectionLoc];
			students[maxLoc] = students[selectionLoc];

			elements[selectionLoc] = maxValue;
			students[selectionLoc++] = name;
		}

		selectionLoc = 0;
		while (selectionLoc < aSize) {
			System.out.println(
					(selectionLoc + 1) + "�� " + students[selectionLoc] + "�л�: " + elements[selectionLoc] + "��");
			selectionLoc++;

		}
	}
}
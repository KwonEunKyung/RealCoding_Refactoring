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
				System.out.println("프로그램을 종료합니다.");
				break;
			default:
				System.out.println("Error");
			}
		}
	}

	public static int menu() {
		int answer = 0;
		System.out.println("\n------------------menu(숫자로 입력)------------------");
		System.out.println("1.성적입력(이전기록 초기화)\n2.전체성적확인\n3.검색\n4.종료");
		while (answer < 1 || answer > 4) {
			System.out.print(">입력 : ");
			answer = myScanner.nextInt();
			System.out.println();
		}
		return answer;
	}

	public static void inputScore() {

		total_student = 0;
		int score = 0;
		int numberOfStudent = 0;

		System.out.print(">학생 수를 입력하세요.: ");
		total_student = myScanner.nextInt();

		while (total_student <= 0) {
			System.out.print(">올바른 입력이 아닙니다. 다시 학생 수를 입력하세요.: ");
			total_student = myScanner.nextInt();
			System.out.print("");
		}

		scores = new int[total_student];
		studentnames = new String[total_student];

		while (numberOfStudent < total_student) {
			System.out.print("");
			System.out.print(">" + (numberOfStudent + 1) + "번째 학생 이름을 입력하시오.: ");
			studentnames[numberOfStudent] = myScanner.next();

			System.out.print("");
			System.out.print(">점수를 입력하시오.: ");
			score = myScanner.nextInt();

			while (score > 100 || score < 0) {
				System.out.println("오류 :정상적인 점수가 아닙니다.");
				System.out.print(">다시 점수를 입력하시오.: ");
				score = myScanner.nextInt();
			}

			scores[numberOfStudent] = score;
			numberOfStudent++;
		}
	}

	public static void totalScore_output() {

		if (total_student <= 0) {
			System.out.println("Error: 성적을 입력하지 않았습니다.\n\n");
			return;
		}

		double average = calcAverage(scores, total_student);
		int Max = calcMax(scores, total_student);
		int Min = calcMin(scores, total_student);

		System.out.println();
		System.out.println("모두" + total_student + "명의 성적이 입력되었습니다.");
		System.out.println("평균은" + average + "입니다.");
		System.out.println();

		System.out.println();
		System.out.println("최고점은 " + Max + " 점 입니다.");
		System.out.println("최저점은 " + Min + " 점 입니다.");
		System.out.println();

		System.out.println("성적순은 다음과 같습니다.");
		selectionSort(studentnames, scores, total_student);
	}

	public static void search_student() {

		if (total_student <= 0) {
			System.out.println("Error: 성적을 입력하지 않았습니다.\n\n");
			return;
		}

		String student_name;

		System.out.print("");
		System.out.print(">학생의 이름을 입력하세요.: ");
		student_name = myScanner.next();

		int num = foundStudent_arrayNum(student_name);
		if (num == -1) {
			System.out.println("입력한 학생 중에서 찾을 수 없습니다.");
			return;
		}

		if (scores[num] > calcAverage(scores, total_student)) {
			System.out.println(studentnames[num] + " 학생의 성적은 " + scores[num] + "점으로 평균 이상입니다.");
		} else if (scores[num] < calcAverage(scores, total_student)) {
			System.out.println(studentnames[num] + " 학생의 성적은 " + scores[num] + "점으로 평균 미만입니다.");
		} else {
			System.out.println(studentnames[num] + " 학생의 성적은 " + scores[num] + "점으로 평균 입니다.");
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
					(selectionLoc + 1) + "등 " + students[selectionLoc] + "학생: " + elements[selectionLoc] + "점");
			selectionLoc++;

		}
	}
}
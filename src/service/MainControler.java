package service;

import java.util.InputMismatchException;
import java.util.Scanner;

import dao.DBconnector;

// Lotto 번호를 생성한다. 만들고 DB에 저장된다. // 생성할 갯수 및 강제 번호 지정 기능
// DB로 부터 값을 가져온다. 숫자 분석한다. 지운다.
public class MainControler {
	static LottoService service = DBconnector.mysqlConn();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("[메인 메뉴]");
			System.out.println("[1.추가 2.삭제 3.출력 4.분석 5.종료] 8:mysql/9:oracle");
			System.out.print("=>");
		} while (menu(scanner));
		scanner.close();
	}

	private static boolean menu(Scanner scanner) {

		long start, end;

		try {
			int select = scanner.nextInt();
			switch (select) {
			case 1:
				System.out.println("[로또 추가]");
				System.out.print("몇개? =>");

				int round = scanner.nextInt();
				start = System.currentTimeMillis();
				service.setLottos(round);
				end = System.currentTimeMillis();

				checkTimer(start, end);
				break;
			case 2:
				System.out.println("[로또 전체 삭제]");
				start = System.currentTimeMillis();
				service.deleteAll();
				end = System.currentTimeMillis();

				checkTimer(start, end);

				break;
			case 3:
				System.out.println("[로또 전체 출력]");
				start = System.currentTimeMillis();
				service.getLottos();
				end = System.currentTimeMillis();

				checkTimer(start, end);

				break;
			case 4:
				System.out.println("[저장된 로또 분석]");
				start = System.currentTimeMillis();
				service.analysis();
				end = System.currentTimeMillis();

				checkTimer(start, end);

				break;
			case 5:
				System.out.println("[종료]");
				return false;
			case 8:
				System.out.println("[Mysql 사용]");
				service = DBconnector.mysqlConn();
				break;
			case 9:
				System.out.println("[Oracle 사용]");
				service = DBconnector.oracleConn();
				break;
			default:
				System.out.println("#잘못된 메뉴를 선택#");
				break;
			}
			return true;
		} catch (InputMismatchException e) {
			System.out.println("#잘못된 입력#");
			scanner.nextLine();
			// e.printStackTrace(); // 에러 내역 출력
			return true;
		}
	}

	private static void checkTimer(long start, long end) {
		System.out.println("#실행시간# : " + (end - start) / 1000.0 + "sec");

	}
}

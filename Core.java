package ascii;

import java.util.Scanner;
import ascii.Map;

public class Core {
	protected Scanner scn;
	public static String[] tagList = {
			"animal",
			"plant",
			"hard",
			"fast",
			"slow",
			"soft",
			"shell",
			"fur",
			"scale",
			"hide",
			"tough",
			"one",
			"two",
			"three",
			"four",
			"five",
			"six",
			"seven",
			"eight",
			"nine",
			"zero",
	};
	public Core() {
		scn = new Scanner(System.in);
	}

	protected void run() {
		Map map = new Map(scn.next());
		scn.nextLine();
		System.out.println(map);
		do {
			map.adjustGoals();
			map.checkStatus();
			map.adjustGoals();
			map.moveAnimals();
			
			map.movePlayer(scn.next());
			
			scn.nextLine();
			map.setTrueMap();
			System.out.println(map);
		} while (!gameOver());
	}

	protected void playLoop() {

	}

	public static void main(String[] args) {
		System.out.println("Enter seed -");
		(new Core()).run();
	}

	protected boolean gameOver() {
		return false;
	}

	protected void displayGame() {
		System.out.println("===========================================================");

		System.out.println("===========================================================");
	}
}

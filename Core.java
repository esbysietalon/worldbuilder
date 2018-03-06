package ascii;

import java.util.Scanner;
import ascii.Map;

public class Core {
	public static Scanner scn;

	public Core() {
		scn = new Scanner(System.in);
	}

	protected void run() {
		Map map = new Map(scn.next());
		scn.nextLine();
		System.out.println();
		System.out.println(map);
		while (!gameOver()) {
			map.movePlayer(scn.next());
			map.updateCreatures();
			scn.nextLine();
			map.setTrueMap();
			System.out.println(map);
		}
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

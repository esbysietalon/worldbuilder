package ascii;

import java.util.Scanner;
import ascii.Map;

public class Core {
	protected Scanner scn;
	public static String[] tagList = {
			"animal", //distinguishes this "Thing" as an animal, indicating that generally it moves around and consumes other organisms to survive
 			"plant", //distinguishes this "Thing" as a plant, indicating a generally static lifestyle and does not consume other organisms to survive 
			"hard", //the material that makes this "Thing" is hard, requiring larger amounts of force to change shape
			"fast", //this "Thing" moves/acts at above average rates
			"short", //this "Thing" is of shorter lengths, either physically or with respect to time
			"long", //this "Thing" is of longer lengths, either physically or with respect to time
			"lifespan", //the general amount of time this "Thing" exists
			"slow", //this "Thing" moves/acts at below average rates
			"stationary", //this "Thing" possesses no ability to move on its own
			"permanent", //this "Thing" will continue to exist until an external force acts against it
			"soft", //the material that makes this "Thing" is soft, requiring smaller amounts of force to change shape
			"shell", //this "Thing" has an external "Thing" covering it that generally is not comprised of the same material
			"fur", //this "Thing" is covered in small hairs or hair-like structures
			"thick", //the material covering this "Thing" is of above average width
			"thin", //the material covering this "Thing" is of below average width 
			"scale", //this "Thing" is covered in small plates that make up a larger skin structure
			"hide", //this "Thing" is covered in skin
			"seed", //this "Thing" is a seed or egg or some kind
			"inorganic", //the material that makes this "Thing" is not organic and is not affected by organic tags
			"organic", //the material that makes this "Thing" is organic and is affected by organic tags
			"tough", //the material that makes this "Thing" is difficult to break/penetrate, but not to deform
			"one", //number tag, can be combined with other number tags to create larger numbers
			"two", //number tag, can be combined with other number tags to create larger numbers
			"three", //number tag, can be combined with other number tags to create larger numbers
			"four", //number tag, can be combined with other number tags to create larger numbers
			"five", //number tag, can be combined with other number tags to create larger numbers
			"six", //number tag, can be combined with other number tags to create larger numbers
			"seven", //number tag, can be combined with other number tags to create larger numbers
			"eight", //number tag, can be combined with other number tags to create larger numbers
			"nine", //number tag, can be combined with other number tags to create larger numbers
			"zero", //number tag, can be combined with other number tags to create larger numbers
			"strong", //number tag, can be combined with other number tags to create larger numbers
			"weak", //number tag, can be combined with other number tags to create larger numbers
			"weather", //this tag indicates a weather condition
			"arraccess" //this tag accesses an archived "Thing" and must use number tags as indices
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

package ascii;

public class Map {
	private double[] table = new double[25 * 25];
	private char[] baseMap = new char[25 * 25];
	private char[] trueMap = new char[25 * 25];

	private Thing[] organics;
	private int playerX = 12;
	private int playerY = 12;
	private int sandCount = 0;
	private int clayCount = 0;
	private int rockCount = 0;
	private int soilCount = 0;
	private double humidity = 1.0;
	private double temperature = 1.0;
	private Player player;
	private static int[] hash = { 722, 719, 326, 682, 439, 52, 895, 650, 206, 766, 664, 734, 940, 801, 507, 381, 711,
			213, 481, 855, 628, 71, 768, 25, 806, 78, 11, 420, 72, 385, 653, 590, 509, 140, 937, 15, 919, 314, 574, 24,
			101, 464, 632, 82, 312, 665, 533, 804, 243, 881, 151, 857, 496, 516, 620, 677, 192, 523, 818, 510, 974, 911,
			333, 983, 754, 877, 683, 605, 581, 105, 424, 376, 193, 840, 115, 383, 313, 680, 416, 87, 346, 742, 187, 477,
			642, 8, 918, 835, 593, 393, 887, 69, 780, 728, 355, 219, 434, 566, 75, 554, 161, 322, 925, 107, 843, 445,
			800, 263, 951, 993, 568, 244, 630, 996, 463, 781, 475, 262, 595, 358, 546, 324, 830, 915, 869, 183, 90, 258,
			759, 408, 901, 405, 209, 976, 159, 752, 950, 753, 238, 867, 137, 446, 931, 478, 294, 932, 201, 520, 625,
			671, 511, 899, 949, 44, 63, 823, 302, 588, 580, 2, 739, 521, 181, 13, 690, 618, 226, 558, 400, 817, 562, 41,
			83, 427, 128, 973, 389, 897, 503, 631, 56, 426, 367, 945, 86, 654, 188, 292, 798, 678, 392, 656, 77, 547,
			338, 538, 681, 190, 757, 133, 737, 185, 482, 670, 784, 938, 872, 885, 269, 545, 212, 873, 440, 941, 955,
			184, 934, 182, 565, 517, 870, 273, 563, 410, 204, 515, 612, 828, 363, 962, 764, 384, 9, 76, 502, 311, 894,
			880, 610, 669, 306, 318, 879, 786, 836, 409, 527, 122, 702, 395, 246, 655, 309, 284, 912, 361, 698, 965,
			741, 924, 362, 822, 749, 851, 1, 790, 461, 108, 778, 704, 404, 575, 926, 160, 162, 865, 494, 825, 639, 862,
			592, 821, 26, 286, 896, 903, 519, 623, 826, 225, 904, 882, 154, 832, 22, 36, 601, 391, 230, 791, 819, 176,
			118, 444, 708, 493, 293, 27, 92, 995, 18, 267, 883, 49, 808, 245, 252, 990, 589, 761, 438, 153, 854, 847,
			858, 944, 235, 174, 514, 637, 684, 228, 19, 471, 709, 727, 35, 522, 971, 202, 959, 969, 720, 242, 132, 236,
			310, 480, 147, 839, 353, 431, 55, 861, 422, 356, 265, 967, 317, 257, 121, 111, 21, 860, 703, 525, 43, 767,
			602, 587, 268, 396, 837, 687, 772, 341, 787, 992, 779, 467, 866, 157, 414, 288, 138, 920, 130, 905, 53, 947,
			458, 148, 598, 613, 136, 717, 788, 526, 571, 325, 614, 33, 100, 785, 755, 917, 672, 624, 998, 323, 271, 557,
			939, 152, 251, 135, 812, 603, 104, 688, 300, 564, 506, 114, 474, 986, 460, 484, 936, 846, 721, 335, 842, 46,
			646, 214, 119, 255, 483, 750, 486, 17, 815, 831, 39, 79, 978, 328, 675, 657, 308, 802, 296, 58, 541, 497,
			189, 792, 970, 850, 131, 80, 124, 0, 191, 37, 298, 743, 16, 638, 997, 329, 99, 98, 290, 40, 224, 891, 364,
			433, 848, 32, 975, 442, 455, 958, 649, 769, 933, 729, 247, 177, 569, 282, 237, 984, 315, 7, 473, 820, 999,
			980, 150, 372, 60, 645, 991, 773, 349, 253, 143, 167, 354, 929, 198, 449, 635, 634, 120, 636, 5, 893, 178,
			304, 718, 673, 379, 20, 707, 352, 220, 274, 856, 922, 763, 387, 928, 972, 4, 277, 747, 606, 450, 407, 666,
			402, 898, 216, 627, 696, 573, 548, 54, 454, 544, 731, 599, 451, 765, 582, 968, 746, 432, 676, 816, 171, 651,
			259, 652, 626, 388, 667, 330, 844, 705, 795, 736, 659, 141, 594, 927, 713, 878, 429, 744, 604, 200, 94, 347,
			714, 51, 144, 66, 567, 45, 399, 900, 662, 534, 644, 771, 316, 942, 499, 394, 756, 555, 320, 902, 239, 272,
			492, 616, 301, 608, 640, 916, 864, 34, 430, 59, 62, 615, 91, 207, 197, 607, 129, 428, 490, 724, 710, 966,
			289, 888, 597, 518, 196, 222, 95, 234, 760, 863, 112, 921, 299, 459, 232, 348, 205, 633, 528, 748, 735, 65,
			529, 807, 647, 180, 248, 169, 472, 725, 693, 814, 701, 194, 803, 38, 70, 770, 577, 279, 583, 504, 797, 297,
			368, 321, 240, 421, 913, 166, 648, 3, 68, 600, 134, 758, 989, 254, 281, 170, 501, 287, 249, 994, 852, 694,
			468, 810, 964, 319, 283, 774, 370, 103, 401, 498, 699, 943, 579, 829, 530, 957, 168, 67, 853, 285, 745, 726,
			73, 960, 491, 351, 892, 415, 512, 331, 489, 30, 738, 824, 811, 543, 751, 874, 572, 988, 227, 537, 155, 845,
			163, 278, 28, 142, 956, 6, 307, 827, 264, 139, 295, 93, 84, 89, 935, 186, 210, 441, 50, 145, 619, 344, 799,
			551, 270, 378, 336, 85, 783, 906, 260, 419, 535, 777, 979, 908, 476, 622, 172, 215, 513, 733, 339, 217, 740,
			961, 875, 985, 668, 621, 123, 552, 536, 469, 553, 813, 256, 359, 679, 365, 561, 871, 907, 57, 64, 158, 532,
			382, 841, 47, 715, 276, 609, 447, 909, 374, 31, 156, 97, 914, 930, 834, 334, 74, 113, 663, 658, 343, 275,
			14, 457, 371, 221, 586, 266, 884, 81, 406, 380, 462, 149, 660, 165, 456, 689, 229, 412, 868, 117, 479, 303,
			695, 164, 782, 411, 556, 886, 375, 366, 345, 340, 838, 199, 423, 417, 96, 398, 674, 125, 910, 397, 327, 923,
			706, 110, 452, 173, 977, 859, 386, 337, 436, 332, 403, 641, 692, 369, 775, 126, 470, 231, 809, 793, 963,
			453, 448, 954, 948, 723, 697, 195, 495, 578, 377, 465, 805, 88, 661, 218, 712, 223, 876, 23, 524, 987, 550,
			946, 102, 179, 732, 629, 373, 796, 241, 981, 531, 437, 617, 425, 570, 982, 418, 12, 686, 762, 596, 435, 540,
			730, 127, 833, 716, 611, 794, 342, 29, 305, 116, 291, 539, 413, 576, 390, 584, 61, 508, 691, 789, 360, 280,
			952, 560, 953, 485, 890, 700, 203, 542, 505, 685, 443, 42, 643, 10, 591, 466, 487, 849, 549, 500, 261, 250,
			350, 889, 208, 233, 211, 106, 48, 175, 559, 357, 776, 585, 146, 109, 488 };
	public final String lookup = "!@#$%^&*()-=~`\\\'\"+_<>,.?/;:abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public final String plantLookup = "XAVWOUMYTH";
	public final String animalLookup = ">PRFKLD})S";
	private static int counter = 0;
	private static double cc = 0;
	private static double seedVal1 = 0;
	private static double seedVal2 = 0;
	private static double seedVal3 = 0;
	private static double seedVal4 = 0;

	public int ecoGetHeight() {
		int max = 0;
		
		return max;
	}

	public static double generateRandomRuntimeHash() {
		// i don't even know what's going on with this function at this point
		// use the updated version
		counter += (int) (seedVal1 + seedVal2 + seedVal3 + seedVal4);
		if (counter % 2 == 0)
			counter += (int) (seedVal1 + seedVal3);
		else if (counter % 2 == 1)
			counter += (int) (seedVal2 + seedVal4);

		return (double) (hash[(int) Math
				.abs(((hash[((int) Math.abs(hash[(int) (Math.abs(counter + seedVal1) % hash.length)] + seedVal2))
						% hash.length]
						+ Math.abs((int) Math.abs(seedVal1 * (counter + (seedVal2 + seedVal4))))
						+ Math.abs(hash[(int) Math.abs(Math
								.abs((hash[(int) Math.abs((counter + (seedVal2 + seedVal4) + seedVal3) % hash.length)]
										+ (int) seedVal4))
								% hash.length)] + (long) Math.abs(seedVal2 * counter))))
						% hash.length)]
				* hash[(int) Math.abs(((hash[(hash[(int) Math.abs((counter + seedVal2) % hash.length)] + (int) seedVal3)
						% hash.length] + (int) Math.abs(seedVal4 * (counter + (seedVal1 + seedVal2))))
						+ (hash[(hash[(int) Math.abs((counter + (seedVal3 + seedVal4) + seedVal1) % hash.length)]
								+ (int) seedVal2) % hash.length] + (long) Math.abs(seedVal3 * counter)))
						% hash.length)])
				/ (double) ((hash.length - 1 + 0.0001) * (hash.length - 1 + 0.0001));
	}

	public static double generateRandomRuntime() {
		// this method uses an insect population model, with coefficients adjusted to
		// put the model into chaos
		double result = 0.0;
		result = (1000 * cc / (Math.pow(1 + cc, 8))) % 1.0;
		cc = result;
		return result;
	}

	public int factorial(int num) {
		if (num <= 1)
			return 1;
		else
			return num * factorial(num - 1);
	}

	public int findInLookup(char a) {
		char[] lookupArray = lookup.toCharArray();
		for (int i = 0; i < lookupArray.length; i++) {
			if (lookupArray[i] == a)
				return i + 1;
		}
		return 0;
	}

	public char reverseLookup(int num) {
		return lookup.charAt(num % lookup.length());
	}

	public int clamp(int min, int max, int num) {
		if (num < min) {
			return min;
		}
		if (num > max) {
			return max;
		}
		return num;
	}

	public Map(String seed) {
		if (seed.length() > 32)
			seed = seed.substring(0, 32);
		player = new Player();
		char[] seedArr = seed.toCharArray();
		int kp = 0;
		int ki = 0;
		while (ki < 5) {
			for (int i = 0; i < seedArr.length; i++) {
				seedVal1 += findInLookup(seedArr[i]) * i;
				if (i > 0)
					seedVal3 += findInLookup(seedArr[i - 1]);

				seedVal2 += findInLookup(seedArr[i]) * seedArr.length;
				if (i < seedArr.length - 1)
					seedVal4 += findInLookup(seedArr[i + 1]);

			}
			char[] tempArr = seedArr.clone();
			if (seedArr.length < 32) {
				seedArr = new char[seedArr.length + 1];
			} else {
				seedArr = new char[seedArr.length];
			}
			System.arraycopy(tempArr, 0, seedArr, 0, tempArr.length);

			seedArr[kp] = reverseLookup((int) Math.abs(seedVal1 + seedVal2 + seedVal3 + seedVal4));
			kp++;
			if (kp == 32) {
				ki++;
				kp = 0;
			}

			if (seedVal1 > seedVal3)
				seedVal1 /= seedVal3 + 1;
			else
				seedVal3 /= seedVal1 + 1;
			if (seedVal2 > seedVal4)
				seedVal2 /= seedVal4 + 1;
			else
				seedVal4 /= seedVal2 + 1;
		}
		seedVal1 = (double) hash[hash[hash[hash[hash[(int) seedVal1 % hash.length]]]]];
		seedVal2 = (double) hash[hash[hash[hash[hash[(int) seedVal2 % hash.length]]]]];
		seedVal3 = (double) hash[hash[hash[hash[hash[(int) seedVal3 % hash.length]]]]];
		seedVal4 = (double) hash[hash[hash[hash[hash[(int) seedVal4 % hash.length]]]]];
		cc = (seedVal1 + seedVal2 + seedVal3 + seedVal4);
		System.out.println(seedVal1 + "  " + seedVal2 + " " + seedVal3 + " " + seedVal4);
		System.out.print("actual seed value: ");
		System.out.println(seedArr);
		System.out.print(seed.toCharArray());
		System.out.println(": ");
		double tMin = Integer.MAX_VALUE;
		double tMax = Integer.MIN_VALUE;
		for (int i = 0; i < 25 * 25; i++) {
			table[i] = (double) Map.generateRandomRuntime();
			if (tMin > table[i])
				tMin = table[i];
			if (tMax < table[i])
				tMax = table[i];
		}
		for (int i = 0; i < 25 * 25; i++) {
			table[i] -= tMin;
			table[i] /= (tMax - tMin);
		}
		setBaseMap();
		setTrueMap();
		adjustGoals();
	}

	public int intClamp(int num, int min, int max) {
		if (num >= max)
			num = max - 1;
		if (num < min)
			num = min;
		return num;
	}

	public void setTrueMap() {
		for (int i = 0; i < 25 * 25; i++) {
			trueMap[i] = baseMap[i];
		}
		
		trueMap[getPlayerPosition()] = '@';
	}

	
	public Player isPlayer(int position) {
		if (position == getPlayerPosition())
			return player;
		return null;
	}


	public void checkStatus() {
		
	}

	public void adjustGoals() {
		
	}

	public void moveAnimals() {
		
	}

	private boolean checkOccupied(int index) {
		if (getPlayerPosition() == index)
			return true;
		
		return false;
	}

	public void movePlayer(String input) {
		boolean hasW = false;
		boolean hasS = false;
		boolean hasA = false;
		boolean hasD = false;
		for (char a : input.toCharArray()) {
			if (a == 'w' || a == 'W')
				hasW = true;
			if (a == 'a' || a == 'A')
				hasA = true;
			if (a == 's' || a == 'S')
				hasS = true;
			if (a == 'd' || a == 'D')
				hasD = true;
		}
		if (!checkOccupied(intClamp((playerX + (hasA ? -1 : 0) + (hasD ? 1 : 0)), 0, 25)
				+ intClamp((playerY + (hasW ? -1 : 0) + (hasS ? 1 : 0)), 0, 25) * 25)) {
			if (hasW)
				playerY--;
			if (hasA)
				playerX--;
			if (hasS)
				playerY++;
			if (hasD)
				playerX++;
		}
		if (playerY < 0)
			playerY = 0;

		if (playerX < 0)
			playerX = 0;

		if (playerX >= 25)
			playerX = 24;

		if (playerY >= 25)
			playerY = 24;

	}

	public boolean getRock(double val) {
		return (val <= 0.05);
	}

	public boolean getSand(double val) {
		return (val <= 0.4);
	}

	public boolean getSoil(double val) {
		return (val <= 0.85);
	}

	public void setBaseMap() {
		for (int i = 0; i < 25 * 25; i++) {
			baseMap[i] = getGraphics(i);
		}
	}



	

	public char getGraphics(int index) {
		double value = table[index];
		if (getRock(value)) {
			rockCount++;
			return 'n';
		}
		if (getSand(value)) {
			sandCount++;
			return '.';
		}
		if (getSoil(value)) {
			soilCount++;
			return '_';
		}
		clayCount++;
		return '=';

	}

	public double pos(double num) {
		if (num < 0.0) {
			return 0.0;
		}
		return num;
	}

	public void populatePlants() {
		int bookmark = 0;

		for (int i = 0; i < 4; i++) {
			int count = 0;
			char surface = ' ';

			switch (i) {
			case 0:
				count = rockCount;
				surface = 'n';
				break;
			case 1:
				count = sandCount;
				surface = '.';
				break;
			case 2:
				count = soilCount;
				surface = '_';
				break;
			case 3:
				count = clayCount;
				surface = '=';
				break;
			}

			

		}
	}

	public void populateAnimals() {
		// herbivore pass
		
		// carnivore pass
		// omnivore pass
		// if biomass can sustain more, omnivore pass
	}

	public int getPlayerPosition() {
		return playerX + playerY * 25;
	}

	public String toString() {
		String output = "";
		for (int y = 0; y < 25; y++) {
			for (int x = 0; x < 25; x++) {
				output += trueMap[x + y * 25] + " ";
			}
			output += "\n";
		}
		return output;
	}

	

	public Thing[] getOrganisms() {
		// TODO Auto-generated method stub
		return organics;
	}

	public double getHumidity() {
		return humidity;
	}

	public double getTemperature() {
		// TODO Auto-generated method stub
		return temperature;
	}
}
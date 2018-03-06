package ascii;

public class Map {
	private static double[] table = new double[25 * 25];
	private char[] baseMap = new char[25 * 25];
	private char[] trueMap = new char[25 * 25];
	private char[] perspecMap = new char[25 * 25];

	private int playerX = 12;
	private int playerY = 12;
	private Player player;
	private static String[] unIDs = { "0000" };
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
	public final static String plantLookup = "XAVWOUMYTH";
	public final static String animalLookup = ">PRFKLD})S";
	private double currRandom = 0;

	private static double cc = 0;
	private static double seedVal1 = 0;
	private static double seedVal2 = 0;
	private static double seedVal3 = 0;
	private static double seedVal4 = 0;
	private static int worldSize = 0;

	public static String[] matTraitsTL = { "roug", "stic", "shin", "toxi", "bnce", "elas", "brit", "tran", "heat",
			"nrgy" };

	public static String[] anatomyTL = { "exos", "eyes", "furr", "scal", "leaf", "hand", "foot", "claw", "mout", "head",
			"tail", "tent", "japp", "horn", "gill", "tetl", "tetn", "ears", "nose", "feel" };
	public static String[] specialTL = { "proj", "spin", "spit", "acid", "stic", "pois", "spik", "slip", "sgmt" };
	public static String[] anatomyPTL = { "vine", "leaf", "thor", "bark", "bran", "spin", "gill", "capp", "frui",
			"flwr", "aloe", "stem", "mvmt" };

	public static String[] habitatTL = { "mtan", "arct", "tund", "dsrt", "trop", "sava", "lake", "rivr", "ocea", "deep",
			"shal" };
	public static String[] behaviorTL = { "pkht", "usoc", "noma", "lone", "nbld", "faml", "ambu", "deco", "danc",
			"sing", "terr", "farm", "cnbl" }; // behavioural nbld is nestbuilding, noma is nomadic, usoc is eusocial,
												// pkht is
	// packhunting, terr is territorial

	// traits
	public static String[] mindTL = { "reas", "tool", "algn", "pers", "tele", "psio" }; // arranged from least int req
	// to most int req, this
	// tagList holds all tags
	// that require significant
	// intelligence to have

	public static String[] featsTL = { "bers", "resi", "pigh", "mite" }; // arranged from least str req to most str req,
																			// this tagList holds all tags
	// that require significant strength to have
	public static String[] phaseTL = { "rflx" }; // arranged from least spd req to most spd req, this tagList holds all
													// tags
	// that require significant speed to have
	public static String[] qualitModTL = { "very", "nott", "nmod" };
	public static String[] reactionTL = { "heat", "lite", "nrgy", "touc" };

	public static Thing[] animalTL = new Thing[0];
	public static Thing[] materialTL = new Thing[0];
	public static Thing[] biomeSect = new Thing[9];

	public static Thing[] localWildlife = new Thing[0];
	public static Thing[] lWPop = new Thing[0];

	public Thing[] animalPop = new Thing[0];
	public Thing[] plantPop = new Thing[0];

	public static int currBiome = -1;

	private static double[] currTerrain;
	private static double[][] biomeConstants;

	public static String generateNumberTag(int num) {
		String output = "0000";
		if (num >= 0) {
			output = (new Integer(num)).toString();
			while (output.length() < 4) {
				output = "0" + output;
			}
		} else {
			output = (new Integer(num)).toString().substring(1);
			while (output.length() < 3) {
				output = "0" + output;
			}
			output = "-" + output;
		}
		return output;
	}

	public static String generateQualityModTag() {
		String output = "";
		output += qualitModTL[(int) (generateRandomRuntime() * qualitModTL.length)];
		return output;
	}

	public static String generateReactionTag() {
		String output = "";
		output += reactionTL[(int) (generateRandomRuntime() * reactionTL.length)];
		return output;
	}

	public static void generateMaterials() {
		int primaryLength = (int) (generateRandomRuntime() * 70) + 30;
		int secondaryLength = (int) (generateRandomRuntime() * primaryLength / 2) + 20 + primaryLength / 2;
		int tertiaryLength = (int) (generateRandomRuntime() * secondaryLength / 2) + 10 + secondaryLength / 2;
		materialTL = new Thing[primaryLength + secondaryLength + tertiaryLength];
		for (int i = 0; i < primaryLength; i++) {
			materialTL[i] = new Thing();
			materialTL[i].addTag("visc_" + generateQualityModTag());
			materialTL[i].addTag("heav_" + generateQualityModTag());
			materialTL[i].addTag("hard_" + generateQualityModTag());
			materialTL[i].addTag("orga_" + generateQualityModTag());
			materialTL[i].addTag(matTraitsTL[(int) (matTraitsTL.length * generateRandomRuntime())] + "_"
					+ generateQualityModTag() + "_prim");
			materialTL[i].addTag(matTraitsTL[(int) (matTraitsTL.length * generateRandomRuntime())] + "_"
					+ generateQualityModTag() + "_seco");
			materialTL[i].addTag(matTraitsTL[(int) (matTraitsTL.length * generateRandomRuntime())] + "_"
					+ generateQualityModTag() + "_tert");
		}
		for (int i = primaryLength; i < primaryLength + secondaryLength; i++) {
			materialTL[i] = new Thing();
			materialTL[i].addTag("visc_" + generateQualityModTag());
			materialTL[i].addTag("heav_" + generateQualityModTag());
			materialTL[i].addTag("hard_" + generateQualityModTag());
			materialTL[i].addTag("orga_" + generateQualityModTag());
			String parent1 = generateNumberTag((int) (generateRandomRuntime() * (primaryLength)));
			String parent2 = generateNumberTag((int) (generateRandomRuntime() * (primaryLength)));

			materialTL[i].addTag("prnt_" + parent1);
			materialTL[i].addTag("prnt_" + parent2);
			materialTL[i].addTag("reac_" + generateReactionTag());
			materialTL[i].addTag(matTraitsTL[(int) (matTraitsTL.length * generateRandomRuntime())] + "_"
					+ generateQualityModTag() + "_prim");
			materialTL[i].addTag(matTraitsTL[(int) (matTraitsTL.length * generateRandomRuntime())] + "_"
					+ generateQualityModTag() + "_seco");
			materialTL[i].addTag(matTraitsTL[(int) (matTraitsTL.length * generateRandomRuntime())] + "_"
					+ generateQualityModTag() + "_tert");
		}
		for (int i = primaryLength + secondaryLength; i < primaryLength + secondaryLength + tertiaryLength; i++) {
			materialTL[i] = new Thing();
			materialTL[i].addTag("visc_" + generateQualityModTag());
			materialTL[i].addTag("heav_" + generateQualityModTag());
			materialTL[i].addTag("hard_" + generateQualityModTag());
			materialTL[i].addTag("orga_" + generateQualityModTag());
			String parent1 = generateNumberTag((int) (generateRandomRuntime() * (primaryLength + secondaryLength)));
			String parent2 = generateNumberTag((int) (generateRandomRuntime() * (primaryLength + secondaryLength)));

			materialTL[i].addTag("prnt_" + parent1);
			materialTL[i].addTag("prnt_" + parent2);
			materialTL[i].addTag("reac_" + generateReactionTag());
			materialTL[i].addTag(matTraitsTL[(int) (matTraitsTL.length * generateRandomRuntime())] + "_"
					+ generateQualityModTag() + "_prim");
			materialTL[i].addTag(matTraitsTL[(int) (matTraitsTL.length * generateRandomRuntime())] + "_"
					+ generateQualityModTag() + "_seco");
			materialTL[i].addTag(matTraitsTL[(int) (matTraitsTL.length * generateRandomRuntime())] + "_"
					+ generateQualityModTag() + "_tert");
		}
		boolean hasOrganic = false;
		for (int i = 0; i < materialTL.length; i++) {
			if (materialTL[i].containsTag("orga_nmod")) {
				hasOrganic = true;
				break;
			}
		}
		if (!hasOrganic) {
			generateMaterials();
			return;
		}
	}

	public static boolean checkLOS() {
		return true;
	}

	public static void generateBiomes() {
		for (int i = 0; i < biomeSect.length; i++) {
			biomeSect[i] = new Thing();
			biomeSect[i].addTag(habitatTL[(int) (generateRandomRuntime() * habitatTL.length)]);
		}
		for (int i = 0; i < biomeSect.length; i++) {
			biomeSect[i].printTags();
		}
		for (int i = 0; i < biomeSect.length; i++) {
			biomeConstants[i][0] = generateRandomRuntime();
			biomeConstants[i][1] = generateRandomRuntime();
			biomeConstants[i][2] = generateRandomRuntime();
			biomeConstants[i][3] = generateRandomRuntime();
			biomeConstants[i][4] = generateRandomRuntime();
		}

		currBiome = (int) (generateRandomRuntime() * worldSize);
		System.out.println();
		System.out.println("current biome is ");
		biomeSect[currBiome].printTags();
		System.out.println();
	}

	public static void generateBehavior() {
		for (int i = 0; i < lWPop.length; i++) {
			if (lWPop[i].containsTag("pnic")) {
				String[] threats = lWPop[i].getAll("thrt");
				// take weighted average of threat positions
				for (int j = 0; j < threats.length; j++) {

				}
			}
			if (lWPop[i].containsTag("hngr")) {
				String[] preyItems = lWPop[i].getAll("prey");
				int minIndex = -1;
				int minDist = 1000000;
				int x = Integer.parseInt(lWPop[i].getValue("posx").substring(5));
				int y = Integer.parseInt(lWPop[i].getValue("posy").substring(5));
				for (int j = 0; j < preyItems.length; j++) {
					int dx = Integer.parseInt(lWPop[j].getValue("posx").substring(5)) - x;
					int dy = Integer.parseInt(lWPop[j].getValue("posy").substring(5)) - y;
					if (dist(dx, dy) < minDist) {
						minDist = dist(dx, dy);
						minIndex = j;
					}
				}
				// if prey is found
				if (minIndex != -1) {

				} else {

				}
			}
		}
	}

	public static void populateOrganisms() {
		localWildlife = new Thing[0];
		lWPop = new Thing[0];
		for (int i = 0; i < animalTL.length; i++) {
			if (animalTL[i].containsTag("habi_" + generateNumberTag(currBiome))) {
				Thing[] temp = localWildlife.clone();
				localWildlife = new Thing[localWildlife.length + 1];
				System.arraycopy(temp, 0, localWildlife, 0, temp.length);
				localWildlife[localWildlife.length - 1] = animalTL[i];
			}
		}
		int totalPop = 0;
		for (int i = 0; i < localWildlife.length; i++) {
			int eco = Integer.parseInt(localWildlife[i].getValue("ecol").substring(5));
			int size = Integer.parseInt(localWildlife[i].getValue("size").substring(5));
			int tempPop = (11 - size) * (2 - eco);
			int distMod = 2;
			if (localWildlife[i].containsTag("plnt")) {
				tempPop *= 5;
			}
			if (localWildlife[i].containsTag("anim")) {
				String[] behaviorArr = localWildlife[i].getAll("bhvr");
				tempPop = (5 - size) * (4 - eco);
				for (int j = 0; j < behaviorArr.length; j++) {
					String behavior = behaviorArr[j].substring(5);

					distMod = 5;
					if (behavior.equals("pkht")) {
						tempPop += 4;

					}
					if (behavior.equals("noma")) {
						tempPop /= 2;
						tempPop++;
						distMod += 5;

					}
					if (behavior.equals("lone")) {
						tempPop = 1;

					}
					if (behavior.equals("terr")) {
						tempPop /= 3;
						tempPop++;
						distMod += 20;

					}
					if (behavior.equals("faml")) {
						tempPop += 4;
						distMod -= 2;

					}
					if (behavior.equals("farm")) {
						tempPop *= 2;
						distMod -= 2;

					}

				}
			}
			Thing[] temp = lWPop.clone();
			lWPop = new Thing[totalPop + tempPop];
			System.arraycopy(temp, 0, lWPop, 0, temp.length);
			int randX = (int) (generateRandomRuntime() * 25);
			int randY = (int) (generateRandomRuntime() * 25);
			for (int j = totalPop; j < totalPop + tempPop; j++) {
				lWPop[j] = new Thing();
				localWildlife[i].copyInto(lWPop[j]);
				if (lWPop[j].containsTag("anim_spec")) {
					lWPop[j].remTag("anim_spec");
					lWPop[j].addTag("anim_indv");
					int origSize = Integer.parseInt(lWPop[j].getValue("size").substring(5, 9));
					if (origSize > 1) {
						origSize += (generateRandomRuntime() > 0.5) ? 0 : -1;
					}
					lWPop[j].remTag("size");
					lWPop[j].addTag("size_" + generateNumberTag(origSize));
					lWPop[j].addTag("feed_" + lWPop[j].getValue("size").substring(5));
					int staminaMod = Integer.parseInt(lWPop[j].getValue("stam").substring(5));
					int strengthMod = Integer.parseInt(lWPop[j].getValue("strm").substring(5));
					int speedMod = Integer.parseInt(lWPop[j].getValue("spdm").substring(5));
					int intMod = Integer.parseInt(lWPop[j].getValue("intm").substring(5));
					if (lWPop[j].containsTag("stam"))
						lWPop[j].remTag("stam");
					if (lWPop[j].containsTag("strm"))
						lWPop[j].remTag("strm");

					if (lWPop[j].containsTag("intm"))
						lWPop[j].remTag("intm");
					if (lWPop[j].containsTag("spdm"))
						lWPop[j].remTag("spdm");
					lWPop[j].addTag(
							"stam_" + generateNumberTag((int) (staminaMod + ((generateRandomRuntime() > 0.5) ? -1 : 1)
									* staminaMod * generateRandomRuntime() * 0.1)));
					lWPop[j].addTag(
							"strm_" + generateNumberTag((int) (strengthMod + ((generateRandomRuntime() > 0.5) ? -1 : 1)
									* strengthMod * generateRandomRuntime() * 0.1)));
					lWPop[j].addTag("spdm_" + generateNumberTag((int) (speedMod
							+ ((generateRandomRuntime() > 0.5) ? -1 : 1) * speedMod * generateRandomRuntime() * 0.1)));
					lWPop[j].addTag("intm_" + generateNumberTag((int) (intMod
							+ ((generateRandomRuntime() > 0.5) ? -1 : 1) * intMod * generateRandomRuntime() * 0.1)));
					if (Integer.parseInt(lWPop[j].getValue("ecol").substring(5)) > 0) {

						lWPop[j].addTag("food_" + lWPop[j].getValue("size").substring(5));
					}
				}
				if (lWPop[j].containsTag("plnt_spec")) {
					lWPop[j].remTag("plnt_spec");
					lWPop[j].addTag("plnt_indv");
				}
				while (randX >= 25 || randX < 0) {
					while (randX >= 25) {
						randX -= 2 * ((int) (generateRandomRuntime() * distMod / 2.0) + distMod / 2.0);
					}
					while (randX < 0) {
						randX += 2 * ((int) (generateRandomRuntime() * distMod / 2.0) + distMod / 2.0);
					}
				}
				while (randY >= 25 || randY < 0) {
					while (randY >= 25) {
						randY -= 2 * ((int) (generateRandomRuntime() * distMod / 2.0) + distMod / 2.0);
					}
					while (randY < 0) {
						randY += 2 * ((int) (generateRandomRuntime() * distMod / 2.0) + distMod / 2.0);
					}
				}
				lWPop[j].addTag("posx_" + generateNumberTag(randX));
				lWPop[j].addTag("posy_" + generateNumberTag(randY));
				randX += (generateRandomRuntime() > 0.5) ? -1
						: 1 * ((int) (generateRandomRuntime() * distMod / 2.0) + distMod / 2.0);
				randY += (generateRandomRuntime() > 0.5) ? -1
						: 1 * ((int) (generateRandomRuntime() * distMod / 2.0) + distMod / 2.0);

				lWPop[j].addTag("orie_" + generateNumberTag((int) (generateRandomRuntime() * 360) + 1));
				lWPop[j].addTag("unid_" + generateUniqueID());
			}
			totalPop += tempPop;
		}
		System.out.println();
		System.out.println("INDIVIDUALS IN CURRENT BIOME(" + currBiome + ") : " + lWPop.length);
		System.out.println();
		updateCreatures();
	}

	// transformative functions on generateRandomRuntime(), useful for cases that
	// require
	// randomness but not uniformly distributed randomness
	public static double generateRandomQuintic() {
		double x = generateRandomRuntime() - 0.5;
		return 16 * (x * x * x * x * x) + 0.5;
	}

	public static double generateRandomSigmoid() {
		double x = generateRandomRuntime() - 0.5;
		return 1 / (1 + Math.pow(Math.E, -16 * x));
	}

	private static double getQuintic(double x) {
		return 16 * (x * x * x * x * x) + 0.5;
	}

	public static double generateRandomTieredQuintic() {
		double x = generateRandomRuntime();
		if (x < 0.5) {
			return 0.5 * getQuintic(2 * x);
		}
		if (x >= 0.5) {
			return 0.5 * getQuintic(2 * (x - 0.5)) + 0.5;
		}
		return x;
	}

	public static Thing getByID(String unID) {
		for (int i = 0; i < lWPop.length; i++) {
			if (lWPop[i].getValue("unID").substring(5).equals(unID)) {
				return lWPop[i];
			}
		}
		return null;
	}

	private double hermiteTangentOne(double x) {
		return x * x * x - 2 * x * x + x;
	}

	private double hermiteTangentTwo(double x) {
		return x * x * x - x * x;
	}

	public double bodyLengthSpline(double a, double b, double x) {
		return hermiteTangentOne(x) * a + hermiteTangentTwo(x) * b + 0.5 * (1 - 4 * (x - 0.5) * (x - 0.5));
	} // -2<=a<=2 and -2<=b<=2 and 0<=x<=1

	public double bodyQuarterSpline(double a, double x) {
		return (1 - x) * (1 - x) * (1 - x) + (1 - x) * (1 - x) * x * a + (1 - x) * x * x * a;
	} // 0<=a<=3 and 0<=x<=1

	// DSQ-algorithm here
	private void diamondSquare() {
		currTerrain = new double[25 * 25];
		currTerrain[0] = biomeConstants[currBiome][0];
		currTerrain[24] = biomeConstants[currBiome][1];
		currTerrain[24 * 25] = biomeConstants[currBiome][2];
		currTerrain[25 * 25 - 1] = biomeConstants[currBiome][3];
		currRandom = biomeConstants[currBiome][4];
		doDiamond(0, 24, 24 * 25, 25 * 25 - 1, 1);

		double max = 0;
		double min = 100;
		for (int i = 0; i < 25 * 25; i++) {
			if (max < currTerrain[i])
				max = currTerrain[i];
			if (min > currTerrain[i])
				min = currTerrain[i];
		}
		for (int i = 0; i < 25 * 25; i++) {
			currTerrain[i] -= min;
			currTerrain[i] /= (max - min);
		}
		for (int i = 0; i < 25 * 25; i++) {
			double avg = currTerrain[i] * 0.2;
			int count = 1;
			if (i > 0) {
				count++;
				avg += currTerrain[i - 1] * 0.1;
				if (i > 23) {
					count++;
					avg += currTerrain[i - 25 + 1] * 0.1;
					if (i > 24) {
						count++;
						avg += currTerrain[i - 25] * 0.1;
						if (i > 25) {
							count++;
							avg += currTerrain[i - 1 - 25] * 0.1;
						}

					}
				}
			}
			if (i < 25 * 25 - 1) {
				count++;
				avg += currTerrain[i + 1] * 0.1;
				if (i < 24 * 25) {
					count++;
					avg += currTerrain[i + 25 - 1] * 0.1;
					if (i < 24 * 25 - 1) {
						count++;
						avg += currTerrain[i + 25] * 0.1;
						if (i < 24 * 25 - 2) {
							count++;
							avg += currTerrain[i + 25 + 1] * 0.1;
						}
					}
				}
			}
			table[i] = avg;
		}
	}

	private void doDiamond(int h, int i, int j, int k, int n) {

		currTerrain[(h - h / 25 * 25 + i - i / 25 * 25) / 2
				+ (h / 25 + j / 25) / 2 * 25] = (currTerrain[h] + currTerrain[i] + currTerrain[j] + currTerrain[k]) / 4
						+ generateRandomIsolated(currRandom) * 2 / (n * 1) - 1.0 / (n * 1);

		currRandom = generateRandomIsolated(currRandom);
		doSquare((h - h / 25 * 25 + i - i / 25 * 25) / 2 + (h / 25 + j / 25) / 2 * 25, n + 1);

	}

	private void doSquare(int index, int n) {

		int y = index / 25 * 25;
		int x = index - index / 25 * 25;
		int dx = (int) (25 / Math.pow(2, n - 1));
		int dy = dx * 25;
		if (dx == 0 || dy == 0)
			return;
		currTerrain[x - dx + y - dy] = currTerrain[index] + generateRandomIsolated(currRandom) * 2 / (n * 1)
				- 1.0 / (n * 1);
		currRandom = generateRandomIsolated(currRandom);
		currTerrain[x + y - dy] = currTerrain[index] + generateRandomIsolated(currRandom) * 2 / (n * 1) - 1.0 / (n * 1);
		currRandom = generateRandomIsolated(currRandom);
		currTerrain[x - dx + y] = currTerrain[index] + generateRandomIsolated(currRandom) * 2 / (n * 1) - 1.0 / (n * 1);
		currRandom = generateRandomIsolated(currRandom);
		doDiamond(x - dx + y - dy, x + y - dy, x - dx + y, x + y, n);
		currTerrain[x + dx + y - dy] = currTerrain[index] + generateRandomIsolated(currRandom) * 2 / (n * 1)
				- 1.0 / (n * 1);
		currRandom = generateRandomIsolated(currRandom);
		currTerrain[x + dx + y] = currTerrain[index] + generateRandomIsolated(currRandom) * 2 / (n * 1) - 1.0 / (n * 1);
		currRandom = generateRandomIsolated(currRandom);
		doDiamond(x + y - dy, x + dx + y - dy, x + y, x + dx + y, n);
		currTerrain[x - dx + y - dy] = currTerrain[index] + generateRandomIsolated(currRandom) * 2 / (n * 1)
				- 1.0 / (n * 1);
		currRandom = generateRandomIsolated(currRandom);
		currTerrain[x + y + dy] = currTerrain[index] + generateRandomIsolated(currRandom) * 2 / (n * 1) - 1.0 / (n * 1);
		currRandom = generateRandomIsolated(currRandom);
		doDiamond(x - dx + y, x + y, x - dx + y + dy, x + y + dy, n);
		currTerrain[x + dx + y + dy] = currTerrain[index] + generateRandomIsolated(currRandom) * 2 / (n * 1)
				- 1.0 / (n * 1);
		currRandom = generateRandomIsolated(currRandom);
		doDiamond(x + y, x + dx + y, x + y + dy, x + dx + y + dy, n);
	}

	public static void readAndUpdate() {
		for (int i = 0; i < lWPop.length; i++) {
			if (lWPop[i].containsTag("move")) {
				int x = Integer.parseInt(lWPop[i].getValue("posx").substring(5));
				int y = Integer.parseInt(lWPop[i].getValue("posy").substring(5));
				x += Integer.parseInt(lWPop[i].getValue("move").substring(5, 9));
				y += Integer.parseInt(lWPop[i].getValue("move").substring(10));

				lWPop[i].remTag("posx");
				lWPop[i].remTag("posy");
				lWPop[i].remTag("move");

				lWPop[i].addTag("posx_" + Map.generateNumberTag(x));
				lWPop[i].addTag("posy_" + Map.generateNumberTag(y));
			}
			// testing movement
			// delete this section once actual AI is set up
			if (lWPop[i].containsTag("anim")) {
				int randX = (Map.generateRandomRuntime() > 0.5) ? 1 : -1;
				int randY = (Map.generateRandomRuntime() > 0.5) ? 1 : -1;
				int x = Integer.parseInt(lWPop[i].getValue("posx").substring(5));
				int y = Integer.parseInt(lWPop[i].getValue("posy").substring(5));
				if (randX + x >= 25 || randX + x < 0)
					randX *= -1;
				if (randY + y >= 25 || randY + y < 0)
					randY *= -1;
				lWPop[i].addTag("move_" + Map.generateNumberTag(randX) + "_" + Map.generateNumberTag(randY));
			}
			// end of testing movement
			if (lWPop[i].containsTag("anim")) {
				if (lWPop[i].containsTag("anim_indv")) {
					if (lWPop[i].containsTag("hngr")) {
						int size = Integer.parseInt(lWPop[i].getValue("size").substring(5));
						int hunger = Integer.parseInt(lWPop[i].getValue("hngr").substring(5));
						lWPop[i].remTag("hngr");
						if (hunger < size) {
							hunger++;
						}
						lWPop[i].addTag("hngr_" + Map.generateNumberTag(hunger));
					}
					if (lWPop[i].containsTag("feed")) {
						int fullness = Integer.parseInt(lWPop[i].getValue("feed").substring(5));
						lWPop[i].remTag("feed");

						if (fullness > 0) {
							lWPop[i].addTag("feed_" + Map.generateNumberTag(fullness - 1));
						} else {
							lWPop[i].addTag("hngr_0000");
						}
					}

				}
			}
		}
	}

	public static void updateCreatures() {
		generateDetailedSensory();
		generatePreyItems();
		readAndUpdate();
	}

	public static void assessThreats() {
		for (int i = 0; i < lWPop.length; i++) {
			while (lWPop[i].containsTag("thrt")) {
				lWPop[i].remTag("thrt");
			}
			String[] percValues = lWPop[i].getAll("perc");
			for (int j = 0; j < percValues.length; j++) {
				Thing thing = Map.getByID(percValues[j].substring(10, 14));
				int thingSize = Integer.parseInt(thing.getValue("size").substring(5));
				int size = Integer.parseInt(lWPop[i].getValue("size").substring(5));
				int thingEcol = Integer.parseInt(lWPop[i].getValue("ecol").substring(5));
				int ecol = Integer.parseInt(lWPop[i].getValue("ecol").substring(5));
				double fearMod = Integer.parseInt(lWPop[i].getValue("fear").substring(5)) / 100.0;
				if (thing.getValue("spec").equals(lWPop[j].getValue("spec"))) {
					if (lWPop[i].containsTag("cnbl") && thingSize * fearMod >= size) {
						lWPop[i].addTag("thrt_" + percValues[j].substring(10, 14));
						continue;
					} else {
						continue;
					}
				}
				double sizeMod = 1.0;
				if (lWPop[i].containsTag("pkht")) {
					if (lWPop[i].containsTag("pkht_very")) {
						sizeMod += 0.8;
					} else if (lWPop[i].containsTag("pkht_nmod")) {
						sizeMod += 0.6;
					} else {
						sizeMod += 0.4;
					}
				}
				double thrtMod = 1.0;
				if (lWPop[i].containsTag("pois")) {
					double poisMod = 0.0;

					thrtMod += poisMod;
				}
				if (lWPop[i].containsTag("horn")) {
					thrtMod += 0.5;
				}
				if (lWPop[i].containsTag("claw")) {
					thrtMod += 0.5;
				}
				if (thingSize * thrtMod * fearMod > size * sizeMod * (1 + max(ecol - thingEcol, 0) / 3.0)) {
					lWPop[i].addTag("thrt_" + percValues[j].substring(10, 14));
					continue;
				}

			}
		}
	}

	public static void generatePreyItems() {
		for (int j = 0; j < lWPop.length; j++) {
			int preyLength = lWPop[j].getAll("prey").length;
			for (int k = 0; k < preyLength; k++) {
				lWPop[j].remTag("prey");
			}
			int eco = Integer.parseInt(lWPop[j].getValue("ecol").substring(5));
			String[] percValues;
			switch (eco) {
			case 0:
				if (lWPop[j].containsTag("cnbl")) {
					percValues = lWPop[j].getAll("perc");
					for (int i = 0; i < percValues.length; i++) {
						Thing thing = Map.getByID(percValues[i].substring(10, 14));
						if (thing.getValue("spec").equals(lWPop[j].getValue("spec"))
								&& Integer.parseInt(thing.getValue("size").substring(5)) <= Integer
										.parseInt(lWPop[j].getValue("size").substring(5))) {
							lWPop[j].addTag("prey_" + percValues[i].substring(10, 14));
							continue;
						}
					}
				}
				break;
			case 1:
				percValues = lWPop[j].getAll("perc"); // 0123456789 -- 10 11 12 13 -- (10, 14)
				for (int i = 0; i < percValues.length; i++) {

					Thing thing = Map.getByID(percValues[i].substring(10, 14));
					if (lWPop[j].containsTag("cnbl")) {
						if (thing.getValue("spec").equals(lWPop[j].getValue("spec"))) {
							lWPop[j].addTag("prey_" + percValues[i].substring(10, 14));
							continue;
						}
					}
					if (thing.containsTag("plnt")) {
						int size = Integer.parseInt(lWPop[j].getValue("size").substring(5));
						int thingEco = Integer.parseInt(lWPop[j].getValue("ecol").substring(5));
						int thingSize = Integer.parseInt(thing.getValue("size").substring(5));
						if (!thing.getValue("spec").equals(lWPop[j].getValue("spec")) || lWPop[j].containsTag("cnbl")) {
							if (thingEco == 0 && thingSize < size) {
								lWPop[j].addTag("prey_" + percValues[i].substring(10, 14));
								continue;
							}

						}
					}
				}
				break;

			case 2:
				percValues = lWPop[j].getAll("perc"); // 0123456789 -- 10 11 12 13 -- (10, 14)
				for (int i = 0; i < percValues.length; i++) {

					Thing thing = Map.getByID(percValues[i].substring(10, 14));
					int size = Integer.parseInt(lWPop[j].getValue("size").substring(5));
					int thingEco = Integer.parseInt(lWPop[j].getValue("ecol").substring(5));
					int thingSize = Integer.parseInt(thing.getValue("size").substring(5));
					int strMod = Integer.parseInt(lWPop[j].getValue("strm").substring(5));
					int spdMod = Integer.parseInt(lWPop[j].getValue("spdm").substring(5));
					int intMod = Integer.parseInt(lWPop[j].getValue("intm").substring(5));
					int tStrMod = Integer.parseInt(thing.getValue("strm").substring(5));
					int tSpdMod = Integer.parseInt(thing.getValue("spdm").substring(5));
					double aptitude = Integer.parseInt(thing.getValue("aptt").substring(5)) / 100.0;

					int sizeMod = 1;
					if (!thing.getValue("spec").equals(lWPop[j].getValue("spec")) || lWPop[j].containsTag("cnbl")) {
						if (thingEco != 0) {

							if (lWPop[j].containsTag("pkht")) {
								sizeMod += 0.5 * (intMod / 100.0);
							}

							if (size * sizeMod * strMod * aptitude > thingSize * tStrMod
									&& (lWPop[j].containsTag("ambu") || spdMod * aptitude > tSpdMod)) {
								lWPop[j].addTag("prey_" + percValues[i].substring(10, 14));
							}
						}
					}

				}
				break;

			case 3:
				percValues = lWPop[j].getAll("perc"); // 0123456789 -- 10 11 12 13 -- (10, 14)
				for (int i = 0; i < percValues.length; i++) {

					Thing thing = Map.getByID(percValues[i].substring(10, 14));

					int size = Integer.parseInt(lWPop[j].getValue("size").substring(5));
					int thingEco = Integer.parseInt(lWPop[j].getValue("ecol").substring(5));
					int thingSize = Integer.parseInt(thing.getValue("size").substring(5));
					if (!thing.getValue("spec").equals(lWPop[j].getValue("spec")) || lWPop[j].containsTag("cnbl")) {
						if (thingEco == 0) {
							lWPop[j].addTag("prey_" + percValues[i].substring(10, 14));
							continue;
						} else {
							int strMod = Integer.parseInt(lWPop[j].getValue("strm").substring(5));
							int spdMod = Integer.parseInt(lWPop[j].getValue("spdm").substring(5));
							int intMod = Integer.parseInt(lWPop[j].getValue("intm").substring(5));
							int tStrMod = Integer.parseInt(thing.getValue("strm").substring(5));
							int tSpdMod = Integer.parseInt(thing.getValue("spdm").substring(5));
							double aptitude = Integer.parseInt(thing.getValue("aptt").substring(5)) / 100.0;
							int sizeMod = 1;

							if (lWPop[j].containsTag("pkht")) {
								sizeMod += 0.5 * (intMod / 100.0);
							}

							if (size * sizeMod * strMod * aptitude > thingSize * tStrMod
									&& (lWPop[j].containsTag("ambu") || spdMod * aptitude > tSpdMod)) {
								lWPop[j].addTag("prey_" + percValues[i].substring(10, 14));
								continue;
							}

						}
					}
				}
				break;

			default:

				break;

			}
		}
	}

	public static String generateUniqueID() {
		String newID = "";
		boolean collision = true;
		while (collision) {
			collision = false;
			newID = generateNumberTag((int) (generateRandomRuntime() * 9999) + 1);
			for (int i = 0; i < unIDs.length; i++) {
				if (unIDs[i].equals(newID)) {
					collision = true;
				}
			}
		}
		String[] temp = unIDs.clone();
		unIDs = new String[unIDs.length + 1];
		System.arraycopy(temp, 0, unIDs, 0, temp.length);
		unIDs[unIDs.length - 1] = newID;
		return newID;
	}

	public static int dist(int a, int b) {
		if (a < 0) {
			a = -a;
		}
		if (b < 0) {
			b = -b;
		}
		int min = a;
		int max = b;
		if (a > b) {
			max = min;
			min = b;
		}
		int output = max * 1007 + min * 441;
		if (max < min << 4) {
			output -= max * 40;
		}

		return (output + 512) >> 10;
	}

	public static void generateOrganisms() {

		animalTL = new Thing[(int) (generateRandomRuntime() * 5 * worldSize + worldSize / 2 + 1)];
		for (int i = 0; i < animalTL.length; i++) {
			int organicMat = (int) (generateRandomRuntime() * materialTL.length);

			while (!materialTL[organicMat].containsTag("orga_nmod")) {
				organicMat = (int) (generateRandomRuntime() * materialTL.length);
			}
			String skinMaterial = generateNumberTag(organicMat);

			organicMat = (int) (generateRandomRuntime() * materialTL.length);
			while (!materialTL[organicMat].containsTag("orga_nmod")) {
				organicMat = (int) (generateRandomRuntime() * materialTL.length);
			}
			String skelMaterial = generateNumberTag(organicMat);

			organicMat = (int) (generateRandomRuntime() * materialTL.length);
			while (!materialTL[organicMat].containsTag("orga_nmod")) {
				organicMat = (int) (generateRandomRuntime() * materialTL.length);
			}
			String flshMaterial = generateNumberTag(organicMat);

			animalTL[i] = new Thing();
			animalTL[i].addTag("skin_" + skinMaterial);
			animalTL[i].addTag("skel_" + skelMaterial);
			animalTL[i].addTag("flsh_" + flshMaterial);

			animalTL[i].addTag("habi_" + generateNumberTag((int) (generateRandomRuntime() * biomeSect.length)));
			animalTL[i].addTag("strm_" + generateNumberTag((int) (generateRandomRuntime() * 201)));
			animalTL[i].addTag("intm_" + generateNumberTag((int) (generateRandomRuntime() * 201)));
			animalTL[i].addTag("spdm_" + generateNumberTag((int) (generateRandomRuntime() * 201)));
			animalTL[i].addTag("fear_" + generateNumberTag((int) (generateRandomRuntime() * 201)));
			animalTL[i].addTag("aptt_" + generateNumberTag((int) (generateRandomRuntime() * 201)));

			int complexity = 5 + (int) (generateRandomRuntime() * 30);
			animalTL[i].addTag("cplx_" + generateNumberTag(complexity));

			if (generateRandomRuntime() > 0.5) {
				animalTL[i].addTag("anim_spec");
				animalTL[i].addTag("spec_" + generateUniqueID());
				animalTL[i]
						.addTag("char_" + animalLookup.charAt((int) (generateRandomRuntime() * animalLookup.length())));
				int sizeNum = (int) (generateRandomRuntime() * 4) + 1;
				animalTL[i].addTag("size_" + generateNumberTag(sizeNum));
				animalTL[i].addTag("ecol_" + generateNumberTag((int) (generateRandomRuntime() * 4)));
				animalTL[i].addTag("stam_" + generateNumberTag((int) (generateRandomRuntime() * 201)));
				for (int j = 0; j < complexity / 5 + 1; j++) {
					String prereqCheck = behaviorTL[(int) (generateRandomRuntime() * behaviorTL.length)];
					while (prereqCheck.equals("pkht") && animalTL[i].containsTag("lone")) {
						prereqCheck = behaviorTL[(int) (generateRandomRuntime() * behaviorTL.length)];
					}
					while (prereqCheck.equals("faml") && animalTL[i].containsTag("lone")) {
						prereqCheck = behaviorTL[(int) (generateRandomRuntime() * behaviorTL.length)];
					}
					while (prereqCheck.equals("usoc") && animalTL[i].containsTag("lone")) {
						prereqCheck = behaviorTL[(int) (generateRandomRuntime() * behaviorTL.length)];
					}
					while (prereqCheck.equals("lone") && animalTL[i].containsTag("pkht")) {
						prereqCheck = behaviorTL[(int) (generateRandomRuntime() * behaviorTL.length)];
					}
					while (prereqCheck.equals("lone") && animalTL[i].containsTag("faml")) {
						prereqCheck = behaviorTL[(int) (generateRandomRuntime() * behaviorTL.length)];
					}
					while (prereqCheck.equals("lone") && animalTL[i].containsTag("usoc")) {
						prereqCheck = behaviorTL[(int) (generateRandomRuntime() * behaviorTL.length)];
					}
					while (prereqCheck.equals("cnbl") && animalTL[i].containsTag("usoc")) {
						prereqCheck = behaviorTL[(int) (generateRandomRuntime() * behaviorTL.length)];
					}
					while (prereqCheck.equals("usoc") && animalTL[i].containsTag("cnbl")) {
						prereqCheck = behaviorTL[(int) (generateRandomRuntime() * behaviorTL.length)];
					}
					animalTL[i].addTag("bhvr_" + behaviorTL[(int) (generateRandomRuntime() * behaviorTL.length)] + "_"
							+ generateQualityModTag());
				}
				// need to contextualize - placement - shape - symmetry
				for (int j = 0; j < complexity; j++) {
					int anatomyNum = ((int) (generateRandomQuintic() * (10 * 15.0 / (complexity + sizeNum))) + 1);

					String prereqCheck = anatomyTL[(int) (generateRandomRuntime() * anatomyTL.length)];

					while ((prereqCheck.equals("hand") || prereqCheck.equals("foot"))
							&& (animalTL[i].getAll("hand").length
									+ animalTL[i].getAll("foot").length >= animalTL[i].getAll("japp").length
											+ animalTL[i].getAll("tent").length + animalTL[i].getAll("tail").length)) {
						prereqCheck = anatomyTL[(int) (generateRandomRuntime() * anatomyTL.length)];
					}

					while (prereqCheck.equals("claw")
							&& (animalTL[i].getAll("claw").length >= animalTL[i].getAll("hand").length
									+ animalTL[i].getAll("foot").length)) {
						prereqCheck = anatomyTL[(int) (generateRandomRuntime() * anatomyTL.length)];
					}

					while ((prereqCheck.equals("tetn") || prereqCheck.equals("tetl"))
							&& !animalTL[i].containsTag("mout")) {
						prereqCheck = anatomyTL[(int) (generateRandomRuntime() * anatomyTL.length)];
					}

					if (prereqCheck.equals("nose") || prereqCheck.equals("eyes") || prereqCheck.equals("ears")
							|| prereqCheck.equals("feel")) {
						anatomyNum /= 4;
						if (anatomyNum == 0) {
							anatomyNum++;
						}
					}
					if (!prereqCheck.equals("claw") && !prereqCheck.equals("tetl") && !prereqCheck.equals("tetn")
							&& !prereqCheck.equals("nose") && anatomyNum % 2 == 1) {
						anatomyNum++;
					}
					if (prereqCheck.equals("claw")) {
						anatomyNum = ((int) (generateRandomRuntime() * (10 * 15.0 / (complexity + sizeNum))) + 1);
					}
					if (prereqCheck.equals("eyes")) {
						int sightRange = (int) (generateRandomRuntime() * 100) + 1;
						int sightResol = (int) (generateRandomRuntime() * 76) + 25;
						int sightCone = (int) (generateRandomRuntime() * 171) + 10;

						prereqCheck += "_rnge_" + generateNumberTag(sightRange);
						prereqCheck += "_reso_" + generateNumberTag(sightResol);
						prereqCheck += "_cone_" + generateNumberTag(sightCone);
					}
					if (prereqCheck.equals("nose")) {
						int smellRange = (int) (generateRandomRuntime() * 200) + 1;
						int smellResol = (int) (generateRandomRuntime() * 100) + 1;
						prereqCheck += "_rnge_" + generateNumberTag(smellRange);
						prereqCheck += "_reso_" + generateNumberTag(smellResol);
					}
					if (prereqCheck.equals("ears")) {
						int hearRange = (int) (generateRandomRuntime() * 100) + 1;
						int hearResol = (int) (generateRandomRuntime() * 100) + 1;
						prereqCheck += "_rnge_" + generateNumberTag(hearRange);
						prereqCheck += "_reso_" + generateNumberTag(hearResol);
					}
					if (prereqCheck.equals("feel")) {
						int feelRange = (int) ((generateRandomRuntime() * 100) + 1) / 90
								* (int) (generateRandomRuntime() * 5 + 1);
						prereqCheck += "_rnge_" + generateNumberTag((feelRange));
						prereqCheck += "_reso_" + generateNumberTag(100);
					}
					prereqCheck += "_" + generateNumberTag(anatomyNum) + "_"
							+ specialTL[(int) (generateRandomRuntime() * specialTL.length)] + "_"
							+ generateQualityModTag();
					animalTL[i].addTag(prereqCheck);

				}
			} else {
				animalTL[i].addTag("plnt_spec");
				animalTL[i].addTag("spec_" + generateUniqueID());
				animalTL[i]
						.addTag("char_" + plantLookup.charAt((int) (generateRandomRuntime() * plantLookup.length())));
				int sizeNum = (int) (generateRandomRuntime() * 10) + 1;
				animalTL[i].addTag("size_" + generateNumberTag(sizeNum));
				animalTL[i].addTag("ecol_" + generateNumberTag(((int) (generateRandomRuntime() * 11)) / 9));

				// need to contextualize - placement - shape - symmetry
				for (int j = 0; j < complexity; j++) {
					String prereqCheck = anatomyPTL[(int) (generateRandomRuntime() * anatomyPTL.length)] + "_"
							+ generateQualityModTag();
					prereqCheck += "_" + specialTL[(int) (generateRandomRuntime() * specialTL.length)] + "_"
							+ generateQualityModTag();
					animalTL[i].addTag(prereqCheck);
				}
			}
		}

	}

	public static void generateDetailedSensory() {
		for (int g = 0; g < lWPop.length; g++) {
			while (lWPop[g].containsTag("perc")) {
				lWPop[g].remTag("perc");
			}

			int x = Integer.parseInt(lWPop[g].getValue("posx").substring(5, 9));
			int y = Integer.parseInt(lWPop[g].getValue("posy").substring(5, 9));
			double z = table[x + y * 25];
			if (lWPop[g].containsTag("eyes")) {
				String[] eyeValues = lWPop[g].getAll("eyes");
				for (int i = 0; i < eyeValues.length; i++) {
					int range = Integer.parseInt(eyeValues[i].substring(10, 14));
					int reso = Integer.parseInt(eyeValues[i].substring(20, 24));
					int cone = Integer.parseInt(eyeValues[i].substring(30, 34));
					double[][] newThings = new double[0][0];
					for (int j = 0; j < lWPop.length; j++) {
						if (j == g)
							continue;
						int thingX = Integer.parseInt(lWPop[j].getValue("posx").substring(5, 9));
						int thingY = Integer.parseInt(lWPop[j].getValue("posy").substring(5, 9));
						int dx = thingX - x;
						int dy = thingY - y;
						if (Map.dist(dx, dy) < range) {
							int orie = Integer.parseInt(lWPop[g].getValue("orie").substring(5, 9));
							int lb = orie - cone;
							int ub = orie + cone;
							double angle = (Math.atan2(dy, dx) / Math.PI * 180);
							if (angle < 0) {
								angle += 360;
							}
							if (angle >= lb && angle <= ub) {
								int seen = -1;
								for (int k = 0; k < newThings.length; k++) {
									if (newThings[k][0] == angle) {
										seen = k;
										break;
									}
								}
								if (seen != -1) {
									double[] ntemp = newThings[seen].clone();
									newThings[seen] = new double[newThings[seen].length + 3];
									System.arraycopy(ntemp, 0, newThings[seen], 0, ntemp.length);
									newThings[seen][newThings[seen].length - 3] = thingX;
									newThings[seen][newThings[seen].length - 2] = thingY;
									newThings[seen][newThings[seen].length - 1] = Integer
											.parseInt(lWPop[j].getValue("unid").substring(5));
								} else {
									double[][] ntemp = new double[newThings.length][];
									for (int k = 0; k < ntemp.length; k++) {
										ntemp[k] = new double[newThings[k].length];
										System.arraycopy(newThings[k], 0, ntemp[k], 0, newThings[k].length);
									}
									newThings = new double[newThings.length + 1][];
									for (int k = 0; k < ntemp.length; k++) {
										newThings[k] = new double[ntemp[k].length];
										System.arraycopy(ntemp[k], 0, newThings[k], 0, ntemp[k].length);
									}
									newThings[newThings.length - 1] = new double[4];
									newThings[newThings.length - 1][0] = angle;
									newThings[newThings.length - 1][1] = thingX;
									newThings[newThings.length - 1][2] = thingY;
									newThings[newThings.length - 1][3] = Integer
											.parseInt(lWPop[j].getValue("unid").substring(5));
								}
							}
						}
					}

					for (int j = 0; j < newThings.length; j++) {
						int n = 0;
						double cx = x;
						double cy = y;
						double radianAngle = newThings[j][0] / 180 * Math.PI;
						while (n < range) {
							cx += Math.cos(radianAngle);
							cy += Math.sin(radianAngle);
							if (cx < 0)
								break;
							if (cx >= 25)
								break;
							if (cy < 0)
								break;
							if (cy >= 25)
								break;
							double maxHeight = -100;
							if (table[(int) cx + (int) cy * 25] > table[x + y * 25] + 0.25 + 0.01 * n) {
								break;
							}
							if (table[(int) cx + (int) cy * 25] > maxHeight) {
								maxHeight = table[(int) cx + (int) cy * 25];
							}
							for (int k = 1; k < newThings[j].length - 2; k += 3) {

								int thingX = (int) (newThings[j][k]);
								int thingY = (int) (newThings[j][k + 1]);
								if (maxHeight > table[(int) thingX + (int) thingY * 25]) {
									continue;
								}
								int dx = thingX - x;
								int dy = thingY - y;
								int perX = (int) (thingX + ((Map.generateRandomRuntime() > 0.5) ? -1 : 1)
										* Map.generateRandomRuntime() * Map.dist(dx, dy) * (100 - reso) / 100.0);
								int perY = (int) (thingY + ((Map.generateRandomRuntime() > 0.5) ? -1 : 1)
										* Map.generateRandomRuntime() * Map.dist(dx, dy) * (100 - reso) / 100.0);
								if ((int) cx == thingX && (int) cy == thingY) {
									lWPop[g].addTag("perc_seen_" + Map.generateNumberTag((int) (newThings[j][k + 2]))
											+ "_" + Map.generateNumberTag(perX) + "_" + Map.generateNumberTag(perY));
								}
							}
							n++;
						}
					}
				}
			}
			if (lWPop[g].containsTag("feel")) {
				String[] feelValues = lWPop[g].getAll("feel");
				for (int i = 0; i < feelValues.length; i++) {
					int range = Integer.parseInt(feelValues[i].substring(10, 14));
					for (int j = 0; j < lWPop.length; j++) {
						if (j == g)
							continue;
						int thingX = Integer.parseInt(lWPop[j].getValue("posx").substring(5, 9));
						int thingY = Integer.parseInt(lWPop[j].getValue("posy").substring(5, 9));
						int dx = thingX - x;
						int dy = thingY - y;
						if (Map.dist(dx, dy) < range) {

							lWPop[g].addTag("perc_felt_" + lWPop[j].getValue("unid").substring(5) + "_"
									+ Map.generateNumberTag(thingX) + "_" + Map.generateNumberTag(thingY));

						}
					}
				}
			}
			if (lWPop[g].containsTag("nose")) {
				String[] noseValues = lWPop[g].getAll("nose");
				for (int i = 0; i < noseValues.length; i++) {
					int range = Integer.parseInt(noseValues[i].substring(10, 14));
					int reso = Integer.parseInt(noseValues[i].substring(20, 24));
					for (int j = 0; j < lWPop.length; j++) {
						if (j == g)
							continue;
						int thingX = Integer.parseInt(lWPop[j].getValue("posx").substring(5, 9));
						int thingY = Integer.parseInt(lWPop[j].getValue("posy").substring(5, 9));
						int dx = thingX - x;
						int dy = thingY - y;
						if (Map.dist(dx, dy) < range) {
							int perX = (int) (thingX + ((Map.generateRandomRuntime() > 0.5) ? -1 : 1)
									* Map.generateRandomRuntime() * Map.dist(dx, dy) * (100 - reso) / 100.0);
							int perY = (int) (thingY + ((Map.generateRandomRuntime() > 0.5) ? -1 : 1)
									* Map.generateRandomRuntime() * Map.dist(dx, dy) * (100 - reso) / 100.0);
							if (Map.checkLOS()) {
								lWPop[g].addTag("perc_smlt_" + lWPop[j].getValue("unid").substring(5) + "_"
										+ Map.generateNumberTag(perX) + "_" + Map.generateNumberTag(perY));
							}
						}
					}
				}
			}
			if (lWPop[g].containsTag("ears")) {
				String[] earValues = lWPop[g].getAll("ears");
				for (int i = 0; i < earValues.length; i++) {
					int range = Integer.parseInt(earValues[i].substring(10, 14));
					int reso = Integer.parseInt(earValues[i].substring(20, 24));
					for (int j = 0; j < lWPop.length; j++) {
						if (j == g)
							continue;
						int thingX = Integer.parseInt(lWPop[j].getValue("posx").substring(5, 9));
						int thingY = Integer.parseInt(lWPop[j].getValue("posy").substring(5, 9));
						int dx = thingX - x;
						int dy = thingY - y;
						if (Map.dist(dx, dy) < range) {
							int perX = (int) (thingX + ((Map.generateRandomRuntime() > 0.5) ? -1 : 1)
									* Map.generateRandomRuntime() * Map.dist(dx, dy) * (100 - reso) / 100.0);
							int perY = (int) (thingY + ((Map.generateRandomRuntime() > 0.5) ? -1 : 1)
									* Map.generateRandomRuntime() * Map.dist(dx, dy) * (100 - reso) / 100.0);
							if (Map.checkLOS()) {
								lWPop[g].addTag("perc_eard_" + lWPop[j].getValue("unid").substring(5) + "_"
										+ Map.generateNumberTag(perX) + "_" + Map.generateNumberTag(perY));
							}
						}
					}
				}
			}
		}
	}

	public static int max(int a, int b) {
		return (a > b) ? a : b;
	}

	public static int min(int a, int b) {
		return (a < b) ? a : b;
	}

	public static double generateRandomRuntime() {
		// this method uses an insect population model, with coefficients adjusted to
		// put the model into chaos
		double result = 0.0;
		result = (1000 * cc / (Math.pow(1 + cc, 8))) % 1.0;
		cc = result;
		return result;
	}

	public static double generateRandomIsolated(double initial) {
		// this method uses an insect population model, with coefficients adjusted to
		// put the model into chaos
		return (1000 * initial / (Math.pow(1 + initial, 8))) % 1.0;
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
		System.out.print("byproduct: ");
		System.out.println(seedArr);

		System.out.print(seed.toCharArray());
		System.out.println(": ");
		double tMin = Integer.MAX_VALUE;
		double tMax = Integer.MIN_VALUE;

		// priming the insect population formula
		for (int i = 0; i < 100; i++) {
			generateRandomRuntime();
		}

		for (int i = 0; i < 25 * 25; i++) {
			table[i] = (double) generateRandomRuntime();
			if (tMin > table[i])
				tMin = table[i];
			if (tMax < table[i])
				tMax = table[i];
		}
		for (int i = 0; i < 25 * 25; i++) {
			table[i] -= tMin;
			table[i] /= (tMax - tMin);
		}

		worldSize = (int) (generateRandomRuntime() * 15) + 1;
		biomeConstants = new double[worldSize][5];
		biomeSect = new Thing[worldSize];
		generateMaterials();
		generateBiomes();
		diamondSquare();
		setBaseMap();

		System.out.println("world size of " + worldSize);
		generateOrganisms();
		populateOrganisms();

		setTrueMap();
		// setPerspecMap();
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
			for (int j = 0; j < lWPop.length; j++) {
				int x = Integer.parseInt(lWPop[j].getValue("posx").substring(5));
				int y = Integer.parseInt(lWPop[j].getValue("posy").substring(5));
				if (x + y * 25 == i) {
					trueMap[i] = lWPop[j].getValue("char").charAt(5);
				}
			}
		}

		trueMap[getPlayerPosition()] = '@';

	}

	public Player isPlayer(int position) {
		if (position == getPlayerPosition())
			return player;
		return null;
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

		if (hasW)
			playerY--;
		if (hasA)
			playerX--;
		if (hasS)
			playerY++;
		if (hasD)
			playerX++;

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
		return (val <= 0.25);
	}

	public boolean getSand(double val) {
		return (val <= 0.50);
	}

	public boolean getSoil(double val) {
		return (val <= 0.75);
	}

	public void setBaseMap() {
		for (int i = 0; i < 25 * 25; i++) {
			baseMap[i] = getGraphics(i);
		}
	}

	public void setPerspecMap() {
		perspecMap = new char[25 * 25];
		for (double theta = 0; theta < Math.PI * 2; theta += 0.01) {
			int n = 0;
			double cx = playerX;
			double cy = playerY;
			double maxHeight = -100;
			while (n < 25) {
				n++;
				if (cx >= 25) {
					break;
				}
				if (cy >= 25) {
					break;
				}
				if (cx < 0) {
					break;
				}
				if (cy < 0) {
					break;
				}
				if (trueMap[(int) cx + (int) cy * 25] > maxHeight) {
					maxHeight = trueMap[(int) cx + (int) cy * 25];
				}
				if (Math.abs(maxHeight - trueMap[(int) cx + (int) cy * 25]) < 0.25) {
					perspecMap[(int) cx + (int) cy * 25] = trueMap[(int) cx + (int) cy * 25];
				}
				cx += Math.cos(theta);
				cy += Math.sin(theta);
			}
		}
		perspecMap[playerX + playerY * 25] = '@';
	}

	public char getGraphics(int index) {
		double value = table[index];

		if (getRock(value)) {
			return 'n';
		}
		if (getSand(value)) {
			return '.';
		}
		if (getSoil(value)) {
			return '_';
		}
		return '=';

	}

	public double pos(double num) {
		if (num < 0.0) {
			return 0.0;
		}
		return num;
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

	public char[] getTrueMap() {
		// TODO Auto-generated method stub
		return trueMap;
	}

}

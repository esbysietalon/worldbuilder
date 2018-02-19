package ascii;

public class Thing {

	private String[] tagList = new String[0];
	private static String lookup = "abcdefghijklmnopqrstuvwxyz0123456789";
	private TagList newTL;

	public Thing() {
		newTL = new TagList();
	}

	public void olderAddTag(String tag) {
		for (int i = 0; i < tagList.length; i++) {
			if (tag.equals(tagList[i]))
				return;
		}
		String[] temp = tagList.clone();
		tagList = new String[tagList.length + 1];
		System.arraycopy(temp, 0, tagList, 0, temp.length);
		tagList[tagList.length - 1] = tag;
		mergeSort(tagList, 0, tagList.length - 1);
	}

	public void addTag(String tag) {
		newTL.addNode(new TagListNode(tag));
	}

	public void copyInto(Thing thing) {
		newTL.copyTree(thing);
	}

	public void remTag(String tag) {
		// newTL.remNode(tag);
		newTL.remNodeTest(tag);
	}

	public String getValue(String tag) {
		return newTL.findTag(tag);
	}

	public void printTags() {
		newTL.printTags();
	}

	public void oldAddTag(String tag) {
		for (int i = 0; i < tagList.length; i++) {
			if (tag.equals(tagList[i]))
				return;
		}
		String[] temp = tagList.clone();
		tagList = new String[tagList.length + 1];
		System.arraycopy(temp, 0, tagList, 0, temp.length);
		tagList[tagList.length - 1] = tag;
	}

	void merge(String arr[], int l, int m, int r) {
		int i, j, k;
		int n1 = m - l + 1;
		int n2 = r - m;
		String[] L = new String[n1], R = new String[n2];
		for (i = 0; i < n1; i++)
			L[i] = arr[l + i];
		for (j = 0; j < n2; j++)
			R[j] = arr[m + 1 + j];
		i = 0;
		j = 0;
		k = l;
		while (i < n1 && j < n2) {
			if (compareAlphaValue(L[i], R[j]) <= 0) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}

	void mergeSort(String[] arr, int l, int r) {
		if (l < r) {
			int m = l + (r - l) / 2;
			mergeSort(arr, l, m);
			mergeSort(arr, m + 1, r);
			merge(arr, l, m, r);
		}
	}

	public void oldRemTag(String tag) {
		for (int i = 0; i < tagList.length; i++) {
			if (tagList[i].contains(tag)) {
				String[] temp1 = new String[i];
				System.arraycopy(tagList, 0, temp1, 0, temp1.length);
				String[] temp2 = new String[tagList.length - i - 1];
				System.arraycopy(tagList, i + 1, temp2, 0, temp2.length);
				tagList = new String[tagList.length - 1];
				System.arraycopy(temp1, 0, tagList, 0, temp1.length);
				System.arraycopy(temp2, 0, tagList, temp1.length, temp2.length);
			}
		}
	}

	public void printTree() {
		newTL.printTree();
	}

	public static int findInLookup(char a) {
		char[] lookupArray = lookup.toCharArray();
		for (int i = 0; i < lookupArray.length; i++) {
			if (lookupArray[i] == a)
				return i + 1;
		}
		return 0;
	}

	public String oldGetValue(String tag) {
		String output = "";
		for (int i = 0; i < tagList.length; i++) {
			if (tagList[i].substring(0, 4).equals(tag)) {
				return tagList[i].substring(5, tagList[i].length());
			}
		}
		return output;
	}

	public String getValueSorted(String tag) {
		int i = tagList.length / 2;
		int j = -1;
		int k = (i + 1) / 2;
		while (j != 0) {
			j = i;
			System.out.println(tagList[i].substring(0, 4));
			if (compareAlphaValue(tagList[i].substring(0, 4), tag) == 0) {
				return tagList[i];
			}
			if (compareAlphaValue(tagList[i].substring(0, 4), tag) > 0) {
				i -= k;
			}
			if (compareAlphaValue(tagList[i].substring(0, 4), tag) < 0) {
				i += k;
			}
			if (i >= tagList.length || j < 0) {
				return "NOTFOUND";
			}
			j = j - i;
			k = (k + 1) / 2;
		}
		if (compareAlphaValue(tagList[i].substring(0, 4), tag) == 0) {
			return tagList[i];
		} else {
			return "NOTFOUND";
		}
	}

	public static int compareAlphaValue(String s1, String s2) {
		int i = 0;
		while (i < Map.max(s1.length(), s2.length())) {
			if (i >= s1.length()) {
				return -1;
			}
			if (i >= s2.length()) {
				return 1;
			}
			if (findInLookup(s1.toLowerCase().charAt(i)) > findInLookup(s2.toLowerCase().charAt(i))) {
				return 1;
			}
			if (findInLookup(s1.toLowerCase().charAt(i)) < findInLookup(s2.toLowerCase().charAt(i))) {
				return -1;
			}
			i++;
		}
		return 0;
	}

	public String[] getTags() {
		return tagList;
	}

	public void buildPerception(Thing thing) {
		int x = Integer.parseInt(getValue("posX"));
		int y = Integer.parseInt(getValue("posY"));
		int thingX = Integer.parseInt(thing.getValue("posX"));
		int thingY = Integer.parseInt(thing.getValue("posY"));
		int dx = thingX - x;
		int dy = thingY - y;
		if (getValue("msen").substring(5).equals("eyes")) {
			int range = Integer.parseInt(getValue("eyes").substring(10, 14));
			int reso = Integer.parseInt(getValue("eyes").substring(20, 24));
			int cone = Integer.parseInt(getValue("eyes").substring(30, 34));
			if (Map.dist(dx, dy) < range) {
				int orie = Integer.parseInt(getValue("orie").substring(5, 9));
				int lb = orie - cone / 2;
				int ub = orie + cone / 2;
				int angle = (int) (Math.atan2(dy, dx) / Math.PI * 180);
				if (angle >= lb && angle <= ub) {
					int perX = (int) (thingX + ((Map.generateRandomRuntime() > 0.5) ? -1 : 1)
							* Map.generateRandomRuntime() * thingX * reso / 100.0);
					int perY = (int) (thingY + ((Map.generateRandomRuntime() > 0.5) ? -1 : 1)
							* Map.generateRandomRuntime() * thingY * reso / 100.0);
					if (Map.checkLOS()) {
						newTL.addNode(new TagListNode("perc_seen_" + thing.getValue("unid") + "_"
								+ Map.generateNumberTag(perX) + "_" + Map.generateNumberTag(perY)));
					}
				}
			}
		}
		if (getValue("msen").substring(5).equals("feel")) {
			int range = Integer.parseInt(getValue("feel").substring(10, 14));
			int reso = Integer.parseInt(getValue("feel").substring(20, 24));
			if (Map.dist(dx, dy) < range) {
				int perX = (int) (thingX + ((Map.generateRandomRuntime() > 0.5) ? -1 : 1) * Map.generateRandomRuntime()
						* thingX * reso / 100.0);
				int perY = (int) (thingY + ((Map.generateRandomRuntime() > 0.5) ? -1 : 1) * Map.generateRandomRuntime()
						* thingY * reso / 100.0);
				if (Map.checkLOS()) {
					newTL.addNode(new TagListNode("perc_felt_" + thing.getValue("unid") + "_"
							+ Map.generateNumberTag(perX) + "_" + Map.generateNumberTag(perY)));
				}
			}
		}
		if (getValue("msen").substring(5).equals("nose")) {
			int range = Integer.parseInt(getValue("nose").substring(10, 14));
			int reso = Integer.parseInt(getValue("nose").substring(20, 24));
			if (Map.dist(dx, dy) < range) {
				int perX = (int) (thingX + ((Map.generateRandomRuntime() > 0.5) ? -1 : 1) * Map.generateRandomRuntime()
						* thingX * reso / 100.0);
				int perY = (int) (thingY + ((Map.generateRandomRuntime() > 0.5) ? -1 : 1) * Map.generateRandomRuntime()
						* thingY * reso / 100.0);
				if (Map.checkLOS()) {
					newTL.addNode(new TagListNode("perc_smlt_" + thing.getValue("unid") + "_"
							+ Map.generateNumberTag(perX) + "_" + Map.generateNumberTag(perY)));
				}
			}
		}
		if (getValue("msen").substring(5).equals("ears")) {
			int range = Integer.parseInt(getValue("ears").substring(10, 14));
			int reso = Integer.parseInt(getValue("ears").substring(20, 24));
			if (Map.dist(dx, dy) < range) {
				int perX = (int) (thingX + ((Map.generateRandomRuntime() > 0.5) ? -1 : 1) * Map.generateRandomRuntime()
						* thingX * reso / 100.0);
				int perY = (int) (thingY + ((Map.generateRandomRuntime() > 0.5) ? -1 : 1) * Map.generateRandomRuntime()
						* thingY * reso / 100.0);
				if (Map.checkLOS()) {
					newTL.addNode(new TagListNode("perc_eard_" + thing.getValue("unid") + "_"
							+ Map.generateNumberTag(perX) + "_" + Map.generateNumberTag(perY)));
				}
			}
		}
		if (getValue("ssen").substring(5).equals("eyes")) {
			int range = Integer.parseInt(getValue("eyes").substring(10, 14));
			int reso = Integer.parseInt(getValue("eyes").substring(20, 24));
			int cone = Integer.parseInt(getValue("eyes").substring(30, 34));
			if (Map.dist(dx, dy) < range) {
				int orie = Integer.parseInt(getValue("orie").substring(5, 9));
				int lb = orie - cone / 2;
				int ub = orie + cone / 2;
				int angle = (int) (Math.atan2(dy, dx) / Math.PI * 180);
				if (angle >= lb && angle <= ub) {
					int perX = (int) (thingX + ((Map.generateRandomRuntime() > 0.5) ? -1 : 1)
							* Map.generateRandomRuntime() * thingX * reso / 100.0);
					int perY = (int) (thingY + ((Map.generateRandomRuntime() > 0.5) ? -1 : 1)
							* Map.generateRandomRuntime() * thingY * reso / 100.0);
					if (Map.checkLOS()) {
						newTL.addNode(new TagListNode("perc_seen_" + thing.getValue("unid") + "_"
								+ Map.generateNumberTag(perX) + "_" + Map.generateNumberTag(perY)));
					}
				}
			}
		}
		if (getValue("ssen").substring(5).equals("feel")) {
			int range = Integer.parseInt(getValue("feel").substring(10, 14));
			int reso = Integer.parseInt(getValue("feel").substring(20, 24));
			if (Map.dist(dx, dy) < range) {
				int perX = (int) (thingX + ((Map.generateRandomRuntime() > 0.5) ? -1 : 1) * Map.generateRandomRuntime()
						* thingX * reso / 100.0);
				int perY = (int) (thingY + ((Map.generateRandomRuntime() > 0.5) ? -1 : 1) * Map.generateRandomRuntime()
						* thingY * reso / 100.0);
				if (Map.checkLOS()) {
					newTL.addNode(new TagListNode("perc_felt_" + thing.getValue("unid") + "_"
							+ Map.generateNumberTag(perX) + "_" + Map.generateNumberTag(perY)));
				}
			}
		}
		if (getValue("ssen").substring(5).equals("nose")) {
			int range = Integer.parseInt(getValue("nose").substring(10, 14));
			int reso = Integer.parseInt(getValue("nose").substring(20, 24));
			if (Map.dist(dx, dy) < range) {
				int perX = (int) (thingX + ((Map.generateRandomRuntime() > 0.5) ? -1 : 1) * Map.generateRandomRuntime()
						* thingX * reso / 100.0);
				int perY = (int) (thingY + ((Map.generateRandomRuntime() > 0.5) ? -1 : 1) * Map.generateRandomRuntime()
						* thingY * reso / 100.0);
				if (Map.checkLOS()) {
					newTL.addNode(new TagListNode("perc_smlt_" + thing.getValue("unid") + "_"
							+ Map.generateNumberTag(perX) + "_" + Map.generateNumberTag(perY)));
				}
			}
		}
		if (getValue("ssen").substring(5).equals("ears")) {
			int range = Integer.parseInt(getValue("ears").substring(10, 14));
			int reso = Integer.parseInt(getValue("ears").substring(20, 24));
			if (Map.dist(dx, dy) < range) {
				int perX = (int) (thingX + ((Map.generateRandomRuntime() > 0.5) ? -1 : 1) * Map.generateRandomRuntime()
						* thingX * reso / 100.0);
				int perY = (int) (thingY + ((Map.generateRandomRuntime() > 0.5) ? -1 : 1) * Map.generateRandomRuntime()
						* thingY * reso / 100.0);
				if (Map.checkLOS()) {
					newTL.addNode(new TagListNode("perc_eard_" + thing.getValue("unid") + "_"
							+ Map.generateNumberTag(perX) + "_" + Map.generateNumberTag(perY)));
				}
			}
		}

	}

	public String[] getAll(String tag) {
		return newTL.findAll(tag);
	}

	public void generateBehavior() {
		// food
		if (containsTag("anim")) {
			addTag("full_" + Integer.parseInt(getValue("size")));
		}
		if (containsTag("pnic")) {

		}
		//
		//
		//
	}

	public void updateStatus() {
		newTL.readAndUpdate();
		if (containsTag("anim_indv")) {
			if (containsTag("full")) {
				int fullness = Integer.parseInt(getValue("full"));
				if (fullness > 0) {
					remTag("full");
					addTag("full_" + Map.generateNumberTag(fullness - 1));
				} else {
					remTag("full");
					addTag("hngr_" + Map.generateNumberTag(Integer.parseInt(getValue("size"))));
				}
			}
		}
	}

	public boolean containsTag(String tag) {

		return newTL.containsTag(tag);
		/*
		 * for (int i = 0; i < tagList.length; i++) { if (tagList[i].contains(tag))
		 * return true; } return false;
		 */
	}

	public void oldPrintTags() {
		System.out.println();
		for (int i = 0; i < tagList.length; i++) {
			if (i != tagList.length - 1)
				System.out.print(tagList[i] + ", ");
			else
				System.out.println(tagList[i]);
		}
	}

}

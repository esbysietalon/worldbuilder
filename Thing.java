package ascii;

public class Thing {

	private String[] tagList = new String[0];

	public Thing() {

	}

	public void addTag(String tag) {
		for (int i = 0; i < tagList.length; i++) {
			if (tag.equals(tagList[i]))
				return;
		}
		String[] temp = tagList.clone();
		tagList = new String[tagList.length + 1];
		System.arraycopy(temp, 0, tagList, 0, temp.length);
		tagList[tagList.length - 1] = tag;
	}

	public void remTag(String tag) {
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

	public String getValue(String tag) {
		String output = "";
		for (int i = 0; i < tagList.length; i++) {
			if (tagList[i].substring(0, 4).equals(tag)) {
				return tagList[i].substring(5, tagList[i].length());
			}
		}
		return output;
	}

	public String[] getTags() {
		return tagList;
	}

	public void generateBehavior() {
		// food
		if (containsTag("anim")) {
			addTag("full_" + Integer.parseInt(getValue("size")));
		}
		if (containsTag("")) {
			
		}
		//
		//
		//
	}

	public void updateStatus() {
		if(containsTag("full")) {
			int fullness = Integer.parseInt(getValue("full"));
			if(fullness > 0) {
				remTag("full");
				addTag("full_" + Map.generateNumberTag(fullness - 1));
			}else {
				remTag("full");
				addTag("hungry_" + Map.generateNumberTag(Integer.parseInt(getValue("size"))));
			}
		}
	}
	
	public boolean containsTag(String tag) {
		for (int i = 0; i < tagList.length; i++) {
			if (tagList[i].contains(tag))
				return true;
		}
		return false;
	}

	public void printTags() {
		System.out.println();
		for (int i = 0; i < tagList.length; i++) {
			if (i != tagList.length - 1)
				System.out.print(tagList[i] + ", ");
			else
				System.out.println(tagList[i]);
		}
	}

}

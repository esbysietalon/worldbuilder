package ascii;

public class TagList {
	private TagListNode root;

	public TagList() {
		root = null;
	}

	private int getHeightHelper(TagListNode node) {
		if (node == null)
			return 0;
		return Map.max(getHeightHelper(node.getLeft()), getHeightHelper(node.getRight())) + 1;
	}

	private int getHeight(TagListNode node) {
		return getHeightHelper(node);
	}

	private int balanceFactor(TagListNode node) {
		int tentative = 0;
		if (node == null) {
			return tentative;
		}
		if (node.getLeft() != null) {
			tentative -= getHeight(node.getLeft());
		}
		if (node.getRight() != null) {
			tentative += getHeight(node.getRight());
		}
		return tentative;
	}

	private void rebalanceHelper(TagListNode node) {

		if (node == null)
			return;

		rebalanceHelper(node.getLeft());
		rebalanceHelper(node.getRight());
		int bf = balanceFactor(node);
		if (bf > 1) {
			int bff = balanceFactor(node.getRight());
			if (bff < 0) {
				rotate("right", "left", node);
			}
			if (bff > 0) {
				rotate("right", "right", node);
			}
		}
		if (bf < -1) {
			int bff = balanceFactor(node.getLeft());
			if (bff > 0) {
				rotate("left", "right", node);
			}
			if (bff < 0) {
				rotate("left", "left", node);
			}
		}

	}

	private void rebalance() {
		rebalanceHelper(root);
	}

	private void rebalanceTest() {
		rebalanceHelperTest(root);
	}

	private void rebalanceHelperTest(TagListNode node) {
		if (node == null) {
			return;
		}
		rebalanceHelperTest(node.getLeft());
		rebalanceHelperTest(node.getRight());
		int bf = balanceFactor(node);
		if (bf < -1) {
			int bff = balanceFactor(node.getLeft());
			if (bff > 0) {
				rotateTest("left", "right", node);
			} else if (bff < 0) {
				rotateTest("left", "left", node);
			}
		} else if (bf > 1) {
			int bff = balanceFactor(node.getRight());
			if (bff > 0) {
				rotateTest("right", "right", node);
			} else if (bff < 0) {
				rotateTest("right", "left", node);
			}
		}

	}

	private void rotateTest(String dir1, String dir2, TagListNode node) {
		TagListNode one = node;
		TagListNode oneP = one.getParent();
		int onePRel = 0;
		if (oneP != null) {
			if (oneP.getLeft() != null && oneP.getLeft().getTag().equals(one.getTag())) {
				onePRel = 1;
			} else {
				onePRel = 2;
			}
		}
		if (dir1.equals("left") && dir2.equals("left")) {
			TagListNode two = node.getLeft();
			TagListNode twoR = two.getRight();
			switch (onePRel) {
			case 0:
				root = two;
				two.setParent(null);
				one.setLeft(twoR);
				if (twoR != null)
					twoR.setParent(one);
				one.setParent(two);
				two.setRight(one);
				break;
			case 1:
				oneP.setLeft(two);
				two.setParent(oneP);
				one.setLeft(twoR);
				if (twoR != null)
					twoR.setParent(one);
				two.setRight(one);
				one.setParent(two);
				break;
			case 2:
				oneP.setRight(two);
				two.setParent(oneP);
				one.setLeft(twoR);
				if (twoR != null)
					twoR.setParent(one);
				two.setRight(one);
				one.setParent(two);
				break;
			default:
				break;
			}
		}
		if (dir1.equals("left") && dir2.equals("right")) {
			TagListNode two = one.getLeft();
			TagListNode three = two.getRight();
			TagListNode threeL = three.getLeft();
			one.setLeft(three);
			three.setParent(one);
			two.setRight(threeL);
			if (threeL != null)
				threeL.setParent(two);
			three.setLeft(two);
			two.setParent(three);
			rotateTest("left", "left", node);
		}
		if (dir1.equals("right") && dir2.equals("right")) {
			TagListNode two = one.getRight();
			TagListNode twoL = two.getLeft();
			switch (onePRel) {
			case 0:
				root = two;
				two.setParent(null);
				one.setRight(twoL);
				if (twoL != null)
					twoL.setParent(one);
				two.setLeft(one);
				one.setParent(two);
				break;
			case 1:
				oneP.setLeft(two);
				two.setParent(oneP);
				one.setRight(twoL);
				if (twoL != null)
					twoL.setParent(one);
				two.setLeft(one);
				one.setParent(two);
				break;
			case 2:
				oneP.setRight(two);
				two.setParent(oneP);
				one.setRight(twoL);
				if (twoL != null)
					twoL.setParent(one);
				two.setLeft(one);
				one.setParent(two);
				break;
			default:
				break;
			}
		}
		if (dir1.equals("right") && dir2.equals("left")) {
			TagListNode two = one.getRight();
			TagListNode three = two.getLeft();
			TagListNode threeR = three.getRight();
			one.setRight(three);
			three.setParent(one);
			two.setLeft(threeR);
			if (threeR != null)
				threeR.setParent(two);
			three.setRight(two);
			two.setParent(three);
			rotateTest("right", "right", node);
		}
	}

	private void rotate(String dir1, String dir2, TagListNode node) {
		if (dir1.equals("right")) {
			if (dir2.equals("left")) {
				TagListNode one = node;
				TagListNode two = node.getRight();
				TagListNode three = node.getRight().getLeft();
				TagListNode threeR = node.getRight().getLeft().getRight();
				one.setRight(three);
				if (three != null)
					three.setParent(one);
				two.setLeft(threeR);
				if (threeR != null)
					threeR.setParent(two);
				if (three != null)
					three.setRight(two);
				two.setParent(three);
			}
			if (dir2.equals("right")) {
				TagListNode one = node;
				TagListNode two = node.getRight();
				TagListNode twoL = node.getRight().getLeft();
				if (one.getParent() != null) {
					if (one.getParent().getLeft() != null && one.getParent().getLeft().getTag().equals(one.getTag())) {
						one.getParent().setLeft(two);
						two.setParent(one.getParent());
					}
					if (one.getParent().getRight() != null
							&& one.getParent().getRight().getTag().equals(one.getTag())) {
						one.getParent().setRight(two);
						two.setParent(one.getParent());
					}
				} else {
					root = two;
					two.setParent(null);
				}
				one.setRight(twoL);
				if (twoL != null)
					twoL.setParent(one);
				two.setLeft(one);
				one.setParent(two);
			}
		}
		if (dir1.equals("left")) {
			if (dir2.equals("right")) {
				TagListNode one = node;
				TagListNode two = node.getLeft();
				TagListNode three = node.getLeft().getRight();
				TagListNode threeL = node.getLeft().getRight().getLeft();
				one.setLeft(three);
				if (three != null)
					three.setParent(one);
				two.setRight(threeL);
				if (threeL != null)
					threeL.setParent(two);
				if (three != null)
					three.setLeft(two);
				two.setParent(three);

			}
			if (dir2.equals("left")) {

				TagListNode one = node;

				TagListNode two = node.getLeft();

				TagListNode twoR = node.getLeft().getRight();

				if (one.getParent() != null) {
					if (one.getParent().getLeft() != null && one.getParent().getLeft().getTag().equals(one.getTag())) {
						one.getParent().setLeft(two);
						two.setParent(one.getParent());
					}
					if (one.getParent().getRight() != null
							&& one.getParent().getRight().getTag().equals(one.getTag())) {
						one.getParent().setRight(two);
						two.setParent(one.getParent());
					}
				} else {
					root = two;
					two.setParent(null);
				}

				one.setLeft(twoR);
				if (twoR != null)
					twoR.setParent(one);
				two.setRight(one);
				one.setParent(two);

			}
		}
		rebalance();
	}

	public void printTree() {
		System.out.println();
		printTreeHelper(root);
		System.out.println();
	}

	private void printTreeHelper(TagListNode node) {
		if (node == null)
			return;
		// System.out.print("LEFT START " + node.getTag());
		printTreeHelper(node.getLeft());
		// System.out.print("LEFT END " + node.getTag());
		System.out.println();
		if (node.getLeft() != null) {
			System.out.print(node.getLeft().getTag());
		} else {
			System.out.print("NULL");
		}

		System.out.print(" <-" + node.getTag() + "-> ");
		if (node.getRight() != null) {
			System.out.print(node.getRight().getTag());
		} else {
			System.out.print("NULL");
		}
		System.out.println();
		// System.out.print("RIGHT START " + node.getTag());
		printTreeHelper(node.getRight());
		// System.out.print("RIGHT END " + node.getTag());
	}

	private void placeNode(TagListNode node, TagListNode newNode) {
		if (node == null) {
			root = newNode;
			return;
		}
		if (Thing.compareAlphaValue(node.getTag(), newNode.getTag()) == 0) {
			return;
		}
		if (Thing.compareAlphaValue(node.getTag(), newNode.getTag()) > 0) {
			if (node.getLeft() != null) {
				placeNode(node.getLeft(), newNode);
				return;
			} else {
				node.setLeft(newNode);
				newNode.setParent(node);
				return;
			}
		}
		if (Thing.compareAlphaValue(node.getTag(), newNode.getTag()) < 0) {
			if (node.getRight() != null) {
				placeNode(node.getRight(), newNode);
				return;
			} else {
				node.setRight(newNode);
				newNode.setParent(node);
				return;
			}
		}

	}

	public void addNode(TagListNode node) {
		placeNode(root, node);
		// rebalanceTest();
		// rebalance();
	}

	private String findTagHelper(TagListNode node, String tag) {
		if (node == null) {
			return null;
		}
		if (Thing.compareAlphaValue(node.getTag().substring(0, 4), tag) == 0) {
			if (node.isActive()) {
				return node.getTag();
			} else {
				return null;
			}
		}
		if (Thing.compareAlphaValue(node.getTag(), tag) > 0) {
			if (node.getLeft() != null) {
				return findTagHelper(node.getLeft(), tag);
			} else {
				return null;
			}
		}
		if (Thing.compareAlphaValue(node.getTag(), tag) < 0) {
			if (node.getRight() != null) {
				return findTagHelper(node.getRight(), tag);
			} else {
				return null;
			}
		}
		return null;
	}

	private TagListNode remNodeHelper(TagListNode node, String tag) {
		if (Thing.compareAlphaValue(node.getTag(), tag) == 0 && node.isActive()) {
			return node;
		}
		if (Thing.compareAlphaValue(node.getTag(), tag) > 0) {
			if (node.getLeft() != null) {
				return remNodeHelper(node.getLeft(), tag);
			} else {
				return null;
			}
		}
		if (Thing.compareAlphaValue(node.getTag(), tag) < 0) {
			if (node.getRight() != null) {
				return remNodeHelper(node.getRight(), tag);
			} else {
				return null;
			}
		}
		return null;
	}

	public void remNodeNewTest(String tag) {
		TagListNode remNode = remTestHelper(root, tag);
		if (remNode == null) {
			return;
		}

		if (remNode.getRight() != null) {
			TagListNode mostLeftRightNode = remNode.getRight();
			int n = 0;
			while (mostLeftRightNode.getLeft() != null) {
				mostLeftRightNode = mostLeftRightNode.getLeft();
				n++;
			}
			if (remNode.getParent() != null) {
				if (remNode.getParent().getLeft() != null
						&& remNode.getParent().getLeft().getTag().equals(remNode.getTag())) {
					remNode.getParent().setLeft(mostLeftRightNode);
				} else if (remNode.getParent().getRight() != null
						&& remNode.getParent().getRight().getTag().equals(remNode.getTag())) {
					remNode.getParent().setRight(mostLeftRightNode);
				}
			} else {
				
				root = mostLeftRightNode;
				
			}
			TagListNode remNodeRight = remNode.getRight();
			TagListNode remNodeLeft = remNode.getLeft();
			if (n == 0) {
				mostLeftRightNode.getParent().setRight(mostLeftRightNode.getRight());
				if (mostLeftRightNode.getRight() != null) {
					mostLeftRightNode.getRight().setParent(mostLeftRightNode.getParent());
				}
				mostLeftRightNode.getParent().setLeft(mostLeftRightNode.getLeft());
				if (mostLeftRightNode.getLeft() != null) {
					mostLeftRightNode.getLeft().setParent(mostLeftRightNode.getParent());
				}
			} else {
				if (mostLeftRightNode.getParent() != null)
					mostLeftRightNode.getParent().setLeft(mostLeftRightNode.getRight());
				if (mostLeftRightNode.getRight() != null) {
					mostLeftRightNode.getRight().setParent(mostLeftRightNode.getParent());
				}
			}

			mostLeftRightNode.setParent(remNode.getParent());
			if (n != 0) {
				mostLeftRightNode.setRight(remNodeRight);
				if (mostLeftRightNode.getRight() != null)
					mostLeftRightNode.getRight().setParent(mostLeftRightNode);
			} else {
				mostLeftRightNode.setRight(remNodeRight.getRight());
				if(mostLeftRightNode.getRight() != null)
					mostLeftRightNode.getRight().setParent(mostLeftRightNode);
			}
			mostLeftRightNode.setLeft(remNodeLeft);
			if (mostLeftRightNode.getLeft() != null) {
				mostLeftRightNode.getLeft().setParent(mostLeftRightNode);
			}
			remNode = null;
		} else if (remNode.getLeft() != null) {
			TagListNode mostRightLeftNode = remNode.getLeft();
			int n = 0;
			while (mostRightLeftNode.getRight() != null) {
				mostRightLeftNode = mostRightLeftNode.getRight();
				n++;
			}
			if (remNode.getParent() != null) {
				if (remNode.getParent().getRight() != null
						&& remNode.getParent().getRight().getTag().equals(remNode.getTag())) {
					remNode.getParent().setRight(mostRightLeftNode);
				} else if (remNode.getParent().getLeft() != null
						&& remNode.getParent().getLeft().getTag().equals(remNode.getTag())) {
					remNode.getParent().setLeft(mostRightLeftNode);
				}
			} else {
				
				root = mostRightLeftNode;
				
			}
			TagListNode remNodeLeft = remNode.getLeft();
			TagListNode remNodeRight = remNode.getRight();
			if (n == 0) {
				mostRightLeftNode.getParent().setLeft(mostRightLeftNode.getLeft());
				if (mostRightLeftNode.getLeft() != null) {
					mostRightLeftNode.getLeft().setParent(mostRightLeftNode.getParent());
				}
				mostRightLeftNode.getParent().setRight(mostRightLeftNode.getRight());
				if (mostRightLeftNode.getRight() != null) {
					mostRightLeftNode.getRight().setParent(mostRightLeftNode.getParent());
				}
			} else {
				if (mostRightLeftNode.getParent() != null)
					mostRightLeftNode.getParent().setRight(mostRightLeftNode.getLeft());
				if (mostRightLeftNode.getLeft() != null) {
					mostRightLeftNode.getLeft().setParent(mostRightLeftNode.getParent());
				}
			}

			mostRightLeftNode.setParent(remNode.getParent());
			if (n != 0) {
				mostRightLeftNode.setLeft(remNodeLeft);
				if (mostRightLeftNode.getLeft() != null)
					mostRightLeftNode.getLeft().setParent(mostRightLeftNode);
			} else {
				mostRightLeftNode.setLeft(remNodeLeft.getLeft());
				if(mostRightLeftNode.getLeft() != null)
					mostRightLeftNode.getLeft().setParent(mostRightLeftNode);
			}
			mostRightLeftNode.setRight(remNodeRight);
			if (mostRightLeftNode.getRight() != null) {
				mostRightLeftNode.getRight().setParent(mostRightLeftNode);
			}
			remNode = null;

		} else {
			if (remNode.getParent() != null) {
				if (remNode.getParent().getLeft() != null
						&& remNode.getParent().getLeft().getTag().equals(remNode.getTag())) {
					remNode.getParent().setLeft(null);
				} else if (remNode.getParent().getRight() != null
						&& remNode.getParent().getRight().getTag().equals(remNode.getTag())) {
					remNode.getParent().setRight(null);
				}
			} else {
				root = null;
			}
		}
		// printTree();
		rebalanceTest();
	}

	private void copyTreeHelper(Thing thing, TagListNode node) {
		if (node == null)
			return;
		copyTreeHelper(thing, node.getLeft());
		thing.addTag(node.getTag());
		copyTreeHelper(thing, node.getRight());
	}

	public void copyTree(Thing thing) {
		copyTreeHelper(thing, root);
	}

	private TagListNode remTestHelper(TagListNode node, String tag) {
		if (node == null) {
			return null;
		}
		if (tag.length() == 4) {
			if (node.getTag().substring(0, 4).equals(tag)) {
				return node;
			}
		} else {
			if (node.getTag().equals(tag)) {
				return node;
			}
		}
		if (Thing.compareAlphaValue(node.getTag(), tag) > 0) {
			if (node.getLeft() != null)
				return remTestHelper(node.getLeft(), tag);
		}
		if (Thing.compareAlphaValue(node.getTag(), tag) < 0) {
			if (node.getRight() != null)
				return remTestHelper(node.getRight(), tag);
		}
		return null;
	}

	public String findTag(String tag) {
		return findTagHelper(root, tag);
	}

	public String[] findAll(String tag) {
		StringBuilder arr = new StringBuilder();
		findAllHelper(root, tag, arr, false);
		String[] array = arr.toString().split(":");
		String[] newArr = new String[0];

		for (int i = 0; i < array.length; i++) {
			if (!array[i].equals("")) {
				String[] temp = newArr.clone();
				newArr = new String[newArr.length + 1];
				System.arraycopy(temp, 0, newArr, 0, temp.length);
				newArr[newArr.length - 1] = array[i];
			}
		}
		return newArr;
	}

	private void findAllHelper(TagListNode node, String tag, StringBuilder arr, boolean found) {
		if (node == null) {
			return;
		}
		if (node.getTag().substring(0, 4).equals(tag)) {
			arr.append(node.getTag() + ":");
			findAllHelper(node.getLeft(), tag, arr, true);
			findAllHelper(node.getRight(), tag, arr, true);
		} else {
			if (Thing.compareAlphaValue(node.getTag(), tag) > 0) {
				if (found) {
					return;
				} else {
					findAllHelper(node.getLeft(), tag, arr, false);
				}
			}
			if (Thing.compareAlphaValue(node.getTag(), tag) < 0) {
				if (found) {
					return;
				} else {
					findAllHelper(node.getRight(), tag, arr, false);
				}
			}
		}
	}

	private void printTagHelper(TagListNode node) {
		if (node == null)
			return;
		if (node.getLeft() != null) {
			printTagHelper(node.getLeft());
		}
		if (node.isActive()) {
			System.out.print(node.getTag() + " ");
		}
		if (node.getRight() != null) {
			printTagHelper(node.getRight());
		}
	}

	public void readAndUpdate() {
		if (containsTag("move")) {
			int x = Integer.parseInt(findTag("posx").substring(5));
			int y = Integer.parseInt(findTag("posy").substring(5));
			x += Integer.parseInt(findTag("move").substring(5, 9));
			y += Integer.parseInt(findTag("move").substring(10));

			remNodeNewTest("posx");
			remNodeNewTest("posy");
			remNodeNewTest("move");

			addNode(new TagListNode("posx_" + Map.generateNumberTag(x)));
			addNode(new TagListNode("posy_" + Map.generateNumberTag(y)));
		}
	}

	private boolean containsTagHelper(TagListNode node, String tag) {
		if (node == null) {
			return false;
		}
		if (node.getTag().contains(tag)) {
			return true;
		} else {
			if (Thing.compareAlphaValue(node.getTag(), tag) > 0) {
				return containsTagHelper(node.getLeft(), tag);
			}
			if (Thing.compareAlphaValue(node.getTag(), tag) < 0) {
				return containsTagHelper(node.getRight(), tag);
			}
		}
		return false;
	}

	public boolean containsTag(String tag) {
		return containsTagHelper(root, tag);
	}

	public void printTags() {

		System.out.println();
		printTagHelper(root);
		System.out.println();
	}
}

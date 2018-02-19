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
		rebalanceHelper(node.getLeft());
		rebalanceHelper(node.getRight());
	}

	private void rebalance() {
		rebalanceHelper(root);
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
		printTreeHelper(node.getLeft());
		System.out.println();
		if (node.getLeft() != null) {
			System.out.print(node.getLeft().getTag());
		} else {
			System.out.print("NULL");
		}
		System.out.print(" <-" + node.getTag() + "(" + balanceFactor(node) + ")" + "-> ");
		if (node.getRight() != null) {
			System.out.print(node.getRight().getTag());
		} else {
			System.out.print("NULL");
		}
		System.out.println();
		printTreeHelper(node.getRight());
	}

	private void placeNode(TagListNode node, TagListNode newNode) {
		if (node == null) {
			root = newNode;
			return;
		}
		if (Thing.compareAlphaValue(node.getTag(), newNode.getTag()) == 0) {
			if (node.isActive()) {
				return;
			} else {
				node.setActive(true);
				return;
			}
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
		rebalance();
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

	public void remNode(String tag) {
		TagListNode result = remNodeHelper(root, tag);
		if (result != null) {
			result.setActive(false);
		}
	}

	public void remNodeTest(String tag) {
		TagListNode remNode = remTestHelper(root, tag);

		if (remNode == null) {
			return;
		}

		if (balanceFactor(remNode) < 0) {

			TagListNode parent = remNode.getParent();
			if (parent != null) {

				if (parent.getLeft() != null && parent.getLeft().getTag().equals(remNode.getTag())) {
					parent.setLeft(remNode.getLeft());

					if (remNode.getLeft() != null) {

						remNode.getLeft().setParent(parent);
						if (remNode.getRight() != null) {

							remNode.getRight().setLeft(remNode.getLeft().getRight());
							remNode.getLeft().setRight(remNode.getRight());
							remNode.getRight().setParent(remNode.getLeft());
						}
					}
				}
				if (parent.getRight() != null && parent.getRight().getTag().equals(remNode.getTag())) {

					parent.setRight(remNode.getLeft());
					if (remNode.getLeft() != null) {
						remNode.getLeft().setParent(parent);
						if (remNode.getRight() != null) {

							remNode.getRight().setLeft(remNode.getLeft().getRight());
							remNode.getLeft().setRight(remNode.getRight());
							remNode.getRight().setParent(remNode.getLeft());
						}
					}
				}
			} else {

				root = remNode.getLeft();
				if (remNode.getLeft() != null)
					remNode.getLeft().setParent(null);
				if (remNode.getLeft() != null) {

					if (remNode.getRight() != null) {
						remNode.getRight().setLeft(remNode.getLeft().getRight());
						remNode.getLeft().setRight(remNode.getRight());
						remNode.getRight().setParent(remNode.getLeft());
					}
				}
			}
		} else {

			TagListNode parent = remNode.getParent();
			if (parent != null) {

				if (parent.getLeft() != null && parent.getLeft().getTag().equals(remNode.getTag())) {
					parent.setLeft(remNode.getRight());

					if (remNode.getRight() != null) {

						remNode.getRight().setParent(parent);
						if (remNode.getLeft() != null) {
							remNode.getLeft().setRight(remNode.getRight().getLeft());
							remNode.getRight().setLeft(remNode.getLeft());
							remNode.getLeft().setParent(remNode.getRight());
						}
					} else {

						remNode.getParent().setLeft(null);

						remNode.setParent(null);
					}
				}
				if (parent.getRight() != null && parent.getRight().getTag().equals(remNode.getTag())) {

					parent.setRight(remNode.getRight());

					if (remNode.getRight() != null) {
						remNode.getRight().setParent(parent);
						if (remNode.getLeft() != null) {

							remNode.getLeft().setRight(remNode.getRight().getLeft());
							remNode.getRight().setLeft(remNode.getLeft());
							remNode.getLeft().setParent(remNode.getRight());
						}
					} else {

						if (remNode.getParent() != null)
							remNode.getParent().setRight(null);
						remNode.setParent(null);

					}
				}
			} else {

				root = remNode.getRight();
				if (remNode.getRight() != null)
					remNode.getRight().setParent(null);
				if (remNode.getRight() != null) {

					if (remNode.getLeft() != null) {
						remNode.getLeft().setRight(remNode.getRight().getLeft());
						remNode.getRight().setLeft(remNode.getLeft());
						remNode.getLeft().setParent(remNode.getRight());
					}
				} else {

					root = null;
				}
			}
		}

		rebalance();
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
		if (node.getTag().contains(tag)) {
			return node;
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
		return arr.toString().split(":");
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

			remNodeTest("posx");
			remNodeTest("posy");
			remNodeTest("move");

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

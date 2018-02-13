package ascii;

public class TagList {
	private TagListNode root;

	public TagList() {
		root = null;
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
				return;
			}
		}
		if (Thing.compareAlphaValue(node.getTag(), newNode.getTag()) < 0) {
			if (node.getRight() != null) {
				placeNode(node.getRight(), newNode);
				return;
			} else {
				node.setRight(newNode);
				return;
			}
		}

	}

	public void addNode(TagListNode node) {
		placeNode(root, node);

	}

	private String findTagHelper(TagListNode node, String tag) {
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

	public String findTag(String tag) {
		return findTagHelper(root, tag);
	}

	private void printTagHelper(TagListNode node) {
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

	private boolean containsTagHelper(TagListNode node, String tag) {
		if (node == null) {
			return false;
		}
		if (node.isActive() && node.getTag().contains(tag)) {
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

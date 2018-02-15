package ascii;

public class TagListNode {
	private String tag;
	private TagListNode left, right, parent;
	private boolean active;
	public TagListNode(String tag) {
		this.tag = tag;
		left = null;
		right = null;
		parent = null;
		active = true;
	}
	public boolean isActive() {
		return active;
	}
	public void setParent(TagListNode node) {
		parent = node;
	}
	public TagListNode getParent() {
		return parent;
	}
	public void setActive(boolean bool) {
		active = bool;
	}
	public void setLeft(TagListNode node) {
		left = node;
	}
	public void setRight(TagListNode node) {
		right = node;
	}
	public TagListNode getLeft() {
		return left;
	}
	public TagListNode getRight() {
		return right;
	}
	public String getTag() {
		return tag;
	}
}

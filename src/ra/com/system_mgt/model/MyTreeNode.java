package ra.com.system_mgt.model;

import java.util.ArrayList;

public class MyTreeNode {
	private String id;
	private String text;
	private ArrayList<MyTreeNode> children;
	private String checked;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public ArrayList<MyTreeNode> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<MyTreeNode> children) {
		this.children = children;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	
	
}

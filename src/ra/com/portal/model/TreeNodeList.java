package ra.com.portal.model;

public class TreeNodeList {
	private long id;
	private String name;
	private String state;
	private long childrenId;
	private String childrenName;
	private String action;
	private String type;
	private String layoutId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getChildrenId() {
		return childrenId;
	}
	public void setChildrenId(long childrenId) {
		this.childrenId = childrenId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getChildrenName() {
		return childrenName;
	}
	public void setChildrenName(String childrenName) {
		this.childrenName = childrenName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLayoutId() {
		return layoutId;
	}
	public void setLayoutId(String layoutId) {
		this.layoutId = layoutId;
	}
	
	

}

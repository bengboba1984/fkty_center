package ra.com.common.model;

public class KeyValueImpl implements KeyValue, java.io.Serializable {
	private String key;
	private String value;
	public KeyValueImpl(String key, String value) {
		this.key = key;
		this.value = value;
	}
	public String getKey() { return key; }
	public String getValue() { return value; }
}
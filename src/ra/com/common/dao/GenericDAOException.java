package ra.com.common.dao;

public class GenericDAOException extends Exception {
	String sql = null;

	public GenericDAOException(String str, Throwable e) {
		super(str, e);
	}

	public GenericDAOException(String str, String sql, Throwable e) {
		super(str, e);
		this.sql = sql;
	}

	public GenericDAOException(String str) {
		super(str);
	}

	public GenericDAOException(String str, String sql) {
		super(str);
		this.sql = sql;
	}

	public GenericDAOException() {
		super();
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
}

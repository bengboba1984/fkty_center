package ra.com.common.dao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import ra.com.common.connection.DBConnection;
import ra.com.common.model.KeyValueImpl;
import ra.com.common.model.ListChunk;

abstract public class GenericDAO implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7285394234058957587L;
	/**
	 * 
	 */

	private Connection conn;
	private static int WARNING_EVERY_N_RECORDS = 5000;
	private static int ALLOWED_MAX_RECORDS = 40000;

	public GenericDAO() throws GenericDAOException {

	}

	public Connection getConnection() {
		return DBConnection.getConnection();
	}

	private static String toString(Object obj) {
		return (obj == null) ? "" : obj.toString();
	}

	private static Object cast(Class type, ResultSet rs, int index)
			throws SQLException {
		Object value;
		if (type == String.class) {
			String stupidElsa = rs.getString(index);
			value = (stupidElsa == null) ? null : stupidElsa.trim();
		} else if (type == java.math.BigDecimal.class) {
			value = rs.getBigDecimal(index);
		} else if ((type == int.class) || (type == Integer.class)) {
			value = new Integer(rs.getInt(index));
		} else if ((type == long.class) || (type == Long.class)) {
			value = new Long(rs.getLong(index));
		} else if ((type == float.class) || (type == Float.class)) {
			value = new Float(rs.getFloat(index));
		} else if ((type == boolean.class) || (type == Boolean.class)) {
			String temp = toString(rs.getString(index)).toUpperCase();
			value = new Boolean(("TRUE".equals(temp) || "Y".equals(temp)));
		} else if (type == java.util.Date.class) {
			value = (Date) rs.getDate(index);
		} else if (type == java.sql.Date.class) {
			value = rs.getDate(index);
		} else if (type == java.sql.Timestamp.class) {
			value = rs.getTimestamp(index);
		} else if (type == java.lang.Character.class) {
			value = rs.getString(index) == null ? null : new Character(rs
					.getString(index).charAt(0));
		} else {

			value = null;
		}

		if ((type == Integer.class) || (type == Long.class)
				|| (type == Float.class) || (type == java.util.Date.class)
				|| (type == java.sql.Timestamp.class)
				|| (type == Boolean.class)) {
			if (rs.wasNull())
				value = null;
		}

		return value;
	}

	public void begin() throws GenericDAOException {
		if (conn != null) {
			try {
				conn.setAutoCommit(false);
			} catch (SQLException e) {
				throw new GenericDAOException("can not begin transaction", e);
			}
		} else {
			throw new GenericDAOException("connection not opened!");
		}
	}

	public void commit() throws GenericDAOException {
		try {
			if (conn != null && !conn.getAutoCommit()) {
				conn.commit();
				conn.setAutoCommit(true);
			} else {
				if (conn == null) {
					throw new GenericDAOException("connection not opened!");
				} else {
					throw new GenericDAOException(
							"first begin then commit please!");
				}
			}
		} catch (SQLException e) {
			throw new GenericDAOException("can not commit transaction!", e);
		}
	}

	public void rollback() throws GenericDAOException {
		try {
			if (conn != null && !conn.getAutoCommit()) {
				conn.rollback();
				conn.setAutoCommit(true);
			} else {
				if (conn == null) {
					throw new GenericDAOException("connection not opened!");
				} else {
					throw new GenericDAOException(
							"first begin then rollback please!");
				}
			}
		} catch (SQLException e) {
			throw new GenericDAOException("can not rollback transaction!", e);
		}
	}

	private List convert(ResultSet rs) throws GenericDAOException {
		try {
			if (rs != null && rs.next()) {
				rs.beforeFirst();
				List retList = new ArrayList();
				ResultSetMetaData meta;
				try {
					meta = rs.getMetaData();

					int colCount = meta.getColumnCount();
					System.out.println("colCount=" + colCount);
					while (rs.next()) {
						Map recordMap = new HashMap();
						for (int i = 1; i < colCount + 1; i++) {
							String name = meta.getColumnName(i);
							Object value = rs.getObject(i);
					/*		System.out.println("column name=" + name
									+ " / value=" + value);*/
							recordMap.put(name, value == null ? "" : value);
						}
						retList.add(recordMap);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new GenericDAOException(
							"can not convert result set to list of map", e);
				}
				return retList;
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static String printSql(String sql, List params) {
		if (params == null)
			return "\n" + sql;
		StringBuffer outSql = new StringBuffer();
		StringTokenizer tokenizer = new StringTokenizer(sql, "?", true);
		Object[] pa = params.toArray();
		for (int i = 0; tokenizer.hasMoreTokens();) {
			String token = tokenizer.nextToken();
			if (token.equals("?")) {
				Object obj = pa[i++];
				outSql.append("'").append(obj).append("'");
			} else {
				outSql.append(token);
			}
		}
		return "\n" + outSql.toString();
	}

	// -------------------------------------------------------------------------

	private void setParameters(PreparedStatement stmt, List params)
			throws GenericDAOException {
		try {
			if (params != null && params.size() > 0) {
				Iterator it = params.iterator();
				int index = 1;
				while (it.hasNext()) {

					Object obj = it.next();
					// if (obj == null) {
					// stmt.setObject(index, "");
					// } else {
					stmt.setObject(index, obj);
					// }
					index++;
				}
			}
		} catch (SQLException e) {
			throw new GenericDAOException("Can not set parameters!", e);
		}
	}

	public void closeConnection(Connection tempConn) throws GenericDAOException {

		try {
			if (tempConn != null) {
				System.out.println("##close connection id="+tempConn.toString());
				tempConn.close();
			} else {
				throw new GenericDAOException("Can not close null connection");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new GenericDAOException("Can not close GenericDAO");
		}
	}

	public void closePreparedStatement(PreparedStatement pstm)
			throws GenericDAOException {

		try {
			if (pstm != null) {
				pstm.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new GenericDAOException("Can not close GenericDAO");
		}

	}

	public void closeStatement(Statement stm) throws GenericDAOException {

		try {
			if (stm != null) {
				stm.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new GenericDAOException("Can not close GenericDAO");
		}

	}

	public void closeResultSet(ResultSet rs) throws GenericDAOException {

		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new GenericDAOException("Can not close GenericDAO");
		}

	}

	public List query(String sql, List params) throws GenericDAOException {
		
		Connection tempConn = DBConnection.getConnection();
		System.out.println(printSql(sql, params));
		List result = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = tempConn.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			setParameters(pstm, params);
			rs = pstm.executeQuery();
			result = convert(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new GenericDAOException("Can not execute query", e);
		} finally {
			closeResultSet(rs);
			closePreparedStatement(pstm);
			closeConnection(tempConn);
		}
		return result;
	}

	public Object queryOne(String sql, List params) throws GenericDAOException {
		List list = query(sql, params);
		if (list == null || list.size() == 0) {
			System.out.println("GenericDAO : no data1");
			return null;
		} else {
			Map record = (Map) list.get(0);
			if (record != null && record.size() > 0) {
				return record.values().toArray()[0];
			} else {
				System.out.println("GenericDAO : no data2");
				return null;
			}
		}
	}

	public boolean simpleExecute(Connection thisConn, String sql, List params)
			throws GenericDAOException {
		System.out.println("sqL:"+printSql(sql, params));
		PreparedStatement pstm = null;
		Statement stm = null;
		boolean flag = false;

		try {
			if (params != null) {
				int dummy = -1;
				pstm = thisConn.prepareStatement(sql);
				setParameters(pstm, params);
				dummy = pstm.executeUpdate();
				if (dummy != -1)
					flag = true;
			} else {
				stm = thisConn.createStatement();
				flag = stm.execute(sql);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new GenericDAOException("Can not execute simpleExecute", e);
		} finally {
			closePreparedStatement(pstm);
			closeStatement(stm);
		}

		return flag;
	}
	public long insertDataForAutoKey(String sql, List params)
			throws GenericDAOException {
		System.out.println("sql="+printSql(sql, params));
		Connection tempConn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		Statement stm = null;
		boolean flag = false;

		try {
			if (params != null) {
				int dummy = -1;
				pstm = tempConn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
				setParameters(pstm, params);
				dummy = pstm.executeUpdate();
				ResultSet resultset = pstm.getGeneratedKeys();
				if (resultset != null && resultset.next()) {
					return resultset.getLong(1);
				}else{
					return Long.parseLong("-1");
				}
			} else {
				stm = tempConn.createStatement();
				flag = stm.execute(sql,Statement.RETURN_GENERATED_KEYS);
				 
				ResultSet resultset = stm.getGeneratedKeys();
				if (resultset != null && resultset.next()) {
					return resultset.getLong(1);
				}else{
					return Long.parseLong("-1");
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new GenericDAOException("Can not execute simpleExecute", e);
		} finally {
			closePreparedStatement(pstm);
			closeStatement(stm);
			closeConnection(tempConn);
		}

	}
	public boolean simpleExecute(String sql, List params)
			throws GenericDAOException {
		System.out.println("sql="+printSql(sql, params));
		Connection tempConn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		Statement stm = null;
		boolean flag = false;

		try {
			if (params != null) {
				int dummy = -1;
				pstm = tempConn.prepareStatement(sql);
				setParameters(pstm, params);
				dummy = pstm.executeUpdate();
				if (dummy != -1)
					flag = true;
			} else {
				stm = tempConn.createStatement();
				flag = stm.execute(sql);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new GenericDAOException("Can not execute simpleExecute", e);
		} finally {
			closePreparedStatement(pstm);
			closeStatement(stm);
			closeConnection(tempConn);
		}

		return flag;
	}

	public int[] simpleExecuteBatch(String sql, List paramsList)
			throws GenericDAOException {
		Connection tempConn = DBConnection.getConnection();
		PreparedStatement pstm = null;
		int[] result;
		try {
			if (paramsList != null) {
				pstm = tempConn.prepareStatement(sql);
				for (Iterator it = paramsList.iterator(); it.hasNext();) {
					List params = (List) it.next();
					setParameters(pstm, params);

					pstm.addBatch();
				}
				System.out
						.println("In simpleExecuteBatch: parameters set over");
				result = pstm.executeBatch();
			} else {
				throw new GenericDAOException("Parameter can not be Null");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new GenericDAOException("Can not execute simpleExecute", e);
		} finally {
			closePreparedStatement(pstm);
			closeConnection(tempConn);
		}

		return result;
	}

	protected ListChunk getListChunkByProperty(String qstr, List params,
			int pageNo, int pageSize, boolean needTotals, String className)
			throws GenericDAOException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList al = new ArrayList();
		System.out.println(printSql(qstr, params));
		int num = -1;
		int localCount = 0;
		int startIndex = (pageNo - 1) * pageSize;
		int count = pageSize;
		Connection tempConn=null;
		Method method;
		try {
			tempConn = DBConnection.getConnection();
			stmt = tempConn.prepareStatement(qstr, rs.TYPE_SCROLL_INSENSITIVE,
					rs.CONCUR_READ_ONLY);
			setParameters(stmt, params);

			rs = stmt.executeQuery();

			// get total count
			if (needTotals) {
				if (tempConn.getMetaData().getDriverName().indexOf("informix") != -1) {
					num = getCount(qstr, params); // removed by
													// Albert
													// 2004-04-22
				} else {
					// uese JDBC 2.0 to get count, forgot it, informix doesn't
					// support it.
					rs.last();
					num = rs.getRow();
					rs.beforeFirst();
				}
			}

			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();

			if (startIndex != 0)
				rs.absolute(startIndex);

			boolean firstRound = true;
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			 System.out.println("In GenericDAO.getListChunkByProperty : class name="+className);
			Class c = classLoader.loadClass(className);
			Method[] methods = c.getMethods();
			HashMap methodHm = new HashMap();
			for (int i = 0; i < methods.length; i++) {
//				 System.out.println("In GenericDAO.getListChunkByProperty : method["+i+"]="+methods[i].getName()+"/"+methods[i]);
				methodHm.put(methods[i].getName(), methods[i]);
			}
			Method[][] columnMethods = new Method[numberOfColumns][2];
			for (int i = 0; i < numberOfColumns; i++) {
//				 System.out.println("In GenericDAO.getListChunkByProperty : column name="+rsmd.getColumnName(i + 1));
				String methodName = toMethodName(rsmd.getColumnName(i + 1));
				columnMethods[i][0] = (Method) methodHm.get("get" + methodName);
				if (columnMethods[i][0] == null)
					columnMethods[i][0] = (Method) methodHm.get("is"
							+ methodName);
				columnMethods[i][1] = (Method) methodHm.get("set" + methodName);
//				 System.out.println("In GenericDAO.getListChunkByProperty : columnMethods["+i+"][0]="+columnMethods[i][0]+"/ columnMethods["+i+"][1]="+columnMethods[i][1]);
				if (columnMethods[i][0] == null || columnMethods[i][1] == null)
					throw new GenericDAOException(
							"getListChunkByProperty() : Can't find method "
									+ methodName + " in " + className, qstr);
			}

			String stupidElsa;

			while (count-- > 0 && rs.next()) {

				Object obj = classLoader.loadClass(className).newInstance();
				for (int i = 0; i < numberOfColumns; i++) {
					method = columnMethods[i][0];
					Object value;
					Class type = method.getReturnType();

					value = cast(type, rs, i + 1);

					method = columnMethods[i][1];
					if (value != null)
						method.invoke(obj, new Object[] { value });
				}
				al.add(obj);
				localCount++;

				if (localCount >= WARNING_EVERY_N_RECORDS) {
					if (localCount % WARNING_EVERY_N_RECORDS == 0) {
					}
					if (localCount > ALLOWED_MAX_RECORDS) {
						throw new SQLException(
								"Please minimize your selection, now : "
										+ localCount + " max : "
										+ ALLOWED_MAX_RECORDS + "\n" + qstr);
					}
				}
			}
			ListChunk lc = new ListChunk(pageNo, pageSize, num, al, startIndex,
					localCount);
			return lc;
		} catch (Exception e) {
			if (tempConn != null)
				closeConnection(tempConn);
			throw new GenericDAOException("getListChunkByProperty() : " + e,
					qstr);

		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
			if (tempConn != null)
				closeConnection(tempConn);
		}

	}

	protected int getCount(String qstr, List params) throws GenericDAOException {
		int count = -1;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String tmpStr;
		Connection tempConn=null;
		try {
			tmpStr = qstr.toUpperCase();
			qstr = "SELECT COUNT(*) FROM (" + qstr + ")";

			tempConn= DBConnection.getConnection();
			stmt = prepareStatement(tempConn, qstr);

			setParameters(stmt, params);

			rs = stmt.executeQuery();

			if (rs.next())
				count = rs.getInt(1);
		} catch (Exception e) {
			// Logger.error(GenericDAO.class.getName(), e.toString() + "\n" +
			// qstr);
			throw new GenericDAOException(e.toString(), qstr);
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(tempConn);
		}
		return count;
	}

	protected PreparedStatement prepareStatement(Connection dbConnection,
			String qstr) throws GenericDAOException {
		PreparedStatement stmt = null;
		try {
			// qstr = qstr.trim();
			if (qstr.startsWith("{")) {
				stmt = (PreparedStatement) dbConnection.prepareCall(qstr);
			} else {
				stmt = dbConnection.prepareStatement(qstr);
			}
			return stmt;
		} catch (SQLException se) {
			throw new GenericDAOException("SQLException while preparing "
					+ "statement : " + se.getMessage());
		}
	}

	private String toMethodName(String fieldName) {
		byte[] name = toPropertyName(fieldName).getBytes();

		name[0] = (byte) Character.toUpperCase((char) name[0]);
		return new String(name);
	}

	private String toPropertyName(String fieldName) {
		boolean upcase = false;
		StringBuffer buf = new StringBuffer();

		// fieldName = fieldName.toLowerCase();
		int size = fieldName.length();

		for (int i = 0; i < size; i++) {
			if (fieldName.charAt(i) == '_') {
				upcase = true;
			} else {
				if (upcase) {
					buf.append(Character.toUpperCase(fieldName.charAt(i)));
				} else {
					buf.append(fieldName.charAt(i));
				}
				upcase = false;
			}
		}
		return buf.toString();
	}

	protected Object getLineItem(String sql, List params, String className)
			throws GenericDAOException {
		ListChunk lc = getListChunkByProperty(sql, params, 1, 1, false,
				className);
		Collection col = lc.getCollection();
		if (col == null || (col.size() == 0)) {
			return null;
		} else {
			return col.iterator().next();
		}
	}

	protected Collection simpleKVQuery(String qstr, List params)
			throws GenericDAOException {
		System.out.println(printSql(qstr, params));
		Connection tempConn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<KeyValueImpl> al = new ArrayList<KeyValueImpl>();
		try {
			stmt = prepareStatement(tempConn, qstr);
			setParameters(stmt, params);
			rs = stmt.executeQuery();
			while (rs.next()) {
				KeyValueImpl kv = new KeyValueImpl(rs.getString(1),
						rs.getString(2));
				al.add(kv);
			}
		} catch (Exception e) {
			// Logger.error(GenericDAO.class.getName(), e.toString());
			throw new GenericDAOException("simpleKVQuerye() : " + e, qstr);
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(tempConn);
		}
		return al.size() == 0 ? null : al;
	}

	public String generateCode(String prefix, String tableName, String column)
			throws GenericDAOException {
		Connection co = DBConnection.getConnection();
		Calendar now = Calendar.getInstance();
		String yearCode = String.valueOf(now.get(Calendar.YEAR)).substring(2);
		String monthCode = String.valueOf(now.get(Calendar.MONTH) + 1);
		monthCode=((monthCode.length()==1)?("0"+monthCode):monthCode);
		String dayCode = String.valueOf(now.get(Calendar.DAY_OF_MONTH));
		dayCode=((dayCode.length()==1)?("0"+dayCode):dayCode);
		String temp = prefix + "-" + yearCode + monthCode + dayCode;
		System.out.println("in generic:temp="+temp);
		String sql = "select max(" + column + ") as 'code' from jxc."
				+ tableName + " where " + column + " like '" + temp + "%'";
		System.out.println("SQL=" + sql);
		String maxCode;
		Statement pstm = null;
		ResultSet rs = null;
		try {
			pstm = co.createStatement();
			rs = pstm.executeQuery(sql);
			rs.next();
			maxCode = rs.getString("code");
			if (maxCode == null || "null".equals(maxCode) || "".equals(maxCode)) {
				maxCode = temp + "001";
			} else {
				int num = Integer.valueOf(maxCode.substring(maxCode.length() - 3))+1;
				maxCode = maxCode.substring(0, maxCode.length() - 3)
						+ ((String.valueOf(num).length())==1?"00"+num:(String.valueOf(num).length())==2?"0"+num:num);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new GenericDAOException("Can not execute query", e);
		} finally {
			closeResultSet(rs);
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			closeConnection(co);
		}
		return maxCode;
	}
}
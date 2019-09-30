package ra.com.common.dao;

final public class DAOFactory { 

	public synchronized static Object getDAO(String className)
			throws GenericDAOException {
		Object dao = null;
		try {
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			dao = classLoader.loadClass(className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericDAOException("Exception while getting DAO : \n"
					+ e.getMessage());
		}
		return dao;

	}

}
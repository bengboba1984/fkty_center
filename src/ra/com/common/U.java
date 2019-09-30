package ra.com.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ra.com.portal.model.TreeNodeList;
import net.sf.json.JSON;
import net.sf.json.JSONArray;

public class U {

	public static JSONArray changeListToJSON(Collection c) {
		return JSONArray.fromObject(c);
	}

	public static String toString(Object obj) {
		return obj != null ? obj.toString() : "";
	}

	public static String getValueByKeyInArray(String key, String[][] ay) {
		int length = ay.length;
		for (int i = 0; i < length ; i++) {
			if (ay[i][0].equals(key)) {
				return ay[i][1];
			}
		}
		return "";
	}

	public static InputStream downloadSimpleExcel(Collection cl,
			String className, String[] headers,String[] columns) {
		HSSFWorkbook wb = new HSSFWorkbook();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {

			HSSFSheet sheet = wb.createSheet("sheet1");
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFFont headFont = wb.createFont();
			headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(headFont);
			if(columns==null){
				columns = headers;
			}
			HSSFRow headerLine = sheet.createRow(0);
			HSSFCell hCell;

			for (int i = 0; i < headers.length; i++) {
				hCell = headerLine.createCell(i);
				hCell.setCellStyle(style);
				hCell.setCellValue(headers[i]);
				sheet.autoSizeColumn(i);
			}

			// get All methods include parent class
			HashMap allMethods = new HashMap();
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			Class c = classLoader.loadClass(className);
			Method[] methods = c.getMethods();
			String tempField = null;
			for (int i = 0; i < methods.length; i++) {
				if (methods[i].getName().startsWith("get")) {
					tempField = methods[i].getName().substring(3);
					allMethods.put(tempField.substring(0, 1).toLowerCase()
							+ tempField.substring(1), methods[i]);
				}
			}
			if(cl!=null&&cl.size()>0){
				Iterator it = cl.iterator();
				int rowIndex = 1;
				String cellValue = "";
				while (it.hasNext()) {
					HSSFRow row = sheet.createRow(rowIndex);
					Object o = it.next();
					for (int j = 0; j < columns.length; j++) {
						HSSFCell cell = row.createCell(j);
						Method m = (Method) allMethods.get(columns[j]);
						cellValue = m.invoke(o) != null ? m.invoke(o)
								.toString() : "";
						//System.out.println("@@@headers="+columns[j]);
						if(isNumeric(cellValue)){
							cell.setCellValue(Double.parseDouble(cellValue));
						}else{
							cell.setCellValue(cellValue);
						}
						
						
					}
					rowIndex++;
				}
			}
			wb.write(os);
		} catch (IOException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] fileContent = os.toByteArray();
		ByteArrayInputStream is = new ByteArrayInputStream(fileContent);
		return is;
	}

	public static List excelToList(File f, String className, String[] headers) {
		// headers�����excel��λ��Ӧ
		ArrayList al = new ArrayList();
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		boolean isE2007 = false; // �ж��Ƿ���excel2007��ʽ
		if (f.getName().endsWith("xlsx"))
			isE2007 = true;

		FileInputStream fis;
		try {
			Class c = classLoader.loadClass(className);
			Method[] methods = c.getMethods();
			HashMap methodHm = new HashMap();
			for (int i = 0; i < methods.length; i++) {
				methodHm.put(methods[i].getName(), methods[i]);
			}
			Method method;
			fis = new FileInputStream(f);
			Workbook wb;
			if (isE2007)
				wb = new XSSFWorkbook(fis);
			else
				wb = new HSSFWorkbook(fis);

			Sheet sheet = wb.getSheetAt(0);
			Iterator<Row> rows = sheet.rowIterator();
			String column;
			int colIndex = 0;

			while (rows.hasNext()) {
				Row row = rows.next();
				if (row.getRowNum() == sheet.getFirstRowNum())
					continue;// ��������
				System.out.println("@@@@row=" + row.getRowNum());
				Object obj = classLoader.loadClass(className).newInstance();
				Iterator<Cell> cells = row.cellIterator();
				while (cells.hasNext()) {
					Cell cell = cells.next();
					column = headers[colIndex];
					method = (Method) methodHm.get("set"
							+ column.substring(0, 1).toUpperCase()
							+ column.substring(1));
					System.out.println("###celltype=" + cell.getCellType());
					switch (cell.getCellType()) { // ���cell�е�������������
					case HSSFCell.CELL_TYPE_NUMERIC:
						if (HSSFDateUtil.isCellDateFormatted(cell)) {
							Date d = cell.getDateCellValue();
							DateFormat formater = new SimpleDateFormat(
									"yyyy-MM-dd");
							method.invoke(obj, new Object[] { U
									.toString(formater.format(d)) });
							System.out.println(U.toString(formater.format(d)));
						} else {
							method.invoke(obj, new Object[] { U.toString(cell
									.getNumericCellValue()) });
							System.out.println(cell.getNumericCellValue());
						}
						break;
					case HSSFCell.CELL_TYPE_STRING:
						if (cell.getStringCellValue() != null)
							method.invoke(obj,
									new Object[] { cell.getStringCellValue() });
						System.out.println(cell.getStringCellValue());
						break;
					case HSSFCell.CELL_TYPE_BOOLEAN:
						method.invoke(obj,
								new Object[] { cell.getBooleanCellValue() });
						System.out.println(cell.getBooleanCellValue());
						break;
					case HSSFCell.CELL_TYPE_FORMULA:
						if (cell.getCellFormula() != null)
							method.invoke(obj,
									new Object[] { cell.getCellFormula() });
						System.out.println(cell.getCellFormula());
						break;
					default:
						System.out.println("unsuported sell type");
						break;
					}
					colIndex++;
				}
				System.out.println("@@@@obj=" + obj.toString());
				al.add(obj);
				colIndex = 0;
			}

		} catch (IOException | InstantiationException | IllegalAccessException
				| ClassNotFoundException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("@@@@@transfer success");
		return al;

	}
	
	public static boolean isNumeric(String str){ 
			String regex="^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";
		   Pattern pattern = Pattern.compile(regex); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
		}
	
//	public static String DataTable2Json(DataTable dt, int pid = -1)  
//    {  
//        StringBuilder jsonBuilder = new StringBuilder();  
//
//
//        for (int i = 0; i < dt.Rows.Count; i++)  
//        {  
//            jsonBuilder.Append("{");  
//            for (int j = 0; j < dt.Columns.Count; j++)  
//            {  
//                int id = pid;  
//                jsonBuilder.Append("\"");  
//                dt.Columns[j].ColumnName = dt.Columns[j].ColumnName.ToLower();  
//                if (dt.Columns[j].ColumnName == "name") dt.Columns[j].ColumnName = "text";  
//                jsonBuilder.Append(dt.Columns[j].ColumnName);  
//                if (dt.Columns[j].ColumnName.ToLower() == "id" && pid != -1)  
//                {  
//                    jsonBuilder.Append("\":" + (id * 10 + Convert.ToInt32(dt.Rows[i][j])) + ",");  
//                }  
//                else  
//                {  
//                    jsonBuilder.Append("\":\"" + dt.Rows[i][j].ToString() + "\",");  
//                }  
//            }  
//
//
//            if (dt.Columns.Count > 0)  
//            {  
//                jsonBuilder.Remove(jsonBuilder.Length - 1, 1);  
//            }  
//            jsonBuilder.Append("},");  
//        }  
//        if (dt.Rows.Count > 0)  
//        {  
//            jsonBuilder.Remove(jsonBuilder.Length - 1, 1);  
//        }  
//        return jsonBuilder.ToString();  
//    } 
//	
//	public String getJson(DataTable dt)  
//    {  
//        StringBuffer json = new StringBuffer();  
//        json.Append("[");  
//        foreach (DataRow dr in dt.Rows)  
//        {  
// 
//           json.Append("{\"id\":" +dr["id"].ToString());  
//           json.Append(",\"text\":\"" +dr["name"].ToString() + "\"");  
//           json.Append(",\"state\":\"closed\"");  
// 
//            DataTable dtChildren = newDataTable();  
// 
// 
//            //调用D层方法获取dataTable  
//            DataSet ds =characterizationTargetBLL.GetList(" type='" +dr["name"].ToString() + "'");  
//            dtChildren = ds.Tables[0];  
// 
//            if (dt != null &&dt.Rows.Count > 0)  
//            {  
//               json.Append(",\"children\":[");  
//               json.Append(DataTable2Json(dtChildren,Convert.ToInt32(dr["id"])));  
//                json.Append("]");  
//            }  
//            json.Append("},");  
// 
//        }  
//        if (dt.Rows.Count > 0)  
//        {  
//            json.Remove(json.Length - 1,1);  
//        }  
//        json.append("]");  
//        return json.toString();  
//    } 
	 public final static String[] split(String source, String delim) {
	        if (source == null) {
	            return null;
	        }
	        StringTokenizer st = new StringTokenizer(source, delim);
	        ArrayList al = new ArrayList();

	        while (st.hasMoreTokens()) {
	            al.add(st.nextToken());
	        }

	        return (String[]) al.toArray(new String[0]);
	    }
	 
	 public static String decode(String value) {
			try {
				if(value!=null&&!"".equals(value)){
					value = java.net.URLDecoder.decode(value, "UTF-8");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return value;
		}
	 
	 public static String[] subStringArr(String[] str,int length){
		 String[] res = new String[length];
		 for(int ind = 0;str!=null&&ind<str.length&&ind<length;ind++){
			 res[ind ] = str[ind];
		 }
		 return res;
	 }

	 public static int compareDatForJob(String oldTime){
		 int count = 1;
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 if(oldTime==null||"".equals(oldTime)){
			 return count;
		 }
		 Calendar calendar = Calendar.getInstance();
		 Date date = calendar.getTime();
		 try {
			Date dateParse = sdf.parse(oldTime);
			count = (int) (date.getTime()-dateParse.getTime())/(1000 * 60 * 10);
			//count =  count + 1;
			System.out.println("============count:"+count);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return count;
	 }
	 public static void copyFile(File oldfile, String newPath, String fileName,boolean deleteFlag) {
		 FileOutputStream fs = null;
			try {
				
				if (oldfile.exists()) { // 文件存在时
					int byteread = 0;
					InputStream inStream = new FileInputStream(oldfile); // 读入原文件
					File fileDir = new File(newPath);
					if (!fileDir.exists()) {
						fileDir.mkdirs();
					}
					newPath = newPath + fileName;
					fs = new FileOutputStream(newPath);
					byte[] buffer = new byte[1024];
					while ((byteread = inStream.read(buffer)) != -1) {
						fs.write(buffer, 0, byteread);
					}
					fs.close();
					inStream.close();
					if(deleteFlag){
						oldfile.delete();
					}
				}
				
			} catch (Exception e) {
				System.out.println("复制单个文件操作出错");
				e.printStackTrace();
			}  
	        finally {   
	            try {   
	                fs.close(); 
	            } catch (Exception e) {
	                e.printStackTrace(); 
	            }   

	        } 
	}
	 
	 public static String decodeString(String value) {
			try {
				if(value!=null&&!"".equals(value)){
					value = java.net.URLDecoder.decode(value, "UTF-8");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return value;
		} 
}

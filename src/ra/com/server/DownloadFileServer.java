package ra.com.server;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import ra.com.dataManagement.bussiness.DataManagementFacade;
import ra.com.dataManagement.model.FtpFile;

public class DownloadFileServer extends HttpServlet {

	public void init(ServletConfig sc) throws ServletException {
		super.init(sc);

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		String fileId = req.getParameter("fileId");
		DataManagementFacade biz = DataManagementFacade.getInstance();
		response.setContentType("application/x-msdownload;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=FTP.zip");
		ZipOutputStream zipos = null;
		try {
			zipos = new ZipOutputStream(new BufferedOutputStream(
					response.getOutputStream()));
			zipos.setMethod(ZipOutputStream.DEFLATED); // 设置压缩方法
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String basePath = biz.getDownloadBasePath();
			if (basePath == null || "".equals(basePath)) {
				return;
			}
			Collection<FtpFile> fileList = biz.downloadFtpFile(fileId);
			DataOutputStream os = null;
			for (FtpFile f : fileList) {
				String path = basePath + File.separator + ("1".equals(f.getType()) ? f.getAccount():f .getTester()) + File.separator;
				File file = new File(path + f.getFileName());
				try {
					// 添加ZipEntry，并ZipEntry中写入文件流
					zipos.putNextEntry(new ZipEntry(f.getFileName()));// 文件名字
					os = new DataOutputStream(zipos);
					InputStream is = new FileInputStream(file);
					byte[] b = new byte[100];
					int length = 0;
					while ((length = is.read(b)) != -1) {
						os.write(b, 0, length);
					}
					is.close();
					zipos.closeEntry();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				os.flush();
				os.close();
				zipos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

	}

}
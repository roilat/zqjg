package cn.roilat.cqzqjg.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.roilat.cqzqjg.common.utils.StringUtils;
import cn.roilat.cqzqjg.core.http.HttpResult;

/**
 * --------------------------- 文件上传 --------------------------- 作者： roilat-J
 * ---------------------------
 */
@RestController
@RequestMapping("common")
public class FileUploadController {

	@Value("${system.file.saveRootPath}")
	private String saveRootPath;
	@Value("${system.file.showPrePath}")
	private String showPrePath;
	
	

	/**
	 * 图片上传
	 * 
	 * @param record
	 * @return
	 */
	@PostMapping(value = "/upload")
	public HttpResult save(MultipartHttpServletRequest request) {
		String bizType = request.getParameter("bizType");
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> map = multipartHttpServletRequest.getFileMap();
		if (map == null || map.isEmpty()) {
			return HttpResult.error("上传文件不存在！");
		}
		Iterator<Entry<String, MultipartFile>> iterator = map.entrySet().iterator();
		List<String> pathList = new ArrayList<String>();
		while (iterator.hasNext()) {
			Entry<String, MultipartFile> item = iterator.next();
			try {
				pathList.add(upload(item.getValue(),item.getValue().getOriginalFilename(), bizType));
			} catch (IllegalStateException | IOException e) {
				return HttpResult.error("上传失败！错误信息是："+e.getMessage());
			}
		}
		return HttpResult.ok(pathList);
	}

	private String upload(MultipartFile multipartFile,String fileName, String bizType) throws IllegalStateException, IOException {
		String dateTime = new SimpleDateFormat("MMddHH24mmss").format(new Date());
		String pathPre = StringUtils.isBlank(saveRootPath) ? "/" : saveRootPath.endsWith("/") ? saveRootPath : saveRootPath + "/";
		StringBuilder relaPath = new StringBuilder();
		relaPath.append(bizType).append("/").append(dateTime).append("_")
				.append(fileName);
		File file = new File(pathPre+relaPath.toString());
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		multipartFile.transferTo(file);
		return showPrePath +relaPath.toString();
	}

	public static void main(String[] args) throws IllegalStateException, IOException {
		FileUploadController controller = new FileUploadController();
		controller.saveRootPath = "/aaa";
		MultipartFile file = new MultipartFile() {
			
			@SuppressWarnings("resource")
			@Override
			public void transferTo(File dest) throws IOException, IllegalStateException {
				FileInputStream fis = new FileInputStream("C:\\Users\\Administrator.PC-20160124BSJA\\Desktop\\logo.png");
				fis.getChannel().transferTo(0, fis.available(), new FileOutputStream(dest).getChannel());
			}
			
			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public long getSize() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public String getOriginalFilename() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public InputStream getInputStream() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getContentType() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public byte[] getBytes() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
		};
		controller.upload(file, "hello.jpg", "bizType");
	}
}

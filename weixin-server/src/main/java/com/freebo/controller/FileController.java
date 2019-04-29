package com.freebo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.freebo.common.MyException;
import com.freebo.common.po.FileInfo;

@RestController
@RequestMapping("/file")
public class FileController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);
	
	private String path = "d:\\";

	@PostMapping
	public FileInfo upload(HttpServletRequest request, MultipartFile file) throws Exception {
		String contextPath = request.getContextPath();
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ contextPath + "/";
		System.out.println("=============================");
		System.out.println("=============================");
		System.out.println("=============================");
		System.out.println(contextPath);
		System.out.println(realPath);
		System.out.println(basePath);

		try {
			System.out.println(file.getName());
			System.out.println(file.getOriginalFilename());
			System.out.println(file.getSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		File localFile = new File(path, file.getOriginalFilename());

		file.transferTo(localFile);

		return new FileInfo(localFile.getAbsolutePath());
	}

	@GetMapping("/{id}")
	public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
		try (
			InputStream inputStream = new FileInputStream(new File(path, id + ".jpg"));
				OutputStream outputStream = response.getOutputStream();) {

			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename=" + id + ".jpg");

			IOUtils.copy(inputStream, outputStream);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new MyException("502", e.getMessage());
		}
	}

}

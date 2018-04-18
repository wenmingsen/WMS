package com.csg.intshop.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FTP上传下载
 * 
 * @author 李城
 * @since 1.0
 * @version 2017-12-03 李城
 */
 public  class  FtpUpAndDownUtil {
	/** 日志 */
    public static final Logger LOGGER = LoggerFactory.getLogger(FtpUpAndDownUtil.class);
      
    /** ftp客户端 */
    public static FTPClient ftpClient;
	
	 /**
	  * 
     * 打开ftp客户端
     * 
     * @param port 获取FTP服务器端口
     * @param  获取FTP服务器IP
     * @param ip  获取FTP服务器IP
     * @param userName 获取FTP服务器账号
     * @param passWord 获取FTP服务器密码
     */
    public static void openFtpClient(int port,String ip,String userName,String passWord) {
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("GBK");
        if (0 != port) {
            ftpClient.setDefaultPort(port);
        }
        try {
            ftpClient.connect(ip);
        } catch (IOException e) {
            LOGGER.error("根据IP连接ftp失败，错误信息：" + e.getMessage());
        }
        try {
        	boolean isLoginSuccess=ftpClient.login(userName, passWord);
        	LOGGER.error("登录" + isLoginSuccess);
        } catch (IOException e) {
            LOGGER.error("根据用户名与密码连接ftp失败，错误信息：" + e.getMessage());
        }
        if (ftpClient.isConnected()) {
            // 设为被动模式
            ftpClient.enterLocalPassiveMode();
            // 不设为二进制传送模式，会收不到0x0D
            try {
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                LOGGER.debug("ftp connect success");
            } catch (IOException e) {
                LOGGER.error("ftp传输模式设为二进制失败，错误信息：" + e.getMessage());
            }
        } else {
            LOGGER.error("ftp连接已被关闭");
        }
    }
    
    /**
     * 关闭ftp客户端
     * 
     * @param isFtp 是ftp
     */
    public static void closeFtpClient(boolean isFtp) {
        if (isFtp && ftpClient.isConnected()) {
            try {
                ftpClient.disconnect();
                LOGGER.info("ftp disconnect success");
            } catch (IOException e) {
                LOGGER.error("ftp关闭连接失败");
            }
        } else {
            LOGGER.info("ftp connect is null");
        }
    }
    
    /**
     * 判断文件存储方式
     * 
     * @return 是ftp，则返回true
     */
    public static  boolean isFtp() {
        return true;
    }
    /**
     * 将ftp中的文件下载到本地 
     * 
     * @param fileName 文件名
     * @return 本地文件路径
     */
	public static boolean downLoadFile(String localPath, String downloadPath,String fileName,int port,String ip,String userName,String passWord) {
		openFtpClient(port ,ip ,userName ,passWord);
		File directory = new File(localPath);
        if (!directory.exists() && !directory.isDirectory()) {
            try {
                directory.mkdirs();
            } catch (Exception e) {
                LOGGER.info("创建文件夹失败：" + localPath);
            }
        }
        String localFilePath = localPath + fileName;
        File localFile = new File(localFilePath);
        OutputStream outStream = null;
        boolean success = false;
        try {
        	
            ftpClient.changeWorkingDirectory(downloadPath);
            outStream = new FileOutputStream(localFile);
            LOGGER.info(fileName + "开始下载....");
            success = ftpClient.retrieveFile(fileName, outStream);
            if (success) {
                LOGGER.info(fileName + "成功下载到" + localFilePath);
            }
        } catch (Exception e) {
            LOGGER.error(fileName + "下载失败,错误信息：" + e.getMessage());
        } finally {
            if (null != outStream) {
                try {
                    outStream.flush();
                    outStream.close();
                } catch (IOException e) {
                    LOGGER.error(fileName + "下载失败,错误信息：" + e.getMessage());
                }
            }
        }
        if (!success) {
            LOGGER.error(fileName + "下载失败!!!");
            return false;
        }else{
        	return success;
        }
    }
    /**
     * 将文件上传到ftp
     * 
     * @param fileName 文件名
     * @param fileType 文件类型
     * @return 上传结果
     */
	public static boolean uploadFile(String fileName,String localPath, String upLoadPath,int port,String ip,String userName,String passWord){
    	openFtpClient(port,ip,userName,passWord);
    	boolean isFtp=isFtp();
        File localFile = new File(localPath);
        InputStream in = null;
        boolean success = false;
        try {
        	boolean isPath= ftpClient.changeWorkingDirectory(upLoadPath);
            if(!isPath){
            	String[] files=upLoadPath.split("/");
               for(int i=0;i<files.length;i++){
	            	   if(!ftpClient.changeWorkingDirectory(files[i])){
	            		   ftpClient.makeDirectory(files[i]);
	            		   ftpClient.changeWorkingDirectory(files[i]);
	            	   }
            	   }
               }
     	   ftpClient.changeWorkingDirectory(upLoadPath);
            in = new FileInputStream(localFile);
            LOGGER.info(fileName + "开始上传....");
            success = ftpClient.storeFile(fileName, in);
            if (success) {
                LOGGER.info(fileName + "成功上传到" + upLoadPath);
            }
        } catch (Exception e) {
            LOGGER.error(fileName + "上传失败,错误信息：" + e.getMessage());
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    LOGGER.error(fileName + "上传失败,错误信息：" + e.getMessage());
                }
            }
            closeFtpClient(isFtp);
        }
        return success;
    }
    
    /**
     * 将本地文件删除
     * 
     * @param localPath 文件路径
     * @param fileName 文件名
     */
	public static void   deleteFile(String localPath,String fileName) {
        File file = new File(localPath + fileName);
        // 判断目录或文件是否存在
        if (file.exists() && file.isFile()) {
            // 判断是否为文件
            file.delete(); // 为文件时调用删除文件方法
        }
    }
	
	public static void main(String[] arg){
		openFtpClient(21,"168.168.4.30","nwyx","nwyx2017");
	}
}



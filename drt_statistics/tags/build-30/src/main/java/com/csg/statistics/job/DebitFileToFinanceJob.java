package com.csg.statistics.job;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.csg.statistics.service.DrtAccountCwgsService;
import com.csg.statistics.service.DrtClientCwgsService;
import com.csg.statistics.service.DrtEleFeepayService;
import com.csg.statistics.util.FileUtil;
import com.csg.statistics.util.SftpUtil;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * 扣款文件处理任务
 * 
 * @author 李达才
 * @since 1.0
 * @version 2017-12-12 李达才
 */
@Component
public class DebitFileToFinanceJob {
	
	@Autowired
    private DrtEleFeepayService drtEleFeepayService;
    
    @Autowired
    private DrtAccountCwgsService drtAccountCwgsService;
    
    @Autowired
    private DrtClientCwgsService drtClientCwgsService;
	
	/** 日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(DebitFileToFinanceJob.class);
      
    /** ftp客户端 */
    private static FTPClient ftpClient;
	
	//@Scheduled(cron="0 0/1 * * * ?")
	public void checkWithPayment(){
		LOGGER.error("------>进入定时器任务：" + new Date());
		
		ChannelSftp sftp = null;
		try {
			sftp = SftpUtil.getSftpConnect(getIp(), getPort(), getUserName(), getPassWord());
			if(sftp != null){
				/*String pathname = //System.getProperty("user.dir")+File.separatorChar+"code"+File.separatorChar
						"D:\\workspace\\drt_server\\drt_person_center\\trunk\\sources\\DRTClient_20171213.txt";
				List<Map<Integer, String>> data1 = FileUtil.readFile(pathname,"\\|","UTF-8");
				drtClientCwgsService.insertClientCwgs(data1);
				pathname = "D:\\workspace\\drt_server\\drt_person_center\\trunk\\sources\\DRTAccount_20171213.txt";
				List<Map<Integer, String>> data2 = FileUtil.readFile(pathname,"\\|","UTF-8");
				drtAccountCwgsService.insertAccountCwgs(data2);
		    	String pathname = "D:\\workspace\\drt_server\\drt_person_center\\trunk\\sources\\DRTClient.txt";
				List<Map<Integer, String>> data3 = FileUtil.readFile(pathname,"\\|","UTF-8");
				drtClientCwgsService.insertClientCwgs(data3);*/
				
				List<String> list = new ArrayList<String>();
				list.add("YWSQBH");
				list.add("JYLX");
				list.add("JE");
				list.add("RUNTIME");
				list.add("CLIENT_NUMBER");
				list.add("FKFZHH");
				list.add("SKFZHH");
				list.add("SKFZHMC");
				list.add("SUBMIT_PERSON");
				list.add("SUBMIT_TIME");
				list.add("ZY");
				list.add("ZHLX");
				list.add("SIGN");
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				calendar.add(Calendar.DATE, -1);
				String day = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
				Date startTime = calendar.getTime();
				calendar.add(Calendar.DATE, 1);
				Date endTime = calendar.getTime();
				List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
				Map<String, Object> map = new HashMap<String,Object>();
				map.put("YWSQBH","业务申请编号");
				map.put("JYLX","交易类型");
				map.put("JE","金额");
				map.put("RUNTIME","执行日");
				map.put("CLIENT_NUMBER","客户编号");
				map.put("FKFZHH","付款方账户号");
				map.put("SKFZHH","收款方账户号");
				map.put("SKFZHMC","收款方账户名称");
				map.put("SUBMIT_PERSON","提交人");
				map.put("SUBMIT_TIME","提交时间");
				map.put("ZY","摘要");
				map.put("ZHLX","账户类型");
				map.put("SIGN","签名值");
				data.add(map);
				data.addAll(drtEleFeepayService.findListByCreatTime(startTime, endTime));
				//FileUtil.witerFile("D:\\workspace\\drt_server\\drt_person_center\\trunk\\sources\\DRTTransaction_"+day+".txt", list, data, "|","UTF-8",false);
				InputStream inputStream = new ByteArrayInputStream(FileUtil.witerToString(list, data, "|").getBytes("UTF-8"));
				//File file = new File("D:\\workspace\\drt_server\\drt_person_center\\trunk\\sources\\DRTTransaction_"+day+".txt");
				SftpUtil.uploadFile(inputStream, "/sources/DRTTransaction_"+day+".txt", sftp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if (sftp != null && sftp.isConnected()) {
					Session session = sftp.getSession();
					sftp.disconnect();
					if(session != null){
						session.disconnect();
					}
				}
			} catch (JSchException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
    	
    }
    
    /**
     * 判断文件存储方式
     * 
     * @return 是ftp，则返回true
     */
    private boolean isFtp() {
        return false;
    }
    
    /**
     * 获取文件路径
     * 
     * @return 文件路径
     */
    private String getDownloadPath() {
        return "/sources/";
        
    }
    
    /**
     * 对账文件上传路径
     * 
     * @return 文件路径
     */
    private String getUploadPath() {
        return "/upload/";
        
    }
    
    /**
     * 对账结果文件上传路径
     * 
     * @return 文件路径
     */
    private String getResultPath() {
        return "/result/";
        
    }
    
    /**
     * 获取本地文件路径
     * 
     * @return 文件路径
     */
    private String getLocalPath() {
        return "D:\\workspace\\drt_server\\drt_transact_check\\trunk\\sources\\";
    }
    
    /**
     * 获取FTP服务器IP
     * 
     * @return ip
     */
    private String getIp() {
        return "192.168.72.128";
    }
    
    /**
     * 获取FTP服务器端口
     * 
     * @return 端口
     */
    private int getPort() {
        return 22;
    }
    
    /**
     * 获取FTP服务器账号
     * 
     * @return 文件路径
     */
    private String getUserName() {
        return "root";
    }
    
    /**
     * 获取FTP服务器密码
     * 
     * @return 文件路径
     */
    private String getPassWord() {
        return "123456";
    }
    
}

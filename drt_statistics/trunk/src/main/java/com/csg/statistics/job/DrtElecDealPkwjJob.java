/**
 * 
 */
package com.csg.statistics.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.csg.statistics.common.config.AppConfig;
import com.csg.statistics.entity.YxPkwjtzxxBean;
import com.csg.statistics.service.DrtDealYxpkwjInfoService;
import com.csg.statistics.service.DrtElecDealPkwjService;
import com.csg.statistics.util.SftpUtil;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;

/**
 * @author Administrator
 *
 */
@Component
public class DrtElecDealPkwjJob {

	private Logger log = LoggerFactory.getLogger(DrtElecDealPkwjJob.class);
	
	@Autowired
	private  DrtElecDealPkwjService drtElecDealPkwjService;
	
	
	/**营销批扣文件通知电融通处理信息Service*/
	@Autowired
	private DrtDealYxpkwjInfoService drtDealYxpkwjInfoService;
	
	/**
	 * 定时调用处理批扣文件通知
	 * */
	
	public void dealPkwj(){
		log.info("调用电费服务处理批扣文件通知.........");
		List<YxPkwjtzxxBean> yxpkwjlist = drtElecDealPkwjService.queryYxPkwjtzxx();
		List<YxPkwjtzxxBean> resultList = new ArrayList<YxPkwjtzxxBean>();
        if (!CollectionUtils.isEmpty(yxpkwjlist)) {
			try {
				ChannelSftp sftp = SftpUtil.getSftpConnect(com.csg.statistics.common.config.AppConfig.getProperty("yxSftpIp"), Integer.parseInt(AppConfig.getProperty("ysSftpPort")), AppConfig.getProperty("yxSftpUserName"), AppConfig.getProperty("yxSftppassWord"));
				for(YxPkwjtzxxBean yxPkwjtzxxBean:yxpkwjlist){
    				if(SftpUtil.isExist(yxPkwjtzxxBean.getWJLJ(),yxPkwjtzxxBean.getDKWJMC(),sftp)){
    					log.info("该营销文件存在sftp中："+yxPkwjtzxxBean.toString());
    					resultList.add(yxPkwjtzxxBean);
    				}else{
    					log.info("该营销文件不存在sftp中："+yxPkwjtzxxBean.toString());
    					drtDealYxpkwjInfoService.updYxPkwjtzxx(yxPkwjtzxxBean.getId());
    				}
    			}
				if(!CollectionUtils.isEmpty(resultList)){
					log.info("处理文件dealPktz的长度：" + resultList.size());
					Date date = new Date();
					//遍历处理批扣文件
					for (YxPkwjtzxxBean yxPkwjtzxxBean : resultList) {
						drtElecDealPkwjService.dealPktz(yxPkwjtzxxBean);
					}
					//drtElecDealPkwjService.dealPktz(resultList);
		            Date d1 = new Date();
		            log.info("cost,dealpktz:"+(d1.getTime()-date.getTime()));
		            
				}else{
					log.info("营销文件还未推送到sftp中，停止微服务调用");
				}
			} catch (JSchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        } else {
            log.info("待处理的营销文件信息为空");
        }
	}
}

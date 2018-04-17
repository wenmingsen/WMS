package com.csg.statistics.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * Description: base64文件工具类
 * Company: Syni
 * @author 杨彬俊
 * @version 1.0
 * @since 2017-11-01
 */
public class BASE64Util {

    private static final Logger logger =
            LoggerFactory.getLogger(BASE64Util.class);

    public BASE64Util() {

    }

    /**
     * 将文件转化为字节数组字符串，并对其进行Base64编码处理
     *
     * @param filePath
     * @return
     */
    public synchronized static String getFileStr(String filePath) {
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(filePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            logger.error("getFileStr()方法出错", e);
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(data);
    }

    public static String getFileStr(InputStream is) throws IOException {
        String fileEncoding = "";
        BASE64Encoder encoder = null;
        byte[] data = null;
        try {
            data = new byte[is.available()];
            is.read(data);
            encoder = new BASE64Encoder();
            fileEncoding = encoder.encode(data);
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            encoder = null;
            data = null;
            if (is != null) {
                try {
                    is.close();
                    is = null;
                } catch (IOException e) {
                    logger.error(e.getMessage());
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
        return fileEncoding;
    }

    /**
     * 对字节数组字符串进行Base64解码并生成文件
     *
     * @param fileStr
     * @param filePath
     * @return
     */
    public synchronized static boolean generateFile(String fileStr, String filePath) {
        if (fileStr == null) {
            // 文件数据为空
            return false;
        } else if (fileStr.contains("data:image/png;base64,")) {
            fileStr = fileStr.replaceFirst("data:image/png;base64,", "");
        } else if (fileStr.contains("data:image/jpg;base64,")) {
            fileStr = fileStr.replaceFirst("data:image/jpg;base64,", "");
        } else if (fileStr.contains("data:image/jpeg;base64,")) {
            fileStr = fileStr.replaceFirst("data:image/jpeg;base64,", "");
        } else if (fileStr.contains("data:image/bmp;base64,")) {
            fileStr = fileStr.replaceFirst("data:image/bmp;base64,", "");
        } else if (fileStr.contains("data:image/gif;base64,")) {
            fileStr = fileStr.replaceFirst("data:image/gif;base64,", "");
        }

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(fileStr);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 保存文件
            OutputStream out = new FileOutputStream(filePath);

            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            logger.error("generateFile()方法出错", e);
            return false;
        }
    }

    public synchronized static boolean generateFile(InputStream fileInputStream, String filePath) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(filePath);
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            logger.error("generateFile error:", e.getMessage());
        } finally {
            try {
                fileInputStream.close();
                outputStream.flush();
                outputStream.close();
                return true;
            } catch (IOException e) {
                logger.error("generateFile error:close stream defeat!");
            }
        }
        return false;
    }

    public synchronized static String base64Decode(String str) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(str);
            return new String(b);
        } catch (Exception e) {
            logger.error("base64Decode()方法出错", e);
            return null;
        }
    }

    public synchronized static String base64Encode(String str) {
        return new BASE64Encoder().encode(str.getBytes());
    }

}

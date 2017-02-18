package com.cetiti.dsp.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


/**
 * 进行http访问的基本类
 * 
 * @author qiaoel@zjhcsoft.com
 * 
 */
public class HttpUtil {

    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final String METHOD_POST = "POST";

    private static final String METHOD_GET = "GET";

    private static final int CONNECTTIMEOUT = 5000;

    private static final int READTIMEOUT = 5000;

    private static class DefaultTrustManager implements X509TrustManager {

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] cert, String oauthType)
                throws java.security.cert.CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] cert, String oauthType)
                throws java.security.cert.CertificateException {
        }
    }

    private static HttpURLConnection getConnection(URL url, String method, String ctype)
            throws IOException {

        HttpURLConnection conn = null;
        if ("https".equals(url.getProtocol())) {
            SSLContext ctx = null;
            try {
                ctx = SSLContext.getInstance("TLS");
                ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() },
                        new SecureRandom());
            } catch (Exception e) {
                throw new IOException(e);
            }
            HttpsURLConnection connHttps = (HttpsURLConnection) url.openConnection();
            connHttps.setSSLSocketFactory(ctx.getSocketFactory());
            connHttps.setHostnameVerifier(new HostnameVerifier() {

                public boolean verify(String hostname, SSLSession session) {
                    return true;// 默认都认证通过
                }
            });
            conn = connHttps;
        } else {
            conn = (HttpURLConnection) url.openConnection();
        }
        conn.setRequestMethod(method);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("User-Agent", "hc-restclient-java-1.0");
        conn.setRequestProperty("Content-Type", ctype);
        conn.setRequestProperty("Connection", "Keep-Alive");
        return conn;

    }

    /**
     * 通过get方法访问，默认编码为utf-8
     * 
     * @param url 访问的url地址
     * @param params 请求需要的参数
     * @return 返回请求响应的数据
     * @throws IOException
     */
    public static String doGet(String url, Map<String, String> params) throws IOException {
        return doGet(url, params, DEFAULT_CHARSET);
    }

    /**
     * 通过get方法访问
     * 
     * @param url 访问的url地址
     * @param params 请求需要的参数
     * @param charset 字符编码
     * @return 返回请求响应的数据
     * @throws IOException
     */
    public static String doGet(String url, Map<String, String> params, String charset)
            throws IOException {
        if (isEmpty(url) || params == null) {
            return null;
        }
        String response = "";
        url += "?" + buildQuery(params, charset);
        
        System.out.println(url);
        HttpURLConnection conn = null;
        String ctype = "application/x-www-form-urlencoded;charset=" + charset;
        conn = getConnection(new URL(url), METHOD_GET, ctype);
        response = getResponseAsString(conn);
        return response;
    }

    /**
     * 
     * @param url api请求的权路径url地址
     * @param params api请求的业务级参数
     * @return
     * @throws IOException
     */
    public static String doPost(String url, Map<String, String> params) throws IOException {
        return doPost(url, params, CONNECTTIMEOUT, READTIMEOUT);
    }
    
    /**
     * 
     * @param url api请求的权路径url地址
     * @param params api请求的业务级参数
     * @return
     * @throws IOException
     */
    public static String doPost(String url, Map<String, String> params,String charset) throws IOException {
        return doPost(url, params, charset,CONNECTTIMEOUT, READTIMEOUT);
    }

    /**
     * 
     * 通过post方法请求数据，默认字符编码为utf-8
     * 
     * @param url 请求的url地址
     * @param params 请求的参数
     * @param connectTimeOut 请求连接过期时间
     * @param readTimeOut 请求读取过期时间
     * @return 请求响应
     * @throws IOException
     */
    public static String doPost(String url, Map<String, String> params, int connectTimeOut,
            int readTimeOut) throws IOException {
        return doPost(url, params, DEFAULT_CHARSET, connectTimeOut, readTimeOut);
    }

    /**
     * 
     * 通过post方法请求数据
     * 
     * @param url 请求的url地址
     * @param params 请求的参数
     * @param charset 字符编码格式
     * @param connectTimeOut 请求连接过期时间
     * @param readTimeOut 请求读取过期时间
     * @return 请求响应
     * @throws IOException
     */
    public static String doPost(String url, Map<String, String> params, String charset,
            int connectTimeOut, int readTimeOut) throws IOException {
        HttpURLConnection conn = null;
        String response = "";
        String ctype = "application/x-www-form-urlencoded;charset=" + charset;
        conn = getConnection(new URL(url), METHOD_POST, ctype);
        conn.setConnectTimeout(connectTimeOut);
        conn.setReadTimeout(readTimeOut);
        conn.getOutputStream().write(buildQuery(params, charset).getBytes(charset));
        response = getResponseAsString(conn);
        return response;
    }

    /**
     * 返回图片二进制数据
     * @Title: doGetImg 
     * @param url
     * @param params
     * @param charset
     * @param connectTimeOut
     * @param readTimeOut
     * @return
     * @throws IOException
     * @return: byte[]
     */
    public static byte[] doGetImg(String url, Map<String, String> params, String charset,
            int connectTimeOut, int readTimeOut) throws IOException{
    	if (isEmpty(url) || params == null) {
            return null;
        }
        url += "?" + buildQuery(params, charset);
        HttpURLConnection conn = null;
        String ctype = "application/x-www-form-urlencoded;charset=" + charset;
        conn = getConnection(new URL(url), METHOD_GET, ctype);
//        response = getResponseAsString(conn);
    	return getResponseAsImg(conn);
    }
    
    /**
     * 获取图片流
     * @Title: doGetImgStream 
     * @param url
     * @param params
     * @param charset
     * @param connectTimeOut
     * @param readTimeOut
     * @return
     * @throws IOException
     * @return: InputStream
     */
    public static InputStream doGetImgStream(String url, Map<String, String> params, String charset,
            int connectTimeOut, int readTimeOut)throws IOException{
    	if (isEmpty(url) || params == null) {
            return null;
        }
        url += "?" + buildQuery(params, charset);
        HttpURLConnection conn = null;
        String ctype = "application/x-www-form-urlencoded;charset=" + charset;
        conn = getConnection(new URL(url), METHOD_GET, ctype);
        return conn.getInputStream();
    }
    
    public static void doGetImp2File(String url, Map<String, String> params,String path, String charset,
            int connectTimeOut, int readTimeOut)throws IOException{
    	
    	if (isEmpty(url) || params == null) {
            return ;
        }
        url += "?" + buildQuery(params, charset);
        HttpURLConnection conn = null;
        String ctype = "application/x-www-form-urlencoded;charset=" + charset;
        conn = getConnection(new URL(url), METHOD_GET, ctype);
        InputStream in =conn.getInputStream();
    }
    /**
     * 
     * @param params 请求参数
     * @return 构建query
     */
    public static String buildQuery(Map<String, String> params, String charset) {
        if (params == null || params.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Entry<String, String> entry : params.entrySet()) {
            if (first) {
                first = false;
            } else {
                sb.append("&");
            }
            String key = entry.getKey();
            String value = entry.getValue();
            if (null!=key&&null!=value) {
                try {
                    sb.append(key).append("=").append(URLEncoder.encode(value, charset));
                } catch (UnsupportedEncodingException e) {}
            }
        }
        //System.out.println(sb.toString());
        return sb.toString();

    }

    public static Map<String, String> splitQuery(String query, String charset) {
        Map<String, String> ret = new HashMap<String, String>();
        if (!isEmpty(query)) {
            String[] splits = query.split("\\&");
            for (String split : splits) {
                String[] keyAndValue = split.split("\\=");
                if (areNotEmpty(keyAndValue) && keyAndValue.length == 2) {
                    try {
                        ret.put(keyAndValue[0], URLDecoder.decode(keyAndValue[1], charset));
                    } catch (UnsupportedEncodingException e) {}
                }
            }
        }
        return ret;
    }
    public static String getResponseAsString(HttpURLConnection conn) throws IOException {
    	String charset = getResponseCharset(conn.getContentType());
        InputStream es = conn.getErrorStream();
        if (es == null) {
            return getStreamAsString(conn.getInputStream(), charset);
        } else {
            String msg = getStreamAsString(es, charset);
            if (isEmpty(msg)) {
                throw new IOException(conn.getResponseCode() + " : " + conn.getResponseMessage());
            } else {
                throw new IOException(msg);
            }
        }

    }
    
    /**
     * 解析图片
     * @Title: getResponseAsImg 
     * @Description: TODO
     * @param conn
     * @return
     * @throws IOException
     * @return: byte[]
     */
    private static byte[] getResponseAsImg(HttpURLConnection conn) throws IOException {
    	String charset = getResponseCharset(conn.getContentType());
        InputStream es = conn.getErrorStream();
        byte[] img =null;
        if (es == null) {
        	String type =conn.getHeaderField("Content-Type");
        	int contentLength = conn.getContentLength();
        	if(isEmpty(type) || -1 ==type.indexOf("image")){
        		throw new IOException("http 返回不是图片类型");
        	}
        	img = new byte[contentLength];
        	conn.getInputStream().read(img);
        	return img;
        } else {
            String msg = getStreamAsString(es, charset);
            if (isEmpty(msg)) {
                throw new IOException(conn.getResponseCode() + " : " + conn.getResponseMessage());
            } else {
                throw new IOException(msg);
            }
        }

    }

    private static String getStreamAsString(InputStream input, String charset) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new InputStreamReader(input, charset));
            String str;
            while ((str = bf.readLine()) != null) {
                sb.append(str);
            }
            return sb.toString();
        } finally {
            if (bf != null) {
                bf.close();
                bf = null;
            }
        }

    }

    private static String getResponseCharset(String ctype) {
        String charset = DEFAULT_CHARSET;
        if (!isEmpty(ctype)) {
            String[] params = ctype.split("\\;");
            for (String param : params) {
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("\\=");
                    if (pair.length == 2) {
                        charset = pair[1].trim();
                    }
                }
            }
        }
        return charset;
    }
    
    /**
     * 判断字符串为空
     * 
     * @param str 字符串信息
     * @return true or false
     */
    private static boolean isEmpty(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符数组，不为空
     * 
     * @param values 字符数组
     * @return true or false
     */
    private static boolean areNotEmpty(String... values) {
        boolean result = true;
        if (values == null || values.length == 0) {
            result = false;
        } else {
            for (String value : values) {
                result &= !isEmpty(value);
                if (result == false) {
                    return result;
                }
            }
        }
        return result;
    }
}

package com.ysughw.bootdemo.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
public class FileSpider {
     
    /**
     * 下载文件
     * @param strUrl 源文件路径   path 文件保存路径
     * @return
     */
    public static void download(String srcUrl,String destPath){
           URL url = null;
           try {
                  url = new URL(srcUrl);
           } catch (MalformedURLException e2) {
                 e2.printStackTrace();
                 return;
           }
 
           InputStream is = null;
            try {
                is = url.openStream();
				FileUtils.copyInputStreamToFile(is, new File(destPath));
            } catch (IOException e1) {
                e1.printStackTrace();
                return;
            }
        }
 
}

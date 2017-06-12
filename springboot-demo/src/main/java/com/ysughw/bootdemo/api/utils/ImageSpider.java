package com.ysughw.bootdemo.api.utils;

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
 
import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
public class ImageSpider {
     
    /**
     * 下载图片
     * 
     * @param strUrl 源文件路径   path 文件保存路径
     * @return
     */
    public static void download(String strUrl,String path){
           URL url = null;
           try {
                  url = new URL(strUrl);
           } catch (MalformedURLException e2) {
                 e2.printStackTrace();
                 return;
           }
 
           InputStream is = null;
            try {
                is = url.openStream();
            } catch (IOException e1) {
                e1.printStackTrace();
                return;
            }
 
            OutputStream os = null;
            try{
                os = new FileOutputStream(path);
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while((bytesRead = is.read(buffer,0,8192))!=-1){
                    os.write(buffer,0,bytesRead);
           }
           }catch(FileNotFoundException e){
               e.printStackTrace();
               return;
           } catch (IOException e) {
               e.printStackTrace();
               return;
          }
        }
 
 
    /**
     * @param args
     */
    public static void main(String[] args) {
         
        Document doc;
        try {
        	String html = "<img width=\"418\" height=\"63\" src=\"http://106.3.131.43/QaRes/63599000/63599000-70168369241371.jpg\" >";
        	doc = Jsoup.parse(html);
//            doc = Jsoup.connect("http://www.oschina.net/code/tag/jsoup").userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31").get();
            Elements imgElements = doc.getElementsByTag("img");
            Set<String> imgSrcSet = new HashSet<String>();
            for (Element img : imgElements) {
                String imgSrc = img.attr("abs:src");
                imgSrcSet.add(imgSrc);
            }
            System.out.println("图片总数："+imgSrcSet.size());
            Iterator<String> i = imgSrcSet.iterator();
            while(i.hasNext()){
                String imgSrc = (String)i.next();
                String imgName = FilenameUtils.getName(imgSrc);
                if (imgName.indexOf(".")!=-1) {
                    if (imgName.indexOf("?")>-1) {
                        imgName = imgName.substring(0, imgName.indexOf("?"));
                    }
                    String saveImagePath = "D:/img/"+imgName;
                    System.out.println("图片抓取开始：");
                    download(imgSrc,saveImagePath);
                    System.out.println("图片抓取结束："+imgSrc+" 保存路径："+saveImagePath);
                }
            }  
 
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
 
}

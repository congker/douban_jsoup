package yunnex.saofu.mobile.controller.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by wencong on 2017/4/21.
 */
public class Demo1 {

     public static final  String url ="http://www.dbmeinv.com/?p=";
     public static final  String picPath ="E:\\Download\\douban";
     public static  String USER_AGENT ="Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0";

    public static void main(String[] args) {
        System.out.println("下载完的图片位于E:\\Download\\douban");

        for (int i=0; i<50; i++){
            try {

                Document doc= Jsoup.connect(url+i).userAgent(USER_AGENT).timeout(3000).data("pager_offset",i+1+"").post();
                Elements img=doc.select("img");
                for (Element ele:img){
                  String src=  ele.absUrl("src");
                  getImage(src);
                }
            }catch (IOException e){
               e.printStackTrace();
            }
        }
        System.out.println("图片下载足够多了");
        System.out.println("图片下载完成");
    }


    public static void getImage(String src){
        int indexName= src.lastIndexOf("/");
        String name= src.substring(indexName,src.length());
        InputStream in= null;
        OutputStream out=null;
          try{

              URL url=new URL(src);
              in=url.openStream();

             File files=new  File(picPath);
             if (!files.exists()){
                 files.mkdirs();
             }
            out=new  BufferedOutputStream(new FileOutputStream(files+name));
             for (int b;(b=in.read())!=-1;)
                 out.write(b);
          }catch (IOException e){
              e.printStackTrace();
          }finally {
              try{
                  in.close();
                  out.close();
              }catch (IOException e){
                  e.printStackTrace();
              }

          }

    }
}

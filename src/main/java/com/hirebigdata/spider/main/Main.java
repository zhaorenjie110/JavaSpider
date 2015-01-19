package com.hirebigdata.spider.main;

import com.hirebigdata.spider.utils.Mongo;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/1/15.
 */
public class Main {
    static final String DBname = "scrapy2";
    static int count = 1;
    public static void main(String[] args){
        Format f = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        while(true) {
            String uid = new Mongo().getUserid(DBname, "zhihu_user_data_ids");
            new Mongo().startCrawl(DBname,"zhihu_user_data_ids",uid);//开始爬取
            System.out.print(count+":"+uid);
            String ret= new Spider().spiderContent(uid);
            if(ret.equals("success")){
                new Mongo().finishCrawl(DBname, "zhihu_user_data_ids", uid);//完成爬取
            }else{
//                new Mongo().errorCrawl(DBname, "zhihu_user_data_ids", uid);//错误爬取
            }
            System.out.println("->finish "+ret+" "+f.format(new Date()));
            count++;
        }
    }
}

package mrgood.com.mvpdemo.constent;

/**
 * Created by Administrator on 2017/1/24 0024.
 */

public interface UrlContent {
    interface Test12306{
        String HOST = "https://kyfw.12306.cn";
        String OTN = "/otn";
    }

    interface showapi{
        String HOST = "http://route.showapi.com";
        String NEWS = "/109-35";
        String appid = "31392";
        String sign = "57eafacb7ef44907a0fb38e7f039b83d";
    }
}

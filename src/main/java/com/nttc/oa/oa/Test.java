package com.nttc.oa.oa;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.taobao.api.ApiException;

import java.util.Date;

/**
 * 功能：
 *
 * @author lijing
 * @date 2020/3/27 12:01
 */
public class Test {
    public static void main(String[] args) throws ApiException {
        scheduleList();
    }

    /**
     * 测试连通性
     */
    public static String getAccessToken(){
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey("dingqnfk6dvdk2pc8niz");
        request.setAppsecret("6MeqRXE7NXvUzv_jmrlaF8TFggohqVkubUk9FcTw-LscSs4LgLJteFAX9_4oiH2Q");
        request.setHttpMethod("GET");
        OapiGettokenResponse response=null;
        try {
             response = client.execute(request);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return response.getAccessToken();
    }


    /**
     * 获得排班信息
     * @throws ApiException
     */
    public static void scheduleList() throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/attendance/schedule/listbyday");
        OapiAttendanceScheduleListbydayRequest req = new OapiAttendanceScheduleListbydayRequest();
        req.setOpUserId(getUserId() );
        req.setUserId(getUserId() );
        req.setDateTime(new Date().getTime());
        OapiAttendanceScheduleListbydayResponse rsp = client.execute(req, getAccessToken());
        System.out.println(rsp.getBody());
    }

    /**
     * 获取用户userid
     * @return
     * @throws ApiException
     */
    public static String getUserId() throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/get_by_mobile");
        OapiUserGetByMobileRequest request = new OapiUserGetByMobileRequest();
        request.setMobile("18398615058");

        OapiUserGetByMobileResponse execute = client.execute(request, getAccessToken());
        return execute.getUserid();

    }
}

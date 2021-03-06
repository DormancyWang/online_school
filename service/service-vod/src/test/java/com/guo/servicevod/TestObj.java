package com.guo.servicevod;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;

import java.util.List;

public class TestObj {

    /**
     * endpoint: oss-cn-beijing.aliyuncs.com
     *       keyid: LTAI5tQXwarrffJxLiSPNyiy
     *       keysecret: eFl4vkyYnoKC8J2YsNHJyexis8LFCX
     *       bucketname: guo-edu
     * @param
  */

    /*获取播放地址函数*/
    public static GetPlayInfoResponse getPlayInfo(DefaultAcsClient client) throws Exception {
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId("df2a7fa951ab4d339b3f411bbd83c300");
        return client.getAcsResponse(request);
    }

    /*以下为调用示例*/
    public static void main(String[] argv) throws ClientException {
//        DefaultAcsClient client = initVodClient("<Your AccessKeyId>", "<Your AccessKeySecret>");
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tQXwarrffJxLiSPNyiy", "eFl4vkyYnoKC8J2YsNHJyexis8LFCX");
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        try {
            response = getPlayInfo(client);
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
            }
            //Base信息
            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }
}

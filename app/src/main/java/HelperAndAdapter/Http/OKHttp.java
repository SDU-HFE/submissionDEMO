package HelperAndAdapter.Http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public  class OKHttp {
    public static String msg;
    public static int length;
    public static String[] target;
    private static void sendOkhttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .get()
                .build();
        client.newCall(request).enqueue(callback);
    }
    public static void sendRequest(String address){
        sendOkhttpRequest(address, new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //获取返回数据并以String格式保存
                String responseData = response.body().string();
                //将String格式转换为json格式
                JSONObject obj = JSON.parseObject(responseData);
                msg =(String) obj.get("msg");
                //获取JSONArray
                JSONArray array=obj.getJSONArray("data");
                //将JSONArray中每个位置分到JSONobject数组里
                 length=array.size();
                 target=new String[length];
                for (int i=0;i<length;i++) {
                    JSONObject TARGET=array.getJSONObject(i);
                   target[i]=TARGET.getString("target");
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                //打印异常栈
                e.printStackTrace();
            }
        });
    }
}

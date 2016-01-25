package com.xxx.web.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class JsonResponse {

    public static String badResult(String cause) {
        JSONObject result = new JSONObject();
        result.put("code", 1);
        result.put("info", cause);
        return "@" + result.toJSONString();
    }

    public static String badResultExistencePhone(String cause) {
        JSONObject result = new JSONObject();
        result.put("code", 2);
        result.put("info", cause);
        return "@" + result.toJSONString();
    }

    public static String badResultExistenceName(String cause) {
        JSONObject result = new JSONObject();
        result.put("code", 3);
        result.put("info", cause);
        return "@" + result.toJSONString();
    }

    public static String badResult2(String cause) {
        JSONObject result = new JSONObject();
        result.put("code", 2);
        result.put("info", cause);
        return "@" + result.toJSONString();
    }

    public static String badResult3(String cause) {
        JSONObject result = new JSONObject();
        result.put("code", 3);
        result.put("info", cause);
        return "@" + result.toJSONString();
    }


    public static String jsonStr(Object object) {
        return "@" + JSONObject.toJSONString(object);
    }

    /**
     * jsonp请求失败
     *
     * @param cause
     * @param callback
     * @return
     */
    public static String badResult(String cause, String callback) {
        JSONObject result = new JSONObject();
        result.put("code", 1);
        result.put("info", cause);
        return "@" + callback + "(" + result.toJSONString() + ")";
    }

    public static String badResult(Map<String, String> cause) {
        JSONObject result = new JSONObject();
        result.put("code", 1);
        result.put("info", cause);
        return "@" + result.toJSONString();
    }

    /**
     * 区分错误类型
     *
     * @param type
     * @param cause
     * @return
     */
    public static String badResult(int type, String cause) {
        JSONObject result = new JSONObject();
        result.put("code", 1);
        result.put("info", ImmutableMap.of("type", type, "msg", cause));
        return "@" + result.toJSONString();
    }

    public static String ok() {
        JSONObject result = new JSONObject();
        result.put("code", 0);
        result.put("info", "成功");
        return "@" + result.toString();
    }

    public static String okWithEmpty() {
        JSONObject result = new JSONObject();
        result.put("code", 0);
        result.put("info", Collections.emptyList());
        return "@" + result.toString();
    }

    public static String ok(String key, Object value) {
        JSONObject result = new JSONObject();
        result.put("code", 0);
        result.put("info", ImmutableMap.of(key, value));
        return "@" + result.toJSONString();
    }

    public static String ok(Object object) {
        JSONObject result = new JSONObject();
        result.put("code", 0);
        result.put("info", object);
        return "@" + result.toJSONString();
    }


    public static String jsonp(Object object, String callback) {
        JSONObject result = new JSONObject();
        result.put("code", 0);
        result.put("info", object);
        return "@" + callback + "(" + result.toJSONString() + ")";
    }

    /**
     * 可以通过map形式传递参数，但是如果是ok，msg这两个参数的值会被覆盖掉
     *
     * @param params
     * @return
     */
    public static String ok(Map<String, Object> params) {
        JSONObject result = new JSONObject();
        result.put("code", 0);
        result.put("info", params);
        return "@" + result.toJSONString();
    }

    /**
     * 可以通过map形式传递参数，但是如果是ok，msg这两个参数的值会被覆盖掉
     *
     * @param params
     * @return
     */
    public static String ok(ImmutableMap<String, Object> params) {
        JSONObject result = new JSONObject();
        result.put("code", 0);
        result.put("info", params);

        return "@" + result.toJSONString();
    }

    public static void main(String[] args) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("test", 1);
        params.put("test1", "ok");
        System.out.println(ok(params));
    }

    private JsonResponse() {
    }
}

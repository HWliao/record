package com.jjshome.im.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * 控制器工具类
 * Created by liaohongwei on 2016/9/22.
 */
public class ControllerUtil {

  public static int COMMON_SUCCESS_NO = 1;

  public static String COMMON_SUCCESS_STR = "操作成功!";

  public static int COMMON_FAIL_NO = 0;

  public static String COMMON_FAIL_STR = "操作失败!";

  public static int SYS_FAIL_NO = -1;

  public static String SYS_FAIL_STR = "系统异常!";

  public static String KEY_INFO_NO = "infoNo";

  public static String KEY_INFO_STR = "infoStr";

  public static String KEY_DATA = "data";

  public static Object wrapRes(int no,String str,Object data){
    Map<String,Object> result = new HashMap<String,Object>();
    result.put(KEY_INFO_NO,no);
    result.put(KEY_INFO_STR,str);
    result.put(KEY_DATA,data);
    return  result;
  }

  public static Object wrapFailRes(){
    return wrapRes(COMMON_FAIL_NO,COMMON_FAIL_STR,null);
  }

  public static Object wrapSuccessRes(Object data){
    return wrapRes(COMMON_SUCCESS_NO,COMMON_SUCCESS_STR,data);
  };
}

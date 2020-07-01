package com.gukki.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ResUtil {
    public static void ResponseJSON(ServletResponse resp, Map<String, Object> resultMap) {
        PrintWriter pw = null;
        try {
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("application/json");
            pw = resp.getWriter();
            pw.println(JSON.toJSONString(resultMap));
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            if (pw != null) {
                pw.flush();
                pw.close();
            }
        }
    }

    public static Map<String, Object> Success() {
        Map<String, Object> map = new HashMap<>();
        map.put("result", "Success");
        map.put("code", 200);
        return map;
    }

    public static Map<String, Object> Fail() {
        Map<String, Object> map = new HashMap<>();
        map.put("result", "Fail");
        map.put("code", 500);
        return map;
    }

    public static Map<String, Object> CustomResult(int code, String msg) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", msg);
        map.put("code", code);
        return map;
    }
}

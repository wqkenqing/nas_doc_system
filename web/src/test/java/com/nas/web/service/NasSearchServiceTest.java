package com.nas.web.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nas.web.util.CommonUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class NasSearchServiceTest {
    @Test
    public void testDemoData() {
        String res = CommonUtil.stream2String(System.class.getResourceAsStream("/demo/demo.txt"), "utf8");
        JSONArray nasDocsArray = JSONArray.parseArray(res);
        List tableDataList = new ArrayList();
        for (Object nasObject : nasDocsArray) {
            JSONObject nasJson = (JSONObject) nasObject;
            JSONObject nasSource = nasJson.getJSONObject("_source");
            String[] rows = new String[4];
            rows[0] = (String) nasSource.get("pbase");
            rows[1] = (String) nasSource.get("pfile");
            rows[2] = (String) nasSource.get("patheFileName");
            rows[3] = (String) nasSource.get("pathFileSuffix");
            tableDataList.add(rows);
        }
        System.out.println(JSONObject.toJSONString(tableDataList));
    }
}

package com.nas.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nas.web.entity.QueryVo;
import com.nas.web.service.NasSearchService;
import com.nas.web.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/nas")
@CrossOrigin
public class NasSearchController {
    @Autowired
    NasSearchService nasSearchService;

    @RequestMapping("/demo")
    public String loadDemoData() throws IOException {
        ClassPathResource resource = new ClassPathResource("demo/demo.txt");
        String res = CommonUtil.stream2String(resource.getInputStream(), "utf8");
        JSONArray nasDocsArray = JSONArray.parseArray(res);
        List tableDataList = new ArrayList();
        for (Object nasObject : nasDocsArray) {
            JSONObject nasJson = (JSONObject) nasObject;
            JSONObject nasSource = nasJson.getJSONObject("_source");
            String[] rows = new String[4];
            rows[0] = (String) nasSource.get("pbase");
            rows[1] = (String) nasSource.get("pathFile");
            rows[2] = (String) nasSource.get("pathFileName");
            rows[3] = (String) nasSource.get("pathFileSuffix");
            tableDataList.add(rows);
        }
        log.info("json data:{}", JSONObject.toJSONString(tableDataList));
        return JSONObject.toJSONString(tableDataList).replace("null", "暂无");
    }

    @RequestMapping("/nasdocs")
    public String loadNasDocs() {
        String res = nasSearchService.findAll("nas_docs");
        return res;
    }


}

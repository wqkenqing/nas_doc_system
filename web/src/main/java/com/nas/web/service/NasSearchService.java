package com.nas.web.service;

import com.alibaba.fastjson.JSONObject;
import com.nas.web.entity.NasDocs;
import com.nas.web.repository.NasDocsRepository;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class NasSearchService extends BaseElasticService {
    @Autowired
    ElasticSearchService searchService;

    @Autowired
    NasDocsRepository nasDocsRepository;

    public String findAll(String indexName) {
        Page page = nasDocsRepository.findAll(PageRequest.of(0, 1000));
        List<NasDocs> nasDocs = page.getContent();
        List<String[]> tableDataList = new ArrayList<>();
        for (NasDocs nasObject : nasDocs) {
            String[] rows = new String[4];
            rows[0] = (String) nasObject.getPbase();
            rows[1] = (String) nasObject.getPathFile();
            rows[2] = (String) nasObject.getPathFileName();
            rows[3] = (String) nasObject.getPathFileSuffix();
            tableDataList.add(rows);
        }
        return JSONObject.toJSONString(tableDataList);
    }
}

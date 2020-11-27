package com.nas.web.repository;

import com.nas.web.entity.NasDocs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 用户仓库
 *
 * @author lx
 * @since 2020/7/17 14:12
 */
public interface NasDocsRepository extends ElasticsearchRepository<NasDocs, String> {

}

package com.solr.opi;

import com.solr.model.CodeTableCode;
import org.apache.solr.handler.dataimport.DataSource;

import java.util.List;

/**
 * 基础信息加载程序。
 */
public interface IInfoLoader
{

    /**
     * 获取代码表  入乡随俗，使用solr的数据源实现，数据库查询
     * @param dataSource  solr的数据源
     * @return
     */
    List<CodeTableCode> getAllCodeTableCodes(DataSource dataSource);
}

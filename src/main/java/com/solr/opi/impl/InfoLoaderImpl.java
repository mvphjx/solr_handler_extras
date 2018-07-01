package com.solr.opi.impl;

import com.solr.model.CodeTableCode;
import com.solr.opi.IInfoLoader;
import org.apache.solr.handler.dataimport.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 信息加载的实现
 */
public class InfoLoaderImpl implements IInfoLoader
{
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Override
    public List<CodeTableCode> getAllCodeTableCodes(DataSource dataSource)
    {
        ArrayList<CodeTableCode> codes = new ArrayList<>();
        String query = "SELECT * FROM CODE_TABLE_CODE";
        Object data = dataSource.getData(query);
        Iterator iterator = (Iterator) data;
        while (iterator.hasNext()){
            CodeTableCode tableCode = new CodeTableCode();
            Map codeMap = (Map) iterator.next();
            tableCode.setCode(codeMap.get("CODE").toString());
            tableCode.setValue(codeMap.get("VALUE").toString());
            tableCode.setCodeTableName(codeMap.get("CODE_TABLE_NAME").toString());
            codes.add(tableCode);
        }
        LOG.info("InfoLoaderImpl:codes.size "+codes.size());
        return codes;
    }

}

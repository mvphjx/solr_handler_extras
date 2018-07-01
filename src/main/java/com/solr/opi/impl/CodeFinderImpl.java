package com.solr.opi.impl;

import com.solr.model.CodeTableCode;
import com.solr.opi.ICodeFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeFinderImpl implements ICodeFinder
{
    private Map<String, Map<String, String>> codeTableMaps = new HashMap<>();
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public boolean doInit(List<CodeTableCode> codes)
    {
        boolean result = true;
        for (CodeTableCode tableCode : codes)
        {
            Map<String, String> codeMap = codeTableMaps.get(tableCode.getCodeTableName());
            if (codeMap == null)
            {
                codeMap = new HashMap<>();
                codeTableMaps.put(tableCode.getCodeTableName(), codeMap);
            }
            codeMap.put(tableCode.getCode(), tableCode.getValue());
        }
        return result;
    }

    @Override
    public String findValue(String codeTableName, String code)
    {
        String value = code;
        Map<String, String> codeMap = codeTableMaps.get(codeTableName);
        if (codeMap != null && codeMap.get(code) != null)
        {
            value = codeMap.get(code);

        }
        return value;
    }
}

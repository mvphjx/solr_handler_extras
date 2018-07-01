package com.solr.transformers;
/**
 * dataimporthandler 翻译代码表
 * 		<entity name="" pk=""
 * 		transformer="com.CodeTransformer"
 * 	    ....
 *
 *  例如性别 sex：1  -》 sex：男
 */

import com.solr.opi.ICodeFinder;
import com.solr.util.ResourceHelper;
import org.apache.solr.handler.dataimport.Context;
import org.apache.solr.handler.dataimport.DataImporter;
import org.apache.solr.handler.dataimport.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;

public class CodeTransformer extends Transformer
{
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public Object transformRow(Map<String, Object> row, Context context)
    {
        String tableName = context.getSolrCore().getName();
        ICodeFinder codeFinder = ResourceHelper.getCodeFinder(context);
        List<Map<String, String>> fields = context.getAllEntityFields();
        for (Map<String, String> field : fields)
        {
            String name = field.get(DataImporter.NAME);
            String columnName = field.get(DataImporter.COLUMN);
            Object value = row.get(columnName);
            //需要配置两个field SEX  #SEX#  一个存放代码  一个存放文本
            if (name != null && name.contains("#"))
            {
                if (value != null)
                {
                    try
                    {
                        String text = codeFinder.findValue(columnName, value.toString());
                        row.put(name, text);
                    }
                    catch (Exception e)
                    {
                        LOG.error(e.toString(),e);
                    }
                }
            }
        }
        return row;
    }

}

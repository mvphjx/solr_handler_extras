package com.hisign.pu.abis.solr.transformers;

import com.hisign.pu.abis.solr.util.ABISResourceHelper;
import com.hisign.pu.comm.base.codeutil.ICodeFinder;
import com.hisign.pu.comm.base.util.ABISHelper;
import org.apache.solr.handler.dataimport.Context;
import org.apache.solr.handler.dataimport.DataImporter;
import org.apache.solr.handler.dataimport.DataSource;
import org.apache.solr.handler.dataimport.Transformer;

import java.util.List;
import java.util.Map;

public class AbisCodeTrimTransformer extends Transformer
{

    @Override
    public Object transformRow(Map<String, Object> row, Context context)
    {
        DataSource dataSource = context.getDataSource();
        String tableName = context.getSolrCore().getName();
        ICodeFinder codeFinder = ABISResourceHelper.getCodeFinder(dataSource);
        List<Map<String, String>> fields = context.getAllEntityFields();
        for (Map<String, String> field : fields) {
            String bty = field.get("BTY");
            if ("BTY".equals(bty))        {
                String columnName = field.get(DataImporter.COLUMN);
                Object value = row.get(columnName);

                if (!ABISHelper.isEmpty(value)){
                    String text = "查询失败";
                    try{
                        text = codeFinder.findValue("BTY", value.toString());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    row.put(columnName, text);
                }
            }
        }
        return row;
    }

}

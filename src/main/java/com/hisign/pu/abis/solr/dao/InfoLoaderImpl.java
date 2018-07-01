package com.hisign.pu.abis.solr.dao;

import com.hisign.pu.abis.orm.codetbl.ent.CodeTableCode;
import com.hisign.pu.abis.orm.util.ABISEntityHelper;
import com.hisign.pu.abis.solr.opi.IInfoLoader;
import org.apache.solr.handler.dataimport.DataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 信息加载的实现。
 *
 * @author 北京北大高科指纹技术有限公司
 * www.idfounder.com
 * 北京海鑫科金高科技股份有限公司
 * www.hisign.com.cn
 * 创建日期：2015年4月14日上午9:18:36
 */
public class InfoLoaderImpl implements IInfoLoader
{
    @Override
    public List<CodeTableCode> getAllCodeTableCodes(DataSource dataSource)
    {
        ArrayList<CodeTableCode> codes = new ArrayList<>();
        String query = "SELECT * FROM CODE_TABLE_CODE";
        Object data = dataSource.getData(query);
        List<Map> datas = (List) data;
        for (Map codeMap : datas)
        {
            CodeTableCode codeTableCode = ABISEntityHelper.toEntity(CodeTableCode.class, codeMap);
            codes.add(codeTableCode);

        }
        return codes;
    }

}

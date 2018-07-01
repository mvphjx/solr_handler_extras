package com.hisign.pu.abis.solr.util;

import com.hisign.pu.abis.orm.codetbl.ent.CodeTableCode;
import com.hisign.pu.abis.orm.util.CodeFinderImpl;
import com.hisign.pu.abis.solr.opi.IInfoLoader;
import com.hisign.pu.comm.base.codeutil.ICodeFinder;
import com.hisign.pu.comm.base.util.ABISHelper;
import org.apache.solr.handler.dataimport.DataSource;

import java.util.List;

/**
 *  访问指纹系统资源  工具类
 * @author 北京海鑫高科指纹技术有限公司
 *         www.idfounder.com
 *         北京海鑫科金高科技股份有限公司
 *         www.hisign.com.cn
 *         创建日期：   2018/6/29 16:56
 */
public class ABISResourceHelper
{
    private static IInfoLoader infoLoader;
    //代码表查询工具
    private static ICodeFinder codeFinder;
    public static ICodeFinder getCodeFinder(DataSource dataSource)
    {
        if(ABISHelper.isEmpty(codeFinder)){
            init(dataSource);
        }
        return codeFinder;
    }
    private static void init(DataSource dataSource){
        List<CodeTableCode> codes = infoLoader.getAllCodeTableCodes(dataSource);
        CodeFinderImpl codeFinderImpl = new CodeFinderImpl();
        codeFinder = codeFinderImpl;
        codeFinderImpl.doInit(codes);
    }
}

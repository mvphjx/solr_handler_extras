package com.solr.util;

import com.solr.model.CodeTableCode;
import com.solr.opi.ICodeFinder;
import com.solr.opi.IInfoLoader;
import com.solr.opi.impl.CodeFinderImpl;
import com.solr.opi.impl.InfoLoaderImpl;
import org.apache.solr.handler.dataimport.Context;
import org.apache.solr.handler.dataimport.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;

/**
 *  访问系统资源  工具类
 *         不清楚solr的对象加载模式，暂时用静态类实现
 *         创建日期：   2018/6/29 16:56
 */
public class ResourceHelper
{
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * 数据库访问工具
     */
    private static IInfoLoader infoLoader;
    /**
     * 代码表查询工具
     */
    private static ICodeFinder codeFinder;
    public static ICodeFinder getCodeFinder(){
        return codeFinder;
    }
    public static ICodeFinder getCodeFinder(Context context)
    {
        if(codeFinder==null){
            init(context);
        }
        return codeFinder;
    }

    /**
     * 查询/缓存  数据库资源
     * 实际上solr加载是单线程。synchronized只是为了显得专业
     * @param context
     */
    private static synchronized void init(Context context){
        if(codeFinder!=null){
            return;
        }
        String  dataSourceStr = context.getEntityAttribute("dataSource");
        //Gets a new DataSource instance
        DataSource dataSource = context.getDataSource(dataSourceStr);
        infoLoader = new InfoLoaderImpl();
        //列描述 TODO

        //代码表
        List<CodeTableCode> codes = infoLoader.getAllCodeTableCodes(dataSource);
        CodeFinderImpl codeFinderImpl = new CodeFinderImpl();
        codeFinder = codeFinderImpl;
        codeFinderImpl.doInit(codes);

        dataSource.close();
    }
}

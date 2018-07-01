package com.hisign.pu.abis.solr.opi;

import com.hisign.pu.abis.orm.codetbl.ent.CodeTableCode;
import org.apache.solr.handler.dataimport.DataSource;

import java.util.List;

/**
 * 基础信息加载程序。
 * @author 北京北大高科指纹技术有限公司
 *         www.idfounder.com
 *         北京海鑫科金高科技股份有限公司
 *         www.hisign.com.cn
 * 创建日期：2015年4月14日上午9:12:01
 */
public interface IInfoLoader
{
    List<CodeTableCode> getAllCodeTableCodes(DataSource dataSource);
}

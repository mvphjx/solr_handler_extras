package com.solr.model;

/**
 * 代码表实体类
 * 创建日期：   2018/6/29 23:06
 */
public class CodeTableCode
{
    private String codeTableName;
    private String code;
    private String value;

    public String getCodeTableName()
    {
        return codeTableName;
    }

    public void setCodeTableName(String codeTableName)
    {
        this.codeTableName = codeTableName;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
}

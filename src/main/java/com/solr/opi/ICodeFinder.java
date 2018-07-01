package com.solr.opi;

import com.solr.model.CodeTableCode;

import java.util.List;

public interface ICodeFinder
{
    boolean doInit(List<CodeTableCode> codes);

    String findValue(String codeTableName, String code);
}

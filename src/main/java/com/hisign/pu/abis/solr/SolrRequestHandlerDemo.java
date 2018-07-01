package com.hisign.pu.abis.solr;

import org.apache.solr.common.params.SolrParams;
import org.apache.solr.handler.RequestHandlerBase;
import org.apache.solr.request.SolrQueryRequest;
import org.apache.solr.response.SolrQueryResponse;

public class SolrRequestHandlerDemo extends RequestHandlerBase
{
    @Override
    public String getDescription()
    {
        return "Solr RequestHandler Demo";
    }


    public void handleRequestBody(SolrQueryRequest req, SolrQueryResponse res) throws Exception
    {
        SolrParams params = req.getParams();
        System.out.println("customized ......");
        res.add("query", req.getParamString());
    }

}

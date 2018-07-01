package com.solr.handler.component;

import org.apache.solr.handler.component.ResponseBuilder;
import org.apache.solr.handler.component.SearchComponent;
import org.apache.solr.search.DocIterator;
import org.apache.solr.search.DocList;
import org.apache.solr.search.DocListAndSet;

import java.io.IOException;

public  class MySearchComponent extends SearchComponent {

    @Override
    public void prepare(ResponseBuilder rb) throws IOException
    {

    }

    @Override
    public void process(ResponseBuilder rb) throws IOException
    {
        DocListAndSet results = rb.getResults();
        DocList docList = results.docList;
        DocIterator iterator = docList.iterator();


    }

    @Override
    public String getDescription()
    {
        return null;
    }
}

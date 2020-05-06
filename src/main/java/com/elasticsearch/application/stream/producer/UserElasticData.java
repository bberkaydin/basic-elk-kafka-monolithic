package com.elasticsearch.test.stream.producer;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

public class UserElasticData {

    public String getUser(String id){
        GetRequest request = new GetRequest("user-index","users",id);
        GetResponse getResponse = null;
        try {
            getResponse = client().get(request);
        } catch (java.io.IOException e){
            e.getLocalizedMessage();
        }
        return getResponse.getSourceAsString();
    }
    private RestHighLevelClient client() {

        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200", "localhost:9201")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

    //    public List<Map<String, Object>> getAllDocs(){
//        int scrollSize = 1000;
//        List<Map<String,Object>> esData = new ArrayList<Map<String,Object>>();
//        SearchResponse response = null;
//        int i = 0;
//        while( response == null || response.getHits().hits().length != 0){
//            response = client().prepareSearch(indexName)
//                    .setTypes(typeName)
//                    .setQuery(QueryBuilders.matchAllQuery())
//                    .setSize(scrollSize)
//                    .setFrom(i * scrollSize)
//                    .execute()
//                    .actionGet();
//            for(SearchHit hit : response.getHits()){
//                esData.add(hit.getSource());
//            }
//            i++;
//        }
//        return esData;
//    }

}

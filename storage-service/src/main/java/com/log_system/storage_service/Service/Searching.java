package com.log_system.storage_service.Service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.json.JsonData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class Searching {
    private final ElasticsearchClient client;

    public List<Map<String,Object>> search(String eventType,String source,Long from,Long to,int page,int size){
        try{
            SearchResponse<Map<String,Object>> response=client.search(
                            s -> s
                                    .index("logs")
                                    .from(page*size)
                                    .size(size)
                                    .sort(sort->sort
                                            .field(f->f
                                                    .field("timestamp")
                                                    .order(SortOrder.Desc)
                                            )
                                    )
                                    .query(q -> q
                                            .bool(b -> {
                                                if (eventType != null) {
                                                    b.must(m -> m
                                                            .term(t -> t
                                                                    .field("eventType.keyword")
                                                                    .value(v -> v.stringValue(eventType))
                                                            )
                                                    );
                                                }

                                                if (source != null) {
                                                    b.must(m -> m
                                                            .term(t -> t
                                                                    .field("source.keyword")
                                                                    .value(v -> v.stringValue(source))
                                                            )
                                                    );
                                                }
                                                if(from!=null && to!=null){
                                                    b.must(m->m.range(r->r
                                                            .field("timestamp")
                                                            .gte(JsonData.of(from))
                                                            .lte(JsonData.of(to))
                                                    )); s
                                                }

                                                return b;
                                            })
                                    ),
                            (Class<Map<String, Object>>) (Class<?>) Map.class
                    );
            return response.hits().hits().stream()
                    .map(hit->hit.source())
                    .toList();
        }catch (Exception ex){
            throw new RuntimeException("Search failed",ex);
        }
    }
}

package com.log_system.storage_service.Service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.log_system.storage_service.document.LogDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogService {
    private final ElasticsearchClient client;

    public void index(LogDocument doc){
        try{
            client.index(i->i
                    .index("logs")
                    .id(doc.getEventId())
                    .document(doc)
            );
        }
        catch (Exception ex){
            throw new RuntimeException("Failed to index log",ex);
        }
    }
}

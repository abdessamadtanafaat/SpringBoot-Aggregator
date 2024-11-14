package com.example.demo.Completable_Future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.example.demo.AggregatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AggregatorServiceSyncCompletableFuture {
    @Autowired
    private RestTemplate restTemplate;

    public AggregatedResponse getAggregatedResponseSyncCompletableFuture() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();

        CompletableFuture<String> result1 = CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject("https://jsonplaceholder.typicode.com/photos", String.class));
        CompletableFuture<String> result2 = CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", String.class));
        CompletableFuture<String> result3 = CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject("https://jsonplaceholder.typicode.com/comments", String.class));

        // Combiner les résultats une fois toutes les tâches terminées
        CompletableFuture<Void> allOf = CompletableFuture.allOf(result1, result2, result3);
        allOf.join(); // Attendre que toutes les tâches soient terminées

        String S1 = result1.get();
        String S2 = result2.get();
        String S3 = result3.get();

        long end = System.currentTimeMillis();
        System.out.println("Execution time For completable Future : " + (end - start) + "ms");

        return new AggregatedResponse(S1, S2, S3);
    }
}

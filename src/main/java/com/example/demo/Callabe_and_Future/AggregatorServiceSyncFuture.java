package com.example.demo.Callabe_and_Future;

import com.example.demo.AggregatedResponse;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class AggregatorServiceSyncFuture {
    @Autowired
    private RestTemplate restTemplate;

    public AggregatedResponse getAggregatedResponseSyncFuture() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Callable<String> call1 = () -> restTemplate.getForObject("https://jsonplaceholder.typicode.com/photos", String.class);
        Callable<String> call2 = () -> restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", String.class);
        Callable<String> call3 = () -> restTemplate.getForObject("https://jsonplaceholder.typicode.com/comments", String.class);

        Future<String> result1 = executor.submit(call1);
        Future<String> result2 = executor.submit(call2);
        Future<String> result3 = executor.submit(call3);

        String S1 = result1.get();
        String S2 = result2.get();
        String S3 = result3.get();

        executor.shutdown();

        long end = System.currentTimeMillis();
        System.out.println("Execution time For Callable and Future : " + (end - start) + "ms");

        return new AggregatedResponse(S1, S2, S3);
    }
}

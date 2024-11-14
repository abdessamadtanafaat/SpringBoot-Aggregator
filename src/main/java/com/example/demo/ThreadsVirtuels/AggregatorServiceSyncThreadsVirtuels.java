package com.example.demo.ThreadsVirtuels;

import com.example.demo.AggregatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AggregatorServiceSyncThreadsVirtuels {
    @Autowired
    private RestTemplate restTemplate;

    public AggregatedResponse getAggregatedResponseSyncThreadsVirtuels() throws InterruptedException {
        long start = System.currentTimeMillis();

        // Variables to hold the results of each request
        final String[] S1 = new String[1];
        final String[] S2 = new String[1];
        final String[] S3 = new String[1];

        // Create and start virtual threads to execute HTTP requests
        Thread vThread1 = Thread.startVirtualThread(() ->
                S1[0] = restTemplate.getForObject("https://jsonplaceholder.typicode.com/photos", String.class));
        Thread vThread2 = Thread.startVirtualThread(() ->
                S2[0] = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", String.class));
        Thread vThread3 = Thread.startVirtualThread(() ->
                S3[0] = restTemplate.getForObject("https://jsonplaceholder.typicode.com/comments", String.class));

        // Wait for all threads to complete
        vThread1.join();
        vThread2.join();
        vThread3.join();

        long end = System.currentTimeMillis();
        System.out.println("Execution time for Threads Virtueles : " + (end - start) + "ms");

        // Return the aggregated response using the results from each thread
        return new AggregatedResponse(S1[0], S2[0], S3[0]);
    }
}

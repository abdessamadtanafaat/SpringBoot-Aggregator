package com.example.demo.Fork_Join_Pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import com.example.demo.AggregatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AggregatorServiceSyncForkJoinPool {
    @Autowired
    private RestTemplate restTemplate;

    private class FetchTask extends RecursiveTask<String> {
        private String url;

        public FetchTask(String url) {
            this.url = url;
        }

        @Override
        protected String compute() {
            return restTemplate.getForObject(url, String.class);
        }
    }

    public AggregatedResponse getAggregatedResponseSyncForkJoinPool() {
        long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool(3);

        FetchTask task1 = new FetchTask("https://jsonplaceholder.typicode.com/photos");
        FetchTask task2 = new FetchTask("https://jsonplaceholder.typicode.com/posts");
        FetchTask task3 = new FetchTask("https://jsonplaceholder.typicode.com/comments");

        forkJoinPool.execute(task1);
        forkJoinPool.execute(task2);
        forkJoinPool.execute(task3);

        String S1 = task1.join();
        String S2 = task2.join();
        String S3 = task3.join();

        long end = System.currentTimeMillis();
        System.out.println("Execution time For ForkJoinPool : " + (end - start) + "ms");

        return new AggregatedResponse(S1, S2, S3);
    }
}

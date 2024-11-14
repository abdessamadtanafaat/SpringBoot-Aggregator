package com.example.demo;
import com.example.demo.Callabe_and_Future.AggregatorServiceSyncFuture;
import com.example.demo.Completable_Future.AggregatorServiceSyncCompletableFuture;
import com.example.demo.Fork_Join_Pool.AggregatorServiceSyncForkJoinPool;
import com.example.demo.ThreadsVirtuels.AggregatorServiceSyncThreadsVirtuels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class AggregatorController {
    @Autowired
    private AggregatorServiceSync aggregatorServiceSync;
    @Autowired
    private AggregatorServiceSyncFuture aggregatorServiceSyncFuture;
    @Autowired
    private AggregatorServiceSyncCompletableFuture aggregatorServiceSyncCompletableFuture;

    @Autowired
    private AggregatorServiceSyncForkJoinPool aggregatorServiceSyncForkJoinPool;

    @Autowired
    private AggregatorServiceSyncThreadsVirtuels aggregatorServiceSyncThreadsVirtuels;
    @GetMapping("/aggregateSync")
    public AggregatedResponse getAggregatedResponseSync() {
        return aggregatorServiceSync.getAggregatedResponseSync();
    }
    @GetMapping("/aggregateSyncFuture")
    public AggregatedResponse getAggregatedResponseSyncFuture() throws ExecutionException, InterruptedException {
        return aggregatorServiceSyncFuture.getAggregatedResponseSyncFuture();
    }
    @GetMapping("/aggregateSyncCompletableFuture")
    public AggregatedResponse getAggregatedResponseSyncCompletableFuture() throws ExecutionException, InterruptedException {
        return aggregatorServiceSyncCompletableFuture.getAggregatedResponseSyncCompletableFuture();
    }

    @GetMapping("/aggregateSyncForkJoinPool")
    public AggregatedResponse getAggregatedResponseSyncForkJoinPool() throws ExecutionException, InterruptedException {
        return aggregatorServiceSyncForkJoinPool.getAggregatedResponseSyncForkJoinPool();
    }

    @GetMapping("/aggregateSyncThreadsVirtuels")
    public AggregatedResponse getAggregatedResponseSyncThreadsVirtuels() throws ExecutionException, InterruptedException {
        return aggregatorServiceSyncThreadsVirtuels.getAggregatedResponseSyncThreadsVirtuels ();
    }

    }


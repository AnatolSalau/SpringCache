package com.example.spring_default_cache.services;

import com.example.spring_default_cache.model.MyRecord;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class MyRecordService {
      @Cacheable(cacheNames = "recordCache", key = "#recordId")
      public MyRecord getFromCacheOrCreate(int recordId) {
            System.out.println("getRecordById start");
            try {
                  Thread.sleep(3000);
            } catch (InterruptedException e) {
                  throw new RuntimeException(e);
            }
            System.out.println("getRecordById end");
            return new MyRecord(recordId, "My record " + Integer.toString(recordId), LocalTime.now());
      }

      @CachePut(cacheNames = "recordCache", key = "#recordId")
      public MyRecord updateInCacheOrCreate(int recordId) {
            System.out.println("updateInCacheOrCreate start");
            try {
                  Thread.sleep(3000);
            } catch (InterruptedException e) {
                  throw new RuntimeException(e);
            }
            System.out.println("updateInCacheOrCreate end");
            return new MyRecord(recordId, "My record " + Integer.toString(recordId), LocalTime.now());
      }

      @CacheEvict(cacheNames = "recordCache", key = "#recordId")
      public MyRecord deleteRecord(int recordId) {
            // some logic

            return null;
      }
}

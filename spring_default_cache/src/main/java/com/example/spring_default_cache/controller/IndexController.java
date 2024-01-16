package com.example.spring_default_cache.controller;

import com.example.spring_default_cache.model.MyRecord;
import com.example.spring_default_cache.services.MyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IndexController {
      @Autowired
      private MyRecordService myRecordService;

      @GetMapping("{id}")
      MyRecord getRecord(@PathVariable(name = "id", required = false) Integer recordId) {
            MyRecord fromCacheOrCreate = myRecordService.getFromCacheOrCreate(recordId);
            return fromCacheOrCreate;
      }

      @PutMapping("{id}")
      MyRecord updateRecord(@PathVariable(name = "id", required = false) Integer recordId) {
            MyRecord fromCacheOrUpdate = myRecordService.updateInCacheOrCreate(recordId);
            return fromCacheOrUpdate;
      }

      @DeleteMapping("{id}")
      String deleteRecord(@PathVariable(name = "id", required = false) Integer recordId) {
            myRecordService.deleteRecord(recordId);
            return "record was delete";
      }
}

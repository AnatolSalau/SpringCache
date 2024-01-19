package com.example.reactiveredis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class Web {
      @Bean
      RouterFunction<ServerResponse> responseRouterFunction(LanguageHandler languageHandler) {
            return responseRouterFunction(GET("/"), languageHandler::all)
                  .andRoute(GET("/{id}"), languageHandler::get);
      }
}

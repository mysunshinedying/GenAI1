package com.mycompany.demo.di.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j  
public class AiService {

    @Value("${my_openai_api_key}")
    private String openAiApiKey;

    public void chat(){
        log.info("OpenAI API Key: {}", openAiApiKey);
    }

}

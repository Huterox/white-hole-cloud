package com.huterox.whitehole.whiteholequiz.config;

import com.huterox.common.wordfilter.WordContext;
import com.huterox.common.wordfilter.WordFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WordFilterConfig {

    @Bean
    public WordContext wordContext(){
        return new WordContext();
    }

    @Bean
    public WordFilter wordFilter(WordContext wordContext){
        return new WordFilter(wordContext);
    }
}

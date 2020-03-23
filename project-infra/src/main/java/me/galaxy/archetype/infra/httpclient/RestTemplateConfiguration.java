package me.galaxy.archetype.infra.httpclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import me.galaxy.archetype.infra.utils.JsonUtils;
import org.apache.http.client.HttpClient;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author duanxiaolei@bytedance.com
 * @Date 2020/3/23 2:26 下午
 **/
@Configuration
public class RestTemplateConfiguration {

    @Primary
    @Bean
    public RestTemplate restTemplate(HttpClient httpClient, HttpMessageConverters converters) {

        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        restTemplate.setMessageConverters(converters.getConverters());

        return restTemplate;
    }

    @Primary
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }

    @Primary
    @Bean
    public ObjectMapper objectMapper(){
        return JsonUtils.objectMapper;
    }

}
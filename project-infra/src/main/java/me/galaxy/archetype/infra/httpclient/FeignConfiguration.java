package me.galaxy.archetype.infra.httpclient;

import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Description
 * @Author duanxiaolei@bytedance.com
 * @Date 2020/3/23 3:52 下午
 **/
@EnableFeignClients(basePackages = {"me.galaxy.archetype.infra"})
@Configuration
public class FeignConfiguration {

    @Bean
    public Decoder feignDecoder(RestTemplate restTemplate) {
        return new ResponseEntityDecoder(new SpringDecoder(() -> new HttpMessageConverters(restTemplate.getMessageConverters())));
    }

    @Bean
    public Encoder feignEncoder(RestTemplate restTemplate) {
        return new SpringEncoder(() -> new HttpMessageConverters(restTemplate.getMessageConverters()));
    }

}
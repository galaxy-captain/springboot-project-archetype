package me.galaxy.archetype.infra.httpclient;

import me.galaxy.archetype.infra.utils.CollectionUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.util.List;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/23 2:23 下午
 **/
@EnableConfigurationProperties(HttpProperties.class)
@Configuration
public class HttpClientConfiguration {

    @Resource
    private HttpProperties httpProperties;

    @Bean
    public SSLConnectionSocketFactory sslConnectionSocketFactory() throws Exception {

        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());

        SSLContext sslContext = builder.build();
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

        return sslConnectionSocketFactory;
    }

    @Bean
    public Registry<ConnectionSocketFactory> registry(SSLConnectionSocketFactory sslConnectionSocketFactory) {

        Registry<ConnectionSocketFactory> registry = RegistryBuilder
                .<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();

        return registry;
    }

    @Bean(destroyMethod = "shutdown")
    public HttpClientConnectionManager httpClientConnectionManager(Registry<ConnectionSocketFactory> registry) {

        PoolingHttpClientConnectionManager connectionPoolingManager = new PoolingHttpClientConnectionManager(registry);
        connectionPoolingManager.setMaxTotal(httpProperties.getPoolTotalMax());
        connectionPoolingManager.setDefaultMaxPerRoute(httpProperties.getPoolRouteMax());

        return connectionPoolingManager;
    }

    @Bean
    public RequestConfig requestConfig() {

        RequestConfig config = RequestConfig.custom()
                // 从线程池获取资源的超时时间
                .setConnectionRequestTimeout(httpProperties.getConnectionRequestTimeout())
                // 建立连接的超时时间
                .setConnectTimeout(httpProperties.getConnectTimeout())
                // 网络请求的超时时间
                .setSocketTimeout(httpProperties.getSocketTimeout())
                .build();

        return config;
    }

    @Order
    @Bean
    public CloseableHttpClient httpClient(HttpClientConnectionManager httpClientConnectionManager,
                                          SSLConnectionSocketFactory sslConnectionSocketFactory,
                                          RequestConfig requestConfig,
                                          @Autowired(required = false) List<HttpRequestInterceptor> httpRequestInterceptors,
                                          @Autowired(required = false) List<HttpResponseInterceptor> httpResponseInterceptors) {

        HttpClientBuilder builder = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setSSLSocketFactory(sslConnectionSocketFactory)
                .setDefaultCookieStore(new BasicCookieStore())
                .setConnectionManager(httpClientConnectionManager);

        // Request拦截器
        if (CollectionUtils.isNotEmpty(httpRequestInterceptors)) {
            AnnotationAwareOrderComparator.sort(httpRequestInterceptors);
            for (HttpRequestInterceptor httpRequestInterceptor : httpRequestInterceptors) {
                builder.addInterceptorLast(httpRequestInterceptor);
            }
        }

        // Response拦截器
        if (CollectionUtils.isNotEmpty(httpResponseInterceptors)) {
            AnnotationAwareOrderComparator.sort(httpResponseInterceptors);
            for (HttpResponseInterceptor httpResponseInterceptor : httpResponseInterceptors) {
                builder.addInterceptorLast(httpResponseInterceptor);
            }
        }

        CloseableHttpClient httpclient = builder.build();

        return httpclient;
    }

}
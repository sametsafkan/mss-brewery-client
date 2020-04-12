package com.sametsafkan.mssbreweryclient.web.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;

@Component
public class ApacheHttpClientConfig {

    private final int defaultMaxPerRoute;
    private final int maxTotal;
    private final int socketTimeout;
    private final int connectionTimeout;

    public ApacheHttpClientConfig(@Value("${apache.connection.defaultMaxPerRoute}") int defaultMaxPerRoute,
                                  @Value("${apache.connection.maxTotal}") int maxTotal,
                                  @Value("${apache.connection.socketTimeout}") int socketTimeout,
                                  @Value("${apache.connection.timeout}") int connectionTimeout){
        this.defaultMaxPerRoute = defaultMaxPerRoute;
        this.maxTotal = maxTotal;
        this.socketTimeout = socketTimeout;
        this.connectionTimeout = connectionTimeout;
    }



    public ClientHttpRequestFactory getApacheClientConnectionManager(){
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        connectionManager.setMaxTotal(maxTotal);

        RequestConfig config = RequestConfig
                .custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectionTimeout)
                .build();

        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(config)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .build();
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }
}

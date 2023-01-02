package rao.pawan.restclient.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class JokeService{

    private final RestTemplate template;
    private final WebClient client;

    @Autowired
    public JokeService(RestTemplateBuilder rtBuilder, WebClient.Builder wcBuilder) {
        template = rtBuilder.build();
        client = wcBuilder.baseUrl("http://api.icndb.com")
                .build();
    }

}
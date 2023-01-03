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

    public String getJokeSync(String first, String last) {
        return template.getForObject(getURL(first, last), JokeResponse.class)
                .getValue()
                .getJoke();
    }

    // Put synchronous blocking call on a dedicated thread
    public Mono<String> getJokeSyncWrapped(String first, String last) {
        return Mono.fromCallable(() -> template.getForObject(getURL(first, last), JokeResponse.class))
                .subscribeOn(Schedulers.boundedElastic())
                .map(jokeResponse -> jokeResponse.getValue()
                        .getJoke());
    }


}
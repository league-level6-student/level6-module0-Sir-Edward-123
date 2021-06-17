package _03_intro_to_authenticated_APIs;

import _03_intro_to_authenticated_APIs.data_transfer_objects.ApiExampleWrapper;
import _03_intro_to_authenticated_APIs.data_transfer_objects.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class NewsApiTest {

    NewsApi newsApi;
    
    @Mock
    WebClient webClient;
    
    @Mock
    RequestHeadersUriSpec rhus;
    
    @Mock
    RequestHeadersSpec rhs;
    
    @Mock
    ResponseSpec rs;
    
    @Mock
    Mono<ApiExampleWrapper> apiExampleWrapperMono;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	
    	newsApi = new NewsApi();
    	newsApi.setWebClient(webClient);
    }

    @Test
    void itShouldGetNewsStoryByTopic() {
        //given
    	String topic = "cats";
    	ApiExampleWrapper expected = new ApiExampleWrapper(); 
    	
    	when(webClient.get()).thenReturn(rhus);
    	when(rhus.uri((Function<UriBuilder, URI>) any())).thenReturn(rhs);
    	when(rhs.retrieve()).thenReturn(rs);
    	when(rs.bodyToMono(ApiExampleWrapper.class)).thenReturn(apiExampleWrapperMono);
    	when(apiExampleWrapperMono.block()).thenReturn(expected);

        //when
    	ApiExampleWrapper actual = newsApi.getNewsStoryByTopic(topic);

        //then
    	assertEquals(expected, actual);
    }

    @Test
    void itShouldFindStory(){
    	//given
    	String topic = "cats";
    	String title = "Cats Do Thing, Very Cool";
    	String content = "Breaking News: Two cats did a thing. Politicians shocked!";
    	String url = "www.catnews.com/articles/cats-do-thing-very-cool/";
    	String expectedMessage = title + " -\n" + content + "\nFull article: " + url;
    	
    	ApiExampleWrapper wrapper = new ApiExampleWrapper();
    	
    	Article article = new Article();
    	article.setTitle(title);
    	article.setContent(content);
    	article.setUrl(url);
    	
    	List<Article> articles = new ArrayList<Article>();
    	articles.add(article);
    	wrapper.setArticles(articles);
    	
    	when(webClient.get()).thenReturn(rhus);
    	when(rhus.uri((Function<UriBuilder, URI>) any())).thenReturn(rhs);
    	when(rhs.retrieve()).thenReturn(rs);
    	when(rs.bodyToMono(ApiExampleWrapper.class)).thenReturn(apiExampleWrapperMono);
    	when(apiExampleWrapperMono.block()).thenReturn(wrapper);

        //when
    	String actualMessage = newsApi.findStory(topic);

        //then
    	assertEquals(expectedMessage, actualMessage);
    }


}
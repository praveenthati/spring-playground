package com.example;

import com.example.controller.OMDBController;
import com.example.model.OMDBConfig;
import com.example.service.OMDBService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testOMDBServiceWithMockServer {

    @Autowired
    private OMDBService service;

    private MockRestServiceServer mockServer;


    @Before
    public void setupService(){

      // This step is not needed as i have config class from application.properties
      //  OMDBConfig config = mock(OMDBConfig.class);
      //  when(config.getMoviesEndPoint()).thenReturn("http://www.omdbapi.com");
      //  this.service = new OMDBService(config);


        this.mockServer = MockRestServiceServer.createServer(this.service.getRestTemplate());
    }

    @Test
    public void getMovies() throws Exception {

        mockServer
                .expect(requestTo("http://www.omdbapi.com/?s=harry"))                // <-- #2
                .andExpect(method(HttpMethod.GET))                              // <-- #3
                .andRespond(withSuccess(getMoviesResponseJson("/data.json"), MediaType.APPLICATION_JSON)); // <-- #4

       // assertThat(service.getMovies("harry").size(), equalTo(10));
        assertThat(service.getMovies("harry").get(0).getTitle(), equalTo("Harry Potter and the Deathly Hallows: Part 2"));

        mockServer.verify();

    }

    private String getMoviesResponseJson(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }
}

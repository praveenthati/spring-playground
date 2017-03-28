package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;





import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PagesController.class)
public class PagesControllerTest {

    @Autowired
    private
    MockMvc mvc;

  /*  @Test
    public void helloTest()
    {
        PagesController controller = new PagesController();
        assertEquals("Hello World!",controller.hello());
    }*/

    @Test
    public void testHelloAction() throws Exception{

        RequestBuilder request = MockMvcRequestBuilders.get("/hello");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!"));

                /*this.mvc.perform(get("/").accept(MediaType.TEXT_PLAIN))
                        .andExpect(status().isOk())
                        .andExpect(content().string("GET to index route"));*/
    }
}
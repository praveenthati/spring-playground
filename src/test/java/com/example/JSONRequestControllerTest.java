//package com.example;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest
//public class JSONRequestControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    private Gson gson = new GsonBuilder().create();
//
//    @Test
//    public void testObjectParams() throws Exception {
//        SearchRequestParams params = new SearchRequestParams("something", "2008");
//
//        MockHttpServletRequestBuilder request = post("/jr/object-example")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(gson.toJson(params));
//
//        this.mvc.perform(request)
//                .andExpect(status().isOk())
//                .andExpect(content().string("Search: q=something from=2008"));
//    }
//
//}
//
////    @Test
////    public void testRawBody() throws Exception {
////        String json = getJSON("/data.json");
////
////        MockHttpServletRequestBuilder request = post("/jr/string-example")
////                .contentType(MediaType.APPLICATION_JSON)
////                .content(json);
////
////        this.mvc.perform(request)
////                .andExpect(status().isOk())
////                .andExpect(content().string(json));
////    }
////
////    private String getJSON(String path) throws Exception {
////        URL url = this.getClass().getResource(path);
////        return new String(Files.readAllBytes(Paths.get(url.getFile())));
////    }
//
////}
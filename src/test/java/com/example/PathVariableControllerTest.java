package com.example;

import com.example.controller.PathVariableController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(PathVariableController.class)
public class PathVariableControllerTest {

    @Autowired
    private
    MockMvc mvc;

    @Test
    public void testGetCommentsForTask() throws Exception {
        int taskId = 4;
        int commentId = 3;

        String uri = String.format("/tasks/%d/comments/%d",taskId,commentId);

        this.mvc.perform(get(uri))
                .andExpect(status().isOk());


        // test as Map

        this.mvc.perform(get(String.format("/tasksAsMap/%d/comments/%d",taskId,commentId)))
                .andExpect(status().isOk())
                .andExpect(content().string("{taskId=4, commentId=3}"));
    }

    @Test
    public void testIndexEndpoint() throws Exception {
        int driverId = 4; // in real life you might pull this from a database...

        this.mvc.perform(get(String.format("/drivers/%d/trips", driverId)))
                .andExpect(status().isOk())
                .andExpect(content().string("driverId is 4;"));
    }

    @Test
    public void testGetVolume() throws Exception {
        int length = 4;
        int width = 3;
        int height = 2;

        this.mvc.perform(post(String.format("/math/volume/%d/%d/%d", length,width,height)))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 4x3x2 rectangle is 24"));
    }


}

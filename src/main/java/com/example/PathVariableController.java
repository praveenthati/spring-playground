package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PathVariableController {

    @GetMapping("/tasks/{taskId}/comments/{commentId}")
    public String getCommentsForTask(@PathVariable int taskId, @PathVariable int commentId) {
        return String.format("taskId is %d; commentId is %d", taskId, commentId);
    }

    @GetMapping("/tasksAsMap/{taskId}/comments/{commentId}")
    public String getCommentsForTaskAsMap(@PathVariable Map pathVariables) {
        return pathVariables.toString(); // {taskId=46, commentId=35}
    }

    @GetMapping("/drivers/{driverId}/trips")
    public String getDrivers(@PathVariable int driverId) {
        return String.format("driverId is %d;", driverId);
    }


    @RequestMapping("/math/volume/{length}/{width}/{height}")
    public String getVolume(@PathVariable int length,@PathVariable int width,@PathVariable int height)
    {
        return String.format("The volume of a %dx%dx%d rectangle is %d", length, width, height,length*width*height);
    }
}

package com.example;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;

@RestController
public class FormDataController {

    @RequestMapping(value = "/commentsfrombody", method = RequestMethod.POST)
    public String getCommentsFromBody(@RequestBody String rawBody)
    {
        return rawBody;
    }

    @PostMapping("/commentsfromparam")
    public String getCommentsFromParam(@RequestParam String content, @RequestParam String author) {
        return String.format("%s said %s", author, content);
    }

    @PostMapping(value = "/commentsusingmap", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String getCommentsFromMAP(@RequestParam Map<String, String> formData) {
        return formData.get("author")+" said "+formData.get("content");
    }

    @PostMapping(value = "/comments")
    public String getCommentsFromObject(Comment search) {
        return search.toString();
    }

    @PostMapping(value = "/math/areafromparams")
    public String computeAreaFromParams(@RequestParam(required = false) String type, @RequestParam(defaultValue = "0") double radius, @RequestParam(defaultValue = "0") double width, @RequestParam(defaultValue = "0") double length) {
        MathService service = new MathService();

        return service.area(type,width,length,radius);
    }

    @PostMapping(value = "/math/area")
    public String computeAreaFromObject(Dimensions params) {
        MathService service = new MathService();

        return service.area(params);
    }




}

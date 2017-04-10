package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ZenService {

    RestTemplate template = new RestTemplate();

    public String getMessage(){
        return this.template.getForObject(
                "https://api.github.com/zen",
                String.class
        );
    }

    public String getMessageWithParams(){
        return this.template.getForObject(
               "https://api.example.com/drivers/{id}/trips",
                String.class,
                42);

    }

//    public MessageReceipt postMessage(Message message) {
//        // Construct a URI from a template
//        URI uri = UriComponentsBuilder
//                .fromUriString("{host}/messages/send")
//                .buildAndExpand(config.getUrl())
//                .toUri();
//
//        // Create the request object
//        RequestEntity<?> request = RequestEntity.post(uri)
//                .header("Authorization", config.getToken())
//                .body(message);
//
//        // Execute the request
//        ResponseEntity<MessageReceipt> response = this.restTemplate.exchange(
//                request,
//                MessageReceipt.class // Declare the _type_ of the response
//        );
//
//        // Get the deserialized response body
//        return response.getBody();
//    }

//    ResponseEntity<Map<String, String>> responseEntity = this.restTemplate.exchange(
//            requestEntity,
//            new ParameterizedTypeReference<Map<String, String>>() {
//            },
//    );
}

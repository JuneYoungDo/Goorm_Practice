package com.ohgiraffers.restapi.section01.response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/response")
public class ResponseRestController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "hello world";
    }

    @GetMapping("/random")
    public int getRandomNumber() {
        return (int) (Math.random() * 10) + 1;
    }

    @GetMapping("/message")
    public Message getMessage() {
        return new Message(200, "==== 응답메세지 ====");
    }

    @GetMapping("/list")
    public List<String> getList() {
        return List.of(new String[]{"떡볶이", "튀김", "김밥"});
    }

    @GetMapping("/map")
    public Map<Integer, String> getMap() {
        Map<Integer, String> messageMap = new HashMap<>();
        messageMap.put(200, "정상응답");
        messageMap.put(404, "페이지를 찾을 수 없음");
        messageMap.put(500, "서버 내부 에러 == 개발자의 잘못");
        return messageMap;
    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage() throws IOException {
        return getClass().getResourceAsStream("/images/spring.png").readAllBytes();
    }

    @GetMapping("/entity")
    public ResponseEntity<Message> getEntity() {

        return ResponseEntity.ok(new Message(123, "hello rest~"));
    }
}

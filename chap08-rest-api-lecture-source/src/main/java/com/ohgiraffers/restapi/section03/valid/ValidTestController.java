package com.ohgiraffers.restapi.section03.valid;

import com.ohgiraffers.restapi.section02.responseEntity.ResponseMessage;
import jakarta.validation.Valid;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/valid")
public class ValidTestController {
    private List<UserDTO> users;

    public ValidTestController() {
        users = new ArrayList<>();

        users.add(new UserDTO(1, "user01", "pass01", "다람쥐", new Date()));
        users.add(new UserDTO(2, "user02", "pass02", "코알라", new Date()));
        users.add(new UserDTO(3, "user03", "pass03", "너구리", new Date()));
    }

    @GetMapping("/users/{userNo}")
    public ResponseEntity<ResponseMessage> findUserByUserNo(@PathVariable("userNo") int userNo)
        throws UserNotFoundException {
        /* 응답 헤더 설정 */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<UserDTO> foundUserList = users.stream().filter(user -> user.getNumber() == userNo)
            .collect(Collectors.toList());

        UserDTO foundUser = null;
        if (foundUserList.size() > 0) {
            foundUser = foundUserList.get(0);
        } else {
            throw new UserNotFoundException("회원 정보를 찾을 수 없습니다.");
        }

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("user", foundUser);

        return ResponseEntity.ok().headers(headers)
            .body(new ResponseMessage(200, "조회 성공", responseMap));
    }


    @PostMapping("/users")
    public ResponseEntity<?> registUser(@Valid @RequestBody UserDTO newUser) {
        System.out.println("user==============" + newUser);

        return ResponseEntity.created(URI.create("/valud/users/" + "userNo")).build();
    }
}

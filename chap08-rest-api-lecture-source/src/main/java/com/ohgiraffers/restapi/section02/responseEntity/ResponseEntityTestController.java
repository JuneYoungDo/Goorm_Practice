package com.ohgiraffers.restapi.section02.responseEntity;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entity")
public class ResponseEntityTestController {

    /* ResponseEntity
     * 결과 데이터와 HTTP 상태코드를 직접 제어할 수 있는 클래스이다.
     * HttpStatus, HttpHeaders, HttpBody
     *  */

    private List<UserDTO> users;

    public ResponseEntityTestController() {
        users = new ArrayList<>();
        users.add(new UserDTO(1, "user01", "pass01", "다람쥐", new Date()));
        users.add(new UserDTO(2, "user02", "pass02", "코알라", new Date()));
        users.add(new UserDTO(3, "user03", "pass03", "너구리", new Date()));
    }

    @GetMapping("/users")
    public ResponseEntity<ResponseMessage> findAllUsers() {
        /* 응답 헤더 설정 */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        /* 응답 데이터 설정 */
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("users", users);

        ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공", responseMap);

        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);
    }

    @GetMapping("/users/{userNo}")
    public ResponseEntity<ResponseMessage> findUserByUserNo(@PathVariable("userNo") int userNo) {
        /* 응답 헤더 설정 */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        UserDTO foundUser = users.stream().filter(user -> user.getNumber() == userNo)
            .collect(Collectors.toList()).get(0);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("user", foundUser);

        return ResponseEntity.ok().headers(headers)
            .body(new ResponseMessage(200, "조회 성공", responseMap));
    }

    @PostMapping("/users")
    public ResponseEntity<?> registUser(@RequestBody UserDTO newUser) {

        int lastUserNo = users.get(users.size() - 1).getNumber();

        newUser.setNumber(lastUserNo + 1);

        users.add(newUser);

        return ResponseEntity.created(
            URI.create("/entity/users/" + users.get(users.size() - 1).getNumber())).build();
    }

    @PutMapping("/users/{userNo}")
    public ResponseEntity<?> modifyUser(@PathVariable("userNo") int userNo,
        @RequestBody UserDTO modifyInfo) {

        UserDTO foundUser = users.stream().filter(user -> user.getNumber() == userNo)
            .collect(Collectors.toList()).get(0);

        foundUser.setId(modifyInfo.getId());
        foundUser.setPwd(modifyInfo.getPwd());
        foundUser.setName(modifyInfo.getName());

        return ResponseEntity.created(URI.create("/entity/users/" + userNo)).build();
    }

    @DeleteMapping("/users/{userNo}")
    public ResponseEntity<?> removeUser(@PathVariable("userNo") int userNo) {

        UserDTO foundUser = users.stream().filter(user -> user.getNumber() == userNo)
            .collect(Collectors.toList()).get(0);
        users.remove(foundUser);

        return ResponseEntity.noContent().build();
    }
}

package com.ohgiraffers.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MethodMappingTestController {

    /* 1. 메소드 방식 미지정 */
    @RequestMapping("/menu/regist")
    public String registMenu(Model model) {

        model.addAttribute("message", "신규 메뉴 등록용 핸들러 메소드 호출함...");

        /* 반환하고자 하는 view의 경로를 포함한 이름을 작성한다. */
        /* resources/templates 하위 부터의 경로를 작성한다. */
        return "mappingResult";
    }

    /* 2. 메소드 방식 지정 */
    /* 요청 URL을 value 속성에 요청, method를 method 속성에 저장 */
    @RequestMapping(value = "/menu/modify", method = RequestMethod.GET)
    public String modifyMenu(Model model){
        model.addAttribute("message","GET 방식의 메뉴 수정용 핸들러 호출함...");

        return "mappingResult";
    }


    /* 3. 요청에 특화된 어노테이션 */
    /**
     * POST     @PostMapping
     * GET      @GetMapping
     * PUT      @PutMapping
     * DELETE   @DeleteMapping
     * PATCH    @PatchMapping
     */
    @GetMapping("/menu/delete")
    public String getDeleteMenu(Model model){
        model.addAttribute("message","GET 방식의 삭제용 핸들러 메소드 호출함...");
        return "mappingResult";
    }

    @PostMapping("/menu/delete")
    public String postDeleteMenu(Model model){

        model.addAttribute("message","POST 방식의 삭제용 핸들러 메소드 호출함...");
        return "mappingResult";
    }

}

package com.ohgiraffers.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/*")
public class ResolverController {

    @GetMapping("string")
    public String stringReturning(Model model) {

        model.addAttribute("forwardMessage", "문자열로 뷰 이름 반환함");
        return "result";
    }

    @GetMapping("string-redirect")
    public String stringRedirect() {

        /* 접두사로 redirect:을 하면 forward가 아닌 redirect 시킨다. */
        return "redirect:/";
    }

    /* 기본적으로 redirect에는 재요청이 발생하므로 request scopes는 소멸된다
     * 하지만 스프링에서는 RedirectAttributes 타입을 통해 redirect 시 속성 값을 저장할 수 있도록 하는 기능을 제공한다. */
    @GetMapping("string-redirect-attr")
    public String stringRedirectFlashAttribute(RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("flashMessage1", "리다이렉트 attr 사용하여 redirect...");
        return "redirect:/";
    }

    @GetMapping("modelandview")
    public ModelAndView modelAndViewReturning(ModelAndView modelAndView) {
        modelAndView.addObject("forwardMessage", "ModelAndView를 이용한 모델과 뷰 반환");
        modelAndView.setViewName("result");

        return modelAndView;
    }

    @GetMapping("modelandview-redirect")
    public ModelAndView modelAndViewRedirect(ModelAndView mv) {
        mv.setViewName("redirect:/");
        return mv;
    }

    @GetMapping("modelandview-redirect-attr")
    public ModelAndView modelAndViewRedirect(ModelAndView mv,
        RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("flashMessage2", "ModelAndView를 이용한 redirect attr");
        mv.setViewName("redirect:/");

        return mv;
    }
}

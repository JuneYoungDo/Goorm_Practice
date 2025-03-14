package com.ohgiraffers.section02.initdestroy.subsection02.annotation;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class Owner {

    @PostConstruct
    public void openShop() {
        System.out.println("사장님이 가게 문을 열었습니다. 이제 쇼핑을 하실 수 있습니다.");
    }

    @PreDestroy
    public void closeShop() {
        System.out.println("사장님이 가게 문을 닫았습니다. 이제 쇼핑을 하실 수 없습니다.");
    }
}

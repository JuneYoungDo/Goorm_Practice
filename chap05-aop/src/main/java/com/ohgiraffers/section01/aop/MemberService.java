package com.ohgiraffers.section01.aop;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberDAO memberDAO;

    @Autowired
    public MemberService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public Map<Long, MemberDTO> selectMembers() {
        System.out.println("selectMembers 메소드 실행");
        return memberDAO.selectMembers();
    }

    public MemberDTO selectMember(Long id) {
        System.out.println("selectMember 메소드 실행");
        return memberDAO.selectMember(id);
    }
}

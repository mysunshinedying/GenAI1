package com.mycompany.demo.database.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.demo.database.dao.MemberDao;
import com.mycompany.demo.database.dto.Pager;
import com.mycompany.demo.database.entity.Member;

import lombok.RequiredArgsConstructor;




@RestController 
@RequestMapping("/database/member")
@RequiredArgsConstructor 
public class DatabaseMemberController {

    private final MemberDao memberDao;

    @GetMapping("/get-member")
    public Member getMember(@RequestParam("memberId") String memberId) {
        // DB 저장된 회원 정보를 가져오기
        Member member = memberDao.selectById(memberId);
        return member;
    }

    @PostMapping("/join-member")
    public Member joinMember(@RequestBody Member member) {
        // DB 저장
        memberDao.insert(member);

        // DB에 저장된 회원 정보 가져오기
        Member dbMember = memberDao.selectById(member.getMemberId());

        return dbMember;
    }

    @PutMapping("/update-member")
    public Member updateMember(@RequestBody Member member) {
        // DB 업데이트
        Member dbMember = memberDao.selectById(member.getMemberId());
        dbMember.setPassword(member.getPassword());
        dbMember.setEmail(member.getEmail());
        memberDao.update(dbMember);

        // DB에 저장된 회원 정보 가져오기
        dbMember = memberDao.selectById(member.getMemberId());

        return dbMember;
    }

    @DeleteMapping("/delete-member")
    public String deleteMember(@RequestParam("memberId") String memberId) {
        int rows = memberDao.deleteById(memberId);
        if(rows > 0) {
            return "memberId: " + memberId + "가 삭제 되었습니다. 삭제된 행 수: " + rows;
        } else {
            return "memberId: " + memberId + "가 존재하지 않습니다.";
        }
    }
    
    @GetMapping("/list-member")
    public Map<String, Object> listMember(@RequestParam("pageNo") int pageNo) {

        Map<String, Object> where = Map.of("memberName", "user 10");

        int totalRows = memberDao.selectCount(where);
        Pager pager = new Pager(5, 5, totalRows, pageNo);
        List<Member> list = memberDao.selectPage(pager, where);

        Map<String, Object> result = Map.of(
            "pager", pager,
            "data", list
        );

        return result;
    }
}
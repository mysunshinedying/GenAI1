package com.mycompany.demo.database.controller;
import com.mycompany.demo.database.exception.DuplicatedMemberIDException;
import com.mycompany.demo.database.exception.NoAccountException;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mycompany.demo.database.dto.*;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.demo.database.entity.Member;
import com.mycompany.demo.database.service.MemberService;

import lombok.extern.slf4j.Slf4j;


@RestController 
@Slf4j 
@RequestMapping("/database/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/get-member")
    public MemberResponse getMember(@RequestParam("memberId") String memberId) throws SQLException {

        if(true) {
//            throw new RuntimeException("테스트 예외 발생");
//            throw new SQLException("DB에서 예외 발생");
//            throw new NullPointerException("NullPointerException 발생");
//            throw  new DuplicatedMemberIDException("이미 아이디가 존재함");
//            throw new NoAccountException("계좌가 존재하지 않음");
        }

        // DB 저장된 회원 정보를 가져오기
        Member member = memberService.getMember(memberId);

        MemberResponse memberResponse = new MemberResponse(
                member.getMemberId(),
                member.getMemberName(),
                member.getEmail(),
                member.getJoinedAt()
        );

        return memberResponse;
    }

    @PostMapping("/join-member")
    public MemberResponse joinMember(@RequestBody JoinMemberRequest joinMemberRequest) {
//        Member member = joinMemberRequest.toEntity(joinMemberRequest);

        Member member = new Member();
        member.setMemberId(joinMemberRequest.getMemberId());
        member.setMemberName(joinMemberRequest.getMemberName());
        member.setPassword(joinMemberRequest.getPassword());
        member.setEmail(joinMemberRequest.getEmail());

        memberService.join(member);
        Member dbMember = memberService.getMember(member.getMemberId());

        return new MemberResponse(
                dbMember.getMemberId(),
                dbMember.getMemberName(),
                dbMember.getEmail(),
                dbMember.getJoinedAt()
        );
    }

    @PutMapping("/update-member")
    public Map<String, Object> updateMember(@RequestBody UpdateMemberRequest updateMemberRequest) {
        Member member = new Member();
        member.setMemberId(updateMemberRequest.getMemberId());
        member.setPassword(updateMemberRequest.getPassword());
        member.setEmail(updateMemberRequest.getEmail());

        int rows = memberService.updateMember(member);

        Map<String, Object> map = new HashMap<>();

        if(rows > 0) {
            Member dbMember = memberService.getMember(member.getMemberId());
            MemberResponse memberResponse = new MemberResponse();
            memberResponse.setMemberId(dbMember.getMemberId());
            memberResponse.setMemberName(dbMember.getMemberName());
            memberResponse.setEmail(dbMember.getEmail());
            memberResponse.setJoinedAt(dbMember.getJoinedAt());

            map.put("status", "success");
            map.put("data", memberResponse);
        } else {
            map.put("status", "fail");
        }
        return map;
    }

    @DeleteMapping("/delete-member")
    public Map<String, Object> deleteMember(@RequestParam("memberId") String memberId) {
        int rows = memberService.deleteMember(memberId);
        Map<String, Object> map = new HashMap<>();

        if(rows > 0) {
            map.put("status", "success");

        } else {
            map.put("status", "fail");
        }

        return map;
    }

    @GetMapping("/list-member")
    public Map<String, Object> listMember(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
        Map<String, Object> where = null;
        int totalRows = memberService.pagingTotalRows(where);
        Pager pager = new Pager(10, 5, totalRows, pageNo);
        List<Member> list = memberService.listMember(pager, where);
        List<MemberResponse> listMemberResponse = new ArrayList<>();

        for(Member dbMember : list) {
            MemberResponse memberResponse = new MemberResponse();
            memberResponse.setMemberId(dbMember.getMemberId());
            memberResponse.setMemberName(dbMember.getMemberName());
            memberResponse.setEmail(dbMember.getEmail());
            memberResponse.setJoinedAt(dbMember.getJoinedAt());
            listMemberResponse.add(memberResponse);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("pager", pager);
        map.put("data", listMemberResponse);

        return map;

        // DTO MAPPING
        // List<MemberResponse> responseList = list.stream().map(dbMember -> {
        //     MemberResponse memberResponse = new MemberResponse();
        //     memberResponse.setMemberId(dbMember.getMemberId());
        //     memberResponse.setMemberName(dbMember.getMemberName());
        //     memberResponse.setEmail(dbMember.getEmail());
        //     memberResponse.setJoinedAt(dbMember.getJoinedAt());
        //     return memberResponse;
        // }).toList();

        // Map<String, Object> map = Map.of(
        //         "pager", pager,
        //         "data", responseList
        // );

        // return map;
    }
}

package com.mycompany.demo.database.controller;
import java.sql.SQLException;
import java.util.ArrayList;
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

import com.mycompany.demo.database.dto.JoinMemberRequest;
import com.mycompany.demo.database.dto.MemberLoginRequest;
import com.mycompany.demo.database.dto.MemberLoginResponse;
import com.mycompany.demo.database.dto.MemberResponse;
import com.mycompany.demo.database.dto.Pager;
import com.mycompany.demo.database.dto.UpdateMemberRequest;
import com.mycompany.demo.database.entity.Member;
import com.mycompany.demo.database.service.MemberService;
import com.mycompany.demo.jwt.service.JwtService;

import lombok.extern.slf4j.Slf4j;


@RestController 
@Slf4j 
@RequestMapping("/database/member")
public class MemberController {

    private final MemberService memberService;
    private final JwtService jwtService;

    public MemberController(MemberService memberService, JwtService jwtService) {
        this.memberService = memberService;
        this.jwtService = jwtService;
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

    @PostMapping("/login-member")
    public MemberLoginResponse login(@RequestBody MemberLoginRequest request) {

        Member member = new Member();
        member.setMemberId(request.getMemberId());
        member.setPassword(request.getPassword());

        String result = memberService.login(member);

        MemberLoginResponse memberLoginResponse = new MemberLoginResponse();

        if(result.equals("success")) {
            // 로그인 성공 처리
            memberLoginResponse.setStatus("success");
            memberLoginResponse.setMemberId(request.getMemberId());

            Member dbMember = memberService.getMember(request.getMemberId());

            String token = jwtService.createJwt(dbMember);            
            memberLoginResponse.setJwt(token);
        } else if(result.equals("wrongmemberId")) {
            // 로그인 실패 처리
            memberLoginResponse.setStatus("wrongmemberId");
            memberLoginResponse.setMessage("회원 아이디가 존재하지 않습니다.");
        } else if(result.equals("wrongPassword")) {
            // 로그인 실패 처리
            memberLoginResponse.setStatus("wrongPassword");
            memberLoginResponse.setMessage("비밀번호가 올바르지 않습니다.");
        }

        return memberLoginResponse;

    }
    
}

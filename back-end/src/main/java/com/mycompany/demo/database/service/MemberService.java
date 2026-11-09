package com.mycompany.demo.database.service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;
import java.util.Map;

import com.mycompany.demo.database.dao.MemberDao;
import com.mycompany.demo.database.dto.Pager;
import com.mycompany.demo.database.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MemberService {

    private final MemberDao memberDao;

    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public Member getMember(String memberId) {
        return memberDao.selectById(memberId);
    }

    public void join(Member member) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberDao.insert(member);
    }

    public int updateMember(Member member) {
        return memberDao.update(member);
    }

    public int deleteMember(String memberId) {
        return memberDao.deleteById(memberId);
    }

    public List<Member> listMember(Pager pager, Map<String, Object> where) {
        return memberDao.selectPage(pager, where);
    }

    public int pagingTotalRows(Map<String, Object> where) {
        return memberDao.selectCount(where);
    }

    public String login(Member member) {
        Member dbMember = memberDao.selectById(member.getMemberId());
        if(dbMember != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if(passwordEncoder.matches(member.getPassword(), dbMember.getPassword())) {
                return "success";
            } else {
                return "wrongPassword";
            }
        }
        return "wrongmemberId";
    }
}

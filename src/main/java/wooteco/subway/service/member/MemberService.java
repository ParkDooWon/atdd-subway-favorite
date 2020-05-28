package wooteco.subway.service.member;

import org.springframework.stereotype.Service;

import wooteco.subway.domain.member.Member;
import wooteco.subway.domain.member.MemberRepository;
import wooteco.subway.infra.JwtTokenProvider;
import wooteco.subway.service.member.dto.LoginRequest;
import wooteco.subway.service.member.dto.UpdateMemberRequest;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public MemberService(MemberRepository memberRepository, JwtTokenProvider jwtTokenProvider) {
        this.memberRepository = memberRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public void updateMember(Member member, UpdateMemberRequest updateMemberRequest) {
        member.update(updateMemberRequest.getName(), updateMemberRequest.getPassword());
        memberRepository.save(member);
    }

    public void deleteMember(Member member) {
        memberRepository.delete(member);
    }

    public String createToken(LoginRequest loginRequest) {
        Member member = memberRepository.findByEmail(loginRequest.getEmail())
            .orElseThrow(IllegalArgumentException::new);
        if (!member.checkPassword(loginRequest.getPassword())) {
            throw new IllegalArgumentException("잘못된 패스워드");
        }

        return jwtTokenProvider.createToken(loginRequest.getEmail());
    }

    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
            .orElseThrow(IllegalArgumentException::new);
    }

    public boolean loginWithForm(String email, String password) {
        Member member = findMemberByEmail(email);
        return member.checkPassword(password);
    }
}

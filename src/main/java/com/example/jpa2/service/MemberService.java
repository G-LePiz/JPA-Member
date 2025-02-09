package com.example.jpa2.service;

import com.example.jpa2.dto.MemberRequestDto;
import com.example.jpa2.dto.MemberResponseDto;
import com.example.jpa2.entity.Member;
import com.example.jpa2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto saveMember(MemberRequestDto memberRequestDto) {

        Member member = new Member(memberRequestDto.getUsername(), memberRequestDto.getPassword(), memberRequestDto.getEmail());
        memberRepository.save(member);

        return new MemberResponseDto(member.getId(), member.getUsername(), member.getEmail());
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAll() {

        List<Member> members = memberRepository.findAll();
        List<MemberResponseDto> dtos = new ArrayList<>();

        for (Member member : members) {
            dtos.add(new MemberResponseDto(member.getId(), member.getUsername(), member.getEmail()));
        }

        return dtos;
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findById(Long id) {

        Member member = memberRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("찾을 수 없습니다."));

        return new MemberResponseDto(member.getId(), member.getUsername(), member.getEmail());
    }

    @Transactional
    public MemberResponseDto updateMember(Long id, MemberRequestDto memberRequestDto) {

        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("수정할 수 없습니다.")
        );

        member.update(memberRequestDto.getUsername(), memberRequestDto.getPassword(), memberRequestDto.getEmail());

        return new MemberResponseDto(member.getId(), member.getUsername(), member.getEmail());
    }

    @Transactional
    public void deleteMember(Long id) {

        memberRepository.deleteById(id);

    }


}

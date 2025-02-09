package com.example.jpa2.controller;

import com.example.jpa2.dto.MemberRequestDto;
import com.example.jpa2.dto.MemberResponseDto;
import com.example.jpa2.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<MemberResponseDto> saveMember(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(memberService.saveMember(memberRequestDto));
    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberResponseDto>> findAll(){
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(memberService.findById(id));
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<MemberResponseDto> updateMember(@PathVariable Long id,
                                                          @RequestBody MemberRequestDto memberRequestDto){
        return ResponseEntity.ok(memberService.updateMember(id, memberRequestDto));
    }

    @DeleteMapping("/members/{id}")
    public void deleteMember(@PathVariable Long id){
        memberService.deleteMember(id);
    }

}

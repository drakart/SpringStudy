package com.kh.spring.member.model.service;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.kh.spring.common.code.Config;
import com.kh.spring.common.mail.MailSender;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.model.repository.MemberRepository;
import com.kh.spring.member.valiator.JoinForm;

@Service
public class MemberService {

	private MemberRepository memberRepository;
	private MailSender mailSender;
	private RestTemplate http;
	
	public MemberService(MemberRepository memberRepository, MailSender mailSender, RestTemplate http) {
		super();
		this.memberRepository = memberRepository;
		this.mailSender = mailSender;
		this.http = http;
	}

	public void insertMember(JoinForm form) {

		memberRepository.insertMember(form);

	}

	public Member authenticateUser(Member member) {
			return memberRepository.authenticateUser(member);
	}

	public Member selectMemberByUserId(String userId) {
		return memberRepository.selectMemberByUserId(userId);
	}

	public void authenticateByEmail(JoinForm form, String token) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("mailTemplate", "join-auth-mail");
		body.add("userId", form.getUserId());
		body.add("persistToken", token);
		
		//RequestTemplate의 기본 Content-type : application/json
		RequestEntity<MultiValueMap<String, String>> request =
				RequestEntity.post(Config.DOMAIN.DESC + "/mail")
				.accept(MediaType.APPLICATION_FORM_URLENCODED)
				.body(body);
		
		String htmlTxt = http.exchange(request, String.class).getBody();
		mailSender.send(form.getEmail(), "회원가입을 축하합니다", htmlTxt);
		
	}

}

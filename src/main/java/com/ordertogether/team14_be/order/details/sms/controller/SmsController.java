package com.ordertogether.team14_be.order.details.sms.controller;

import com.ordertogether.team14_be.member.persistence.entity.Member;
import com.ordertogether.team14_be.member.presentation.LoginMember;
import com.ordertogether.team14_be.order.details.sms.dto.SmsReq;
import com.ordertogether.team14_be.order.details.sms.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sms")
public class SmsController {

	private final SmsService smsService;

	@GetMapping
	public ResponseEntity<Void> getOrdersInfo(
			@LoginMember Member member, @RequestBody SmsReq smsReq) {
		smsService.sendLinkToParticipant(member, smsReq);
		return ResponseEntity.ok().build();
	}
}

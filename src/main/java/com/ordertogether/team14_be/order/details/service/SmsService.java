package com.ordertogether.team14_be.order.details.sms.service;

import com.ordertogether.team14_be.member.persistence.entity.Member;
import com.ordertogether.team14_be.order.details.sms.dto.SmsReq;
import com.ordertogether.team14_be.order.details.sms.util.SmsUtil;
import com.ordertogether.team14_be.spot.entity.Spot;
import com.ordertogether.team14_be.spot.repository.SimpleSpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsService {
	private final SmsUtil smsUtil;
	private final SimpleSpotRepository simpleSpotRepository;

	public ResponseEntity<?> sendLinkToParticipant(Member member, SmsReq smsReq) {
		// 수신번호 형태에 맞춰 "-"을 ""로 변환
		String phoneNum = member.getPhoneNumber().replaceAll("-", "");

		Spot spot =
				simpleSpotRepository
						.findById(smsReq.spotId())
						.orElseThrow(() -> new IllegalArgumentException("스팟 정보가 없습니다."));

		String link = spot.getTogetherOrderLink();
		smsUtil.sendOne(phoneNum, link);

		return ResponseEntity.ok().build();
	}
}

package com.fa.sonagi.oauth.info;

import java.util.Map;

import com.fa.sonagi.oauth.entity.ProviderType;
import com.fa.sonagi.oauth.info.inpl.GoogleOAuth2UserInfo;
import com.fa.sonagi.oauth.info.inpl.KakaoOAuth2UserInfo;
import com.fa.sonagi.oauth.info.inpl.NaverOAuth2UserInfo;

public class OAuth2UserInfoFactory {

	public static OAuth2UserInfo getOAuth2UserInfo(ProviderType providerType, Map<String, Object> attributes) {
		return switch (providerType) {
			case GOOGLE -> new GoogleOAuth2UserInfo(attributes);
			case NAVER -> new NaverOAuth2UserInfo(attributes);
			case KAKAO -> new KakaoOAuth2UserInfo(attributes);
			default -> throw new IllegalArgumentException("Invalid Provider Type.");
		};
	}
}
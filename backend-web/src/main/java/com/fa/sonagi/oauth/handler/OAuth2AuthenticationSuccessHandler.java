package com.fa.sonagi.oauth.handler;

import static com.fa.sonagi.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository.*;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.fa.sonagi.jwt.JwtTokenProvider;
import com.fa.sonagi.jwt.Token;
import com.fa.sonagi.oauth.entity.ProviderType;
import com.fa.sonagi.oauth.entity.RoleType;
import com.fa.sonagi.oauth.info.OAuth2UserInfo;
import com.fa.sonagi.oauth.info.OAuth2UserInfoFactory;
import com.fa.sonagi.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.fa.sonagi.oauth.utils.CookieUtil;
import com.fa.sonagi.user.entity.Users;
import com.fa.sonagi.user.repository.UserRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private static final String ANDORIOD_DEVICE_TOKEN = "androidToken";
	private final OAuth2AuthorizationRequestBasedOnCookieRepository authorizationRequestRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final RedisTemplate<String, String> redisTemplate;
	private final UserRepository userRepository;
	@Value("${app.oauth2.authorizedRedirectUris}")
	private String redirectUri;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException {
		String targetUrl = determineTargetUrl(request, response, authentication);

		if (response.isCommitted()) {
			log.error("Response has already been committed. Unable to redirect to " + targetUrl);
			return;
		}

		clearAuthenticationAttributes(request, response);
		getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) {
		Optional<String> redirectUri = CookieUtil.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
		                                         .map(Cookie::getValue);

		if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
			log.error("determineTargetUrl - redirectUri : {} , 인증을 진행할 수 없습니다.", redirectUri);
			throw new IllegalArgumentException(
				"Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
		}

		String targetUrl = redirectUri.orElse(getDefaultTargetUrl());
		OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken)authentication;
		ProviderType providerType = ProviderType.valueOf(authToken.getAuthorizedClientRegistrationId().toUpperCase());

		OidcUser user = ((OidcUser)authentication.getPrincipal());
		OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, user.getAttributes());
		Collection<? extends GrantedAuthority> authorities = ((OidcUser)authentication.getPrincipal()).getAuthorities();

		String socialId = userInfo.getId();
		RoleType roleType =
			hasAuthority(authorities, RoleType.ROLE_ADMIN.name()) ? RoleType.ROLE_ADMIN : RoleType.ROLE_USER;

		Users bySocialId = userRepository.findBySocialId(socialId);
		Token tokenInfo = jwtTokenProvider.createToken(String.valueOf(bySocialId.getUserId()), socialId,
			roleType.name());

		// RT{userId} : refreshToken 형식으로 redis 저장.
		redisTemplate.opsForValue()
		             .set("RT" + socialId, tokenInfo.getRefreshToken(), tokenInfo.getExpireTime(),
			             TimeUnit.MILLISECONDS);

		CookieUtil.deleteCookie(request, response, REFRESH_TOKEN);
		CookieUtil.addCookie(response, REFRESH_TOKEN, tokenInfo.getRefreshToken(),
			JwtTokenProvider.getRefreshTokenExpireTimeCookie());

		// // 기기 토큰 값 업데이트
		// Optional<String> androidDeviceToken = CookieUtil.getCookie(request, ANDORIOD_DEVICE_TOKEN)
		//                                                 .map(Cookie::getValue);
		// if (androidDeviceToken.isPresent() && !bySocialId.getFirebaseToken().equals(androidDeviceToken.get())) {
		// 	bySocialId.updateFCMToken(androidDeviceToken.get());
		// 	userRepository.save(bySocialId);
		// }

		return UriComponentsBuilder.fromUriString(targetUrl)
		                           .queryParam("accessToken", tokenInfo.getAccessToken())
		                           .build()
		                           .toUriString();
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
		super.clearAuthenticationAttributes(request);
		authorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
	}

	private boolean hasAuthority(Collection<? extends GrantedAuthority> authorities, String authority) {
		if (authorities == null) {
			return false;
		}
		for (GrantedAuthority grantedAuthority : authorities) {
			if (authority.equals(grantedAuthority.getAuthority())) {
				return true;
			}
		}
		return false;
	}

	private boolean isAuthorizedRedirectUri(String uri) {
		URI clientRedirectUri = URI.create(uri);
		URI authorizedUri = URI.create(redirectUri);

		return authorizedUri.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
			&& authorizedUri.getPort() == clientRedirectUri.getPort();
	}

}
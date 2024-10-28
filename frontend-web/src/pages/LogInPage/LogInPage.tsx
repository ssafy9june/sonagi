import { Background } from '@/components/atoms/Background/Background.styles';
import babyBackground from '@/assets/images/background.png';
import blueBaby from '@/assets/images/img-baby-blue.png';
import google from '@/assets/images/img-logo-google.png';
import kakao from '@/assets/images/img-logo-kakao.png';
import naver from '@/assets/images/img-logo-naver.png';
import { Image } from '@/components/atoms/Image/Image';
import { Text } from '@/components/atoms/Text/Text.styles';

import {
  ButtonContainer,
  LogInPageContainer,
  LogInPageWrapper,
  LogoContainer,
} from '@/pages/LogInPage/LogInPage.styles';
import SocialButton from '@/components/molecules/SocialButton/SocialButton';
import { useRecoilValue } from 'recoil';
import { userInfoState } from '@/states/userState';
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { PATH } from '@/constants/path';

// TODO: slightly / slowly 흔들거리는 baby Animation
const LogInPage = () => {
  const OAUTH2_REDIERECT_URI = `${process.env.REACT_APP_BASE_URL}/oauth/redirect`;
  const onSocialButtonClick = (socialName: string) => {
    const AUTH_URL = `${process.env.REACT_APP_SERVER_URL}/api/oauth2/authorization/${socialName}?redirect_uri=${OAUTH2_REDIERECT_URI}`;
    window.location.href = AUTH_URL;
  };
  const userInfo = useRecoilValue(userInfoState);
  const navigate = useNavigate();
  // 로그인 되어있는 유저라면 main 페이지로
  useEffect(() => {
    if (userInfo && userInfo.userId && userInfo.userId > 0) {
      navigate(PATH.MAIN);
    }
  }, [userInfo, navigate]);

  return (
    <Background $background={babyBackground}>
      <LogInPageContainer>
        <LogInPageWrapper>
          <LogoContainer>
            <Image src={blueBaby} width={11} />
            <Text size="medium1">소중한 우리 아기를 위한 육아일기</Text>
            <Text size="headXLarge">소나기</Text>
          </LogoContainer>
          <ButtonContainer>
            <SocialButton
              src={google}
              buttonText="구글로 로그인하기"
              onClick={() => onSocialButtonClick('google')}
            />
            <SocialButton
              src={kakao}
              buttonText="카카오톡으로 로그인하기"
              onClick={() => onSocialButtonClick('kakao')}
            />
            <SocialButton
              src={naver}
              buttonText="네이버로 로그인하기"
              // onClick={() => onSocialButtonClick('naver')}
              onClick={() => alert('네이버 로그인 API 검수 중입니다.')}
            />
            {/* <GoogleLogIn o기GoogleSignIn={onGoogleLogIn} /> */}
          </ButtonContainer>
        </LogInPageWrapper>
      </LogInPageContainer>
    </Background>
  );
};

export default LogInPage;

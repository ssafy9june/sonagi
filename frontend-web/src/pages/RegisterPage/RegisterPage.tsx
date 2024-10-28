import { Background } from '@/components/atoms/Background/Background.styles';
import orangeBackground from '@/assets/images/background-orange-to-blue.png';
import { Text } from '@/components/atoms/Text/Text.styles';
import yellowBaby from '@/assets/images/img-baby-yellow.png';
import { Image } from '@/components/atoms/Image/Image';
import * as S from '@/pages/RegisterPage/RegisterPages.styles';
import { useRecoilValue } from 'recoil';
import { userInfoState } from '@/states/userState';
import { useNavigate } from 'react-router-dom';
import { PATH } from '@/constants/path';
import RegisterButton from '@/components/molecules/RegisterButton/RegisterButton';
import theme from '@/styles/theme';

import baby from '@/assets/images/img-baby.png';
import babyCard from '@/assets/images/img-baby-card.png';
import { babiesOfUserState } from '@/states/babyState';
import { useEffect } from 'react';

const RegisterPage = () => {
  const userInfo = useRecoilValue(userInfoState);
  const babiesOfUser = useRecoilValue(babiesOfUserState);
  const navigate = useNavigate();
  // 관리자 이동
  useEffect(() => {
    if (userInfo.auth && userInfo.auth === 'ROLE_ADMIN') {
      navigate(PATH.ADMIN);
    }
  }, [userInfo.auth, navigate]);
  // 아기가 있으면 MAIN 페이지로
  useEffect(() => {
    if (babiesOfUser.length !== 0) {
      navigate(PATH.MAIN);
    }
  }, [babiesOfUser, navigate]);

  const toBabyCode = () => {
    navigate(PATH.BABYCODE);
  };

  const toBabyAdd = () => {
    navigate(PATH.REGISTERBABYPROFILE);
  };

  return (
    <Background $background={orangeBackground}>
      <S.RegisterPageContainer>
        <S.RegisterPageWrapper>
          <S.LogoContainer>
            <Image src={yellowBaby} width={8} />
            <Text color={theme.color.black3}>
              {userInfo.name}님의
              <br />
              아이 정보를 추가해주세요
            </Text>
          </S.LogoContainer>
          <S.ButtonContainer>
            <RegisterButton
              src={baby}
              buttonText="우리 아이 등록하기"
              onClick={toBabyAdd}
            />

            <RegisterButton
              src={babyCard}
              buttonText="초대 코드 등록"
              onClick={toBabyCode}
            />
          </S.ButtonContainer>
        </S.RegisterPageWrapper>
      </S.RegisterPageContainer>
    </Background>
  );
};

export default RegisterPage;

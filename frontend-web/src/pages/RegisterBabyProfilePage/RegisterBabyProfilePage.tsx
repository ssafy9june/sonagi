import { Background } from '@/components/atoms/Background/Background.styles';
import orangeBackground from '@/assets/images/background-orange-to-blue.png';
import { Text } from '@/components/atoms/Text/Text.styles';
import * as S from '@/pages/RegisterBabyProfilePage/RegisterBabyProfilePage.style';
import Back from '@/components/atoms/Back/Back';
import RegisterBabyProfile from '@/components/organisms/RegisterBabyProfile/RegisterBabyProfile';
const RegisterBabyProfilePage = () => {
  return (
    <Background $background={orangeBackground}>
      <Back />
      <S.RegisterBabyProfilePageContainer>
        <S.RegisterBabyProfilePageWrapper>
          <Text>
            우리 아이의 출생일과
            <br /> 성별, 이름을 입력해주세요
          </Text>
          <RegisterBabyProfile />
          <S.AddBabyWrapper />
        </S.RegisterBabyProfilePageWrapper>
      </S.RegisterBabyProfilePageContainer>
    </Background>
  );
};

export default RegisterBabyProfilePage;

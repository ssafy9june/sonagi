import { Image } from '@/components/atoms/Image/Image';
import { Text } from '@/components/atoms/Text/Text.styles';
import {
  AuthorityTagWrapper,
  CoParentContainer,
  Container,
  SettingContainer,
  UserContainer,
  UserNameWrapper,
} from '@/pages/MyPagePage/MyPagePage.styles';
import family from '@/assets/images/img-family.png';
import { useRecoilState, useRecoilValue } from 'recoil';
import { userInfoState } from '@/states/userState';
import Button from '@/components/atoms/Button/Button';
import setting from '@/assets/images/icon-setting-grey.png';
import theme from '@/styles/theme';
import { BabiesOfUser, User } from '@/types';
import { CoparentList } from '@/components/organisms/CoparentList/CoparentList';
import { useState } from 'react';
import { UserUpdateModal } from '@/components/organisms/UserUpdateModal/UserUpdateModal';
import { babiesOfUserState, selectedBabyState } from '@/states/babyState';
import authorityBlue from '@/assets/images/btn-authority-blue.png';
import authorityGreen from '@/assets/images/btn-authority-green.png';
import { useNavigate } from 'react-router-dom';
import { PATH } from '@/constants/path';
import { logout } from '@/apis/User/userAPI';

const MyPagePage = () => {
  const [userInfo, setUserInfo] = useRecoilState(userInfoState);
  const [babyInfosOfUser, setbabyInfosOfUser] =
    useRecoilState(babiesOfUserState);
  const babyInfo: BabiesOfUser = useRecoilValue(selectedBabyState);
  const navigate = useNavigate();

  // 사용자 정보 수정
  const [userModalOpen, setuserModalOpen] = useState(false);

  const modalClose = (
    setState: React.Dispatch<React.SetStateAction<boolean>>
  ) => {
    setState(false);
  };

  const navigateToAlarmPage = () => {
    navigate(PATH.ALARM);
  };
  const navigateToRegister = () => {
    navigate(PATH.REGAGAIN);
  };
  const handleLogout = async () => {
    await logout();
    localStorage.clear();
    window.location.href = PATH.ROOT;
  };
  const navigateToFAQForUserPage = () => {
    navigate(PATH.FAQFORUSER);
  };

  const navigateToQuestionPage = () => {
    navigate(PATH.QUESTIONFORUSER);
  };
  const handleAlbam = () => {
    alert('아직 준비중 입니다.');
  };
  return (
    <Container>
      <UserUpdateModal
        onModalClose={() => modalClose(setuserModalOpen)}
        modalOpen={userModalOpen}
      />

      <UserContainer>
        <Image src={family} width={5} height={5} />
        <UserNameWrapper>
          <Text size="medium2" color={theme.color.black3}>
            환영합니다
          </Text>
          <Text size="headSmall" color={theme.color.black3}>
            <b>{userInfo.name}</b>님.
          </Text>
          <AuthorityTagWrapper>
            <Image
              src={babyInfo.authority === 'Y' ? authorityBlue : authorityGreen}
              height={1.3}
            />
          </AuthorityTagWrapper>
        </UserNameWrapper>
        <Button
          option="default"
          size="xSmall"
          onClick={() => setuserModalOpen(true)}
        >
          <Image src={setting} width={1} />
          <Text size="small">정보 수정</Text>
        </Button>
      </UserContainer>
      <CoParentContainer>
        <Text size="medium1">공동 양육자 관리</Text>
        <CoparentList />
      </CoParentContainer>
      <SettingContainer>
        <Text size="medium1" onClick={navigateToRegister}>
          우리 아이 추가하기
        </Text>
        <Text size="medium1" onClick={navigateToAlarmPage}>
          알림 설정
        </Text>
        <Text size="medium1" onClick={navigateToQuestionPage}>
          질문 남기기
        </Text>
        <Text size="medium1" onClick={handleAlbam}>
          앨범 생성하기
        </Text>
        <Text size="medium1" onClick={navigateToFAQForUserPage}>
          자주 묻는 질문(FAQ)
        </Text>
        <Text size="medium1" onClick={handleLogout}>
          로그아웃
        </Text>
      </SettingContainer>
    </Container>
  );
};

export default MyPagePage;

import { Background } from '@/components/atoms/Background/Background.styles';
import orangeBackground from '@/assets/images/background-orange-to-blue.png';
import { Text } from '@/components/atoms/Text/Text.styles';
import * as S from '@/pages/UpdateBabyProfilePage/UpdateBabyProfilePage.styles';
import Back from '@/components/atoms/Back/Back';
import UpdateBabyProfile from '@/components/organisms/UpdateBabyProfile/UpdateBabyProfile';
import { BabyStateChangeModal } from '@/components/organisms/BabyStateChangeModal/BabyStateChangeModal';
import { useState } from 'react';
import { useRecoilValue } from 'recoil';
import { selectedBabyState } from '@/states/babyState';

const UpdateBabyProfilePage = () => {
  const babyInfo = useRecoilValue(selectedBabyState);

  const [babyStateModalOpen, setBabyStateModalOpen] = useState(false);

  const modalClose = (
    setState: React.Dispatch<React.SetStateAction<boolean>>
  ) => {
    setState(false);
  };

  return (
    <Background $background={orangeBackground}>
      <BabyStateChangeModal
        onModalClose={() => modalClose(setBabyStateModalOpen)}
        modalOpen={babyStateModalOpen}
        babyInfo={babyInfo}
      />
      <Back />
      <S.UpdateBabyProfilePageContainer>
        <S.UpdateBabyProfilePageWrapper>
          <Text>
            우리 아이의 출생일과
            <br /> 성별, 이름을 수정해주세요
          </Text>
          <UpdateBabyProfile />
          <S.AddBabyWrapper />
          <S.DataDeleteWrapper>
            <Text size="medium1" onClick={() => setBabyStateModalOpen(true)}>
              데이터 삭제하기
            </Text>
          </S.DataDeleteWrapper>
        </S.UpdateBabyProfilePageWrapper>
      </S.UpdateBabyProfilePageContainer>
    </Background>
  );
};

export default UpdateBabyProfilePage;

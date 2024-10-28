import { useState } from 'react';
import { useRecoilValue } from 'recoil';

import Button from '@/components/atoms/Button/Button';
import { Image } from '@/components/atoms/Image/Image';
import { Text } from '@/components/atoms/Text/Text.styles';
import { Background } from '@/components/atoms/Background/Background.styles';

import setting from '@/assets/images/icon-setting-grey.png';
import babyBlue from '@/assets/images/img-baby-blue.png';
import babyYellow from '@/assets/images/img-baby-yellow.png';
import babyVaccine from '@/assets/images/btn-vaccination.png';
import babyCard from '@/assets/images/btn-invite-code.png';
import BackgroundImg from '@/assets/images/background.png';

import BabyPersonalInfoContainer from '@/components/organisms/BabyPersonalInfoContainer/BabyPersonalInfoContainer';
import * as S from '@/components/organisms/OurBabyInfo/OurBabyInfo.styles';
import { BabiesOfUser, User } from '@/types';
import { selectedBabyState } from '@/states/babyState';
import { useNavigate } from 'react-router-dom';
import { PATH } from '@/constants/path';
import { BabyCodeModal } from '@/components/organisms/BabyCodeModal/BabyCodeModal';
import { useGetBabyDetail } from '@/apis/Baby/Queries/useGetBabyDetail';
import { userInfoState } from '@/states/userState';
import moment from 'moment';
import theme from '@/styles/theme';

const OurBabyInfo = () => {
  const navigate = useNavigate();
  const babyInfo: BabiesOfUser = useRecoilValue(selectedBabyState);
  const userInfo: User = useRecoilValue(userInfoState);
  const [isMale, setIsMale] = useState(babyInfo.gender === 'M');

  const [babyCodeModalOpen, setBabyCodeModalOpen] = useState(false);

  const modalClose = (
    setState: React.Dispatch<React.SetStateAction<boolean>>
  ) => {
    setState(false);
  };

  const babyBirthDate = moment(
    useGetBabyDetail(babyInfo.babyId, userInfo.userId).birthDate
  );
  const today = moment(new Date());
  const dayFromBirth = today.diff(babyBirthDate, 'days');
  const weekFromBirth = today.diff(babyBirthDate, 'weeks');

  return (
    <S.OurBabyInfoWholeContainer>
      <Background $background={BackgroundImg}>
        <S.EmptyContainer className="scrollable">
          <BabyCodeModal
            onModalClose={() => modalClose(setBabyCodeModalOpen)}
            modalOpen={babyCodeModalOpen}
          />
          {babyInfo.authority === 'Y' && (
            <S.InfoEditWrapper>
              <Button
                option="default"
                size="xSmall"
                $backgroundColor={theme.color.white2}
                onClick={() => {
                  navigate(PATH.UPDATEBABYPROFILE);
                }}
              >
                <Image src={setting} width={1} />
                <Text size="small">정보 수정</Text>
              </Button>
            </S.InfoEditWrapper>
          )}
          <S.OurBabyInfoContainer>
            <S.BabyNameWrapper>
              <Text size="headMedium" $fontWeight={700}>
                {babyInfo.name}
              </Text>
            </S.BabyNameWrapper>
            <div style={{ height: '144px' }}>
              <Image
                src={isMale ? babyBlue : babyYellow}
                height={100}
                $unit="%"
              />
            </div>
            <div
              style={{
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                marginBottom: '10px',
              }}
            >
              <Text size="medium1"> 함께한 시간 {dayFromBirth}일</Text>
              <Text size="headMedium" $fontWeight={700}>
                {weekFromBirth}주
              </Text>
            </div>
            <S.UpperButtonContainer>
              <Image
                onClick={() => navigate(PATH.MEDICALINFO)}
                src={babyVaccine}
                width={10.2}
              />

              <Image
                src={babyCard}
                width={10.2}
                onClick={() => setBabyCodeModalOpen(true)}
              />
            </S.UpperButtonContainer>
            <S.ButtonsDivider />
            <BabyPersonalInfoContainer
              isDisease={true}
            ></BabyPersonalInfoContainer>
            <BabyPersonalInfoContainer
              isDisease={false}
            ></BabyPersonalInfoContainer>
          </S.OurBabyInfoContainer>
        </S.EmptyContainer>
      </Background>
    </S.OurBabyInfoWholeContainer>
  );
};

export default OurBabyInfo;

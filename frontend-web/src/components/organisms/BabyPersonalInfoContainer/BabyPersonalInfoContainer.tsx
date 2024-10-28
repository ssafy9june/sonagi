import React, { useState } from 'react';
import { Text } from '@/components/atoms/Text/Text.styles';
import * as S from '@/components/organisms/BabyPersonalInfoContainer/BabyPersonalInfoContainer.style';
import { BabyPersonalInfoButton } from '@/components/molecules/BabyPersonalInfoButton/BabyPersonalInfoButton';
import AddCautionButton from '@/components/molecules/AddCautionButton/AddCautionButton';
import { useRecoilValue } from 'recoil';
import { useGetIllness } from '@/apis/Memo/Queries/useGetIllness';
import { selectedBabyState } from '@/states/babyState';
import { useGetCaution } from '@/apis/Memo/Queries/useGetCaution';
// import AddCautionButton from '@/components/molecules/AddCautionButton/AddCautionButton';

interface BabyPersonalInfoProps {
  isDisease: boolean;
}

interface BabyPersonalInfoArrayProps {
  memoId: number;
  userId: number;
  name: string;
  memo: string;
}

const BabyPersonalInfoContainer = ({ isDisease }: BabyPersonalInfoProps) => {
  const disease =
    '우리 아이는 이러한 <span style="font-weight: 700">질병/알러지</span>등이 있어요';

  const info =
    '우리 아이를 돌봐주실 땐 <span style="font-weight: 700">이런 점을 주의해주세요</span>';

  const babyInfo = useRecoilValue(selectedBabyState);
  // const queryClient = useQueryClient();

  const diseaseArray = useGetIllness(babyInfo.babyId);
  const cautionArray = useGetCaution(babyInfo.babyId);

  const BabyPersonalInfoArray = isDisease ? diseaseArray : cautionArray;

  const BabyPersonalInfoButtonHandler = () => {
    return BabyPersonalInfoArray.map(
      (data: BabyPersonalInfoArrayProps, index: number) => (
        <BabyPersonalInfoButton
          key={index}
          memo={data.memo}
          name={data.name}
          memoId={data.memoId}
          isDisease={isDisease}
          userId={data.userId}
        />
      )
    );
  };

  return (
    <S.BPICContainer>
      <Text
        size="medium1"
        dangerouslySetInnerHTML={{ __html: isDisease ? disease : info }}
        style={{ margin: '10px 0px 15px 0px' }}
      />
      <S.BPICScrollWrapper>
        <S.BPICButtonWrapper>
          {BabyPersonalInfoButtonHandler()}
          {BabyPersonalInfoArray.length < 8 ? (
            <>
              <AddCautionButton isDisease={isDisease} />
            </>
          ) : null}
        </S.BPICButtonWrapper>
      </S.BPICScrollWrapper>
    </S.BPICContainer>
  );
};

export default BabyPersonalInfoContainer;

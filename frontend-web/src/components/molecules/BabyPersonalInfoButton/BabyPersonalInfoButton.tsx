import Button from '@/components/atoms/Button/Button';
import { Text } from '@/components/atoms/Text/Text.styles';
import * as S from '@/components/molecules/BabyPersonalInfoButton/BabyPersonalInfoButton.styles';
import { CautionModal } from '@/components/organisms/CautionModal/CautionModal';
import { useState } from 'react';

interface BabyPersonalInfoButtonProps {
  memo: string;
  name: string;
  isDisease: boolean;
  memoId: number;
  userId: number;
}

const BabyPersonalInfoButton = ({
  memo,
  name,
  isDisease,
  memoId,
  userId,
}: BabyPersonalInfoButtonProps) => {
  const [cautionModalOpen, setCautionModalOpen] = useState(false);

  const modalClose = (
    setState: React.Dispatch<React.SetStateAction<boolean>>
  ) => {
    setState(false);
  };

  return (
    <>
      <CautionModal
        name={name}
        memo={memo}
        onModalClose={() => modalClose(setCautionModalOpen)}
        modalOpen={cautionModalOpen}
        isDisease={isDisease}
        isView={true}
        memoId={memoId}
        userId={userId}
      />
      <S.BabyPersonalInfoButtonContainer
        onClick={() => {
          setCautionModalOpen(true);
        }}
      >
        <Button>
          <S.ShowTextWrapper>
            <Text size="medium3">{memo}</Text>
          </S.ShowTextWrapper>
          <Text style={{ marginTop: '5px', float: 'right' }} size="small">
            {name}
          </Text>
        </Button>
      </S.BabyPersonalInfoButtonContainer>
    </>
  );
};

export { BabyPersonalInfoButton };

import * as S from '@/components/organisms/CautionModal/CautionModal.styles';
import Modal from '@/components/organisms/Modal/Modal';
import { BabiesOfUser, CustomModal } from '@/types';
import { useRecoilValue } from 'recoil';
import { selectedBabyState } from '@/states/babyState';
import { CautionRecorder } from '@/components/molecules/CautionRecorder/CautionRecorder';
import { Text } from '@/components/atoms/Text/Text.styles';
import Button from '@/components/atoms/Button/Button';
import theme from '@/styles/theme';
import { CautionDetail } from '@/components/molecules/CautionDetail/CautionDetail';

interface CautionModalProps {
  onModalClose: () => void;
  modalOpen: boolean;
  name?: string;
  memo?: string;
  isDisease: boolean;
  isView: boolean;
  memoId?: number;
  userId?: number;
}

const CautionModal = ({
  onModalClose,
  modalOpen,
  name,
  memo,
  isDisease,
  isView,
  memoId,
  userId,
}: CautionModalProps) => {
  const babyInfo: BabiesOfUser = useRecoilValue(selectedBabyState);
  const buttonHandler = () => {
    onModalClose();
  };

  return (
    <>
      <Modal isOpen={modalOpen} onClose={onModalClose}>
        <S.CautionModalContainer>
          {isView && memo && name && memoId && userId ? (
            <CautionDetail
              onModalClose={onModalClose}
              name={name}
              memo={memo}
              memoId={memoId}
              userId={userId}
              isDisease={isDisease}
            ></CautionDetail>
          ) : (
            <CautionRecorder
              onModalClose={onModalClose}
              isDisease={isDisease}
            ></CautionRecorder>
          )}
        </S.CautionModalContainer>
      </Modal>
    </>
  );
};

export { CautionModal };

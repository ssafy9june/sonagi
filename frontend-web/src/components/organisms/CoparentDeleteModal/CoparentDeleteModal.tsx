import { useDeleteCoparent } from '@/apis/Baby/Mutations/useDeleteCoparent';
import Button from '@/components/atoms/Button/Button';
import { Text } from '@/components/atoms/Text/Text.styles';
import {
  CoparentDeleteButtonContainer,
  CoparentDeleteModalContainer,
  CoparentDeleteModalContentWrapper,
  CoparentDeleteModalTitleWrapper,
} from '@/components/organisms/CoparentDeleteModal/CoparentDeleteModal.styles';
import Modal from '@/components/organisms/Modal/Modal';
import { Toast } from '@/components/organisms/Toast/Toast';
import { selectedBabyState } from '@/states/babyState';
import theme from '@/styles/theme';
import { CustomModal, User } from '@/types';
import { useQueryClient } from '@tanstack/react-query';
import { useState } from 'react';
import { useRecoilValue } from 'recoil';

interface Props extends CustomModal {
  coparent: User;
}

const CoparentDeleteModal = ({ onModalClose, modalOpen, coparent }: Props) => {
  const [showToast, setShowToast] = useState<boolean>(false);
  const useDeleteCoparentMutation = useDeleteCoparent();

  const babyInfo = useRecoilValue(selectedBabyState);
  const queryClient = useQueryClient();

  const deleteCoparent = (coparentId: number) => {
    useDeleteCoparentMutation.mutate(
      {
        coparentId: coparentId,
        babyId: babyInfo.babyId,
      },
      {
        onSuccess: () => {
          setShowToast(true);
          queryClient.invalidateQueries(['coParent', babyInfo.babyId]);
          onModalClose();
        },
        onError: () => {
          console.log('삭제 실패');
        },
      }
    );
  };

  return (
    <CoparentDeleteModalContainer>
      {showToast && (
        <Toast
          message={`${coparent.name}님을 삭제하였습니다`}
          setToast={setShowToast}
        />
      )}
      <Modal isOpen={modalOpen} onClose={onModalClose}>
        <CoparentDeleteModalTitleWrapper>
          <Text size="large" className="title">
            <b>{coparent.name}</b>님을 공동 양육자 목록에서 {'\n'}
            삭제하시겠습니까?
          </Text>
        </CoparentDeleteModalTitleWrapper>
        <CoparentDeleteModalContentWrapper>
          <Text size="medium3" color={theme.color.danger}>
            {coparent.name}님이 작성한 모든 기록이 사라집니다.
          </Text>
        </CoparentDeleteModalContentWrapper>
        <CoparentDeleteButtonContainer>
          <Button option="primary" onClick={onModalClose}>
            취소
          </Button>
          <Button
            option="danger"
            onClick={() => deleteCoparent(coparent.userId)}
          >
            삭제
          </Button>
        </CoparentDeleteButtonContainer>
      </Modal>
    </CoparentDeleteModalContainer>
  );
};

export { CoparentDeleteModal };

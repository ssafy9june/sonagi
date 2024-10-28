import Modal from '@/components/organisms/Modal/Modal';
import { CustomModal } from '@/types';

interface CheckBabyModalProps extends CustomModal {}

const CheckBabyModal = ({ onModalClose, modalOpen }: CheckBabyModalProps) => {
  return (
    <Modal height={22} onClose={onModalClose} isOpen={modalOpen}>
      <p>아이 정보를 확인해주세요</p>
      <p>아이 이름: </p>
      <p>아이 생년월일 : </p>
    </Modal>
  );
};

export { CheckBabyModal };

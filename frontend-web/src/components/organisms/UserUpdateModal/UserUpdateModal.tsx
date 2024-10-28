import { useUpdateUser } from '@/apis/User/Mutations/useUpdateUser';
import Button from '@/components/atoms/Button/Button';
import Input from '@/components/atoms/Input/Input';
import { Text } from '@/components/atoms/Text/Text.styles';
import Modal from '@/components/organisms/Modal/Modal';
import {
  UpdateButtonWrapper,
  UserModalContainer,
} from '@/components/organisms/UserUpdateModal/UserUpdateModal.styles';
import { userInfoState } from '@/states/userState';
import theme from '@/styles/theme';
import { CustomModal } from '@/types';
import { ChangeEvent, useState } from 'react';
import { useRecoilState } from 'recoil';

const UserUpdateModal = ({ onModalClose, modalOpen }: CustomModal) => {
  const [userInfo, setUserInfo] = useRecoilState(userInfoState);
  const [newUserName, setNewUserName] = useState(userInfo.name);
  const updateUserMutation = useUpdateUser();

  const plcaeholder = '수정할 이름을 입력해주세요';

  const onChangeInput = (e: ChangeEvent<HTMLInputElement>) => {
    setNewUserName(e.target.value);
  };

  const onUpdateClick = () => {
    updateUserMutation.mutate({ userId: userInfo.userId, name: newUserName });
    setUserInfo({ ...userInfo, name: newUserName });
    onModalClose();
  };

  return (
    <Modal onClose={onModalClose} isOpen={modalOpen}>
      <UserModalContainer>
        <Text size="medium2" color={theme.color.gray1}>
          수정할 닉네임을 작성해주세요.
        </Text>
        <Input
          onChange={onChangeInput}
          value={newUserName}
          placeholder={plcaeholder}
          fontSize={theme.fontSize.headMedium}
          $borderRadius={22}
          autoFocus
        />
        <UpdateButtonWrapper>
          <Button option="danger" onClick={onModalClose}>
            수정 취소
          </Button>
          <Button option="activated" size="large" onClick={onUpdateClick}>
            수정하기
          </Button>
        </UpdateButtonWrapper>
      </UserModalContainer>
    </Modal>
  );
};

export { UserUpdateModal };

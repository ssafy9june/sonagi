import { useGetCoParent } from '@/apis/Baby/Queries/useGetCoParent';
import { Image } from '@/components/atoms/Image/Image';
import {
  CoParentListContainer,
  CoParentWrapper,
} from '@/components/organisms/CoparentList/CoparentList.styles';
import { userInfoState } from '@/states/userState';
import { BabiesOfUser, User } from '@/types';
import { useRecoilValue } from 'recoil';
import deleteIcon from '@/assets/images/icon-delete-red-circle.png';
import { Text } from '@/components/atoms/Text/Text.styles';
import theme from '@/styles/theme';
import { useState } from 'react';
import { BabyCodeModal } from '@/components/organisms/BabyCodeModal/BabyCodeModal';
import { selectedBabyState } from '@/states/babyState';
import { CoparentDeleteModal } from '@/components/organisms/CoparentDeleteModal/CoparentDeleteModal';

const CoparentList = () => {
  const userInfo: User = useRecoilValue(userInfoState);
  const babyInfo: BabiesOfUser = useRecoilValue(selectedBabyState);
  const coparents = useGetCoParent(userInfo.userId, babyInfo.babyId);

  const profileColor: string[] = ['red', 'green', 'blue'];

  // 공동 양육자 초대 모달
  const [coparentModalOpen, setCoparentModalOpen] = useState(false);

  // 공동 양육자 삭제 모달
  const [coparentDeleteModalOpen, setCoparentDeleteModalOpen] = useState(false);
  const [deleteCoparent, setDeleteCoparent] = useState<User>();

  const onClickDeleteBtn = (coparent: User) => {
    setDeleteCoparent(coparent);
    setCoparentDeleteModalOpen(true);
  };

  const modalClose = (
    setState: React.Dispatch<React.SetStateAction<boolean>>
  ) => {
    setState(false);
  };

  return (
    <>
      <BabyCodeModal
        onModalClose={() => modalClose(setCoparentModalOpen)}
        modalOpen={coparentModalOpen}
      />
      {deleteCoparent && (
        <CoparentDeleteModal
          coparent={deleteCoparent}
          onModalClose={() => modalClose(setCoparentDeleteModalOpen)}
          modalOpen={coparentDeleteModalOpen}
        />
      )}

      <CoParentListContainer>
        {coparents &&
          coparents.map((coparent, idx) => {
            return (
              <CoParentWrapper key={coparent.userId}>
                {babyInfo.authority === 'Y' && (
                  <Image
                    onClick={() => onClickDeleteBtn(coparent)}
                    className="delete"
                    src={deleteIcon}
                    height={30}
                    $unit="%"
                  />
                )}
                <Image
                  src={require(`@/assets/images/icon-user-${
                    profileColor[idx % 3]
                  }.png`)}
                  height={80}
                  $unit="%"
                />
                <Text size="medium3">{coparent.name}</Text>
              </CoParentWrapper>
            );
          })}

        <CoParentWrapper>
          <Image
            src={require('@/assets/images/icon-plus-blue-circle-dash.png')}
            height={80}
            $unit="%"
            onClick={() => setCoparentModalOpen(true)}
          />
          <Text size="medium3" color={theme.color.blue}>
            추가하기
          </Text>
        </CoParentWrapper>
      </CoParentListContainer>
    </>
  );
};

export { CoparentList };

import * as S from '@/components/molecules/CautionDetail/CautionDetail.styles';
import Button from '@/components/atoms/Button/Button';
import { Text } from '@/components/atoms/Text/Text.styles';
import { ChangeEvent, useState } from 'react';
import bellImg from '@/assets/images/img-bell.png';
import { Image } from '@/components/atoms/Image/Image';
import { useDeleteIllness } from '@/apis/Memo/Mutations/useDeleteIllness';
import { useQueryClient } from '@tanstack/react-query';
import { useRecoilValue } from 'recoil';
import { selectedBabyState } from '@/states/babyState';
import { userInfoState } from '@/states/userState';
import { editIllness } from '@/apis/Memo/memoAPI';
import { useEditIllness } from '@/apis/Memo/Mutations/useEditIllness';
import { useDeleteCaution } from '@/apis/Memo/Mutations/useDeleteCaution';
import { useEditCaution } from '@/apis/Memo/Mutations/useEditCaution';
import theme from '@/styles/theme';

interface CautionDetailProps {
  onModalClose: () => void;
  name: string;
  memo: string;
  memoId: number;
  userId: number;
  isDisease: boolean;
}

const CautionDetail = ({
  onModalClose,
  name,
  memo,
  memoId,
  userId,
  isDisease,
}: CautionDetailProps) => {
  const [inputValue, setInputValue] = useState(memo);
  const [inputCount, setInputCount] = useState(inputValue.length);
  const [isEdit, setIsEdit] = useState(false);

  const useDeleteIllnessMutation = useDeleteIllness();
  const useEditIllnessMutation = useEditIllness();
  const useDeleteCautionMutation = useDeleteCaution();
  const useEditCautionMutation = useEditCaution();

  const selectedUserInfo = useRecoilValue(userInfoState);

  const [cautionMemo, setCautionMemo] = useState(memo);

  const deleteBtnHandler = (memoId: number) => {
    isDisease
      ? useDeleteIllnessMutation.mutate(memoId)
      : useDeleteCautionMutation.mutate(memoId);
    onModalClose();
  };

  const onInputHandler = (e: ChangeEvent<HTMLTextAreaElement>) => {
    setCautionMemo(e.target.value);
    setInputCount(e.target.value.length);
    setInputValue(e.target.value);
  };

  const buttonActionHandler = () => {
    isDisease
      ? useEditIllnessMutation.mutate({
          memoId: memoId,
          memo: cautionMemo,
        })
      : useEditCautionMutation.mutate({
          memoId: memoId,
          memo: cautionMemo,
        });
    onModalClose();
  };

  // };

  return (
    <>
      {isEdit ? (
        <>
          <S.MemoWrapper>
            <S.MemoArea
              value={inputValue}
              // onTouchStart={onTouchStartHandler}
              onChange={onInputHandler}
              maxLength={139}
            ></S.MemoArea>
            <Text>
              <S.WordCount>{inputCount}/140</S.WordCount>
            </Text>
          </S.MemoWrapper>
          <Button size="large" option="activated" onClick={buttonActionHandler}>
            <Text color="white" size="headSmall">
              수정하기
            </Text>
          </Button>
        </>
      ) : (
        <>
          <S.MemoWrapper style={{ backgroundColor: theme.color.white1 }}>
            <div style={{ display: 'flex', alignItems: 'center' }}>
              <Image
                src={bellImg}
                width={26}
                $unit="px"
                style={{ marginRight: '5px' }}
              ></Image>
              <Text
                style={{
                  justifyContent: 'left',
                  alignItems: 'center',
                }}
                size="headSmall"
              >
                {name}님
              </Text>
            </div>
            <S.Divider></S.Divider>
            <S.showTextWrapper>
              <Text size="medium2">{memo}</Text>
            </S.showTextWrapper>
          </S.MemoWrapper>
          {selectedUserInfo.userId === userId ? (
            <>
              <S.UpdateButtonWrapper>
                <Button
                  option="primary"
                  size="large"
                  onClick={() => setIsEdit(true)}
                >
                  수정하기
                </Button>
                <Button
                  option="danger"
                  size="large"
                  onClick={() => deleteBtnHandler(memoId)}
                >
                  삭제하기
                </Button>
              </S.UpdateButtonWrapper>
            </>
          ) : null}
        </>
      )}
    </>
  );
};

export { CautionDetail };

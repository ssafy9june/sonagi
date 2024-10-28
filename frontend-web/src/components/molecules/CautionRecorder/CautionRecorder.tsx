import * as S from '@/components/molecules/CautionRecorder/CautionRecorder.styles';
import Button from '@/components/atoms/Button/Button';
import { Text } from '@/components/atoms/Text/Text.styles';
import { ChangeEvent, useState } from 'react';
import { useRecoilValue } from 'recoil';
import { selectedBabyState } from '@/states/babyState';
import { userInfoState } from '@/states/userState';
import { useAddIllness } from '@/apis/Memo/Mutations/useAddIllness';
import { useAddCaution } from '@/apis/Memo/Mutations/useAddCaution';

const CautionRecorder = ({ onModalClose, isDisease }: any) => {
  const [inputValue, setInputValue] = useState('');
  const [inputCount, setInputCount] = useState(''.length);
  const [memo, setMemo] = useState('');

  const babyInfo = useRecoilValue(selectedBabyState);
  const userInfo = useRecoilValue(userInfoState);
  const addIllnessMutation = useAddIllness();
  const addCautionMutation = useAddCaution();

  const onInputHandler = (e: ChangeEvent<HTMLTextAreaElement>) => {
    setMemo(e.target.value);
    setInputCount(e.target.value.length);
    setInputValue(e.target.value);
  };

  const buttonActionHandler = () => {
    if (memo.length === 0) {
      alert('내용을 작성해주세요');
    } else {
      isDisease
        ? addIllnessMutation.mutate({
            userId: userInfo.userId,
            babyId: babyInfo.babyId,
            memo: memo,
          })
        : addCautionMutation.mutate({
            userId: userInfo.userId,
            babyId: babyInfo.babyId,
            memo: memo,
          });

      console.log('buttonActionlog');
      onModalClose();
    }
  };
  return (
    <>
      <S.MemoWrapper>
        <S.MemoArea
          value={inputValue}
          // onTouchStart={onTouchStartHandler}
          onChange={onInputHandler}
          maxLength={139}
          placeholder={'주의사항을 입력해주세요.'}
        ></S.MemoArea>
        <Text>
          <S.WordCount>{inputCount}/140</S.WordCount>
        </Text>
      </S.MemoWrapper>
      <Button // 상태에 따른 option 전달
        size="large"
        option="activated"
        onClick={buttonActionHandler}
      >
        <Text color="white" size="headSmall">
          등록하기
        </Text>
      </Button>
    </>
  );
};

export { CautionRecorder };

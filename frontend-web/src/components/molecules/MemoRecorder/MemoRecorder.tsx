import { useState, ChangeEvent } from 'react';
import * as S from '@/components/molecules/MemoRecorder/MemoRecorder.style';
import { Text } from '@/components/atoms/Text/Text.styles';
import theme from '@/styles/theme';

interface MemoRecorderProps {
  setMemo: (value: string) => void;
  placeholder: string;
}

const MemoRecorder = ({ setMemo, placeholder }: MemoRecorderProps) => {
  // 초기값이 없으면 initialPlaceholder는 '상세 정보..', 있으면 props 값
  const [inputValue, setInputValue] = useState(placeholder);
  const [inputCount, setInputCount] = useState(placeholder.length);

  const onInputHandler = (e: ChangeEvent<HTMLTextAreaElement>) => {
    setMemo(e.target.value);
    setInputCount(e.target.value.length);
    setInputValue(e.target.value);
  };

  return (
    <>
      <S.MemoTextWrapper>
        <Text size="headSmall"> 메모 </Text>
      </S.MemoTextWrapper>
      <S.MemoWrapper>
        <S.MemoArea
          value={inputValue}
          onChange={onInputHandler}
          maxLength={50}
          placeholder={'상세 정보를 입력해주세요.'}
        ></S.MemoArea>
        <Text>
          <S.WordCount>
            <Text size="medium3" color={theme.color.gray1}>
              {inputCount}/50
            </Text>
          </S.WordCount>
        </Text>
      </S.MemoWrapper>
    </>
  );
};

export default MemoRecorder;

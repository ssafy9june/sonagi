import React, { useState, ChangeEvent } from 'react';
import * as S from '@/components/molecules/DiaryRecorder/DiaryRecorder.style';
import { Text } from '@/components/atoms/Text/Text.styles';
import theme from '@/styles/theme';

interface DiaryRecorderProps {
  onDataUpdate: (data: string) => void;
  tContent?: string;
}

const DiaryRecorder = ({ onDataUpdate, tContent }: DiaryRecorderProps) => {
  const [inputCount, setInputCount] = useState<number>(
    tContent ? tContent.length : 0
  );
  const [content, setContent] = useState<string>(tContent ? tContent : '');

  const onInputHandler = (e: ChangeEvent<HTMLTextAreaElement>) => {
    setContent(e.target.value);
    onDataUpdate(e.target.value); // 콜백 함수 호출하여 데이터 전달
    setInputCount(e.target.value.length);
  };

  return (
    <>
      <S.MemoWrapper>
        <S.MemoArea
          onChange={onInputHandler}
          maxLength={250}
          placeholder="오늘 우리 아이의 하루를 작성해주세요."
          value={content}
        ></S.MemoArea>
        <Text>
          <S.WordCount>{inputCount}/250</S.WordCount>
        </Text>
      </S.MemoWrapper>
    </>
  );
};

export default DiaryRecorder;

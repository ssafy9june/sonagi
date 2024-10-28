import React, { ChangeEvent, useState } from 'react';
import * as S from '@/components/molecules/Recorder/Recorder.styles';
import { Text } from '@/components/atoms/Text/Text.styles';

interface RecorderProps {
  onDataUpdate: (data: string) => void;
  placeHolder?: string;
  height?: string;
  wordCounter?: number;
}

const Recorder: React.FC<RecorderProps> = ({
  onDataUpdate,
  placeHolder = '',
  height = '0vh',
  wordCounter = 0,
}) => {
  const [inputCount, setInputCount] = useState<number>(0);
  const [content, setContent] = useState<string>('');

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
          maxLength={wordCounter !== 0 ? wordCounter : 1000}
          $height={height}
          placeholder={placeHolder}
          value={content}
        ></S.MemoArea>
        <Text>
          {wordCounter !== 0 && (
            <S.WordCount>
              {inputCount}/{wordCounter}
            </S.WordCount>
          )}
        </Text>
      </S.MemoWrapper>
    </>
  );
};

export default Recorder;

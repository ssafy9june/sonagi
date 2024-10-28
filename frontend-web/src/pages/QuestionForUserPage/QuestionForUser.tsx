import React, { useState } from 'react';
import { Background } from '@/components/atoms/Background/Background.styles';
import orangeBackground from '@/assets/images/background-orange-to-blue.png';
import * as S from '@/pages/QuestionForUserPage/QuestionForUser.styles';
import Back from '@/components/atoms/Back/Back';
import Recorder from '@/components/molecules/Recorder/Recorder';
import { Text } from '@/components/atoms/Text/Text.styles';
import Button from '@/components/atoms/Button/Button';
import { useNavigate } from 'react-router-dom';
import { PATH } from '@/constants/path';
import { useAddQuestion } from '@/apis/User/Mutations/useAddQuestion';
import { useRecoilValue } from 'recoil';
import { userInfoState } from '@/states/userState';
import { QuestionPost } from '@/types';

const QuestionForUser = () => {
  const navigate = useNavigate();
  const [questionContent, setQuestionContent] = useState<string>('');
  const [questionTitle, setQuestionTitle] = useState<string>('');

  const userInfo = useRecoilValue(userInfoState);
  const useAddQuestionMutation = useAddQuestion();

  const onDataUpdate = (content: string) => {
    setQuestionContent(content);
  };
  const onDataUpdateTitle = (title: string) => {
    setQuestionTitle(title);
  };

  const handleSubmit = () => {
    useAddQuestionMutation.mutate(
      {
        title: questionTitle,
        content: questionContent,
        userId: userInfo.userId,
        createdAt: new Date(new Date().getTime() + 9 * 60 * 60 * 1000)
          .toISOString()
          .slice(0, 10),
      } as QuestionPost,
      {
        onSuccess: () => {
          alert('빠른 시일 내에 반영, 답변 드리겠습니다');
          navigate(PATH.MYPAGE);
        },
      }
    );
  };

  return (
    <>
      <Background $background={orangeBackground}>
        <S.QuestionForUserContainer>
          <Back> </Back>
          <S.QuestionForUserHeader>
            <Text size={'headMedium'} $fontWeight={900}>
              질문 & 요청 등록
            </Text>
          </S.QuestionForUserHeader>
          <S.QuestionForUserBody>
            <Recorder
              onDataUpdate={onDataUpdate}
              wordCounter={30}
              height="2.5rem"
              placeHolder="제목을 입력해주세요"
            ></Recorder>
            <Recorder
              onDataUpdate={onDataUpdateTitle}
              wordCounter={250}
              height="35vh"
              placeHolder="질문, 요청 사항을 입력 해주세요."
            ></Recorder>
          </S.QuestionForUserBody>
          <S.RegisterBtnContainer>
            <Button option="activated" onClick={handleSubmit}>
              등록하기
            </Button>
          </S.RegisterBtnContainer>
        </S.QuestionForUserContainer>
      </Background>
    </>
  );
};

export default QuestionForUser;

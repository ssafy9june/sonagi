import { useState, useEffect } from 'react';
import { Link, Outlet, useParams } from 'react-router-dom';
import { instance } from '@/apis/instance';
import {
  ContentContainer,
  DetailContent,
  DetailTitle,
  QuestionDetailContainer,
  AdminButton,
  DetailContainer,
} from '../AdminPage/AdminPage.style';

const QuestionDetailPage = () => {
  const questionID = useParams().id;
  const [detailQuestionData, setDetailQuestionData] = useState<any>([]);

  useEffect(() => {
    instance
      .get(`/questions/${questionID}`)
      .then(response => {
        setDetailQuestionData(response.data);
      })

      .catch(function (error) {
        console.log(error);
      });
  }, [questionID]);

  return (
    <DetailContainer>
      <ContentContainer>
        <QuestionDetailContainer>
          <a>사용자 ID : {detailQuestionData.userId}</a>
          <a>등록일 : {detailQuestionData.createdAt}</a>
        </QuestionDetailContainer>
        <DetailTitle>
          <p>제목 : {detailQuestionData.title}</p>
        </DetailTitle>
        <DetailContent>
          <p>내용 : {detailQuestionData.content}</p>
        </DetailContent>
        <AdminButton>
          <Link to={`/admin/question`}>목록</Link>
        </AdminButton>
        <Outlet />
      </ContentContainer>
    </DetailContainer>
  );
};

export default QuestionDetailPage;

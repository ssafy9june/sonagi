import { useState, useEffect } from 'react';
import { Link, Outlet } from 'react-router-dom';
import { Question } from '@/types';
import { instance } from '@/apis/instance';
import { QuestionContainer, MainContainer } from '../AdminPage/AdminPage.style';

const QuestionPage = () => {
  const [currentPost, setCurrentPost] = useState<Question[]>([]);

  const getQuestionList = async () => {
    try {
      const response = await instance.get(`/questions`);
      return response.data;
    } catch (error) {
      throw new Error('no data return ed from the API - 문의사항');
    }
  };

  useEffect(() => {
    getQuestionList()
      .then(questionList => {
        setCurrentPost([...questionList].reverse());
      })
      .catch(error => {
        console.error('문의사항 데이터 가져오기 실패:', error);
      });
  }, []);
  return (
    <MainContainer>
      <Outlet />
      <QuestionContainer>
        <table>
          <colgroup>
            <col width="15%" />
            <col width="30%" />
            <col width="20%" />
            <col width="44%" />
          </colgroup>

          <thead>
            <tr>
              <th>번호</th>
              <th>제목</th>
              <th>사용자</th>
              <th>등록일</th>
            </tr>
          </thead>

          <tbody>
            {currentPost.map((questionListItem, index) => (
              <tr key={index}>
                <td>{index + 1}</td>
                <td className="title">
                  <Link to={`/admin/question/${questionListItem.questionId}`}>
                    {questionListItem.title}
                  </Link>
                </td>
                <td>{questionListItem.userId}</td>
                <td>{questionListItem.createdAt}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </QuestionContainer>
    </MainContainer>
  );
};

export default QuestionPage;

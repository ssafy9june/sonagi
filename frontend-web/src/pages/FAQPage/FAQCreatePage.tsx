import { useState } from 'react';
import { userInfoState } from '@/states/userState';
import { useRecoilValue } from 'recoil';
import { instance } from '@/apis/instance';
import { useNavigate, Outlet } from 'react-router-dom';
import {
  AdminButtonContainer,
  AdminButton,
  ContentContainer,
  DetailContent,
  DetailTitle,
  DetailContainer,
  CategoryConatiner,
} from '../AdminPage/AdminPage.style';
const FAQCreatePage = () => {
  const userInfo = useRecoilValue(userInfoState);
  const navigate = useNavigate();
  const categories = ['회원정보', '운영정책', '이용문의', '기타'];
  const [title, setTitle] = useState<string>('');
  const [content, setContent] = useState<string>('');
  const [category, setCategory] = useState<string>('');
  const formSubmit = async () => {
    if (title.length === 0) {
      alert('제목을 입력해 주세요.');
    } else if (content.length === 0) {
      alert('내용을 입력해 주세요.');
    } else if (title.length > 30) {
      alert('제목은 최대 30자까지 입력 가능합니다.');
    } else if (content.length > 250) {
      alert('내용은 최대 250자까지 입력 가능합니다.');
    } else if (category === '') {
      alert('카테고리를 선택해주세요.');
    } else {
      instance
        .post('http://localhost:8080/api/faqs', {
          userId: userInfo.userId,
          category: category,
          title: title,
          content: content,
        })
        .then(function () {
          navigate('/admin/faq');
        })

        .catch(function (error) {
          console.log(error);
        });
    }
  };
  const formCancel = async () => {
    if (window.confirm('게시글 작성을 취소하시겠습니까?')) {
      navigate('/admin/faq');
    } else {
      return false;
    }
  };
  return (
    <DetailContainer>
      <ContentContainer>
        <form>
          <CategoryConatiner>
            <label htmlFor="category">카테고리 :</label>
            <select
              id="category"
              value={category}
              onChange={e => setCategory(e.target.value)}
            >
              <option value="">카테고리를 선택하세요</option>
              {categories.map(category => (
                <option key={category} value={category}>
                  {category}
                </option>
              ))}
            </select>
          </CategoryConatiner>
          <DetailTitle>
            <input
              type="text"
              id="title"
              value={title}
              onChange={e => setTitle(e.target.value)}
              placeholder="제목을 입력해주세요."
            />
          </DetailTitle>
          <DetailContent>
            <textarea
              name="content"
              id="content"
              value={content}
              onChange={e => setContent(e.target.value)}
              placeholder="내용을 입력해주세요."
            />
          </DetailContent>
        </form>
        <AdminButtonContainer>
          <AdminButton onClick={formSubmit}>등록</AdminButton>
          <AdminButton onClick={formCancel}>취소</AdminButton>
        </AdminButtonContainer>
        <Outlet />
      </ContentContainer>
    </DetailContainer>
  );
};

export default FAQCreatePage;

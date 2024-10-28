import { useState, useEffect } from 'react';
import { Link, useNavigate, Outlet, useParams } from 'react-router-dom';
import { instance } from '@/apis/instance';
import {
  AdminButtonContainer,
  AdminButton,
  ContentContainer,
  DetailContent,
  DetailTitle,
  DetailContainer,
  DetailCategory,
} from '../AdminPage/AdminPage.style';

const FAQDetailPage = () => {
  const faqId = useParams().id;
  const navigate = useNavigate();
  const [detailFAQData, setDetailFAQData] = useState<any>([]);

  useEffect(() => {
    instance
      .get(`/faqs/${faqId}`)
      .then(response => {
        setDetailFAQData(response.data);
      })

      .catch(function (error) {
        console.log(error);
      });
  }, [faqId]);

  const deleteFAQ = async () => {
    if (window.confirm('게시글을 삭제하시겠습니까?')) {
      try {
        await instance.delete(`/faqs/${faqId}`);
        alert('게시글이 삭제되었습니다.');
        navigate('/admin/faq');
      } catch (error) {
        console.log(error);
      }
    }
  };
  return (
    <DetailContainer>
      <ContentContainer>
        <DetailCategory>카테고리 : {detailFAQData.category}</DetailCategory>
        <DetailTitle>{detailFAQData.title}</DetailTitle>
        <DetailContent>{detailFAQData.content}</DetailContent>
      </ContentContainer>
      <AdminButtonContainer>
        <AdminButton>
          <Link to={`/admin/faq/modify/${detailFAQData.faqId}`}>수정</Link>
        </AdminButton>
        <AdminButton onClick={deleteFAQ}>삭제</AdminButton>
        <AdminButton>
          <Link to={`/admin/faq`}>목록</Link>
        </AdminButton>
      </AdminButtonContainer>
      <Outlet />
    </DetailContainer>
  );
};

export default FAQDetailPage;

import { useState, useEffect } from 'react';
import * as S from '@/pages/FAQForUserPage/FAQForUserPage.styles';
import FAQAccordion from '@/components/molecules/FAQAccordion/FAQAccordion';
import { instance } from '@/apis/instance';
import { Link } from 'react-router-dom';
import {
  AdminButtonContainer,
  AdminButton,
} from '../AdminPage/AdminPage.style';

interface FAQItem {
  title: string;
  content: string;
}

interface FAQCategory {
  category: string;
  faqs: FAQItem[];
}

const FAQPage = () => {
  const [selectedCategory, setSelectedCategory] = useState<string>('전체');
  const [currentPost, setCurrentPost] = useState<FAQCategory[]>([]);

  const handleCategoryClick = (category: string) => {
    setSelectedCategory(category);
  };

  const getFAQList = async () => {
    try {
      const memberFAQResponse = await instance.get(`/faqs/member`);
      const operationFAQResponse = await instance.get(`/faqs/operation`);
      const inquiryFAQResponse = await instance.get(`/faqs/use`);
      const etcFAQResponse = await instance.get(`/faqs/etc`);

      setCurrentPost([
        {
          category: '전체',
          faqs: [
            ...memberFAQResponse.data,
            ...operationFAQResponse.data,
            ...inquiryFAQResponse.data,
            ...etcFAQResponse.data,
          ],
        },
        {
          category: '회원정보',
          faqs: memberFAQResponse.data,
        },
        {
          category: '운영정책',
          faqs: operationFAQResponse.data,
        },
        {
          category: '이용문의',
          faqs: inquiryFAQResponse.data,
        },
        {
          category: '기타',
          faqs: etcFAQResponse.data,
        },
      ]);
    } catch (error) {
      console.error('FAQ 데이터 가져오기 실패:', error);
    }
  };

  useEffect(() => {
    getFAQList();
  }, []);

  return (
    <>
      <S.FAQHeader></S.FAQHeader>
      <S.CategoryToggleContainer>
        {currentPost.map(category => (
          <S.CategoryToggleButton
            key={category.category}
            onClick={() => handleCategoryClick(category.category)}
            $isSelected={selectedCategory === category.category}
          >
            {category.category}
          </S.CategoryToggleButton>
        ))}
      </S.CategoryToggleContainer>
      <S.AdminFAQContainer>
        {currentPost.map(category => (
          <div key={category.category}>
            {category.faqs.map(faq => (
              <FAQAccordion
                key={faq.title}
                title={faq.title}
                content={faq.content}
                isVisible={
                  selectedCategory === '전체' ||
                  selectedCategory === category.category
                }
              />
            ))}
          </div>
        ))}
      </S.AdminFAQContainer>
      <AdminButtonContainer>
        <AdminButton>
          <Link to={`/admin/faq/create`}>등록하기</Link>
        </AdminButton>
      </AdminButtonContainer>
    </>
  );
};

export default FAQPage;

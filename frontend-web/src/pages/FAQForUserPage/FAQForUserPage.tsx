import { useState, useEffect } from 'react';
import * as S from '@/pages/FAQForUserPage/FAQForUserPage.styles';
import FAQAccordion from '@/components/molecules/FAQAccordion/FAQAccordion';
import { instance } from '@/apis/instance';
import { Text } from '@/components/atoms/Text/Text.styles';
import Back from '@/components/atoms/Back/Back';
import { Background } from '@/components/atoms/Background/Background.styles';
import orangeBackground from '@/assets/images/background-orange-to-blue.png';

interface FAQItem {
  title: string;
  content: string;
}

interface FAQCategory {
  category: string;
  faqs: FAQItem[];
}

const FAQForUserPage = () => {
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
      <Background $background={orangeBackground}>
        <Back> </Back>
        <S.FAQHeader>
          <Text size={'large'} $fontWeight={900}>
            자주 묻는 질문
          </Text>
        </S.FAQHeader>
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
        <S.FAQContatiner>
          {currentPost.map(category => (
            <div key={category.category}>
              {category.faqs.map(faq => (
                <FAQAccordion
                  key={faq.title}
                  title={faq.title}
                  content={faq.content}
                  isVisible={selectedCategory === category.category}
                />
              ))}
            </div>
          ))}
        </S.FAQContatiner>
      </Background>
    </>
  );
};

export default FAQForUserPage;

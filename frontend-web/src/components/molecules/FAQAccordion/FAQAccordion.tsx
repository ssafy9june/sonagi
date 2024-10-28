import React, { useState } from 'react';
import * as S from '@/components/molecules/FAQAccordion/FAQAccordion.styles';
import { Text } from '@/components/atoms/Text/Text.styles';

interface FAQAccordionProps {
  title: string;
  content: string;
  isVisible: boolean;
}
const FAQAccordion: React.FC<FAQAccordionProps> = ({
  title,
  content,
  isVisible,
}) => {
  const [isExpanded, setIsExpanded] = useState(false);

  const toggleAccordion = () => {
    setIsExpanded(!isExpanded);
  };

  return (
    <>
      <S.AccordionContainer $isExpanded={isExpanded} $isVisible={isVisible}>
        <S.AccordionHeader onClick={toggleAccordion}>
          <Text size={'medium1Bold'}>{title}</Text>
          <S.Icon $isExpanded={isExpanded}>▼</S.Icon>
        </S.AccordionHeader>
        {isExpanded && (
          <S.AccordionContent>
            <Text size={'medium2'}>{content}</Text>
          </S.AccordionContent>
        )}
      </S.AccordionContainer>
    </>
  );
};

export default FAQAccordion;

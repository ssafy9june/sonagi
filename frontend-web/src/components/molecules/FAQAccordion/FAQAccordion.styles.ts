import theme from '@/styles/theme';
import { styled } from 'styled-components';

interface AccordionContainerProps {
  $isExpanded: boolean;
  $isVisible: boolean;
}

interface IconProps {
  $isExpanded: boolean;
}

const AccordionContainer = styled.div<AccordionContainerProps>`
  overflow: hidden;
  display: ${({ $isVisible }) => ($isVisible ? 'block' : 'none')};
  max-height: ${({ $isExpanded }) => ($isExpanded ? 'fit-content' : 'inherit')};
`;

const AccordionHeader = styled.div`
  display: flex;
  border-bottom: 1px solid ${theme.color.gray3};
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
  background-color: ${theme.color.white1};
  p {
    width: 90%;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
`;

const Icon = styled.span<IconProps>`
  margin-left: 1rem;
  color: ${theme.color.gray3};
  transform: ${({ $isExpanded }) => ($isExpanded ? 'rotate(180deg)' : 'none')};
`;

const AccordionContent = styled.div`
  padding: 1rem;
  background-color: ${theme.color.gray4};
`;
export { AccordionContainer, AccordionHeader, Icon, AccordionContent };

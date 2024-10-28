import theme from '@/styles/theme';
import styled from 'styled-components';

const FAQContatiner = styled.div`
  overflow: scroll;
  box-sizing: border-box;
  max-height: calc(100vh - 114px);
  display: flex;
  flex-direction: column;
`;
const AdminFAQContainer = styled.div`
  overflow: scroll;
  box-sizing: border-box;
  max-height: calc(100vh - 114px);
  display: flex;
  flex-direction: column;
  max-height: 60vh;
  margin-bottom: 10px;
`;
const FAQHeader = styled.div`
  padding: 1.25rem 1rem 1rem 1rem;
  display: flex;
  justify-content: center;
  align-items: center;
`;
const CategoryToggleContainer = styled.div`
  display: flex;
  justify-content: center;
  margin-bottom: 1rem;
  gap: 0.5rem;
`;
const CategoryToggleButton = styled.button<{ $isSelected: boolean }>`
  box-sizing: border-box;
  border: 1px solid ${theme.color.gray1};
  padding: 0.5rem;
  background-color: ${props =>
    props.$isSelected ? theme.color.black2 : theme.color.white1};
  color: ${props => (props.$isSelected ? '#ffffff' : '#000000')};
  border-radius: 0.25rem;
  transition:
    background-color 0.3s ease-in-out,
    color 0.3s ease-in-out;
`;

export {
  FAQContatiner,
  FAQHeader,
  CategoryToggleContainer,
  CategoryToggleButton,
  AdminFAQContainer,
};

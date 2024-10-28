import { styled } from 'styled-components';

interface CardStyleProp {
  $borderColor: string;
}

const CardContainer = styled.div<CardStyleProp>`
  width: 85vw;
  border: 1.5px solid ${props => props.$borderColor};
  margin-bottom: 30px;
  background-color: white;
  padding: 30px;
  margin-left: auto;
  margin-right: auto;
  border-radius: 11px;
  box-shadow: ${({ theme }) => theme.shadow.shadow1};
`;

export { CardContainer };

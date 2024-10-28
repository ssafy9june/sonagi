import {
  CardBarWrapperProps,
  YesterdayProps,
} from '@/components/molecules/CardContent/CardContentBar';
import { styled } from 'styled-components';

const CardBarContainer = styled.div`
  height: 10px;
  background-color: ${({ theme }) => theme.color.gray4};
  border-radius: 4px;
  margin-bottom: 2rem;
  margin-top: 0.5rem;
  position: relative;
`;

const CardBarWrapper = styled.div<CardBarWrapperProps>`
  height: 10px;
  width: ${props => `${props.$ratio}%`};
  background-color: ${props => (props.$borderColor + '96').slice(0, 7)};
  border-radius: 4px;
`;

const Yesterday = styled.div<YesterdayProps>`
  display: flex;
  flex-direction: column;
  align-items: center;
  top: -5px;
  left: ${props => `${props.$yesterDayRatio}%`};
  position: absolute;

  p {
    top: 25px;
    position: absolute;
    width: max-content;
    text-align: center;
  }
`;

const YesterdayLine = styled.div`
  height: 20px;
  border-left: 1px dashed ${({ theme }) => theme.color.gray1};
  margin-bottom: 5px;
`;

export { CardBarContainer, CardBarWrapper, Yesterday, YesterdayLine };

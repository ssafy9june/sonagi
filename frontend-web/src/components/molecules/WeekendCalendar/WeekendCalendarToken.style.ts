import { styled } from 'styled-components';
import theme from '../../../styles/theme';

interface TokenWrapperProps {
  $statusOfDay: string;
}
interface FloatingPointProps {
  $isRecordedDate: boolean;
}

export const TokenWrapper = styled.div<TokenWrapperProps>`
  display: flex;
  position: relative;
  flex-direction: column;
  text-align: center;
  background-color: ${({ $statusOfDay }) =>
    $statusOfDay === 'isToday' ? theme.color.calendarTodayBg : undefined};
  border-radius: 12px;
  padding: 16px 8px 16px 8px;
  box-sizing: border-box;
  min-width: 14%;
`;

export const FloatingPoint = styled.div<FloatingPointProps>`
  position: absolute;
  font-weight: 900;
  font-size: 20px;
  top: -25%;
  left: 50%;
  opacity: ${({ $isRecordedDate }) => ($isRecordedDate ? '100%' : '0%')};
  color: ${() => theme.color.calendarDot};
  transform: translate(-50%, 50%);
`;

import { styled } from 'styled-components';

interface HealthBarGraphStyleProps {
  $barHeight: number;
}

const Container = styled.div`
  width: 90vw;
  height: 36vh;
  margin: 30px auto 90px;
  position: relative;
`;

const LineContainer = styled.div`
  display: flex;
  height: inherit;
  flex-direction: column-reverse;
  justify-content: space-between;
  width: 100%;
`;

const LineWrapper = styled.div`
  height: 10px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  text-align: right;
`;

const Line = styled.div`
  border: 1px solid ${({ theme }) => theme.color.gray4};
  width: calc(100% - 20px);
  height: 0.1px;
`;

const BarContainer = styled.div`
  width: inherit;
  padding: 0px 20px 0px 40px;
  height: calc(100% - 10px);
  display: flex;
  justify-content: space-between;
  align-items: end;
  position: absolute;
  bottom: 5px;
`;

const Wrapper = styled.div<HealthBarGraphStyleProps>`
  width: 33px;
  height: ${props => `calc(${props.$barHeight}%)`};
  display: flex;
  align-items: center;
  flex-direction: column-reverse;
  position: relative;
`;

const HealthBar = styled.div<{ height: number; color: string }>`
  height: ${props => `${props.height}%`};
  background-color: ${props => props.color};
  border-radius: 8px;
  width: 16px;
  transform-origin: 50% 65%;
  transform: scale(0.9);
`;

// 체온
const FeverWrapper = styled.div`
  height: 10px;
  position: absolute;
  top: -27px;
  width: 44px;
  height: 23px;
  display: flex;
  border-radius: 10px;
  justify-content: center;
  align-items: center;
  border: 1px solid ${({ theme }) => theme.color.graphFeverAverage};
  background-color: ${({ theme }) => theme.color.white1};
`;

const DateContainer = styled.div`
  width: 100%;
  padding: 0px 20px 0px 40px;
  height: calc(100% / 9);
  display: flex;
  align-items: center;
  justify-content: space-between;
  text-align: center;
`;

const CategoryContainer = styled.div`
  display: flex;
  float: right;
  justify-content: space-between;
  margin-right: 20px;
  position: relative;
  margin-bottom: 30px;
`;

const CategoryWrapper = styled.div`
  display: flex;
  width: fit-content;
  margin-top: 10px;
  margin-left: 10px;
`;

const CategoryCircle = styled.div<{ $bgColor: string; $borderColor?: string }>`
  width: 14px;
  height: 14px;
  border-radius: 50%;
  background-color: ${props => props.$bgColor};
  margin-right: 6px;
  border: ${props =>
    props.$borderColor ? `1px solid ${props.$borderColor}` : 'none'};
`;

export {
  Container,
  LineContainer,
  LineWrapper,
  Line,
  // Time,
  BarContainer,
  Wrapper,
  HealthBar,
  FeverWrapper,
  DateContainer,
  CategoryContainer,
  CategoryWrapper,
  CategoryCircle,
};

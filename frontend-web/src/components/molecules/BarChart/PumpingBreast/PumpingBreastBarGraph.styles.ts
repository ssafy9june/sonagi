import { styled } from 'styled-components';

interface PumpingBreastBarGraphStyleProps {
  $barHeight: number;
}

const Container = styled.div`
  width: 90vw;
  height: 50vh;
  margin: 30px auto 30px;
  display: flex;
  flex-direction: column;
`;

const Time = styled.div`
  width: max-content;
  display: flex;
  flex-direction: column;
  height: 100%;
`;

const BarContainer = styled.div`
  width: inherit;
  height: 100%;
  display: flex;
  justify-content: space-between;
  align-items: end;
  bottom: calc(100% / 9 + 3px);
`;

const Wrapper = styled.div<PumpingBreastBarGraphStyleProps>`
  width: 33px;
  height: 100%;
  display: flex;
  flex-direction: column-reverse;
  border-radius: 4px;
  background-color: ${({ theme }) => theme.color.gray4};
`;

const Bar = styled.div<{ height: number; color: string }>`
  height: ${props => `${props.height}%`};
  background-color: ${props => props.color};
  border-radius: 4px;
`;

// 횟수
const CountContainer = styled.div`
  width: 100%;
  margin-top: 10px;
  display: flex;
  justify-content: space-between;
`;

const CountWrapper = styled.div`
  border: 1px solid ${({ theme }) => theme.color.graphCountsOfPumpingBreast};
  border-radius: 11px;
  width: 33px;
  text-align: center;
  padding: 5px 0px;
`;

// 날짜
const DateContainer = styled.div`
  width: 100%;
  height: calc(100% / 9);
  margin: 10px 0px;
  border-top: 1px solid ${({ theme }) => theme.color.gray4};
  border-bottom: 1px solid ${({ theme }) => theme.color.gray4};
  bottom: 0;
  display: flex;
  align-items: center;
  justify-content: space-between;
  text-align: center;
`;

const CategoryContainer = styled.div`
  display: flex;
  justify-content: end;
`;

const CategoryWrapper = styled.div`
  display: flex;
  margin-left: 4.5%;

  > div {
    margin-right: 6px;
  }
`;

const CategoryCircle = styled.div<{
  $bgColor: string;
  $borderColor?: string;
}>`
  width: 14px;
  height: 14px;
  border-radius: 50%;
  background-color: ${props => props.$bgColor};
  border: ${props =>
    props.$borderColor ? `1px solid ${props.$borderColor}` : 'none'};
`;

export {
  Container,
  Time,
  BarContainer,
  Wrapper,
  Bar,
  CountContainer,
  CountWrapper,
  DateContainer,
  CategoryContainer,
  CategoryWrapper,
  CategoryCircle,
};

import { styled } from 'styled-components';

interface SleepBarGraphStyleProps {
  $barHeight: number;
}

const Container = styled.div`
  width: 90vw;
  height: 50vh;
  margin: 30px auto 100px;
  position: relative;
`;

const LineContainer = styled.div`
  display: flex;
  height: inherit;
  flex-direction: column;
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

const Wrapper = styled.div<SleepBarGraphStyleProps>`
  width: 33px;
  height: ${props => `${props.$barHeight}%`};
  display: flex;
  flex-direction: column;
  position: relative;
`;

const SleepBar = styled.div<{ color: string; top: number; pass: number }>`
  background-color: ${props => props.color};
  border-radius: 4px;
  position: absolute;
  top: ${props => `calc((100% - 10px) / 1440 * ${props.top})`};
  width: 100%;
  height: ${props => `calc(100% / 1440 * ${props.pass})`};
  display: flex;
  justify-content: center;
  align-items: center;
`;

const DateContainer = styled.div`
  width: 100%;
  padding: 0px 20px 0px 40px;
  height: calc(100% / 12);
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

const CategoryCircle = styled.div<{ $bgColor: string }>`
  width: 14px;
  height: 14px;
  border-radius: 50%;
  background-color: ${props => props.$bgColor};
  margin-right: 6px;
`;

export {
  Container,
  LineContainer,
  LineWrapper,
  Line,
  BarContainer,
  Wrapper,
  SleepBar,
  DateContainer,
  CategoryContainer,
  CategoryWrapper,
  CategoryCircle,
};

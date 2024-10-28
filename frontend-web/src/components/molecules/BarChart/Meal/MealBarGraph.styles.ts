import { styled } from 'styled-components';

interface MealBarGraphStyleProps {
  $barHeight: number;
}

const Container = styled.div`
  width: 90vw;
  height: 50vh;
  margin: 30px auto 60px;
  position: relative;
`;

const LineContainer = styled.div`
  display: flex;
  height: inherit;
  flex-direction: column;
  justify-content: space-between;
  position: relative;
`;

const Line = styled.div`
  border: 1px solid ${({ theme }) => theme.color.gray4};
`;

const BarContainer = styled.div`
  width: inherit;
  padding: 0px 15px;
  height: inherit;
  display: flex;
  justify-content: space-between;
  align-items: end;
  position: absolute;
  bottom: calc(100% / 10);
`;

const Wrapper = styled.div<MealBarGraphStyleProps>`
  width: 35px;
  height: ${props => `${props.$barHeight}%`};
  display: flex;
  flex-direction: column-reverse;
`;

const Bar = styled.div<{ height: number; color: string }>`
  height: ${props => `${props.height}%`};
  background-color: ${props => props.color};
  border-radius: 4px;
`;

const DateContainer = styled.div`
  width: 100%;
  height: 10%;
  padding: 0px 15px;
  position: absolute;
  bottom: 0;
  display: flex;
  align-items: center;
  justify-content: space-between;
  text-align: center;
`;

const CategoryContainer = styled.div`
  display: flex;
  width: inherit;
  padding: 0px 15px;
  justify-content: flex-end;
`;

const CategoryWrapper = styled.div`
  display: flex;
  width: fit-content;
  margin-top: 10px;
  margin-left: 20px;

  > div {
    margin-right: 6px;
  }
`;

const CategoryCircle = styled.div<{ $bgColor: string }>`
  width: 14px;
  height: 14px;
  border-radius: 50%;
  background-color: ${props => props.$bgColor};
`;

export {
  Container,
  LineContainer,
  Line,
  BarContainer,
  Wrapper,
  Bar,
  DateContainer,
  CategoryContainer,
  CategoryWrapper,
  CategoryCircle,
};

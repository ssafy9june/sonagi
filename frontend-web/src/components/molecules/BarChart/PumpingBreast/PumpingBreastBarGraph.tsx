import { Text } from '@/components/atoms/Text/Text.styles';
import {
  Container,
  BarContainer,
  Wrapper,
  Bar,
  DateContainer,
  CategoryContainer,
  CategoryWrapper,
  CategoryCircle,
  CountContainer,
  CountWrapper,
} from '@/components/molecules/BarChart/PumpingBreast/PumpingBreastBarGraph.styles';
import theme from '@/styles/theme';

interface PumpItem {
  cnt: number;
  amount: number;
}

interface PumpWeekProps {
  [key: string]: PumpItem;
}

const PumpingBreastBarGraph = ({ data }: PumpWeekProps) => {
  const days = Object.keys(data);
  const values = Object.values(data);

  return (
    <Container>
      <BarContainer>
        {values.map((value: PumpItem, idx) => {
          return (
            <Wrapper key={idx} $barHeight={100}>
              <Bar
                height={value.amount}
                color={theme.color.graphAmountsOfPumpingBreast}
              />
            </Wrapper>
          );
        })}
      </BarContainer>
      <CountContainer>
        {values.map((value: PumpItem, idx) => {
          return (
            <CountWrapper key={idx}>
              <Text size="xSmall">{value.cnt}회</Text>
            </CountWrapper>
          );
        })}
      </CountContainer>
      <DateContainer>
        {days.map(day => {
          return (
            <Text key={day} width={35} size="xSmall">
              {day}
            </Text>
          );
        })}
      </DateContainer>
      <CategoryContainer>
        <CategoryWrapper>
          <CategoryCircle
            $bgColor={theme.color.white1}
            $borderColor={theme.color.graphCountsOfPumpingBreast}
          />
          <Text size="xSmall">횟수</Text>
        </CategoryWrapper>
        <CategoryWrapper>
          <CategoryCircle $bgColor={theme.color.graphAmountsOfPumpingBreast} />
          <Text size="xSmall">용량</Text>
        </CategoryWrapper>
      </CategoryContainer>
    </Container>
  );
};

export { PumpingBreastBarGraph };

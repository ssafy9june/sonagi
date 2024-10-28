import { Text } from '@/components/atoms/Text/Text.styles';
import {
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
} from '@/components/molecules/BarChart/Meal/MealBarGraph.styles';
import theme from '@/styles/theme';

interface MealWeekItem {
  babyFoodAmount: number;
  breastFeedingAmount: number;
  infantFormulaAmount: number;
  milkAmount: number;
}

interface MealWeekProps {
  [key: string]: MealWeekItem;
}

const MealBarGraph = ({ data }: MealWeekProps) => {
  // 10개의 line
  let lines: JSX.Element[] = [];

  for (let i = 0; i < 11; i++) {
    lines = [...lines, <Line key={i} />];
  }

  const lineLen = 10;
  const percent100 = 100 - 100 / lineLen;

  const days = Object.keys(data);
  const values = Object.values(data);

  return (
    <Container>
      <LineContainer>{lines}</LineContainer>
      <BarContainer>
        {values.map((value: MealWeekItem, idx) => {
          return (
            <Wrapper key={idx} $barHeight={percent100}>
              <Bar
                height={value.infantFormulaAmount}
                color={theme.color.graphInfantFormula}
              />

              <Bar
                height={value.breastFeedingAmount}
                color={theme.color.graphBreastFeeding}
              />
              <Bar
                height={value.babyFoodAmount}
                color={theme.color.graphBabyFood}
              />
              <Bar height={value.milkAmount} color={theme.color.graphMilk} />
            </Wrapper>
          );
        })}
      </BarContainer>
      <DateContainer>
        {days.map(date => {
          return (
            <Text width={35} size="xSmall" key={date}>
              {date}
            </Text>
          );
        })}
      </DateContainer>
      <CategoryContainer>
        <CategoryWrapper>
          <CategoryCircle $bgColor={theme.color.graphInfantFormula} />
          <Text size="xSmall">분유</Text>
        </CategoryWrapper>
        <CategoryWrapper>
          <CategoryCircle $bgColor={theme.color.graphBreastFeeding} />
          <Text size="xSmall">유축 수유</Text>
        </CategoryWrapper>
        <CategoryWrapper>
          <CategoryCircle $bgColor={theme.color.graphBabyFood} />
          <Text size="xSmall">이유식</Text>
        </CategoryWrapper>
        <CategoryWrapper>
          <CategoryCircle $bgColor={theme.color.graphMilk} />
          <Text size="xSmall">우유</Text>
        </CategoryWrapper>
      </CategoryContainer>
    </Container>
  );
};

export { MealBarGraph };

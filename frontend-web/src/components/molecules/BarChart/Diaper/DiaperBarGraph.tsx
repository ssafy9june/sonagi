import { Text } from '@/components/atoms/Text/Text.styles';
import {
  Container,
  LineContainer,
  LineWrapper,
  Line,
  BarContainer,
  Wrapper,
  DiaperBar,
  DateContainer,
  CategoryContainer,
  CategoryWrapper,
  CategoryCircle,
} from '@/components/molecules/BarChart/Diaper/DiaperBarGraph.styles';
import theme from '@/styles/theme';

interface DiaperWeekItem {
  pees: number[];
  poops: number[];
}

interface DiaperWeekProps {
  [key: string]: DiaperWeekItem;
}

const DiaperBarGraph = ({ data }: DiaperWeekProps) => {
  const times: number[] = [0, 3, 6, 9, 12, 15, 18, 21, 24];

  const days = Object.keys(data);
  const values: DiaperWeekItem[] = Object.values(data);

  return (
    <Container>
      <LineContainer>
        {[...times].map(time => {
          return (
            <LineWrapper key={time}>
              <Text size="xSmall" width={10}>
                {time}
              </Text>
              <Line />
            </LineWrapper>
          );
        })}
      </LineContainer>

      <BarContainer>
        {values.map((value, idx) => {
          return (
            <Wrapper key={idx} $barHeight={100}>
              {value.pees.map((pee, idx) => {
                return (
                  <DiaperBar key={idx} top={pee} color={theme.color.graphPee} />
                );
              })}

              {value.poops.map((poop, idx) => {
                return (
                  <DiaperBar
                    key={idx}
                    top={poop}
                    color={theme.color.graphPoop}
                  />
                );
              })}
            </Wrapper>
          );
        })}
      </BarContainer>

      <DateContainer>
        {days.map(day => {
          return (
            <Text key={day} width={35} size="xSmall">
              {day}
            </Text>
          );
        })}
      </DateContainer>
      <LineWrapper>
        <Text size="xSmall" width={10}></Text>
        <Line />
      </LineWrapper>
      <CategoryContainer>
        <CategoryWrapper>
          <CategoryCircle $bgColor={theme.color.graphPoop} />
          <Text size="xSmall">대변</Text>
        </CategoryWrapper>
        <CategoryWrapper>
          <CategoryCircle $bgColor={theme.color.graphPee} />
          <Text size="xSmall">소변</Text>
        </CategoryWrapper>
      </CategoryContainer>
    </Container>
  );
};

export { DiaperBarGraph };

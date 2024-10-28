import { Text } from '@/components/atoms/Text/Text.styles';
import {
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
} from '@/components/molecules/BarChart/Sleep/SleepBarGraph.styles';
import theme from '@/styles/theme';

interface SleepsProps {
  createdTime: number;
  passTime: number;
}

interface SleepWeekItem {
  sleeps: SleepsProps[];
}

interface SleepWeekProps {
  [key: string]: SleepWeekItem;
}

const SleepBarGraph = ({ data }: SleepWeekProps) => {
  const times: number[] = [0, 3, 6, 9, 12, 15, 18, 21, 24];

  const days = Object.keys(data);
  const values: SleepWeekItem[] = Object.values(data);

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
              {value.sleeps.map((sleep, sIdx) => {
                return (
                  <SleepBar
                    key={sIdx}
                    top={sleep.createdTime}
                    pass={sleep.passTime}
                    color={theme.color.graphSleep}
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
          <CategoryCircle $bgColor={theme.color.graphSleep} />
          <Text size="xSmall">수면</Text>
        </CategoryWrapper>
      </CategoryContainer>
    </Container>
  );
};

export { SleepBarGraph };

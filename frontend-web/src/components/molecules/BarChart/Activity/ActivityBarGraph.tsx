import { Text } from '@/components/atoms/Text/Text.styles';
import {
  Container,
  LineContainer,
  LineWrapper,
  Line,
  BarContainer,
  Wrapper,
  ActivityBar,
  DateContainer,
  CategoryContainer,
  CategoryWrapper,
  CategoryCircle,
} from '@/components/molecules/BarChart/Activity/ActivityBarGraph.styles';
import theme from '@/styles/theme';

interface ActivitysProps {
  createdTime: number;
  passTime: number;
}

interface ActivityWeekItem {
  plays: ActivitysProps[];
  tummytimes: ActivitysProps[];
}

interface ActivityWeekProps {
  [key: string]: ActivityWeekItem;
}

const ActivityBarGraph = ({ data }: ActivityWeekProps) => {
  const times: number[] = [0, 3, 6, 9, 12, 15, 18, 21, 24];

  const days = Object.keys(data);
  const values: ActivityWeekItem[] = Object.values(data);

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
              {value.plays.map((play, pIdx) => {
                return (
                  <ActivityBar
                    key={pIdx}
                    top={play.createdTime}
                    pass={play.passTime}
                    color={theme.color.graphPlayTime}
                  />
                );
              })}

              {value.tummytimes.map((tummytime, tIdx) => {
                return (
                  <ActivityBar
                    key={tIdx}
                    top={tummytime.createdTime}
                    pass={tummytime.passTime}
                    color={theme.color.graphTummyTime}
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
          <CategoryCircle $bgColor={theme.color.graphPlayTime} />
          <Text size="xSmall">놀이</Text>
        </CategoryWrapper>
        <CategoryWrapper>
          <CategoryCircle $bgColor={theme.color.graphTummyTime} />
          <Text size="xSmall">터미타임</Text>
        </CategoryWrapper>
      </CategoryContainer>
    </Container>
  );
};

export { ActivityBarGraph };

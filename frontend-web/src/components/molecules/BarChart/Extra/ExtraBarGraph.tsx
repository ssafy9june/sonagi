import { Text } from '@/components/atoms/Text/Text.styles';
import {
  Container,
  LineContainer,
  LineWrapper,
  Line,
  BarContainer,
  Wrapper,
  ExtraBar,
  DateContainer,
  CategoryContainer,
  CategoryWrapper,
  CategoryCircle,
} from '@/components/molecules/BarChart/Extra/ExtraBarGraph.styles';
import theme from '@/styles/theme';

interface ExtraWeekItem {
  cnt: number;
}

interface ExtraWeekProps {
  [key: string]: ExtraWeekItem;
}
const ExtraBarGraph = ({ data }: ExtraWeekProps) => {
  const days = Object.keys(data);
  const values: ExtraWeekItem[] = Object.values(data);

  // 최대 횟수에 따라 선의 개수가 달라짐
  let maxCnt = 4; // 현재 기록의 최대 병원 + 투약 cnt 값

  values.forEach(value => {
    maxCnt = maxCnt > value.cnt ? maxCnt : value.cnt;
  });

  const lines = [...Array(maxCnt + 1)];

  return (
    <Container>
      <LineContainer>
        {lines.map((_, idx) => {
          return (
            <LineWrapper key={idx}>
              <Text size="xSmall" width={10}>
                {idx}
              </Text>
              <Line />
            </LineWrapper>
          );
        })}
      </LineContainer>

      <BarContainer>
        {values.map((value, idx) => {
          return (
            <Wrapper key={idx} $barHeight={(100 * value.cnt) / maxCnt}>
              {[...Array(value.cnt)].map((_, hIdx) => {
                return (
                  <ExtraBar
                    key={hIdx}
                    height={100}
                    color={theme.color.graphExtra}
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
          <CategoryCircle $bgColor={theme.color.graphExtra} />
          <Text size="xSmall">기타</Text>
        </CategoryWrapper>
      </CategoryContainer>
    </Container>
  );
};

export { ExtraBarGraph };

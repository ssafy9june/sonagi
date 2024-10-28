import { Text } from '@/components/atoms/Text/Text.styles';
import {
  Container,
  LineContainer,
  LineWrapper,
  Line,
  BarContainer,
  Wrapper,
  HealthBar,
  FeverWrapper,
  DateContainer,
  CategoryContainer,
  CategoryWrapper,
  CategoryCircle,
} from '@/components/molecules/BarChart/Health/HealthBarGraph.styles';
import theme from '@/styles/theme';

interface HealthWeekItem {
  feverAvg: number;
  hospitalCnt: number;
  medicationCnt: number;
}

interface HealthWeekProps {
  [key: string]: HealthWeekItem;
}

const HealthBarGraph = ({ data }: HealthWeekProps) => {
  const days = Object.keys(data);
  const values: HealthWeekItem[] = Object.values(data);

  // 최대 횟수에 따라 선의 개수가 달라짐
  let maxCnt = 4; // 현재 기록의 최대 병원 + 투약 cnt 값

  values.forEach(value => {
    maxCnt =
      maxCnt > value.hospitalCnt + value.medicationCnt
        ? maxCnt
        : value.hospitalCnt + value.medicationCnt;
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
          const totalCnt = value.hospitalCnt + value.medicationCnt;

          return (
            <Wrapper key={idx} $barHeight={(100 * totalCnt) / maxCnt}>
              {[...Array(value.hospitalCnt)].map((_, hIdx) => {
                return (
                  <HealthBar
                    key={hIdx}
                    height={100}
                    color={theme.color.graphHospital}
                  />
                );
              })}
              {[...Array(value.medicationCnt)].map((_, mIdx) => {
                return (
                  <HealthBar
                    key={mIdx}
                    height={100}
                    color={theme.color.graphMedication}
                  />
                );
              })}
              <FeverWrapper>
                <Text size="xSmall">{value.feverAvg}℃</Text>
              </FeverWrapper>
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
          <CategoryCircle
            $bgColor={'tranasparent'}
            $borderColor={theme.color.graphFeverAverage}
          />
          <Text size="xSmall">평균 체온</Text>
        </CategoryWrapper>
        <CategoryWrapper>
          <CategoryCircle $bgColor={theme.color.graphHospital} />
          <Text size="xSmall">병원 진료</Text>
        </CategoryWrapper>
        <CategoryWrapper>
          <CategoryCircle $bgColor={theme.color.graphMedication} />
          <Text size="xSmall">투약</Text>
        </CategoryWrapper>
      </CategoryContainer>
    </Container>
  );
};

export { HealthBarGraph };

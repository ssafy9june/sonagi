import { CardContentBar } from '@/components/molecules/CardContent/CardContentBar';
import { CardContentText } from '@/components/molecules/CardContent/CardContentText';
import { CardHeader } from '@/components/molecules/CardHeader/CardHeader';
import { CardContainer } from '@/components/organisms/Card/Card.styles';
import { categoryToColorMap } from '@/constants/categoryToColorMap';
import theme from '@/styles/theme';
import { PeriodType } from '@/types/card';

interface DiaperCardProps {
  data: {
    peeCnt: number;
    peeCntPercent: number;
    yesterdayPeeCntPercent: number;
    poopCnt: number;
    poopCntPercent: number;
    yesterdayPoopCntPercent: number;
  };
  graphType?: PeriodType;
}

const DiaperCard = ({ data, graphType = 'day' }: DiaperCardProps) => {
  const currentTheme = categoryToColorMap['Diaper'];
  const color = theme.color[currentTheme];
  const darkColor = theme.color.cardDiaper1;
  const lightColor = theme.color.cardDiaper2;

  return (
    <CardContainer $borderColor={color}>
      <CardHeader
        text="배변 통계"
        imgSrc={require(`@/assets/images/img-diaper.png`)}
      />
      <CardContentText type="대변" data={data.poopCnt} unit="회" />
      <CardContentBar
        $borderColor={
          data.poopCntPercent > data.yesterdayPoopCntPercent
            ? darkColor
            : lightColor
        }
        $ratio={data.poopCntPercent}
        $yesterDayRatio={data.yesterdayPoopCntPercent}
        $graphType={graphType}
      />

      <CardContentText type="소변" data={data.peeCnt} unit="회" />
      <CardContentBar
        $borderColor={
          data.peeCntPercent > data.yesterdayPeeCntPercent
            ? darkColor
            : lightColor
        }
        $ratio={data.peeCntPercent}
        $yesterDayRatio={data.yesterdayPeeCntPercent}
        $graphType={graphType}
      />
    </CardContainer>
  );
};

export { DiaperCard };

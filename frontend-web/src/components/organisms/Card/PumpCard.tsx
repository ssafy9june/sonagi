import { CardContentBar } from '@/components/molecules/CardContent/CardContentBar';
import { CardContentText } from '@/components/molecules/CardContent/CardContentText';
import { CardHeader } from '@/components/molecules/CardHeader/CardHeader';
import { CardContainer } from '@/components/organisms/Card/Card.styles';
import { categoryToColorMap } from '@/constants/categoryToColorMap';
import theme from '@/styles/theme';
import { PeriodType } from '@/types/card';

interface PumpCardProps {
  data: {
    cnt: number;
    cntPercent: number;
    yesterdayCntPercent: number;
    amount: number;
    amountPercent: number;
    yesterdayAmountPercent: number;
  };
  graphType?: PeriodType;
}

const PumpCard = ({ data, graphType = 'day' }: PumpCardProps) => {
  const currentTheme = categoryToColorMap['Pump'];
  const color = theme.color[currentTheme];
  const darkColor = theme.color.cardPumpingBreast1;
  const lightColor = theme.color.cardPumpingBreast2;

  return (
    <CardContainer $borderColor={color}>
      <CardHeader
        text="유축 통계"
        imgSrc={require(`@/assets/images/img-pump.png`)}
      />
      <CardContentText type="횟수" data={data.cnt} unit="회" />
      <CardContentBar
        $borderColor={
          data.cntPercent > data.yesterdayCntPercent ? darkColor : lightColor
        }
        $ratio={data.cntPercent}
        $yesterDayRatio={data.yesterdayCntPercent}
        $graphType={graphType}
      />

      <CardContentText type="용량" data={data.amount} unit="ml" />
      <CardContentBar
        $borderColor={
          data.amountPercent > data.yesterdayAmountPercent
            ? darkColor
            : lightColor
        }
        $ratio={data.amountPercent}
        $yesterDayRatio={data.yesterdayAmountPercent}
        $graphType={graphType}
      />
    </CardContainer>
  );
};

export { PumpCard };

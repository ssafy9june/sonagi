import { CardContentBar } from '@/components/molecules/CardContent/CardContentBar';
import { CardContentText } from '@/components/molecules/CardContent/CardContentText';
import { CardHeader } from '@/components/molecules/CardHeader/CardHeader';
import { CardContainer } from '@/components/organisms/Card/Card.styles';
import { categoryToColorMap } from '@/constants/categoryToColorMap';
import theme from '@/styles/theme';
import { PeriodType } from '@/types/card';

interface MealCardProps {
  data: {
    amount: number;
    amountPercent: number;
    yesterdayCntPercent: number;
    cnt: number;
    cntPercent: number;
    yesterdayAmountPercent: number;
  };
  graphType?: PeriodType;
}

const MealCard = ({ data, graphType = 'day' }: MealCardProps) => {
  const currentTheme = categoryToColorMap['Meal'];
  const color = theme.color[currentTheme];
  const darkColor = theme.color.cardMeal2;
  const lightColor = theme.color.cardMeal3;
  return (
    <CardContainer $borderColor={color}>
      <CardHeader
        text="식사 통계"
        imgSrc={require(`@/assets/images/img-meal.png`)}
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

export { MealCard };

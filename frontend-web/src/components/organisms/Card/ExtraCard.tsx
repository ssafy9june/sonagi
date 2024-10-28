import { CardContentBar } from '@/components/molecules/CardContent/CardContentBar';
import { CardContentText } from '@/components/molecules/CardContent/CardContentText';
import { CardHeader } from '@/components/molecules/CardHeader/CardHeader';
import { CardContainer } from '@/components/organisms/Card/Card.styles';
import { categoryToColorMap } from '@/constants/categoryToColorMap';
import theme from '@/styles/theme';
import { PeriodType } from '@/types/card';

interface ExtraCardProps {
  data: {
    cnt: number;
    cntPercent: number;
    yesterdayCntPercent: number;
  };
  graphType?: PeriodType;
}

const ExtraCard = ({ data, graphType = 'day' }: ExtraCardProps) => {
  const currentTheme = categoryToColorMap['Extra'];
  const color = theme.color[currentTheme];
  const darkColor = theme.color.cardExtra;
  const lightColor = theme.color.gray3;

  return (
    <CardContainer $borderColor={color}>
      <CardHeader
        text="기타 통계"
        imgSrc={require(`@/assets/images/img-extra.png`)}
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
    </CardContainer>
  );
};

export { ExtraCard };

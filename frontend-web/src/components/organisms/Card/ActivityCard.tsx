import { CardContentBar } from '@/components/molecules/CardContent/CardContentBar';
import { CardContentText } from '@/components/molecules/CardContent/CardContentText';
import { CardHeader } from '@/components/molecules/CardHeader/CardHeader';
import { CardContainer } from '@/components/organisms/Card/Card.styles';
import { categoryToColorMap } from '@/constants/categoryToColorMap';
import theme from '@/styles/theme';
import { PeriodType } from '@/types/card';

interface ActivityCardProps {
  data: {
    cnt: number;
    cntPercent: number;
    yesterdayCntPercent: number;
    activityHour: number;
    activityMinute: number;
    activityPercent: number;
    yesterdayActivityPercent: number;
  };
  graphType?: PeriodType;
}

const ActivityCard = ({ data, graphType = 'day' }: ActivityCardProps) => {
  const currentTheme = categoryToColorMap['Activity'];
  const color = theme.color[currentTheme];
  const darkColor = theme.color.cardActivity1;
  const lightColor = theme.color.cardActivity2;

  return (
    <CardContainer $borderColor={color}>
      <CardHeader
        text="놀이 통계"
        imgSrc={require(`@/assets/images/img-activity.png`)}
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

      <CardContentText
        type="시간"
        data={data.activityHour}
        unit="시간"
        data2={data.activityMinute}
        unit2="분"
      />
      <CardContentBar
        $borderColor={
          data.activityPercent > data.yesterdayActivityPercent
            ? darkColor
            : lightColor
        }
        $ratio={data.activityPercent}
        $yesterDayRatio={data.yesterdayActivityPercent}
        $graphType={graphType}
      />
    </CardContainer>
  );
};

export { ActivityCard };

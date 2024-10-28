import { CardContentBar } from '@/components/molecules/CardContent/CardContentBar';
import { CardContentText } from '@/components/molecules/CardContent/CardContentText';
import { CardHeader } from '@/components/molecules/CardHeader/CardHeader';
import { CardContainer } from '@/components/organisms/Card/Card.styles';
import { categoryToColorMap } from '@/constants/categoryToColorMap';
import theme from '@/styles/theme';
import { PeriodType } from '@/types/card';

interface HealthCardProps {
  data: {
    feverAvg: number;
    feverAvgPercent: number;
    yesterdayFeverAvgPercent: number;
    hospitalCnt: number;
    hospitalCntPercent: number;
    yesterdayHospitalCntPercent: number;
    medicationCnt: number;
    medicationCntPercent: number;
    yesterdayMedicationCntPercent: number;
  };
  graphType?: PeriodType;
}

const HealthCard = ({ data, graphType = 'day' }: HealthCardProps) => {
  const currentTheme = categoryToColorMap['Health'];
  const color = theme.color[currentTheme];
  const darkColor = theme.color.cardHealth1;
  const lightColor = theme.color.cardHealth2;

  return (
    <CardContainer $borderColor={color}>
      <CardHeader
        text="건강 통계"
        imgSrc={require(`@/assets/images/img-health.png`)}
      />
      <CardContentText type="체온" data={data.feverAvg} unit="도" />
      <CardContentBar
        $borderColor={
          data.feverAvgPercent > data.yesterdayFeverAvgPercent
            ? darkColor
            : lightColor
        }
        $ratio={data.feverAvgPercent}
        $yesterDayRatio={data.yesterdayFeverAvgPercent}
        $graphType={graphType}
      />

      <CardContentText type="병원 진료" data={data.hospitalCnt} unit="회" />
      <CardContentBar
        $borderColor={
          data.hospitalCntPercent > data.yesterdayHospitalCntPercent
            ? darkColor
            : lightColor
        }
        $ratio={data.hospitalCntPercent}
        $yesterDayRatio={data.yesterdayHospitalCntPercent}
        $graphType={graphType}
      />

      <CardContentText type="투약" data={data.medicationCnt} unit="회" />
      <CardContentBar
        $borderColor={
          data.medicationCntPercent > data.yesterdayMedicationCntPercent
            ? darkColor
            : lightColor
        }
        $ratio={data.medicationCntPercent}
        $yesterDayRatio={data.yesterdayMedicationCntPercent}
        $graphType={graphType}
      />
    </CardContainer>
  );
};

export { HealthCard };

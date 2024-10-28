import { Text } from '@/components/atoms/Text/Text.styles';
import {
  CardBarContainer,
  CardBarWrapper,
  Yesterday,
  YesterdayLine,
} from '@/components/molecules/CardContent/CardContentBar.styles';
import { categoryToColorMap } from '@/constants/categoryToColorMap';
import { selectedCategoryState } from '@/states/categoryState';
import theme from '@/styles/theme';
import { PeriodType } from '@/types/card';
import { useRecoilValue } from 'recoil';

export interface YesterdayProps {
  $yesterDayRatio: number;
}

export interface CardBarWrapperProps {
  $borderColor: string;
  $ratio: string | number;
}

export interface CardContentBarProps
  extends YesterdayProps,
    CardBarWrapperProps {
  $graphType: PeriodType;
}

const CardContentBar = ({
  $borderColor,
  $ratio,
  $yesterDayRatio,
  $graphType,
}: CardContentBarProps) => {
  return (
    <>
      <CardBarContainer>
        <CardBarWrapper $borderColor={$borderColor} $ratio={$ratio} />
        <Yesterday $yesterDayRatio={$yesterDayRatio}>
          <YesterdayLine />

          <Text size="xSmall">{$graphType === 'day' ? '어제' : '지난주'}</Text>
        </Yesterday>
      </CardBarContainer>
    </>
  );
};

export { CardContentBar };

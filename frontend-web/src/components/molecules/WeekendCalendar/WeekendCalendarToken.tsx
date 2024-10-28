import React from 'react';
import { Text } from '@/components/atoms/Text/Text.styles';
import * as S from './WeekendCalendarToken.style';
import theme from '@/styles/theme';
import { Dayjs } from 'dayjs';

interface WeekendCalendarProps {
  dayNumber: Dayjs;
  dayOfWeek: string;
  $statusOfDay: string;
  $isRecordedDate?: boolean;
  onClick: () => void;
}
interface ColorMap {
  [beforeToday: string]: string;
}
const WeekendCalendarToken = ({
  dayNumber,
  dayOfWeek,
  $statusOfDay,
  $isRecordedDate = false,
  onClick,
}: WeekendCalendarProps) => {
  const colorMap: ColorMap = {
    beforeToday: 'black',
    isToday: theme.color.calendarTodayFont,
    afterToday: theme.color.calendarBeforeFont,
  };

  return (
    <>
      <S.TokenWrapper $statusOfDay={$statusOfDay} onClick={onClick}>
        <S.FloatingPoint $isRecordedDate={$isRecordedDate}>ㆍ</S.FloatingPoint>
        <Text size="large" color={colorMap[$statusOfDay]} $fontWeight={900}>
          {dayNumber.date()}
        </Text>
        <Text
          size="medium3"
          color={
            $statusOfDay === 'beforeToday' ? 'black' : colorMap[$statusOfDay]
          }
          $fontWeight={500}
        >
          {dayOfWeek}
        </Text>
      </S.TokenWrapper>
    </>
  );
};

export default WeekendCalendarToken;

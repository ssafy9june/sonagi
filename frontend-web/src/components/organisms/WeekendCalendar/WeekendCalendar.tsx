import React, { useEffect, useState } from 'react';
import WeekendCalendarToken from '../../molecules/WeekendCalendar/WeekendCalendarToken';
import getWeekendDate from '../../../utils/weekendUtils';
import WeekendCalendarContainer from './WeekendCalendar.styles';
import dayjs, { Dayjs } from 'dayjs';
import { useRecoilState, useRecoilValue } from 'recoil';
import { selectedDateState } from '@/states/dateState';
import { formatDate } from '@/utils/formatDate';
import { writtenDiaryDateList } from '@/states/diaryState';

const WeekendCalendar = () => {
  const [selectedDate, setSelectedDate] = useRecoilState(selectedDateState);
  const [weekendDateList, setWeekendDateList] = useState<
    Array<{ date: Dayjs; day: string }>
  >([]);
  const checkingDateList: string[] = useRecoilValue(writtenDiaryDateList);

  useEffect(() => {
    setWeekendDateList(getWeekendDate(new Date(selectedDate)));
  }, [selectedDate]);

  const jStatusOfDay = (newDate: Dayjs): string => {
    const curDate = new Date();
    curDate.setHours(0, 0, 0, 0);
    const curDay = dayjs(curDate);
    if (newDate.isSame(dayjs(selectedDate), 'day')) {
      return 'isToday';
    }
    if (curDay.isBefore(newDate, 'day')) {
      return 'afterToday';
    }
    return 'beforeToday';
  };

  const handleChangeDay = (date: Date) => {
    const newDate = new Date();
    newDate.setHours(0, 0, 0, 0);
    if (dayjs(date).isAfter(newDate, 'day')) {
      return;
    }
    setSelectedDate(formatDate(date));
  };

  return (
    <>
      <WeekendCalendarContainer>
        {weekendDateList.map((dateMap, index) => (
          <WeekendCalendarToken
            key={index}
            dayNumber={dateMap['date']}
            dayOfWeek={dateMap['day']}
            $statusOfDay={jStatusOfDay(dateMap['date'])}
            $isRecordedDate={checkingDateList.includes(
              dateMap['date'].toISOString().slice(0, 10)
            )}
            onClick={() => handleChangeDay(dateMap['date'].toDate())}
          />
        ))}
      </WeekendCalendarContainer>
    </>
  );
};

export default WeekendCalendar;

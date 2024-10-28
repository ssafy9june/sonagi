import { Image } from '@/components/atoms/Image/Image';
import { Text } from '@/components/atoms/Text/Text.styles';
import { CalendarBarContainer } from '@/components/molecules/CalendarBar/CalendarBar.style';
import iconArrowMiniLeftGrey from '@/assets/images/icon-arrow-mini-left-grey.png';
import iconArrowMiniRightGrey from '@/assets/images/icon-arrow-mini-right-grey.png';
import { useEffect, useState } from 'react';
import { Value } from 'react-calendar/dist/cjs/shared/types';
import moment from 'moment';
import { CalendarModal } from '@/components/organisms/CalendarModal/CalendarModal';
import dayjs from 'dayjs';
import { selectedDateState } from '@/states/dateState';
import { useRecoilState } from 'recoil';
import { formatDate } from '@/utils/formatDate';

const CalendarBar = () => {
  const [modalOpen, setModalOpen] = useState<boolean>(false);
  const [selectedDate, setSelectedDate] = useRecoilState(selectedDateState);

  const onClickCalendarBar = () => {
    setModalOpen(true);
  };

  const onCalendarChange = (newDate: Value) => {
    if (newDate instanceof Date) {
      setSelectedDate(formatDate(newDate));
    }
  };

  const onModalClose = () => {
    setModalOpen(false);
  };

  const onChangeDay = (days: number) => {
    const newDate = new Date(selectedDate);
    newDate.setDate(newDate.getDate() + days);
    newDate.setHours(0, 0, 0, 0);
    if (dayjs(newDate).isAfter(new Date())) {
      return;
    }
    setSelectedDate(formatDate(newDate));
  };

  return (
    <>
      <CalendarModal
        pickDate={new Date(selectedDate)}
        onModalClose={onModalClose}
        modalOpen={modalOpen}
        onCalendarChange={onCalendarChange}
      />

      <CalendarBarContainer>
        <Image
          src={iconArrowMiniLeftGrey}
          width={1.3}
          height={1.3}
          onClick={() => onChangeDay(-1)}
        />
        <Text
          onClick={onClickCalendarBar}
          size="medium1"
          style={{ margin: '0px 10px' }}
        >
          {moment(new Date(selectedDate)).format('YYYY년 M월 D일')}
        </Text>
        <Image
          src={iconArrowMiniRightGrey}
          width={1.3}
          height={1.3}
          onClick={() => onChangeDay(1)}
        />
      </CalendarBarContainer>
    </>
  );
};

export default CalendarBar;

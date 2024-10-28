import { useState } from 'react';
import { Text, TextProps } from '@/components/atoms/Text/Text.styles';
import Button from '@/components/atoms/Button/Button';
import * as S from '@/components/molecules/TimeRecorder/TimeRecorder.style';
import theme from '@/styles/theme';

import dayjs, { Dayjs } from 'dayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { MobileTimePicker } from '@mui/x-date-pickers/MobileTimePicker';

interface TimerProps {
  name?: string;
  initialTime?: string;
  selectedDate?: string;
  setCreatedTime: (value: string | null) => void;
  textSize?: TextProps['size'];
}

const Timer = ({
  name = '기록 시간',
  initialTime,
  selectedDate,
  setCreatedTime,
  textSize = 'headSmall',
}: TimerProps) => {
  // get으로 받아온 기록 정보를 dayjs에 넣을 형식으로 바꿔주기
  const timeValue = `${selectedDate}T${String(initialTime)?.substring(0, 5)}`;

  // state에서 value값을 dayjs로 바꿔주기
  const [value, setValue] = useState<Dayjs | null>(
    dayjs(timeValue)
    // timeValue ? dayjs(timeValue) : dayjs('2022-04-17T17:30')
    // 값이 없을수가 있을까? 무조건 들어온다는 전제로 주석처리
  );
  return (
    <>
      <S.TimerTextWrapper>
        <Text size={textSize}>{name}</Text>
      </S.TimerTextWrapper>
      <S.TimerButtonWrapper>
        <LocalizationProvider dateAdapter={AdapterDayjs}>
          <MobileTimePicker
            defaultValue={value}
            onChange={data => {
              setCreatedTime(data!.format('HH:mm:ss')); // 이건 상위의 state값을 변경..
              setValue(data); // data 타입은 object - dayjs
            }}
            sx={{
              width: '100%',
              opacity: 0,
              position: 'absolute',
              top: 0,
              left: 0,
            }}
          />
        </LocalizationProvider>
        <Button
          $borderRadius="8px"
          size="large"
          $backgroundColor={theme.color.lightgrey}
          $border={'0'}
        >
          <S.ButtonWrapper>
            <Text size="headLarge">{value?.format('hh')}</Text>
            <Text size="medium1" style={{ paddingTop: '6px' }}>
              시
            </Text>
          </S.ButtonWrapper>
        </Button>
        <Button
          $borderRadius="8px"
          size="large"
          $backgroundColor={theme.color.lightgrey}
          $border={'0'}
        >
          <S.ButtonWrapper>
            <Text size="headLarge">{value?.format('mm')}</Text>
            <Text size="medium1" style={{ paddingTop: '6px' }}>
              분
            </Text>
          </S.ButtonWrapper>
        </Button>
        <Button $borderRadius="8px" size="large">
          <Text size="headMedium">{value?.format('A')}</Text>
        </Button>
      </S.TimerButtonWrapper>
    </>
  );
};

export default Timer;

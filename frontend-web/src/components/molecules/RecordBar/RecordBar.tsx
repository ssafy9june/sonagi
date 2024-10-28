import React, { useState } from 'react';
import Button from '@/components/atoms/Button/Button';
import moment from 'moment';
import { RecordBarContainer } from '@/components/molecules/RecordBar/RecordBar.styles';
import { useRecoilState, useRecoilValue } from 'recoil';
import { selectedCategoryState } from '@/states/categoryState';
import { Category } from '@/types';
import { recordsByCategory } from '@/states/recordState';
import { Text } from '@/components/atoms/Text/Text.styles';
import { PATH } from '@/constants/path';
import { useAddRecord } from '@/apis/Record/Mutations/useAddRecord';
import { userInfoState } from '@/states/userState';
import { selectedBabyState } from '@/states/babyState';
import { selectedDateState } from '@/states/dateState';

interface RecordBarProps {
  onRecordUpdated: () => void;
}

const RecordBar = ({ onRecordUpdated }: RecordBarProps) => {
  const [pickDate, setPickTime] = useState(new Date());

  const currentCategory = useRecoilValue(selectedCategoryState(PATH.MAIN));
  const selectedDate = useRecoilValue(selectedDateState);

  const records = recordsByCategory[currentCategory || 'All'] || [];
  const [userInfo] = useRecoilState(userInfoState);
  const [selectedBaby] = useRecoilState(selectedBabyState);

  // 버튼 누르면 post
  const addRecordMutation = useAddRecord();

  const handleButtonClick = async (
    recordType: string,
    color: string,
    category: Category,
    queryName: string
  ) => {
    setPickTime(new Date());
    const nowTime = moment(pickDate).format('HH:mm:ss');
    const nowDate = selectedDate;
    console.log(nowDate, nowTime, queryName, recordType); // ex. medications, 투약

    if (
      [
        'infantFormulas',
        'breastFeedings',
        'babyFoods',
        'milks',
        'pumpingBreasts',
      ].includes(queryName)
    ) {
      await addRecordMutation.mutateAsync({
        type: queryName,
        userId: userInfo.userId,
        babyId: selectedBaby.babyId,
        amount: 0,
        createdTime: nowTime,
        createdDate: nowDate,
        memo: '',
        // queryName: queryName,
      });
    } else if (
      [
        'pees',
        'poops',
        'hospitals',
        'medications',
        'snacks',
        'extras',
      ].includes(queryName)
    ) {
      await addRecordMutation.mutateAsync({
        type: queryName,
        userId: userInfo.userId,
        babyId: selectedBaby.babyId,
        createdTime: nowTime,
        createdDate: nowDate,
        memo: '',
        // queryName,
      });
    } else if (['sleeps', 'plays', 'tummytimes'].includes(queryName)) {
      await addRecordMutation.mutateAsync({
        type: queryName,
        userId: userInfo.userId,
        babyId: selectedBaby.babyId,
        createdTime: nowTime,
        createdDate: nowDate,
        endTime: nowTime,
        memo: '',
        // queryName: queryName,
      });
    } else if (queryName === 'feedings') {
      await addRecordMutation.mutateAsync({
        type: queryName,
        userId: userInfo.userId,
        babyId: selectedBaby.babyId,
        leftStartTime: nowTime,
        rightStartTime: nowTime,
        leftEndTime: nowTime,
        rightEndTime: nowTime,
        createdDate: nowDate,
        createdTime: nowTime,
        memo: '',
        // queryName: queryName,
      });
    } else {
      await addRecordMutation.mutateAsync({
        type: queryName,
        userId: userInfo.userId,
        babyId: selectedBaby.babyId,
        createdTime: nowTime,
        createdDate: nowDate,
        bodyTemperature: 36.5,
        memo: '',
        // queryName: queryName,
      });
    }

    onRecordUpdated();
  };

  return (
    <RecordBarContainer>
      {records.map((record, idx) => (
        <Button
          option="default"
          size="xSmall"
          key={idx}
          $borderColor={record.color}
          onClick={() =>
            handleButtonClick(
              record.type,
              record.color,
              record.category,
              record.queryName
            )
          }
        >
          <Text size="medium3">{record.type}</Text>
        </Button>
      ))}
    </RecordBarContainer>
  );
};

export default RecordBar;

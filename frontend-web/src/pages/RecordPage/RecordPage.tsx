import { useEffect, useState } from 'react';
import { useRecoilValue } from 'recoil';

import CalendarBar from '@/components/molecules/CalendarBar/CalendarBar';
import CategoryBar from '@/components/molecules/CategoryBar/CategoryBar';
import RecordContainer from '@/components/organisms/RecordContainer/RecordContainer';
import { PATH } from '@/constants/path';
import { selectedBabyState } from '@/states/babyState';
import { useGetAllCategoryRecords } from '@/apis/Record/Queries/useGetAllCategoryRecords';
import {
  AllRecords,
  CombinedRecord,
  RecordedValues,
} from '@/types/recordTypes';
import { selectedDateState } from '@/states/dateState';

type StringKeys<T> = Extract<keyof T, string>;

const RecordPage = () => {
  // 처음 화면에 들어왔을 때 날짜 설정, selectedDate로 리코일에 저장
  const selectedDate = useRecoilValue(selectedDateState);

  // 처음 화면에 들어왔을 때 아이 id를 리코일에서 가져와 사용
  const selectedBaby = useRecoilValue(selectedBabyState);

  // 저장된 아이 id랑 날짜로 get요청
  const recordedList = useGetAllCategoryRecords(
    selectedBaby.babyId,
    selectedDate
  );

  const [combinedData, setCombinedDataState] = useState<CombinedRecord[]>([]);

  useEffect(() => {
    const computeCombinedData = (recordedList: RecordedValues) => {
      const tempCombinedData: CombinedRecord[] = [];
      Object.keys(recordedList).forEach(
        (key: StringKeys<typeof recordedList>) => {
          recordedList[key].forEach((item: AllRecords) => {
            tempCombinedData.push({ ...item, category: key });
            //   tempCombinedData = [
            //     ...tempCombinedData,
            //     { ...item, category: key },
            //   ]; // 왜인지... 스프레드연산자로 하니 조금 더 느립니다
          });
        }
      );

      tempCombinedData.sort((a, b) => {
        const timeA = a.createdTime || '00:00:00';
        const timeB = b.createdTime || '00:00:00';
        return timeA.localeCompare(timeB);
      });
      setCombinedDataState(tempCombinedData);
    };

    if (recordedList) {
      computeCombinedData(recordedList);
    }
  }, [recordedList]);

  return (
    <>
      <section>
        <CalendarBar></CalendarBar>
        <CategoryBar path={PATH.MAIN}></CategoryBar>
      </section>
      <div style={{ paddingTop: 'env(safe-area-inset-top)' }}>
        <RecordContainer combinedData={combinedData}></RecordContainer>
      </div>
    </>
  );
};

export default RecordPage;

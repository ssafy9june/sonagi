import { useEffect, useRef } from 'react';
import RecordBar from '@/components/molecules/RecordBar/RecordBar';
import RecordBlock from '@/components/molecules/RecordBlock/RecordBlock';
import { useRecoilState, useRecoilValue } from 'recoil';
import { selectedCategoryState } from '@/states/categoryState';
import { RecordContainerStyle } from '@/components/organisms/RecordContainer/RecordContainer.styles';
import { PATH } from '@/constants/path';
import { CombinedRecord, categotyToRecordMap } from '@/types/recordTypes';
import theme from '@/styles/theme';
import { fetchCounterState } from '@/states/fetchCounterState';
import { recordEnToKo, recordTypeToCategory } from '@/constants/recordEnToKo';
import { recordTypeToIdKey } from '@/constants/recordTypeToIdKey';

type RecordContainerProps = {
  combinedData: CombinedRecord[];
};

const RecordContainer = ({ combinedData }: RecordContainerProps) => {
  const currentCategory = useRecoilValue(selectedCategoryState(PATH.MAIN));
  const containerRef = useRef<HTMLDivElement>(null);

  // 선택된 카테고리에 따라 쌓인 기록 블록들 필터링
  // const filteredRecordList = recordedList.filter(record => {
  //   if (currentCategory === 'All') {
  //     return true;
  //   }
  //   return record.category === currentCategory;
  // });

  const filteredRecordCategories = categotyToRecordMap[currentCategory];
  const filteredRecordList = filteredRecordCategories
    ? combinedData.filter(record =>
        filteredRecordCategories.includes(record.category!)
      )
    : combinedData;

  const [fetchCounter, setFetchCounter] = useRecoilState(fetchCounterState);

  useEffect(() => {
    const container = containerRef.current;
    const savedScrollTop = localStorage.getItem('scrollPosition');
    if (container && savedScrollTop) {
      container.scrollTop = Number(savedScrollTop);
    }
  }, [combinedData, fetchCounter]);

  useEffect(() => {
    const container = containerRef.current;
    if (container) {
      const onScroll = () => {
        localStorage.setItem('scrollPosition', String(container.scrollTop));
      };

      container.addEventListener('scroll', onScroll);
      return () => {
        container.removeEventListener('scroll', onScroll);
      };
    }
  }, [combinedData, fetchCounter]);

  const onRecordUpdated = () => {
    setFetchCounter(fetchCounter => fetchCounter + 1);
    const container = containerRef.current;
    if (container) {
      setTimeout(() => {
        const targetScrollTop = container.scrollHeight;
        container.scrollTo({
          top: targetScrollTop,
          behavior: 'smooth',
        });
      }, 500); // DOM이 완전히 업데이트 된 후 스크롤 위치를 조정
    }
  };

  return (
    <>
      <RecordContainerStyle className="scrollable" ref={containerRef}>
        {filteredRecordList.map((record: CombinedRecord, index) => {
          console.log('뿌려주고 있는 record 정보', record);
          const recordIdKey = recordTypeToIdKey[record.category!];
          const recordId = record[recordIdKey as keyof CombinedRecord];
          return 'amount' in record ? (
            <RecordBlock
              key={index}
              color={theme.color[recordTypeToCategory[record.category!]]}
              recordType={recordEnToKo[record.category!]}
              record={record}
              time={
                record.createdTime ? record.createdTime.substring(0, 5) : ''
              }
              recordId={recordId}
              amount={record.amount}
            />
          ) : (
            <RecordBlock
              key={index}
              color={theme.color[recordTypeToCategory[record.category!]]}
              recordType={recordEnToKo[record.category!]}
              record={record}
              time={
                record.createdTime ? record.createdTime.substring(0, 5) : ''
              }
              recordId={recordId}
            />
          );
        })}
      </RecordContainerStyle>
      <RecordBar onRecordUpdated={onRecordUpdated}></RecordBar>
    </>
  );
};

export default RecordContainer;

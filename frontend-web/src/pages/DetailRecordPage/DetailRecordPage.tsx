import DiaperPage from '@/pages/DetailRecordPage/DiaperPage/DiaperPage';
import FeedingPage from '@/pages/DetailRecordPage/FeedingPage/FeedingPage';
import InfantFormulaPage from '@/pages/DetailRecordPage/InfantFormulaPage/InfantFormulaPage';
import SleepPage from '@/pages/DetailRecordPage/SleepPage/SleepPage';
import TemperaturePage from '@/pages/DetailRecordPage/TemperaturePage/TemperaturePage';
import * as S from '@/pages/DetailRecordPage/DetailRecordPage.style';

import { useLocation } from 'react-router-dom';

const DetailRecordPage = () => {
  const location = useLocation();
  const name = location.state.recordType;
  const recordName = location.state.recordName;
  const recordId = location.state.recordId;

  const DetailRecordRenderer = () => {
    if (name === '체온') {
      return (
        <TemperaturePage
          name={name}
          recordName={recordName}
          recordId={recordId}
        />
      );
    } else if (name === '수유') {
      return (
        <FeedingPage name={name} recordName={recordName} recordId={recordId} />
      );
    } else if (name === '수면' || name === '놀이' || name === '터미 타임') {
      return (
        <SleepPage name={name} recordName={recordName} recordId={recordId} />
      );
    } else if (
      name === '유축 수유' ||
      name === '분유' ||
      name === '이유식' ||
      name === '우유' ||
      name === '유축'
    ) {
      return (
        <InfantFormulaPage
          name={name}
          recordName={recordName}
          recordId={recordId}
        ></InfantFormulaPage>
      );
    } else {
      return (
        <DiaperPage name={name} recordName={recordName} recordId={recordId} />
      );
    }
  };

  return (
    <S.DetailRecordPageWrapper>
      {DetailRecordRenderer()}
    </S.DetailRecordPageWrapper>
  );
};

export { DetailRecordPage };

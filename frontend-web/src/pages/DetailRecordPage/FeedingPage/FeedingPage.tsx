import TimeRecorder from '@/components/molecules/TimeRecorder/TimeRecorder';
import MemoRecorder from '@/components/molecules/MemoRecorder/MemoRecorder';
import BreastFeedRecorder from '@/components/molecules/BreastFeedRecorder/BreastFeedRecorder';
import * as S from '@/pages/DetailRecordPage/FeedingPage/FeedingPage.style';
import Back from '@/components/atoms/Back/Back';
import Button from '@/components/atoms/Button/Button';
import { useGetRecordDetails } from '@/apis/Record/Queries/useGetRecordDetails';
import { useCallback, useState } from 'react';
import { useRecoilValue } from 'recoil';
import { selectedDateState } from '@/states/dateState';
import { useUpdateRecord } from '@/apis/Record/Mutations/useUpdateRecord';
import { useNavigate } from 'react-router-dom';
import { useQueryClient } from '@tanstack/react-query';
import { useDeleteRecord } from '@/apis/Record/Mutations/useDeleteRecord';
import { DetailRecordButtonContainer } from '@/pages/DetailRecordPage/DetailRecordPage.style';

interface NameProps {
  name: string;
  recordName: string;
  recordId: number;
}

const FeedingPage = ({ name, recordName, recordId }: NameProps) => {
  const recordDetails = useGetRecordDetails(recordName, recordId);
  const selectedDate = useRecoilValue(selectedDateState);

  const [isLeft, setIsLeft] = useState(true);
  const [leftStartTime, setLeftStartTime] = useState(
    recordDetails.leftStartTime
  );
  const [rightStartTime, setRightStartTime] = useState(
    recordDetails.rightStartTime
  );
  const [leftEndTime, setLeftEndTime] = useState(recordDetails.leftEndTime);
  const [rightEndTime, setRightEndTime] = useState(recordDetails.rightEndTime);
  const [memo, setMemo] = useState(recordDetails.memo);

  const navigate = useNavigate();
  const RouteHandler = useCallback(() => navigate(-1), [navigate]);
  const updateRecordMutation = useUpdateRecord();
  const deleteRecordMutation = useDeleteRecord();
  const queryClient = useQueryClient();

  const handleUpdate = async () => {
    const currentRecord = {
      mealId: recordId,
      leftStartTime,
      rightStartTime,
      leftEndTime,
      rightEndTime: rightEndTime,
      memo,
    };
    await updateRecordMutation.mutateAsync(
      {
        record: currentRecord,
        queryName: recordName,
      },
      {
        onSuccess() {
          queryClient.invalidateQueries(['recordDetails', recordId]);
        },
      }
    );
    RouteHandler();
  };

  const deleteRecord = (recordName: string, recordId: number) => {
    deleteRecordMutation.mutate(
      {
        type: recordName,
        recordId,
      },
      {
        onSuccess: () => {
          queryClient.invalidateQueries(['record', recordId]);
        },
      }
    );
    RouteHandler();
  };

  return (
    <>
      <Back>{name + ' 상세페이지'}</Back>
      <S.FeedingPageContainer>
        <S.FeedingPageWrapper className="scrollable">
          <S.Divider className="upperFeedingPageDivider">
            <BreastFeedRecorder
              isLeft={isLeft}
              setIsLeft={setIsLeft}
            ></BreastFeedRecorder>
          </S.Divider>
          {isLeft ? (
            <>
              <S.Divider>
                <TimeRecorder
                  key="left-start-time"
                  name="왼쪽 수유 시작 시간"
                  textSize="medium2"
                  initialTime={leftStartTime}
                  selectedDate={selectedDate}
                  setCreatedTime={setLeftStartTime}
                ></TimeRecorder>
              </S.Divider>
              <S.Divider>
                <TimeRecorder
                  key="left-end-time"
                  name="왼쪽 수유 종료 시간"
                  textSize="medium2"
                  initialTime={leftEndTime}
                  selectedDate={selectedDate}
                  setCreatedTime={setLeftEndTime}
                ></TimeRecorder>
              </S.Divider>
            </>
          ) : (
            <>
              <S.Divider>
                <TimeRecorder
                  key="right-start-time"
                  name="오른쪽 수유 시작 시간"
                  textSize="medium2"
                  initialTime={rightStartTime}
                  selectedDate={selectedDate}
                  setCreatedTime={setRightStartTime}
                ></TimeRecorder>
              </S.Divider>
              <S.Divider>
                <TimeRecorder
                  key="right-end-time"
                  textSize="medium2"
                  name="오른쪽 수유 종료 시간"
                  initialTime={rightEndTime}
                  selectedDate={selectedDate}
                  setCreatedTime={setRightEndTime}
                ></TimeRecorder>
              </S.Divider>
            </>
          )}

          <S.Divider>
            <MemoRecorder setMemo={setMemo} placeholder={memo}></MemoRecorder>
          </S.Divider>
          <DetailRecordButtonContainer>
            <Button
              option="danger"
              onClick={() => deleteRecord(recordName, recordId)}
            >
              삭제하기
            </Button>
            <Button option="activated" size="large" onClick={handleUpdate}>
              수정하기
            </Button>
          </DetailRecordButtonContainer>
        </S.FeedingPageWrapper>
      </S.FeedingPageContainer>
    </>
  );
};

export default FeedingPage;

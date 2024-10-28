import { deleteDiary } from '@/apis/Diary/diaryAPI';
import { useMutation, useQueryClient } from '@tanstack/react-query';

// 일기 삭제
const useDeleteDiary = () => {
  const queryCilent = useQueryClient();
  return useMutation((diaryId: number) => deleteDiary(diaryId), {
    onSuccess: () => {
      queryCilent.invalidateQueries(['diaryRecordDateList']);
      queryCilent.invalidateQueries(['diaryRecordDates']);
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { useDeleteDiary };

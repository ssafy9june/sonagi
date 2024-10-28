import { useMutation, useQueryClient } from '@tanstack/react-query';
import { addDiary } from '@/apis/Diary/diaryAPI';

// 일기 등록
const useAddDiary = () => {
  const queryCilent = useQueryClient();
  return useMutation((formData: FormData) => addDiary(formData), {
    onSuccess: () => {
      queryCilent.invalidateQueries(['diaryRecordDateList']);
      queryCilent.invalidateQueries(['diaryRecordDates']);
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { useAddDiary };

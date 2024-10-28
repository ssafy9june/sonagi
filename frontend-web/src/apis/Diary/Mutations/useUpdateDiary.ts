import { updateDiary } from '@/apis/Diary/diaryAPI';
import { useMutation, useQueryClient } from '@tanstack/react-query';

// 일기 수정
const useUpdateDiary = () => {
  const queryCilent = useQueryClient();
  return useMutation((formData: FormData) => updateDiary(formData), {
    onSuccess: () => {
      queryCilent.invalidateQueries(['diaryRecordDateList']);
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { useUpdateDiary };

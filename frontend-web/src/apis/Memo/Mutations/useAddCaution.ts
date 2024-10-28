import { useMutation, useQueryClient } from '@tanstack/react-query';
import { addCaution } from '@/apis/Memo/memoAPI';
import { BabyMemo } from '@/types';

// 질병 메모
const useAddCaution = () => {
  const queryClient = useQueryClient();
  return useMutation((memo: BabyMemo) => addCaution(memo), {
    onSuccess: aa => {
      console.log(aa);
      queryClient.invalidateQueries(['caution']);
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { useAddCaution };

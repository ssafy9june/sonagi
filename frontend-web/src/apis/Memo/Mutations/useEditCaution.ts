import { useMutation, useQueryClient } from '@tanstack/react-query';
import { editCaution } from '@/apis/Memo/memoAPI';
import { EditBabyMemo } from '@/types';

// 질병 메모
const useEditCaution = () => {
  const queryClient = useQueryClient();
  return useMutation((memo: EditBabyMemo) => editCaution(memo), {
    onSuccess: aa => {
      console.log(aa);
      queryClient.invalidateQueries(['caution']);
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { useEditCaution };

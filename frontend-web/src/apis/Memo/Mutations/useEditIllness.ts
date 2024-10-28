import { useMutation, useQueryClient } from '@tanstack/react-query';
import { editIllness } from '@/apis/Memo/memoAPI';
import { EditBabyMemo } from '@/types';

// 질병 메모
const useEditIllness = () => {
  const queryClient = useQueryClient();
  return useMutation((memo: EditBabyMemo) => editIllness(memo), {
    onSuccess: aa => {
      console.log(aa);
      queryClient.invalidateQueries(['illness']);
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { useEditIllness };

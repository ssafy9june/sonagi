import { useMutation, useQueryClient } from '@tanstack/react-query';
import { deleteCaution } from '@/apis/Memo/memoAPI';

// 질병 메모
const useDeleteCaution = () => {
  const queryClient = useQueryClient();

  return useMutation((CautionId: number) => deleteCaution(CautionId), {
    onSuccess: () => {
      queryClient.invalidateQueries(['caution']);
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { useDeleteCaution };

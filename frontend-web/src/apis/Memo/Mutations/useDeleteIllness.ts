import { useMutation, useQueryClient } from '@tanstack/react-query';
import { deleteIllness } from '@/apis/Memo/memoAPI';

// 질병 메모
const useDeleteIllness = () => {
  const queryClient = useQueryClient();

  return useMutation((IllnessId: number) => deleteIllness(IllnessId), {
    onSuccess: () => {
      queryClient.invalidateQueries(['illness']);
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { useDeleteIllness };

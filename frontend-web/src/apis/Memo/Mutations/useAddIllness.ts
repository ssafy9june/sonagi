import { useMutation, useQueryClient } from '@tanstack/react-query';
import { addIllness } from '@/apis/Memo/memoAPI';
import { useRecoilValue } from 'recoil';
import { userInfoState } from '@/states/userState';
import { BabyMemo } from '@/types';

// 질병 메모
const useAddIllness = () => {
  const userInfo = useRecoilValue(userInfoState);
  const queryClient = useQueryClient();
  return useMutation((memo: BabyMemo) => addIllness(memo), {
    onSuccess: aa => {
      console.log(aa);
      queryClient.invalidateQueries(['illness']);
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { useAddIllness };

import { useMutation, useQueryClient } from '@tanstack/react-query';
import { Baby } from '@/types';
import { addBaby } from '@/apis/Baby/babyAPI';
import { useRecoilValue } from 'recoil';
import { userInfoState } from '@/states/userState';

const useAddBaby = () => {
  const userInfo = useRecoilValue(userInfoState);
  const queryClient = useQueryClient();
  return useMutation((baby: Baby) => addBaby(baby), {
    onSuccess: () => {
      // useQueryClient는 React Query의 캐싱 시스템과 상호작용
      // 데이터가 변경될 수 있는 경우
      queryClient.invalidateQueries(['baby', userInfo.userId]);
    },
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { useAddBaby };

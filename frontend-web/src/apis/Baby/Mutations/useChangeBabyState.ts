import { useMutation } from '@tanstack/react-query';
import { changeBabyState } from '@/apis/Baby/babyAPI';

const useChangeBabyState = () => {
  return useMutation((babyId: number) => changeBabyState(babyId));
};

export { useChangeBabyState };

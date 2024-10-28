import { useMutation } from '@tanstack/react-query';
import { UpdateBaby } from '@/types';
import { updateBaby } from '@/apis/Baby/babyAPI';

const useUpdateBaby = () => {
  return useMutation((baby: UpdateBaby) => updateBaby(baby), {
    onSuccess: () => {},
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { useUpdateBaby };

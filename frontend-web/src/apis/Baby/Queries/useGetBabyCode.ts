import { getBabyCode } from '@/apis/Baby/babyAPI';
import { useQuery } from '@tanstack/react-query';

const useGetBabyCode = (babyId: number): string => {
  const { data: codeObj } = useQuery(['babyCode', babyId], () =>
    getBabyCode(babyId)
  );

  return codeObj?.code;
};

export { useGetBabyCode };

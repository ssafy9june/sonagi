import { useQuery } from '@tanstack/react-query';
import { getCaution } from '@/apis/Memo/memoAPI';

const useGetCaution = (babyId: number) => {
  const { data } = useQuery(['caution', babyId], () => getCaution(babyId));
  return data;
};

export { useGetCaution };

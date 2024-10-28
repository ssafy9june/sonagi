import { useQuery } from '@tanstack/react-query';
import { getIllness } from '@/apis/Memo/memoAPI';

const useGetIllness = (babyId: number) => {
  const { data } = useQuery(['illness', babyId], () => getIllness(babyId));
  return data;
};

export { useGetIllness };

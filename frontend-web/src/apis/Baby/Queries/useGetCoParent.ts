import { getCoParent } from '@/apis/Baby/babyAPI';
import { User } from '@/types';
import { useQuery } from '@tanstack/react-query';

const useGetCoParent = (userId: number, babyId: number): User[] => {
  const { data: coparent } = useQuery(['coParent', babyId], () =>
    getCoParent(userId, babyId)
  );

  return coparent;
};

export { useGetCoParent };

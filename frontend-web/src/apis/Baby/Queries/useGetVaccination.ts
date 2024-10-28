import { Vaccination } from '@/types';
import { useQuery } from '@tanstack/react-query';
import { getVaccination } from '@/apis/Baby/babyAPI';

const useGetVaccination = (babyId: number): Vaccination[] => {
  const { data: vaccination } = useQuery(['vaccination', babyId], () =>
    getVaccination(babyId)
  );

  return vaccination;
};

export { useGetVaccination };

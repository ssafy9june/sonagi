import { useQuery } from '@tanstack/react-query';
import { getVaccinationDetail } from '../babyAPI';
import { Vaccination } from '@/types';

const useGetVaccinationDetail = (
  babyId: number,
  vaccintaionId: number
): Vaccination => {
  const { data: vaccintaion } = useQuery(['vaccinationDetail', babyId], () =>
    getVaccinationDetail(babyId, vaccintaionId)
  );

  return vaccintaion;
};

export { useGetVaccinationDetail };

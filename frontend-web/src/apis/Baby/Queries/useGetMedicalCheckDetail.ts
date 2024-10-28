import { useQuery } from '@tanstack/react-query';
import { getMedicalCheckDetail } from '../babyAPI';
import { MedicalCheck } from '@/types';

const useGetMedicalCheckDetail = (
  babyId: number,
  medicalCheckId: number
): MedicalCheck => {
  const { data: medicalCheck } = useQuery(['medicalCheckDetail', babyId], () =>
    getMedicalCheckDetail(babyId, medicalCheckId)
  );

  return medicalCheck;
};

export { useGetMedicalCheckDetail };

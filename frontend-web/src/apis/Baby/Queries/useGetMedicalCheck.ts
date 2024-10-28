import { MedicalCheck } from '@/types';
import { useQuery } from '@tanstack/react-query';
import { getMedicalCheck } from '@/apis/Baby/babyAPI';

const useGetMedicalCheck = (babyId: number): MedicalCheck[] => {
  const { data: medicalCheck } = useQuery(['medicalCheck', babyId], () =>
    getMedicalCheck(babyId)
  );

  return medicalCheck;
};

export { useGetMedicalCheck };

import { UpdateMedicalCheck } from '@/types';
import { useMutation, useQueryClient } from '@tanstack/react-query';
import { updateMedicalCheckDate } from '../babyAPI';

const useUpdateMedicalCheckDate = () => {
  const queryClient = useQueryClient();
  return useMutation(
    (medicalCheck: UpdateMedicalCheck) => updateMedicalCheckDate(medicalCheck),
    {
      onSuccess: () => {
        queryClient.invalidateQueries(['medicalCheckDetail']);
      },
      onError: (err: Error) => {
        console.log(err);
      },
    }
  );
};

export { useUpdateMedicalCheckDate };

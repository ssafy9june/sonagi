import { UpdateVaccination } from '@/types';
import { useMutation, useQueryClient } from '@tanstack/react-query';
import { updateVaccinationDate } from '../babyAPI';

const useUpdateVaccinationDate = () => {
  const queryClient = useQueryClient();
  return useMutation(
    (vaccination: UpdateVaccination) => updateVaccinationDate(vaccination),
    {
      onSuccess: () => {
        queryClient.invalidateQueries(['vaccinationDetail']);
      },
      onError: (err: Error) => {
        console.log(err);
      },
    }
  );
};

export { useUpdateVaccinationDate };

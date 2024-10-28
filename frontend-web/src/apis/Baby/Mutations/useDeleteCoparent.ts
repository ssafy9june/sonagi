import { deleteCoparent } from '@/apis/Baby/babyAPI';
import { useMutation, useQueryClient } from '@tanstack/react-query';
import { useRecoilValue } from 'recoil';
import { selectedBabyState } from '@/states/babyState';

interface CoparentProps {
  babyId: number;
  coparentId: number;
}

const useDeleteCoparent = () => {
  const queryClient = useQueryClient();
  const selectedBaby = useRecoilValue(selectedBabyState);

  return useMutation(({ babyId, coparentId }: CoparentProps) =>
    deleteCoparent(babyId, coparentId)
  );
};

export { useDeleteCoparent };

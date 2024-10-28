import { useQuery } from '@tanstack/react-query';
// import { BabiesOfUser } from '@/types';
import { getBabyDetail } from '@/apis/Baby/babyAPI';

interface BabyDetail {
  babyId: number;
  name: string;
  birthDate: string;
  gender: string;
  authority: string;
  isDeleted: string;
  deletedAt: string;
}

const useGetBabyDetail = (babyId: number, userId: number): BabyDetail => {
  const { data: selectedBabyInfo } = useQuery(['baby', babyId, userId], () =>
    getBabyDetail(babyId, userId)
  );
  return selectedBabyInfo;
};

export { useGetBabyDetail };

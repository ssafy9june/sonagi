import { getUserName } from '@/apis/User/userAPI';
import { User } from '@/types';
import { useQuery } from '@tanstack/react-query';

const useGetUserName = (userId: number): User => {
  const { data: userNameDto } = useQuery(['userName', userId], () =>
    getUserName(userId)
  );

  return userNameDto;
};

export { useGetUserName };

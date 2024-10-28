import { useMutation } from '@tanstack/react-query';
import { User } from '@/types';
import { updateUser } from '@/apis/User/userAPI';

const useUpdateUser = () => {
  return useMutation((user: User) => updateUser(user), {
    onSuccess: () => {},
    onError: (err: Error) => {
      console.log(err);
    },
  });
};

export { useUpdateUser };

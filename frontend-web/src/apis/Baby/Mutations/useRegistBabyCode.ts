import { registerBabyCode } from '@/apis/Baby/babyAPI';
import { useMutation } from '@tanstack/react-query';

interface UseRegisterBabyCodeProps {
  userId: number;
  code: string;
}

const useRegisterBabyCode = () => {
  return useMutation(({ userId, code }: UseRegisterBabyCodeProps) =>
    registerBabyCode(userId, code)
  );
};

export { useRegisterBabyCode };

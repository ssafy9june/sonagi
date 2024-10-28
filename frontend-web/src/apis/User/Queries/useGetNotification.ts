import { getNotification } from '@/apis/User/userAPI';
import { useQuery } from '@tanstack/react-query';

interface UserNotification {
  userId: number;
  calarm: 'N' | 'Y';
  malarm: 'N' | 'Y';
  dalarm: 'N' | 'Y';
  valarm: 'N' | 'Y';
}

const useGetNotification = (userId: number): UserNotification => {
  const { data: alarms } = useQuery(['alarms', userId], () =>
    getNotification(userId)
  );

  return alarms;
};

export { useGetNotification };

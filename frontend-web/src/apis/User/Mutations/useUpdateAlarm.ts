import { updateAlarm } from '@/apis/User/userAPI';
import { useMutation } from '@tanstack/react-query';

interface UpdateAlarmProps {
  alarmType: string;
  userId: number;
  alarmState: boolean;
}

const useUpdateAlarm = () => {
  return useMutation(({ alarmType, userId, alarmState }: UpdateAlarmProps) =>
    updateAlarm(alarmType, userId, alarmState)
  );
};

export { useUpdateAlarm };

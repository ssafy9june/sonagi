import { useUpdateAlarm } from '@/apis/User/Mutations/useUpdateAlarm';
import { StyledLabel } from '@/components/molecules/ToggleSwitch/ToggleSwitch.styles';
import { Toast } from '@/components/organisms/Toast/Toast';
import { userInfoState } from '@/states/userState';
import { ChangeEvent, useState } from 'react';
import { useRecoilValue } from 'recoil';

interface ToggleSwitchProps {
  switchState: boolean;
  setSwitchState: React.Dispatch<React.SetStateAction<boolean>>;
  id: string;
}

const ToggleSwitch = ({
  switchState,
  setSwitchState,
  id,
}: ToggleSwitchProps) => {
  const updateAlarmMutation = useUpdateAlarm();
  const userInfo = useRecoilValue(userInfoState);

  const onToggleChange = (e: ChangeEvent<HTMLInputElement>) => {
    console.log('---', e.target.checked);

    updateAlarmMutation.mutate(
      {
        alarmType: e.target.id,
        userId: userInfo.userId,
        alarmState: e.target.checked,
      },
      {
        onSuccess() {
          console.log(e.target.id, '변경완료');
        },
      }
    );
    setSwitchState(e.target.checked);
  };

  return (
    <StyledLabel htmlFor={id} checked={switchState}>
      <input
        id={id}
        type="checkbox"
        checked={switchState}
        onChange={onToggleChange}
      />
    </StyledLabel>
  );
};

export { ToggleSwitch };

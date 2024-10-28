import { useUpdateAlarm } from '@/apis/User/Mutations/useUpdateAlarm';
import { useGetNotification } from '@/apis/User/Queries/useGetNotification';
import Back from '@/components/atoms/Back/Back';
import { Text } from '@/components/atoms/Text/Text.styles';
import { ToggleSwitch } from '@/components/molecules/ToggleSwitch/ToggleSwitch';
import {
  AlarmContainer,
  AlarmWrapper,
  Alarms,
} from '@/pages/AlarmPage/AlarmPage.styles';
import { userInfoState } from '@/states/userState';
import theme from '@/styles/theme';
import { nyToBoolean } from '@/utils/stringToBooleanUtil';
import { useState } from 'react';
import { useRecoilValue } from 'recoil';

const AlarmPage = () => {
  const userInfo = useRecoilValue(userInfoState);
  const alarms = useGetNotification(userInfo.userId);

  // 검진
  const [checkupState, setCheckupState] = useState<boolean>(
    nyToBoolean(alarms.calarm)
  );

  //접종
  const [vaccinationState, setVaccinationState] = useState<boolean>(
    nyToBoolean(alarms.valarm)
  );

  // 식사
  const [mealState, setMealState] = useState<boolean>(
    nyToBoolean(alarms.malarm)
  );

  // 일기
  const [diaryState, setDiaryState] = useState<boolean>(
    nyToBoolean(alarms.dalarm)
  );

  return (
    <AlarmContainer>
      <Back>알림 설정</Back>
      <Alarms>
        <AlarmWrapper>
          <Text
            size="headSmall"
            color={checkupState ? theme.color.black3 : theme.color.gray1}
          >
            영유아 검진 알림
          </Text>
          <ToggleSwitch
            id="calarm"
            switchState={checkupState}
            setSwitchState={setCheckupState}
          />
        </AlarmWrapper>
        <AlarmWrapper>
          <Text
            size="headSmall"
            color={vaccinationState ? theme.color.black3 : theme.color.gray1}
          >
            예방 접종 알림
          </Text>
          <ToggleSwitch
            id="valarm"
            switchState={vaccinationState}
            setSwitchState={setVaccinationState}
          />
        </AlarmWrapper>
        <AlarmWrapper>
          <Text
            size="headSmall"
            color={mealState ? theme.color.black3 : theme.color.gray1}
          >
            식사 알림
          </Text>
          <ToggleSwitch
            id="malarm"
            switchState={mealState}
            setSwitchState={setMealState}
          />
        </AlarmWrapper>
        <AlarmWrapper>
          <Text
            size="headSmall"
            color={diaryState ? theme.color.black3 : theme.color.gray1}
          >
            일기 알림
          </Text>
          <ToggleSwitch
            id="dalarm"
            switchState={diaryState}
            setSwitchState={setDiaryState}
          />
        </AlarmWrapper>
      </Alarms>
    </AlarmContainer>
  );
};

export default AlarmPage;

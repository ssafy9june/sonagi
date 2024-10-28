import GenderButtons from '@/components/molecules/GenderButtons/GenderButtons';
import { useState, ChangeEvent, useEffect } from 'react';
import Button from '@/components/atoms/Button/Button';
import Input from '@/components/atoms/Input/Input';
import theme from '@/styles/theme';
import { Value } from 'react-calendar/dist/cjs/shared/types';
import moment from 'moment';
import { CalendarModal } from '@/components/organisms/CalendarModal/CalendarModal';
import UBPWrapper from '@/components/organisms/UpdateBabyProfile/UpdateBabyProfile.style';
import { useNavigate } from 'react-router-dom';
import { PATH } from '@/constants/path';
import { userInfoState } from '@/states/userState';
import { useRecoilState, useRecoilValue } from 'recoil';
import { selectedBabyState } from '@/states/babyState';
import { useUpdateBaby } from '@/apis/Baby/Mutations/useUpdateBaby';
import { UpdateBaby } from '@/types';
import { useGetBabyDetail } from '@/apis/Baby/Queries/useGetBabyDetail';
// import { useGetBaby } from '@/apis/Baby/Queries/useGetBaby';

const UpdateBabyProfile = () => {
  const [selectedBabyInfo, setSelectedBabyInfo] =
    useRecoilState(selectedBabyState);
  const userInfo = useRecoilValue(userInfoState);

  const today = new Date();

  const babyBirthDate = new Date(
    useGetBabyDetail(selectedBabyInfo.babyId, userInfo.userId).birthDate
  );

  const babyInfo: UpdateBaby = {
    birthDate: today.toISOString().split('T')[0],
    gender: selectedBabyInfo.gender,
    name: selectedBabyInfo.name,
    babyId: selectedBabyInfo.babyId,
  };

  const navigate = useNavigate();

  const [value, setValue] = useState<string>(babyInfo.name);
  const [gender, setGender] = useState<'M' | 'F'>(babyInfo.gender);
  const [option, setOption] = useState<'deActivated' | 'activated'>(
    'deActivated'
  );

  const updateBabyMutation = useUpdateBaby();

  useEffect(() => {
    if (value.length > 0) {
      setOption('activated');
    } else {
      setOption('deActivated');
    }
  }, [value]);

  const changeInput = (e: ChangeEvent<HTMLInputElement>) => {
    setValue(e.target.value);
  };

  const [modalOpen, setModalOpen] = useState<boolean>(false);

  // BabiesofUser에 date가 없음, 따라서 today 활용
  const [pickDate, setPickDate] = useState<Date>(babyBirthDate);

  const onClickAction = () => {
    setModalOpen(true);
  };

  const onCalendarChange = (value: Value) => {
    if (value instanceof Date) {
      setPickDate(value);
    }
  };

  const onModalClose = () => {
    setModalOpen(false);
  };

  const toOurBabyPage = async () => {
    if (option === 'activated') {
      await updateBabyMutation.mutateAsync({
        birthDate: moment(pickDate).format('YYYY-MM-DD'),
        gender: gender,
        name: value,
        babyId: babyInfo.babyId,
      });

      setSelectedBabyInfo({
        authority: selectedBabyInfo.authority,
        gender: gender,
        name: value,
        babyId: babyInfo.babyId,
      });

      navigate(PATH.OURBABY);
    }
  };

  return (
    <UBPWrapper>
      <CalendarModal
        pickDate={pickDate}
        onModalClose={onModalClose}
        modalOpen={modalOpen}
        onCalendarChange={onCalendarChange}
      />
      <GenderButtons gender={gender} setGender={setGender} />
      <Button
        option="default"
        size="large"
        $borderRadius="14px"
        onClick={onClickAction}
      >
        {moment(pickDate).format('YYYY년 MM월 D일')}
      </Button>
      <Input
        onChange={changeInput}
        value={value}
        $borderRadius={14}
        fontSize={theme.fontSize.headSmall}
        height={3.5}
      />
      <Button
        option={option}
        size="medium"
        onClick={toOurBabyPage}
        style={{ width: '16rem', height: '50px', borderRadius: '13px' }}
      >
        수정하기
      </Button>
    </UBPWrapper>
  );
};

export default UpdateBabyProfile;

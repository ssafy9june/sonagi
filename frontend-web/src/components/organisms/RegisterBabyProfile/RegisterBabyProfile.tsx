import GenderButtons from '@/components/molecules/GenderButtons/GenderButtons';
import { useState, ChangeEvent, useEffect } from 'react';
import Button from '@/components/atoms/Button/Button';
import Input from '@/components/atoms/Input/Input';
import theme from '@/styles/theme';
import { Value } from 'react-calendar/dist/cjs/shared/types';
import moment from 'moment';
import { CalendarModal } from '@/components/organisms/CalendarModal/CalendarModal';
import RBPWrapper from '@/components/organisms/RegisterBabyProfile/RegisterBabyProfile.style';
import { useNavigate } from 'react-router-dom';
import { PATH } from '@/constants/path';
import { userInfoState } from '@/states/userState';
import { useRecoilState } from 'recoil';
import { useAddBaby } from '@/apis/Baby/Mutations/useAddBaby';
// import { useGetBaby } from '@/apis/Baby/Queries/useGetBaby';

const RegisterBabyProfile = () => {
  const navigate = useNavigate();
  const [userInfo, setUserInfo] = useRecoilState(userInfoState); // userInfo.name을 가져다쓸것
  const [value, setValue] = useState<string>('');
  const [gender, setGender] = useState<'M' | 'F'>('M');
  const placeholder = '이름을 입력하세요';
  const [option, setOption] = useState<'deActivated' | 'activated'>(
    'deActivated'
  );

  const addBabyMutation = useAddBaby();

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

  const today = new Date();
  const [pickDate, setPickDate] = useState<Date>(today);

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

  const toMain = () => {
    if (option === 'activated') {
      addBabyMutation.mutate({
        birthDate: moment(pickDate).format('YYYY-MM-DD'),
        gender,
        name: value,
        userId: userInfo.userId, // userInfo는 recoil에!
      });
      navigate(PATH.MAIN);
    }
  };

  return (
    <RBPWrapper>
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
        placeholder={placeholder}
        $borderRadius={14}
        fontSize={theme.fontSize.headSmall}
        height={3.5}
      />
      <Button
        option={option}
        size="medium"
        onClick={toMain}
        style={{ width: '16rem', height: '50px', borderRadius: '13px' }}
      >
        등록하기
      </Button>
    </RBPWrapper>
  );
};

export default RegisterBabyProfile;

import Button from '@/components/atoms/Button/Button';
import { Image } from '@/components/atoms/Image/Image';
import { BabyBarContainer } from '@/components/molecules/BabyBar/BabyBar.styles';
import babyCircleBlue from '@/assets/images/img-baby-blue-circle.png';
import babyCircleYellow from '@/assets/images/img-baby-yellow-circle.png';

import { useGetBaby } from '@/apis/Baby/Queries/useGetBaby';
import { useRecoilState } from 'recoil';
import { useEffect } from 'react';
import { userInfoState } from '@/states/userState';
import { BabiesOfUser } from '@/types';
// import { babiesOfUserState, selectedBabyState } from '../../../states/babyState';
import theme from '@/styles/theme';
import { babiesOfUserState, selectedBabyState } from '@/states/babyState';

const BabyBar = () => {
  const [userInfo, setUserInfo] = useRecoilState(userInfoState);
  const [selectedBaby, setSelectedBaby] = useRecoilState(selectedBabyState);
  const [babyInfo, setBabyInfo] = useRecoilState(babiesOfUserState);

  const babies = useGetBaby(userInfo.userId);

  useEffect(() => {
    if (babies && babies.length > 0) {
      setBabyInfo(babies);
      setSelectedBaby(babies[0]);
    }
  }, [babies, setBabyInfo, setSelectedBaby]); // ESLint의 react-hooks/exhaustive-deps 규칙때문에 함수도 포함시킴

  const babyClicked = (babyInfo: BabiesOfUser) => {
    setSelectedBaby(babyInfo);
  };

  return (
    <BabyBarContainer>
      {babies?.map((item, index) => (
        // 예외적으로 BabyBar의 padding값을 inline style으로 적용
        <Button
          option="default"
          size="xSmall"
          key={index}
          style={{ padding: '3px 9px 3px 4px' }}
          onClick={() => babyClicked(item)}
          $border={
            selectedBaby === item
              ? `1.5px solid ${theme.color.cardMeal3}`
              : undefined
          }
        >
          <Image
            src={item.gender === 'M' ? babyCircleBlue : babyCircleYellow}
            width={1.2}
            style={{
              transform: 'rotate(-10.68deg)',
              margin: '2px',
            }}
          />
          {item.name}
        </Button>
      ))}
    </BabyBarContainer>
  );
};

export default BabyBar;

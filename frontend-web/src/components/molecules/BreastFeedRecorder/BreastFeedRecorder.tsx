import { Text } from '@/components/atoms/Text/Text.styles';
import Button from '@/components/atoms/Button/Button';
import theme from '@/styles/theme';
import * as S from '@/components/molecules/BreastFeedRecorder/BreastFeedRecorder.style';
import LeftBreast from '@/assets/images/img-breast-left.png';
import RightBreast from '@/assets/images/img-breast-right.png';
import { Image } from '@/components/atoms/Image/Image';
import { useState } from 'react';

interface BreastFeedProps {
  isLeft: boolean;
  setIsLeft: (value: boolean) => void;
}

const BreastFeed = ({ isLeft, setIsLeft }: BreastFeedProps) => {
  const leftHandler = () => {
    setIsLeft(true);
  };
  const rightHandler = () => {
    setIsLeft(false);
  };

  return (
    <>
      <S.BreastFeedWrapper>
        <Button
          size="xLarge"
          $borderColor={isLeft ? theme.color.categoryMeal : 'transparent'}
          $backgroundColor={isLeft ? null : theme.color.lightgrey}
          style={{
            filter: isLeft ? undefined : 'grayscale(100%)',
            height: '65px',
          }}
          $borderRadius="14px"
          onClick={leftHandler}
        >
          <S.ButtonWrapper>
            <Text size="medium1" style={{ marginRight: '8px' }}>
              왼쪽
            </Text>
            <Image src={LeftBreast} width={35} $unit="px"></Image>
          </S.ButtonWrapper>
        </Button>
        <Button
          size="xLarge"
          $borderColor={!isLeft ? theme.color.categoryMeal : 'transparent'}
          $backgroundColor={!isLeft ? null : theme.color.lightgrey}
          style={{
            filter: !isLeft ? undefined : 'grayscale(100%)',
            height: '65px',
          }}
          $borderRadius="14px"
          onClick={rightHandler}
        >
          <S.ButtonWrapper>
            <Image src={RightBreast} width={35} $unit="px"></Image>
            <Text size="medium1" style={{ marginLeft: '6px' }}>
              오른쪽
            </Text>
          </S.ButtonWrapper>
        </Button>
      </S.BreastFeedWrapper>
    </>
  );
};

export default BreastFeed;

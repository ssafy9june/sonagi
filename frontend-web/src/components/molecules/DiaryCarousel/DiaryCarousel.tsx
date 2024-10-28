import * as S from '@/components/molecules/DiaryCarousel/DiaryCarousel.styles';
import React, { useEffect, useState } from 'react';
import IconArrowLeftWhite from '@/assets/images/icon-arrow-left-white.png';
import IconArrowRightWhite from '@/assets/images/icon-arrow-right-white.png';
import ImgDefaultBaby from '@/assets/images/img-default-baby.png';
interface DiaryCarouselProps {
  images: string[];
}

const DiaryCarousel: React.FC<DiaryCarouselProps> = ({ images }) => {
  // 위치에 따른 보여주는 값을 설정.
  const [currentIndex, setCurrentIndex] = useState<number>(0);
  const [currnetXsize, setCurrentXsize] = useState<string>('');

  const handleStep = (step: number) => {
    setCurrentIndex(
      prevIndex => (prevIndex + step + images.length) % images.length
    );
    console.log(currentIndex);
  };
  const indexToXsize = (index: number): string => {
    const Xsize = index * -100;
    return `translateX(${Xsize}%)`;
  };

  useEffect(() => {
    setCurrentXsize(indexToXsize(currentIndex));
  }, [currentIndex]);

  return (
    <>
      <S.CarouselContainer>
        {images.length > 1 && (
          <S.PrevButton
            src={IconArrowLeftWhite}
            onClick={() => handleStep(-1)}
          ></S.PrevButton>
        )}
        {images.length > 1 && (
          <S.NextButton
            src={IconArrowRightWhite}
            onClick={() => handleStep(1)}
          ></S.NextButton>
        )}
        <S.CarouselSlider $transformXsize={currnetXsize}>
          {images.map((url, index) => (
            <S.CarouselImage key={index} src={url} />
          ))}
          {images.length === 0 && <S.CarouselImage src={ImgDefaultBaby} />}
        </S.CarouselSlider>
      </S.CarouselContainer>
    </>
  );
};

export default DiaryCarousel;

import React from 'react';
import DiaryCarousel from '@/components/molecules/DiaryCarousel/DiaryCarousel';
import * as S from '@/components/organisms/DiaryCard/DiaryCard.styles';
import IconMeatballWhite from '@/assets/images/icon-meatball-white.png';
import ImgDiaryWrote from '@/assets/images/img-diary-wrote.png';
import { Image } from '@/components/atoms/Image/Image';
import { Text } from '@/components/atoms/Text/Text.styles';
import theme from '@/styles/theme';
import { useNavigate } from 'react-router-dom';
import { PATH } from '@/constants/path';
import { formatTime } from '../../../utils/formatDate';

interface DiaryCardProps {
  diaryId: number;
  writer: string;
  writenDate: string;
  content: string;
  imgUrls: string[];
}

const DiaryCard: React.FC<DiaryCardProps> = ({
  diaryId,
  writer,
  writenDate,
  content,
  imgUrls,
}) => {
  const navigate = useNavigate();
  const handleEditClick = () => {
    navigate(`${PATH.DIARYUPDATE}/${diaryId}`); // 이동할 경로에 diaryId를 추가
  };

  return (
    <>
      <S.DiaryCardContainer>
        <S.DiaryEditBtn
          src={IconMeatballWhite}
          onClick={handleEditClick}
        ></S.DiaryEditBtn>
        <DiaryCarousel images={imgUrls}></DiaryCarousel>
        <S.DiaryContentContainer>
          <S.DiaryContentTitle>
            <Image
              src={ImgDiaryWrote}
              width={1.8}
              height={1.8}
              $unit="rem"
            ></Image>
            <Text size={'headSmall'} $fontWeight={500}>
              {writer}
            </Text>
            <Text size="medium3" color={theme.color.gray1}>
              {formatTime(writenDate.slice(0, 5))}
            </Text>
          </S.DiaryContentTitle>
          <S.Divider></S.Divider>
          <Text size="medium2" $fontWeight={400} color={theme.color.black1}>
            {content}
          </Text>
        </S.DiaryContentContainer>
      </S.DiaryCardContainer>
    </>
  );
};

export default DiaryCard;

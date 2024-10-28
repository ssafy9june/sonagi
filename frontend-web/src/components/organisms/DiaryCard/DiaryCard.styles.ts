import theme from '@/styles/theme';
import { styled } from 'styled-components';

const DiaryCardContainer = styled.div`
  position: relative;
  border: 2px solid ${theme.color.gray3};
  border-radius: 1rem;
  background-color: white;
  padding: 1rem;
  width: 100%;
`;

const DiaryEditBtn = styled.img`
  display: inline-block;
  position: absolute;
  width: 13%;
  z-index: 100;
  margin: 1rem;
  top: 0%;
  right: 0;
  transform: translate(-10%, 0);
`;
const DiaryContentContainer = styled.div`
  background-color: white;
  padding: 1rem;
  box-sizing: border-box;
`;

const DiaryContentTitle = styled.div`
  display: flex;
  justify-content: start;
  align-items: center;
  box-sizing: border-box;
  gap: 0.5rem;
`;
const Divider = styled.div`
  border-top: 1px solid ${theme.color.gray3};
  margin-top: 0.75rem;
  margin-bottom: 1.25rem;
  box-sizing: border-box;
  width: 100%;
`;
const ImgWrapper = styled.div``;
const TitleWrapper = styled.div``;
const TimeWrapper = styled.div``;
export {
  DiaryCardContainer,
  DiaryEditBtn,
  DiaryContentContainer,
  DiaryContentTitle,
  ImgWrapper,
  TitleWrapper,
  TimeWrapper,
  Divider,
};

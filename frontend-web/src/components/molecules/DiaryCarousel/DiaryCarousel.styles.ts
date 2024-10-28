import { styled } from 'styled-components';

interface CarouselSliderProps {
  $transformXsize: string;
}
const CarouselContainer = styled.div`
  position: relative;
  max-width: 100%;
  overflow: hidden;
  background-color: white;
  border-radius: 1rem;
`;

const CarouselImage = styled.img`
  min-width: 100%;
  max-height: 22vh;
  object-fit: cover;
`;

const CarouselSlider = styled.div<CarouselSliderProps>`
  display: flex;
  transition: transform 0.3s ease-in-out;
  transform: ${({ $transformXsize }) => $transformXsize};
`;

const PrevButton = styled.img`
  position: absolute;
  width: 14%;
  top: 50%;
  left: 0;
  z-index: 100;
  transform: translate(10%, -50%);
`;

const NextButton = styled.img`
  position: absolute;
  width: 14%;
  top: 50%;
  right: 0;
  z-index: 100;
  transform: translate(-10%, -50%);
`;

export {
  CarouselContainer,
  CarouselSlider,
  CarouselImage,
  PrevButton,
  NextButton,
};

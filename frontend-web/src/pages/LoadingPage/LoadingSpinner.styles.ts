import { styled } from 'styled-components';

const LoadingSpinnerContainer = styled.div`
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 10000;
`;

const LoadingRotatingImg = styled.div`
  animation: progress-animation 2s infinite;

  @keyframes progress-animation {
    0% {
      transform: rotate(0deg);
    }
    100% {
      transform: rotate(360deg);
    }
  }
`;

export { LoadingSpinnerContainer, LoadingRotatingImg };

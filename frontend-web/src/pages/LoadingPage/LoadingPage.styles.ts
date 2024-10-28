import styled from 'styled-components';

const LoadingPageContainer = styled.div`
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const LoadingPageWrapper = styled.div`
  max-width: 390px;
  min-width: 300px;
  width: 100vw;
  text-align: center;
  font-family: 'Happiness-Sans';
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

export { LoadingPageContainer, LoadingPageWrapper, LoadingRotatingImg };

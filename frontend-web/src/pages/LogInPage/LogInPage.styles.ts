import styled from 'styled-components';

const LogInPageContainer = styled.div`
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const LogInPageWrapper = styled.div`
  max-width: 390px;
  min-width: 300px;
  padding: 0px 40px;
  width: 100vw;
  height: 70vh;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
  text-align: center;
  font-family: 'Happiness-Sans';
`;

const LogoContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const ButtonContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 13rem;
  width: 100%;
  justify-content: space-between;
`;

export { LogInPageWrapper, LogInPageContainer, LogoContainer, ButtonContainer };

import styled from 'styled-components';
const RegisterAgainPageContainer = styled.div`
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const RegisterAgainPageWrapper = styled.div`
  padding: 0px 40px;
  width: 100vw;
  height: 70vh;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
  text-align: center;
  font-family: 'Happiness-Sans';

  > p {
    // 건너뛰기
    text-decoration: underline;
    text-decoration-color: ${({ theme }) => theme.color.black3};
    text-underline-offset: 8px;
    text-decoration-thickness: 0.3px;
  }
`;

const LogoContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1.2rem;
`;

const ButtonContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 8.5rem;
  justify-content: space-between;
  width: 100%;
`;

export {
  RegisterAgainPageWrapper,
  RegisterAgainPageContainer,
  LogoContainer,
  ButtonContainer,
};

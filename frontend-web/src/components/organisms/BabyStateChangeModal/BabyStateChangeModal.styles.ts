import { styled } from 'styled-components';

const BabyStateChangeModalContainer = styled.div``;

const BabyStateChangeModalTitleWrapper = styled.div`
  white-space: pre-line;
  margin-bottom: 10px;
`;

const BabyStateChangeModalContentWrapper = styled.div`
  margin-bottom: 20px;
`;

const BabyStateChangeButtonContainer = styled.div`
  display: flex;
  gap: 20px;

  button {
    height: 48px;
    border-radius: 18px;
  }
`;

export {
  BabyStateChangeModalContainer,
  BabyStateChangeModalTitleWrapper,
  BabyStateChangeModalContentWrapper,
  BabyStateChangeButtonContainer,
};

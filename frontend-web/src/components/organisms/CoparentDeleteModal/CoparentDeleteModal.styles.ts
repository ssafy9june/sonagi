import { styled } from 'styled-components';

const CoparentDeleteModalContainer = styled.div``;

const CoparentDeleteModalTitleWrapper = styled.div`
  white-space: pre-line;
  margin-bottom: 10px;
`;

const CoparentDeleteModalContentWrapper = styled.div`
  margin-bottom: 20px;
`;

const CoparentDeleteButtonContainer = styled.div`
  display: flex;
  gap: 20px;

  button {
    height: 48px;
    border-radius: 18px;
  }
`;

export {
  CoparentDeleteModalContainer,
  CoparentDeleteModalTitleWrapper,
  CoparentDeleteModalContentWrapper,
  CoparentDeleteButtonContainer,
};

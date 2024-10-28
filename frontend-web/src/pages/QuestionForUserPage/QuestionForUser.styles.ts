import { styled } from 'styled-components';

const QuestionForUserContainer = styled.div`
  display: flex;
  flex-direction: column;
  box-sizing: border-box;
  overflow: scroll;
  padding: 2rem 1rem 1rem 1rem;
  margin: 0 1rem 0 1rem;
  gap: 1.5rem;
  height: 100vh;
`;
const QuestionForUserHeader = styled.div`
  text-align: center;
  padding: 1rem;
`;
const QuestionForUserBody = styled.div`
  display: flex;
  flex-direction: column;
  gap: 0.75rem;
`;

const RegisterBtnContainer = styled.div`
  display: flex;
  flex-direction: row;
  gap: 0.5rem;
  width: 100%;
  box-sizing: border-box;
`;

export {
  QuestionForUserContainer,
  QuestionForUserHeader,
  QuestionForUserBody,
  RegisterBtnContainer,
};

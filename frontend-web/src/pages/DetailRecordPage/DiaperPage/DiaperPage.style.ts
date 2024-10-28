import styled from 'styled-components';
import theme from '@/styles/theme';

const DiaperPageContainer = styled.div`
  /* width: calc(100% - 20px); */
  display: flex;
  /* align-items: center; */
  justify-content: center;
  background-color: white;
`;

const DiaperPageWrapper = styled.div`
  height: calc(100vh - 8rem);
  margin-top: 60px;
  padding: 10px 40px;
  /* width: 100vw; */
  display: flex;
  flex-direction: column;
  /* justify-content: space-around; */
  align-items: center;
  text-align: center;
  font-family: 'Happiness-Sans';
`;

const Divider = styled.div`
  border-top: 1.25px solid ${theme.color.gray4};
  padding: 20px 0px;
  width: 76vw;
`;

export { DiaperPageContainer, DiaperPageWrapper, Divider };

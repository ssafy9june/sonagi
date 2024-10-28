import styled from 'styled-components';
import theme from '@/styles/theme';

export const TemperaturePageContainer = styled.div`
  /* width: calc(100% - 20px); */
  display: flex;
  /* align-items: center; */
  justify-content: center;
  background-color: white;
`;

export const TemperaturePageWrapper = styled.div`
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

export const Divider = styled.div`
  border-top: 1px solid ${theme.color.gray3};
  padding: 20px 0px;
  width: 76vw;
`;

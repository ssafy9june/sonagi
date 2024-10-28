import { styled } from 'styled-components';

const AlarmContainer = styled.div`
  display: flex;
  height: calc(100% - 52px);
  background-color: ${({ theme }) => theme.color.white1};
  flex-direction: column;
  position: relative;
`;

const Alarms = styled.div`
  margin: 4rem 1.5rem 0px;
  border-top: 1px solid ${({ theme }) => theme.color.gray3};
`;

const AlarmWrapper = styled.div`
  display: flex;
  margin: 4vh 0px;
  padding: 0px 5vw;
  width: 100%;
  justify-content: space-between;
`;

export { AlarmContainer, Alarms, AlarmWrapper };

import { styled } from 'styled-components';
import theme from './../../../styles/theme';

const WeekendCalendarContainer = styled.div`
  display: flex;
  background-color: ${() => theme.color.white1};
  box-sizing: border-box;
  padding: 1rem 0.5rem 0.5rem 0.5rem;
  justify-content: space-around;
`;

export default WeekendCalendarContainer;

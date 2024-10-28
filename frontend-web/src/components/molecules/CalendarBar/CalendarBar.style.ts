import styled from 'styled-components';

const CalendarBarContainer = styled.div`
  background: ${({ theme }) => theme.color.white1};
  padding: 0.3rem 0.5rem;
  display: flex;
  max-width: 100%;
  height: 50px;
  justify-content: center;
  align-items: center;
  border-bottom: 2px dashed ${({ theme }) => theme.color.gray3};
`;

export { CalendarBarContainer };

import styled from 'styled-components';

export const StyledTabBar = styled.nav`
  display: flex;
  justify-content: space-around;
  align-items: center;
  background-color: white;
  position: fixed;
  bottom: 0;
  width: 100%;
  box-shadow: 0 -5px 10px 0 ${({ theme }) => theme.color.black3 + '10'};
`;

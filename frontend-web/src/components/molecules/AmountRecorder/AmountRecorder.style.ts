import styled from 'styled-components';

export const AmountButtonWrapper = styled.div`
  display: flex;
  justify-content: space-evenly;
  width: 100%;
  gap: 5px;

  p {
    color: ${({ theme }) => theme.color.blue};
  }
`;

export const LeftWrapper = styled.div`
  text-align: left;
`;

export const AmountRecorderContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 20px;
`;

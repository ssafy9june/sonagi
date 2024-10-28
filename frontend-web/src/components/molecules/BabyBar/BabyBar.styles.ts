import styled from 'styled-components';

const BabyBarContainer = styled.div`
  padding: 0.3rem 0.5rem;
  display: flex;
  max-width: 100%;
  overflow-x: auto;
  gap: 5px;

  button {
    flex: 0 0 auto;
  }
`;

const BabyBarStyle = styled.button`
  display: flex;
`;

export { BabyBarContainer, BabyBarStyle };

import styled from 'styled-components';

const RecordBarContainer = styled.div`
  display: flex;
  overflow-x: scroll;
  gap: 4px;
  /* margin-top: 12px; */

  & > :first-child {
    margin-left: 0.5rem;
  }
  & > :last-child {
    margin-right: 0.5rem;
  }
`;

export { RecordBarContainer };

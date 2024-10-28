import styled from 'styled-components';

const DiaryListContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: start;
  box-sizing: border-box;
  padding: 1.5rem;
  gap: 0.75rem;
  height: calc(100vh - 15rem);
  overflow: scroll;
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
  &::-webkit-scrollbar {
    display: none;
  }
`;

export { DiaryListContainer };

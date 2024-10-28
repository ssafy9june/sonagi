import styled from 'styled-components';

const BPICContainer = styled.div`
  /* height: 160px; */
  /* border-top: 2px dashed ${({ theme }) => theme.color.white1}; */
  padding: 0 27px;
  /* margin: 0  */
  width: 100%;
`;

const BPICScrollWrapper = styled.div`
  display: flex;
  overflow-x: auto; /* Enable horizontal scrolling */
  overflow-y: hidden; /* Hide vertical scrollbar */
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
  &::-webkit-scrollbar {
    display: none;
  }
  width: 100%;
`;

const BPICButtonWrapper = styled.div`
  display: flex;
  gap: 10px;
`;

export { BPICContainer, BPICScrollWrapper, BPICButtonWrapper };

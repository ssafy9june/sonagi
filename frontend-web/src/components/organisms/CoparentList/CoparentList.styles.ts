import { styled } from 'styled-components';

const CoParentListContainer = styled.div`
  width: 100%;
  display: flex;
  overflow-x: auto;
  flex-wrap: nowrap;
  gap: 10px;
  padding-top: 3vh;
`;

const CoParentWrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  flex: 0 0 auto;
  position: relative;

  .delete {
    position: absolute;
    right: -5px;
    top: -5px;
  }
`;

export { CoParentListContainer, CoParentWrapper };

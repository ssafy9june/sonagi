import { styled } from 'styled-components';

const CategoryBarContainer = styled.div`
  background-color: white;
`;

const GraphContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: white;
`;

const ToggleContainer = styled.div`
  display: flex;
  justify-content: space-between;
  width: 90vw;
  gap: 15px;
  margin-bottom: 10px;
`;

export { CategoryBarContainer, GraphContainer, ToggleContainer };

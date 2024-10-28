import { styled } from 'styled-components';

const MedicalCheckContainer = styled.div`
  height: calc(100% - 52px);
  width: 100%;
  background-color: ${({ theme }) => theme.color.white1};
  display: flex;
  flex-direction: column;
`;

const MedicalCheckWrapper = styled.div`
  margin-top: 70px;
  margin-bottom: 30px;
  padding: 0px 30px;
  -ms-overflow-style: none;
  scrollbar-width: none;
  overflow: auto;
  &::-webkit-scrollbar {
    display: none;
  }
`;

export { MedicalCheckContainer, MedicalCheckWrapper };

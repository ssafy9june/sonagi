import { styled } from 'styled-components';

const MedicalInfoContainer = styled.div`
  height: calc(100% - 52px);
  width: 100%;
  background-color: ${({ theme }) => theme.color.white1};
  display: flex;
  flex-direction: column;
`;

const MedicalStatusWrapper = styled.div`
  margin-bottom: 30px;
  padding: 0px 20px;
  -ms-overflow-style: none;
  scrollbar-width: none;
  overflow: auto;
  &::-webkit-scrollbar {
    display: none;
  }
`;

const MedicalInfoImageContainer = styled.div`
  display: flex;
  margin: 70px auto 20px;
`;

const MedicalInfoImage = styled.div`
  z-index: 1;
`;

const DividerLine = styled.div`
  position: absolute;
  margin-top: 15px;
  height: 1px;
  left: 20px;
  right: 20px;
  background-color: ${({ theme }) => theme.color.gray3};
`;

export {
  MedicalInfoContainer,
  MedicalStatusWrapper,
  MedicalInfoImageContainer,
  MedicalInfoImage,
  DividerLine,
};

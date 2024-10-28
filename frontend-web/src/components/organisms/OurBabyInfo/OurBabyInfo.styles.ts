import styled from 'styled-components';

const OurBabyInfoContainer = styled.div`
  /* border-top: 2px dashed ${({ theme }) => theme.color.white1}; */
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  /* margin-top: 30px; */
  padding-top: 30px;
  padding-bottom: 90px;
  gap: 15px;
`;

const UpperButtonWrapper = styled.div`
  display: flex;
  justify-content: center;
  padding: 0px 6px 0px 14px;
  align-items: center;
  width: 100%;
  /* border-bottom: 2px dashed ${({ theme }) => theme.color.white1}; */
`;

const UpperButtonContainer = styled.div`
  display: flex;
  justify-content: space-around;
  width: 100%;
  padding: 0 30px;
  gap: 10px;
  /* gap: 20px; */
  /* border-bottom: 2px dashed ${({ theme }) => theme.color.white1}; */
  /* padding: 10px, 0px; */
`;

const ButtonsDivider = styled.div`
  border-top: 2px dashed ${({ theme }) => theme.color.white1};
  width: 100%;
`;

const BabyNameWrapper = styled.div`
  display: flex;
  /* justify-content: space-evenly; */
  justify-content: center;
  width: 100%;
  /* position: absolute; */
`;

const InfoEditWrapper = styled.div`
  border-top: 2px dashed ${({ theme }) => theme.color.white1};
  display: flex;
  justify-content: end;
  /* align-items: center; */
  padding: 16px;
  position: absolute;
  width: 100%;

  button {
    padding: 8px;

    img {
      margin-right: 8px;
    }
  }
`;

const OurBabyInfoWholeContainer = styled.div`
  display: flex;
  height: 100vh;
  /* height: calc(100% - 52px); */
  background-color: ${({ theme }) => theme.color.white1};
  flex-direction: column;
  position: relative;
`;

const EmptyContainer = styled.div`
  height: calc(100% - 52px);
`;

export {
  OurBabyInfoContainer,
  UpperButtonWrapper,
  BabyNameWrapper,
  InfoEditWrapper,
  ButtonsDivider,
  UpperButtonContainer,
  OurBabyInfoWholeContainer,
  EmptyContainer,
};

import styled from 'styled-components';

const UpdateBabyProfilePageContainer = styled.div`
  height: 88vh;
  display: flex;
  align-items: center;
  justify-content: center;
`;
const UpdateBabyProfilePageWrapper = styled.div`
  /* padding: 0px 40px; */
  width: 100vw;
  height: 70vh;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
  text-align: center;
  font-family: 'Happiness-Sans';
`;
const UpdateBabyProfileWrapper = styled.div`
  margin: 30px;
`;

// 데이터 삭제하기
const DataDeleteWrapper = styled.p`
  text-decoration: underline;
  text-decoration-color: ${({ theme }) => theme.color.black3};
  text-underline-offset: 8px;
  text-decoration-thickness: 0.3px;
`;

const AddBabyWrapper = styled.div`
  /* margin-bottom: 10px; */
`;
export {
  UpdateBabyProfilePageContainer,
  UpdateBabyProfilePageWrapper,
  UpdateBabyProfileWrapper,
  AddBabyWrapper,
  DataDeleteWrapper,
};

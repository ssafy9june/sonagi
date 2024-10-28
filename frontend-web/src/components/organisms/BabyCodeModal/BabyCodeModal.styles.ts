import { styled } from 'styled-components';

const BabyCodeModalContainer = styled.div`
  // 초대 코드 title
  > p {
    padding: 10px;
    border-bottom: 1px solid ${({ theme }) => theme.color.gray3};
  }

  // 카카오톡 공유하기 버튼
  button {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 20px;

    img {
      margin-right: 10px;
    }
  }
`;

const BabyCodeWrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 40px;

  p {
    margin-right: 20px;
  }
`;

export { BabyCodeModalContainer, BabyCodeWrapper };

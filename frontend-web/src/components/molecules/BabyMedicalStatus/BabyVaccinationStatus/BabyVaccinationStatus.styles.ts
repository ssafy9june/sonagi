import styled from 'styled-components';

const VaccinationBlock = styled.div`
  display: flex;
  justify-content: space-between;
  padding: 15px;
  border-bottom: 1px solid ${({ theme }) => theme.color.gray3};
`;

const VaccinationImage = styled.div`
  display: flex;
  align-items: center;
`;

const VaccinationItem = styled.div`
  display: flex;
  flex-direction: column;
  margin-left: 20px;
  margin-right: auto;
`;

export { VaccinationBlock, VaccinationImage, VaccinationItem };

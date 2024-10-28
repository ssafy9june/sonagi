import styled from 'styled-components';

const VaccinationContentWrapper = styled.div`
  display: flex;
  flex-direction: column;
  border-bottom: 1px solid ${({ theme }) => theme.color.gray3};
  line-height: 1.7rem;
  padding: 15px 0px;
`;

const VaccinationCalendarButton = styled.div`
  display: flex;
  justify-content: space-between;
`;

export { VaccinationContentWrapper, VaccinationCalendarButton };

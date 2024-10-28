import { useRecoilValue } from 'recoil';
import {
  VaccinationContainer,
  VaccinationWrapper,
} from './VaccinationPage.styles';
import VaccinationContent from '@/components/molecules/VaccinationContent/VaccinationContent';
import { selectedVaccinationState } from '@/states/medicineState';
import { BabiesOfUser, Vaccination } from '@/types';
import { selectedBabyState } from '@/states/babyState';
import { useGetVaccinationDetail } from '@/apis/Baby/Queries/useGetVaccinationDetail';
import Back from '@/components/atoms/Back/Back';

const VaccinationPage = () => {
  const babyInfo: BabiesOfUser = useRecoilValue(selectedBabyState);
  const vaccinationId: number = useRecoilValue(selectedVaccinationState);

  // hook
  const vaccinationDetail: Vaccination = useGetVaccinationDetail(
    babyInfo.babyId,
    vaccinationId
  );

  return (
    <VaccinationContainer>
      <Back>예방접종 상세</Back>
      <VaccinationWrapper>
        <VaccinationContent vaccinationData={vaccinationDetail} />
      </VaccinationWrapper>
    </VaccinationContainer>
  );
};

export default VaccinationPage;

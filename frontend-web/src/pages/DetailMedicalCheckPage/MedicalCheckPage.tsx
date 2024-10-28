import { useRecoilValue } from 'recoil';
import { selectedMedicalCheckState } from '@/states/medicineState';
import { BabiesOfUser, MedicalCheck } from '@/types';
import { selectedBabyState } from '@/states/babyState';
import Back from '@/components/atoms/Back/Back';
import {
  MedicalCheckContainer,
  MedicalCheckWrapper,
} from './MedicalCheckPage.styles';
import MedicalCheckContent from '@/components/molecules/MedicalCheckContent/MedicalCheckContent';
import { useGetMedicalCheckDetail } from '@/apis/Baby/Queries/useGetMedicalCheckDetail';

const MedicalCheckPage = () => {
  const babyInfo: BabiesOfUser = useRecoilValue(selectedBabyState);
  const medicalCheckId: number = useRecoilValue(selectedMedicalCheckState);

  // hook
  const medicalCheckDetail: MedicalCheck = useGetMedicalCheckDetail(
    babyInfo.babyId,
    medicalCheckId
  );

  return (
    <MedicalCheckContainer>
      <Back>예방접종 상세</Back>
      <MedicalCheckWrapper>
        <MedicalCheckContent medicalCheckData={medicalCheckDetail} />
      </MedicalCheckWrapper>
    </MedicalCheckContainer>
  );
};

export default MedicalCheckPage;

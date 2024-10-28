import { Text } from '@/components/atoms/Text/Text.styles';
import checkBlue from '@/assets/images/img-check-blue.png';
import checkGrey from '@/assets/images/img-check-grey.png';
import arrow from '@/assets/images/icon-arrow-mini-right-grey.png';
import { Image } from '@/components/atoms/Image/Image';
import {
  VaccinationBlock,
  VaccinationImage,
  VaccinationItem,
} from './BabyVaccinationStatus.styles';
import { Vaccination } from '@/types';
import theme from '@/styles/theme';
import { useNavigate } from 'react-router-dom';
import { PATH } from '@/constants/path';
import { useRecoilState } from 'recoil';
import { selectedVaccinationState } from '@/states/medicineState';

interface BabyVaccinationStatusProps {
  vaccinationData: Vaccination;
}

const BabyVaccinationStatus = ({
  vaccinationData,
}: BabyVaccinationStatusProps) => {
  const navigate = useNavigate();
  const imageSrc =
    vaccinationData.vaccinationDate === null ? checkGrey : checkBlue;
  const [vaccinationId, setVaccinationId] = useRecoilState(
    selectedVaccinationState
  );
  const today = new Date();
  const startDate = new Date(vaccinationData.startDate);

  const handleClick = (id: number) => {
    setVaccinationId(id);
    navigate(PATH.VACCINATION);
  };

  return (
    <VaccinationBlock
      onClick={() => handleClick(vaccinationData.vaccinationId)}
    >
      <VaccinationImage>
        <Image src={imageSrc} width={1.4} height={1.4} />
      </VaccinationImage>
      <VaccinationItem>
        <Text size="headSmall" style={{ marginBottom: '5px' }}>
          {vaccinationData.disease}
        </Text>
        <Text size="medium2" style={{ marginBottom: '5px' }}>
          {vaccinationData.vaccineName}
        </Text>
        {vaccinationData.vaccinationDate && (
          <Text size="medium3" color={theme.color.blue}>
            검진일: {vaccinationData.vaccinationDate}
          </Text>
        )}
        {!vaccinationData.vaccinationDate && today >= startDate && (
          <Text size="medium3" color={theme.color.danger}>
            권장일: {vaccinationData.startDate} ~ {vaccinationData.endDate}
          </Text>
        )}
        {!vaccinationData.vaccinationDate && today < startDate && (
          <Text size="medium3" color={theme.color.gray1}>
            권장일: {vaccinationData.startDate} ~ {vaccinationData.endDate}
          </Text>
        )}
      </VaccinationItem>
      <VaccinationImage>
        <Image src={arrow} width={1.3} height={1.3} />
      </VaccinationImage>
    </VaccinationBlock>
  );
};

export default BabyVaccinationStatus;

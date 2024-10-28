import { useRecoilValue } from 'recoil';
import { useEffect, useRef, useState } from 'react';
import { selectedBabyState } from '@/states/babyState';
import { BabiesOfUser } from '@/types';
import { useGetVaccination } from '@/apis/Baby/Queries/useGetVaccination';
import { useGetMedicalCheck } from '@/apis/Baby/Queries/useGetMedicalCheck';
import {
  DividerLine,
  MedicalInfoContainer,
  MedicalInfoImage,
  MedicalInfoImageContainer,
  MedicalStatusWrapper,
} from './MedicalInfoPage.styles';
import toggleLeft from '@/assets/images/btn-toggle-left-activated.png';
import toggleRight from '@/assets/images/btn-toggle-right-activated.png';
import Back from '@/components/atoms/Back/Back';
import { Image } from '@/components/atoms/Image/Image';
import BabyVaccinationStatus from '@/components/molecules/BabyMedicalStatus/BabyVaccinationStatus/BabyVaccinationStatus';
import BabyMedicalCheckStatus from '@/components/molecules/BabyMedicalStatus/BabyMedicalCheckStatus/BabyMedicalCheckStatus';

const MedicalInfoPage = () => {
  const babyInfo: BabiesOfUser = useRecoilValue(selectedBabyState);
  const vaccinationList = useGetVaccination(babyInfo.babyId);
  const medicalCheckList = useGetMedicalCheck(babyInfo.babyId);

  const [medicalFlag, setMedicalFlag] = useState(true);
  const imageSrc = medicalFlag ? toggleLeft : toggleRight;

  const clickImage = () => {
    setMedicalFlag(!medicalFlag);
  };

  const containerRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const container = containerRef.current;
    const savedScrollTop = localStorage.getItem('scrollPosition');
    if (container && savedScrollTop) {
      container.scrollTop = Number(savedScrollTop);
    }
  });

  useEffect(() => {
    const container = containerRef.current;
    if (container) {
      const onScroll = () => {
        localStorage.setItem('scrollPosition', String(container.scrollTop));
      };

      container.addEventListener('scroll', onScroll);
      return () => {
        container.removeEventListener('scroll', onScroll);
      };
    }
  });

  return (
    <MedicalInfoContainer>
      <Back>예방접종 / 검진</Back>
      <MedicalInfoImageContainer>
        <DividerLine></DividerLine>
        <MedicalInfoImage>
          <Image src={imageSrc} width={12.5} height={2} onClick={clickImage} />
        </MedicalInfoImage>
      </MedicalInfoImageContainer>
      {medicalFlag && (
        <MedicalStatusWrapper>
          {vaccinationList.map((vaccination, index) => (
            <BabyVaccinationStatus key={index} vaccinationData={vaccination} />
          ))}
        </MedicalStatusWrapper>
      )}
      {!medicalFlag && (
        <MedicalStatusWrapper>
          {medicalCheckList.map((medicalCheck, index) => (
            <BabyMedicalCheckStatus
              key={index}
              medicalCheckData={medicalCheck}
            />
          ))}
        </MedicalStatusWrapper>
      )}
    </MedicalInfoContainer>
  );
};

export default MedicalInfoPage;

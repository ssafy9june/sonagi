import React, { useState } from 'react';
import { Text } from '@/components/atoms/Text/Text.styles';
import { UpdateVaccination, Vaccination } from '@/types';
import {
  VaccinationCalendarButton,
  VaccinationContentWrapper,
} from './VaccinationContent.styles';
import Button from '@/components/atoms/Button/Button';
import theme from '@/styles/theme';
import calendarImg from '@/assets/images/icon-calendar-blue.png';
import check from '@/assets/images/img-check-blue.png';
import { Image } from '@/components/atoms/Image/Image';
import { Value } from 'react-calendar/dist/cjs/shared/types';
import { formatDate } from '@/utils/formatDate';
import { useUpdateVaccinationDate } from '@/apis/Baby/Mutations/useUpdateVaccinationDate';
import { CalendarModal } from '@/components/organisms/CalendarModal/CalendarModal';

interface BabyVaccintaionProps {
  vaccinationData: Vaccination;
}

const VaccinationContent = ({ vaccinationData }: BabyVaccintaionProps) => {
  const today = new Date();
  const startDate = new Date(vaccinationData.startDate);
  const [modalOpen, setModalOpen] = useState<boolean>(false);
  const useUpdateVaccinationDateMutation = useUpdateVaccinationDate();

  const onClickCalendar = () => {
    setModalOpen(true);
  };

  const onModalClose = () => {
    setModalOpen(false);
  };

  const onCalendarChange = (newDate: Value) => {
    if (newDate instanceof Date) {
      const vaccination: UpdateVaccination = {
        vaccinationStatusId: vaccinationData.vaccinationStatusId,
        vaccinationDate: formatDate(newDate),
      };
      useUpdateVaccinationDateMutation.mutate(vaccination);
    }
  };

  const formattedContent1 = vaccinationData.content1
    .split('\n')
    .map((line, index) => (
      <React.Fragment key={index}>
        {line}
        <br />
      </React.Fragment>
    ));
  const formattedContent2 = vaccinationData.content2
    .split('\n')
    .map((line, index) => (
      <React.Fragment key={index}>
        {line}
        <br />
      </React.Fragment>
    ));
  const formattedContent3 = vaccinationData.content3
    .split('\n')
    .map((line, index) => (
      <React.Fragment key={index}>
        {line}
        <br />
      </React.Fragment>
    ));
  const formattedContent4 = vaccinationData.content4
    .split('\n')
    .map((line, index) => (
      <React.Fragment key={index}>
        {line}
        <br />
      </React.Fragment>
    ));
  const formattedContent5 = vaccinationData.content5
    .split('\n')
    .map((line, index) => (
      <React.Fragment key={index}>
        {line}
        <br />
      </React.Fragment>
    ));
  const formattedContent6 = vaccinationData.content6
    .split('\n')
    .map((line, index) => (
      <React.Fragment key={index}>
        {line}
        <br />
      </React.Fragment>
    ));

  return (
    <>
      <CalendarModal
        pickDate={new Date()}
        onModalClose={onModalClose}
        modalOpen={modalOpen}
        onCalendarChange={onCalendarChange}
      />
      <VaccinationContentWrapper>
        <Text
          size="headMedium"
          $fontWeight={900}
          style={{ marginBottom: '7px' }}
        >
          {vaccinationData.disease}
        </Text>
        {vaccinationData.vaccinationDate && (
          <VaccinationCalendarButton>
            <div
              style={{
                display: 'flex',
                flexDirection: 'row',
              }}
            >
              <Text
                size="medium3"
                style={{ marginBottom: '15px', marginRight: '7px' }}
                color={theme.color.medicalblue}
              >
                접종이 완료되었어요
              </Text>
              <Image src={check} height={1.5} width={1.5}></Image>
            </div>
            <Button
              onClick={onClickCalendar}
              size="xSmall"
              $backgroundColor={theme.color.gray4}
              $border="none"
              style={{ height: '24px', marginTop: '1px' }}
            >
              <Text
                size="small"
                $fontWeight={400}
                color={theme.color.medicalblue}
              >
                {vaccinationData.vaccinationDate}
              </Text>
              &nbsp;
              <Image src={calendarImg} height={1}></Image>
            </Button>
          </VaccinationCalendarButton>
        )}
        {!vaccinationData.vaccinationDate && (
          <VaccinationCalendarButton>
            {today >= startDate && (
              <Text
                size="medium3"
                style={{ marginBottom: '15px' }}
                color={theme.color.danger}
              >
                권장일: {vaccinationData.startDate} ~ {vaccinationData.endDate}
              </Text>
            )}
            {today < startDate && (
              <Text size="medium3" style={{ marginBottom: '15px' }}>
                권장일: {vaccinationData.startDate} ~ {vaccinationData.endDate}
              </Text>
            )}
            <Button
              onClick={onClickCalendar}
              size="xSmall"
              $backgroundColor={theme.color.gray4}
              $border="none"
              style={{ height: '24px', marginTop: '1px' }}
            >
              <Text
                size="small"
                $fontWeight={400}
                color={theme.color.medicalblue}
              >
                접종일 등록 &nbsp;
              </Text>
              <Image src={calendarImg} height={1}></Image>
            </Button>
          </VaccinationCalendarButton>
        )}
      </VaccinationContentWrapper>
      <VaccinationContentWrapper>
        <Text
          size="headSmall"
          $fontWeight={900}
          style={{ marginBottom: '7px', marginTop: '7px' }}
        >
          {vaccinationData.title1}
        </Text>
        <Text size="medium2" style={{ marginBottom: '15px' }}>
          {formattedContent1}
        </Text>
      </VaccinationContentWrapper>
      <VaccinationContentWrapper>
        <Text
          size="headSmall"
          $fontWeight={900}
          style={{ marginBottom: '7px', marginTop: '7px' }}
        >
          {vaccinationData.title2}
        </Text>
        <Text size="medium2" style={{ marginBottom: '15px' }}>
          {formattedContent2}
        </Text>
      </VaccinationContentWrapper>
      <VaccinationContentWrapper>
        <Text
          size="headSmall"
          $fontWeight={900}
          style={{ marginBottom: '7px', marginTop: '7px' }}
        >
          {vaccinationData.title3}
        </Text>
        <Text size="medium2" style={{ marginBottom: '15px' }}>
          {formattedContent3}
        </Text>
      </VaccinationContentWrapper>
      <VaccinationContentWrapper>
        <Text
          size="headSmall"
          $fontWeight={900}
          style={{ marginBottom: '7px', marginTop: '7px' }}
        >
          {vaccinationData.title4}
        </Text>
        <Text size="medium2" style={{ marginBottom: '15px' }}>
          {formattedContent4}
        </Text>
      </VaccinationContentWrapper>
      <VaccinationContentWrapper>
        <Text
          size="headSmall"
          $fontWeight={900}
          style={{ marginBottom: '7px', marginTop: '7px' }}
        >
          {vaccinationData.title5}
        </Text>
        <Text size="medium2" style={{ marginBottom: '15px' }}>
          {formattedContent5}
        </Text>
      </VaccinationContentWrapper>
      <VaccinationContentWrapper>
        <Text
          size="headSmall"
          $fontWeight={900}
          style={{ marginBottom: '7px', marginTop: '7px' }}
        >
          {vaccinationData.title6}
        </Text>
        <Text size="medium2" style={{ marginBottom: '15px' }}>
          {formattedContent6}
        </Text>
      </VaccinationContentWrapper>
    </>
  );
};

export default VaccinationContent;

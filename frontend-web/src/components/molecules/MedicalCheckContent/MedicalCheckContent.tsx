import { MedicalCheck, UpdateMedicalCheck } from '@/types';
import {
  MedicalCheckCalendarButton,
  MedicalCheckContentWrapper,
} from './MedicalCheckContent.styles';
import { Text } from '@/components/atoms/Text/Text.styles';
import Button from '@/components/atoms/Button/Button';
import theme from '@/styles/theme';
import calendarImg from '@/assets/images/icon-calendar-blue.png';
import check from '@/assets/images/img-check-blue.png';
import { Image } from '@/components/atoms/Image/Image';
import React, { useState } from 'react';
import { CalendarModal } from '@/components/organisms/CalendarModal/CalendarModal';
import { formatDate } from '@/utils/formatDate';
import { Value } from 'react-calendar/dist/cjs/shared/types';
import { useUpdateMedicalCheckDate } from '@/apis/Baby/Mutations/useUpdateMedicalCheckDate';

interface BabyMedicalCheckProps {
  medicalCheckData: MedicalCheck;
}

const MedicalCheckContent = ({ medicalCheckData }: BabyMedicalCheckProps) => {
  const today = new Date();
  const startDate = new Date(medicalCheckData.startDate);
  const [modalOpen, setModalOpen] = useState<boolean>(false);
  const useUpdateMedicalCheckDateMutation = useUpdateMedicalCheckDate();

  const onClickCalendar = () => {
    setModalOpen(true);
  };

  const onModalClose = () => {
    setModalOpen(false);
  };

  const onCalendarChange = (newDate: Value) => {
    if (newDate instanceof Date) {
      const medicalCheck: UpdateMedicalCheck = {
        checkupStatusId: medicalCheckData.checkupStatusId,
        checkupDate: formatDate(newDate),
      };
      useUpdateMedicalCheckDateMutation.mutate(medicalCheck);
    }
  };

  const formattedContent1 = medicalCheckData.content1
    .split('\n')
    .map((line, index) => (
      <React.Fragment key={index}>
        {line}
        <br />
      </React.Fragment>
    ));
  const formattedContent2 = medicalCheckData.content2
    .split('\n')
    .map((line, index) => (
      <React.Fragment key={index}>
        {line}
        <br />
      </React.Fragment>
    ));
  const formattedContent3 = medicalCheckData.content3
    .split('\n')
    .map((line, index) => (
      <React.Fragment key={index}>
        {line}
        <br />
      </React.Fragment>
    ));
  const formattedContent4 = medicalCheckData.content4
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
      <MedicalCheckContentWrapper>
        <Text
          size="headMedium"
          $fontWeight={900}
          style={{ marginBottom: '7px' }}
        >
          {medicalCheckData.checkupName}
        </Text>
        {medicalCheckData.checkupDate && (
          <MedicalCheckCalendarButton>
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
                검진이 완료되었어요
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
                {medicalCheckData.checkupDate}
              </Text>
              &nbsp;
              <Image src={calendarImg} height={1}></Image>
            </Button>
          </MedicalCheckCalendarButton>
        )}
        {!medicalCheckData.checkupDate && (
          <MedicalCheckCalendarButton>
            {today >= startDate && (
              <Text
                size="medium3"
                style={{ marginBottom: '15px' }}
                color={theme.color.danger}
              >
                권장일: {medicalCheckData.startDate} ~{' '}
                {medicalCheckData.endDate}
              </Text>
            )}
            {today < startDate && (
              <Text size="medium3" style={{ marginBottom: '15px' }}>
                권장일: {medicalCheckData.startDate} ~{' '}
                {medicalCheckData.endDate}
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
          </MedicalCheckCalendarButton>
        )}
      </MedicalCheckContentWrapper>
      <MedicalCheckContentWrapper>
        <Text
          size="headSmall"
          $fontWeight={900}
          style={{ marginBottom: '7px', marginTop: '7px' }}
        >
          {medicalCheckData.title1}
        </Text>
        <Text size="medium2" style={{ marginBottom: '15px' }}>
          {formattedContent1}
        </Text>
      </MedicalCheckContentWrapper>
      <MedicalCheckContentWrapper>
        <Text
          size="headSmall"
          $fontWeight={900}
          style={{ marginBottom: '7px', marginTop: '7px' }}
        >
          {medicalCheckData.title2}
        </Text>
        <Text size="medium2" style={{ marginBottom: '15px' }}>
          {formattedContent2}
        </Text>
      </MedicalCheckContentWrapper>
      <MedicalCheckContentWrapper>
        <Text
          size="headSmall"
          $fontWeight={900}
          style={{ marginBottom: '7px', marginTop: '7px' }}
        >
          {medicalCheckData.title3}
        </Text>
        <Text size="medium2" style={{ marginBottom: '15px' }}>
          {formattedContent3}
        </Text>
      </MedicalCheckContentWrapper>
      <MedicalCheckContentWrapper>
        <Text
          size="headSmall"
          $fontWeight={900}
          style={{ marginBottom: '7px', marginTop: '7px' }}
        >
          {medicalCheckData.title4}
        </Text>
        <Text size="medium2" style={{ marginBottom: '15px' }}>
          {formattedContent4}
        </Text>
      </MedicalCheckContentWrapper>
    </>
  );
};

export default MedicalCheckContent;

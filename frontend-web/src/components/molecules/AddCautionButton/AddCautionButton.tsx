import Button from '@/components/atoms/Button/Button';
import { Text } from '@/components/atoms/Text/Text.styles';
import { CautionModal } from '@/components/organisms/CautionModal/CautionModal';
import { useState } from 'react';

interface AddCautionButtonProps {
  isDisease: boolean;
}

const AddCautionButton = ({ isDisease }: AddCautionButtonProps) => {
  const [cautionModalOpen, setCautionModalOpen] = useState(false);
  const modalClose = (
    setState: React.Dispatch<React.SetStateAction<boolean>>
  ) => {
    setState(false);
  };
  const buttonHandler = () => {
    setCautionModalOpen(true);
  };
  return (
    <>
      <CautionModal
        onModalClose={() => modalClose(setCautionModalOpen)}
        modalOpen={cautionModalOpen}
        isDisease={isDisease}
        isView={false}
      />
      <>
        <svg
          onClick={() => {
            setCautionModalOpen(true);
          }}
          xmlns="http://www.w3.org/2000/svg"
          width="135"
          height="166"
          viewBox="0 0 135 166"
          fill="none"
        >
          <rect
            x="1"
            y="1"
            width="133"
            height="164"
            rx="11"
            fill="white"
            fillOpacity="0.5"
            stroke="#A6D6F0"
            strokeWidth="2"
            strokeDasharray="2 2"
          />
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="27"
            height="27"
            viewBox="0 0 27 27"
            fill="none"
            x="54" // Adjust the x-coordinate to horizontally center the cross
            y="66" // Adjust the y-coordinate to vertically center the cross
          >
            <path d="M13.25 0V13.25V26.5" stroke="#93C2DC" strokeWidth="2" />
            <path
              d="M26.5 13.25L13.25 13.25L0 13.25"
              stroke="#93C2DC"
              strokeWidth="2"
            />
          </svg>
        </svg>
      </>
    </>
  );
};

export default AddCautionButton;

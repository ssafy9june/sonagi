import Button from '@/components/atoms/Button/Button';
import { Image } from '@/components/atoms/Image/Image';
import { GenderButtonsContainer } from '@/components/molecules/GenderButtons/GenderButtons.style';
import blueBabyCircle from '@/assets/images/img-baby-blue-circle.png';
import yellowBabyCircle from '@/assets/images/img-baby-yellow-circle.png';

interface Props {
  gender: 'M' | 'F';
  setGender: (gender: 'M' | 'F') => void;
}

const GenderButtons = ({ gender, setGender }: Props) => {
  const handleMaleState = () => {
    setGender('M');
  };

  const handleFemaleState = () => {
    setGender('F');
  };

  return (
    <>
      <GenderButtonsContainer>
        {/* TODO 이미지 크기 서로 다른거 고치기 */}
        <Image
          width={5}
          src={gender === 'M' ? blueBabyCircle : yellowBabyCircle}
        />
        <Button
          option={gender === 'M' ? 'primary' : 'default'}
          size="medium"
          onClick={handleMaleState}
        >
          남자
        </Button>
        <Button
          option={gender === 'F' ? 'primary' : 'default'}
          size="medium"
          onClick={handleFemaleState}
        >
          여자
        </Button>
      </GenderButtonsContainer>
    </>
  );
};

export default GenderButtons;

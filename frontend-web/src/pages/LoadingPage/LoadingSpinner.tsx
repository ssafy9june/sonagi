import yellowBaby from '@/assets/images/img-baby-yellow-circle.png';
import { Image } from '@/components/atoms/Image/Image';
import {
  LoadingRotatingImg,
  LoadingSpinnerContainer,
} from '@/pages/LoadingPage/LoadingSpinner.styles';

const LoadingSpinner = () => {
  return (
    <LoadingSpinnerContainer>
      <LoadingRotatingImg>
        <Image src={yellowBaby} width={8}></Image>
      </LoadingRotatingImg>
    </LoadingSpinnerContainer>
  );
};

export { LoadingSpinner };

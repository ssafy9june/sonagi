import { Background } from '@/components/atoms/Background/Background.styles';
import backgroundGradient from '@/assets/images/background-gradient.png';
import {
  LoadingPageContainer,
  LoadingPageWrapper,
  LoadingRotatingImg,
} from '@/pages/LoadingPage/LoadingPage.styles';
import { Text } from '@/components/atoms/Text/Text.styles';
import { Image } from '@/components/atoms/Image/Image';
import yellowBaby from '@/assets/images/img-baby-yellow-circle.png';

const LoadingPage = () => {
  return (
    <Background $background={backgroundGradient}>
      <LoadingPageContainer>
        <LoadingPageWrapper>
          <LoadingRotatingImg>
            <Image
              src={yellowBaby}
              width={8}
              //   style={{ marginBottom: '40px' }}
            ></Image>
          </LoadingRotatingImg>
          <Text
            size="headSmall"
            style={{ marginTop: '40px', marginBottom: '100px' }}
          >
            로딩중...
          </Text>
        </LoadingPageWrapper>
      </LoadingPageContainer>
    </Background>
  );
};

export default LoadingPage;

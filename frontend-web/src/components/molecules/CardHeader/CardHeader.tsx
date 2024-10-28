import { Image } from '@/components/atoms/Image/Image';
import { Text } from '@/components/atoms/Text/Text.styles';
import { CardHeaderWrapper } from '@/components/molecules/CardHeader/CardHeader.style';

interface CardHeaderProps {
  text: string;
  imgSrc: string;
}

const CardHeader = ({ text, imgSrc }: CardHeaderProps) => {
  return (
    <CardHeaderWrapper>
      <Text size="headSmall">{text}</Text>
      <Image src={imgSrc} height={1.5} />
    </CardHeaderWrapper>
  );
};

export { CardHeader };

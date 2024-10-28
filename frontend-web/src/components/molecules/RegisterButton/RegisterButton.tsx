import Button from '@/components/atoms/Button/Button';
import { Image } from '@/components/atoms/Image/Image';
import { Text } from '@/components/atoms/Text/Text.styles';
import { ButtonContents } from '@/components/molecules/SocialButton/SocialButton.style';
import theme from '@/styles/theme';
import { MouseEventHandler } from 'react';

interface RegisterButtonProps {
  src: string;
  buttonText: string;
  onClick?: MouseEventHandler<HTMLButtonElement>;
}

const RegisterButton = ({ src, buttonText, onClick }: RegisterButtonProps) => {
  return (
    <Button
      option="imgBtn"
      size="xLarge"
      $borderColor={theme.color.white2}
      onClick={onClick}
    >
      <ButtonContents>
        <Image src={src} width={2} />
        <Text size="headSmall">{buttonText}</Text>
      </ButtonContents>
    </Button>
  );
};

export default RegisterButton;

import Button from '@/components/atoms/Button/Button';
import Input from '@/components/atoms/Input/Input';
import { Text } from '@/components/atoms/Text/Text.styles';
import theme from '@/styles/theme';
import * as S from '@/components/molecules/RegisterField/RegisterField.style';
import { ChangeEvent, useEffect, useState } from 'react';

interface RegisterFieldProps {
  option?:
    | 'default'
    | 'imgBtn'
    | 'deActivated'
    | 'ActivatedOrange'
    | 'ActivatedBlue'
    | 'activated'
    | 'primary'
    | 'danger';
  size?: 'small' | 'xSmall' | 'medium' | 'large';
  $backgroundColor?: typeof theme.color;
  alertMessage?: string;
  onClickButtonAction?: (value: string) => void;
  placeholder?: string;
  activeButtonColor?: string;
  $maxLength?: number;
}

const RegisterField = ({
  size,
  alertMessage,
  onClickButtonAction,
  placeholder,
  $maxLength,
  activeButtonColor = theme.gradient.orangeBtn,
}: RegisterFieldProps) => {
  const [value, setValue] = useState<string>('');
  const [bgColor, setBgColor] = useState<string>(theme.color.gray3);
  const [option, setOption] = useState<'deActivated' | 'activated'>(
    'deActivated'
  );
  const textColor =
    option === 'activated' ? theme.color.white1 : theme.color.gray1;

  const onChangeInput = (e: ChangeEvent<HTMLInputElement>) => {
    setValue(e.target.value);
  };

  const onClickButton = () => {
    onClickButtonAction && onClickButtonAction(value);
  };

  useEffect(() => {
    if (value.length > 0) {
      setBgColor(activeButtonColor);
      setOption('activated'); // 값이 유효하면 activated 설정
    } else {
      setBgColor(theme.color.gray3);
      setOption('deActivated'); // 값이 유효하지 않으면 default 설정
    }
  }, [value.length, activeButtonColor]);

  return (
    <S.RegisterFieldStyle>
      <Input
        onChange={onChangeInput}
        value={value}
        alertMessage={alertMessage}
        placeholder={placeholder}
        fontSize={theme.fontSize.large}
        $borderRadius={22}
        maxLength={$maxLength}
      />
      <Button
        option={option} // 상태에 따른 option 전달
        size="large"
        onClick={onClickButton}
      >
        <Text size="headSmall" color={textColor}>
          등록하기
        </Text>
      </Button>
    </S.RegisterFieldStyle>
  );
};

export default RegisterField;

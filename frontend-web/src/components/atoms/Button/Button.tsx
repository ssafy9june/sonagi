import { forwardRef } from 'react';
import * as S from './Button.styles';

import type { ForwardedRef } from 'react';

export interface ButtonProps
  extends React.ButtonHTMLAttributes<HTMLButtonElement> {
  option?:
    | 'default'
    | 'imgBtn'
    | 'deActivated'
    | 'ActivatedOrange'
    | 'ActivatedBlue'
    | 'activated'
    | 'primary'
    | 'danger';
  size?: 'small' | 'xSmall' | 'medium' | 'large' | 'xLarge';
  $backgroundColor?: string;
  $borderColor?: string;
  $borderRadius?: string;
  $textAlign?: string;
  $border?: string;
  $color?: string;
}

const Button = (
  {
    option,
    size,
    $backgroundColor,
    $borderColor,
    $border,
    $color,
    children,
    ...attributes
  }: ButtonProps,
  ref: ForwardedRef<HTMLButtonElement>
) => {
  return (
    <S.Button
      ref={ref}
      option={option}
      size={size}
      $border={$border}
      $backgroundColor={$backgroundColor}
      $borderColor={$borderColor}
      $color={$color}
      {...attributes}
    >
      {children}
    </S.Button>
  );
};

export default forwardRef(Button);

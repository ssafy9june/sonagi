import styled, { css } from 'styled-components';
import { ButtonProps } from './Button';
import theme from '@/styles/theme';

const getOptionStyling = (
  option: Required<ButtonProps>['option'],
  props: ButtonProps
) => {
  const defaultStyle = {
    default: css`
      background: ${({ theme }) => theme.color.white1};
    `,
    // 배경색상의 white1 OPACITY가 60%
    imgBtn: css`
      background: ${({ theme }) => theme.color.white2};
      border-color: ${({ theme }) => theme.color.white};
    `,
    deActivated: css`
      background: ${({ theme }) => theme.color.gray4};
    `,
    ActivatedOrange: css`
      background: ${({ theme }) => theme.gradient.orangeBtn};
      box-shadow: ${({ theme }) => theme.shadow.shadow1};
      border: none;
    `,
    ActivatedBlue: css`
      background: ${({ theme }) => theme.gradient.skyblueBtn};
      box-shadow: ${({ theme }) => theme.shadow.shadow1};
      border: none;
    `,
    // 배경색상이 blue, txt가 white
    activated: css`
      background: ${({ theme }) => theme.color.primaryblue};
      color: ${({ theme }) => theme.color.white1};
      border: none;
    `,
    // 배경색상이 white, txt가 blue
    primary: css`
      background: ${({ theme }) => theme.color.white1};
      border-color: ${({ theme }) => theme.color.skyblue};
      color: ${({ theme }) => theme.color.blue};
    `,
    danger: css`
      background: ${({ theme }) => theme.color.danger};
      color: ${({ theme }) => theme.color.white1};
      border: none;
    `,
  };

  return props.$backgroundColor
    ? css`
        background: ${props.$backgroundColor};
      `
    : defaultStyle[option];
};

const getSizeStyling = (size: Required<ButtonProps>['size']) => {
  const style = {
    xSmall: css`
      width: fit-content;
      min-width: fit-content;
      font-size: ${({ theme }) => theme.fontSize.medium3};
      border-radius: 22px;
      align-items: center;
      display: flex;
      padding: 0.4rem 0.9rem;
    `,
    small: css`
      height: 36px;
      font-size: ${({ theme }) => theme.fontSize.medium1};
    `,
    medium: css`
      height: 44px;
      font-size: ${({ theme }) => theme.fontSize.medium2};
      border-radius: 9px;
    `,
    large: css`
      height: 55px;
      font-size: ${({ theme }) => theme.fontSize.medium1};
    `,
    xLarge: css`
      height: 62px;
      font-size: ${({ theme }) => theme.fontSize.large};
    `,
  };
  return style[size];
};

const Button = styled.button<ButtonProps>`
  width: 100%;
  text-align: ${props => props.$textAlign || 'center'};
  color: ${props => props.$color || props.theme.color.black3};
  border-radius: ${props => props.$borderRadius || '22px'};
  border: ${props =>
    props.$border ||
    `1px solid ${props.$borderColor || props.theme.color.gray2}`};
  ${({ size = 'large' }) => getSizeStyling(size)};
  ${({ option = 'default', ...props }) => getOptionStyling(option, props)};
  background-color: ${props => props.$backgroundColor};
`;

export { Button };

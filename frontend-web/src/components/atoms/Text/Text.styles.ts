import theme from '@/styles/theme';
import styled, { css } from 'styled-components';

export interface TextProps {
  size?:
    | 'headXLarge'
    | 'headLarge'
    | 'headMedium'
    | 'headSmall'
    | 'large'
    | 'medium1'
    | 'medium1Bold'
    | 'medium2'
    | 'medium3'
    | 'small'
    | 'xSmall';
  color?: typeof theme.color;
  /**
   * width의 단위는 픽셀입니다.
   */
  width?: number;
  $fontWeight?: number;
}

const getSizeStyling = (size: Required<TextProps>['size'] = 'headMedium') => {
  const style = {
    // main 소나기 타이틀
    headXLarge: css`
      font-size: ${({ theme }) => theme.fontSize.headXLarge};
      font-weight: bold;
      letter-spacing: ${({ theme }) => theme.letterSpacing.narrow};
    `,
    // 기록 필드 시간
    headLarge: css`
      font-size: ${({ theme }) => theme.fontSize.headLarge};
    `,
    headMedium: css`
      font-size: ${({ theme }) => theme.fontSize.headMedium};
    `,
    headSmall: css`
      font-size: ${({ theme }) => theme.fontSize.headSmall};
    `,
    // 큰 버튼
    large: css`
      font-size: ${({ theme }) => theme.fontSize.large};
    `,
    medium1: css`
      font-size: ${({ theme }) => theme.fontSize.medium1};
    `,
    medium1Bold: css`
      font-size: ${({ theme }) => theme.fontSize.medium1};
      font-weight: bold;
    `,
    // 작은 버튼
    medium2: css`
      font-size: ${({ theme }) => theme.fontSize.medium2};
    `,
    medium3: css`
      font-size: ${({ theme }) => theme.fontSize.medium3};
    `,
    small: css`
      font-size: ${({ theme }) => theme.fontSize.small};
      letter-spacing: ${({ theme }) => theme.letterSpacing.spread};
    `,
    // 하단 푸터 아이콘 아래의 텍스트
    xSmall: css`
      font-size: ${({ theme }) => theme.fontSize.xSmall};
    `,
  };
  return style[size];
};

const Text = styled.p<TextProps>`
  ${({ size = 'headMedium' }) => getSizeStyling(size)};
  color: ${props => props.color || props.theme.color.black3};
  width: ${props => `${props.width}px`};
  font-weight: ${props => `${props.$fontWeight}`};
`;

export { Text };

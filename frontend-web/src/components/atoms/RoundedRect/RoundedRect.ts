import styled from 'styled-components';
import theme from '@/styles/theme';

interface RoundedRectProps {
  width?: string;
  height?: string;
  color?: NonNullable<typeof theme.color>; // 색상은 무조건 지정
  borderRadius?: string;
}

const RoundedRect = styled.div<RoundedRectProps>`
  width: ${({ width = '12px' }) => width};
  height: ${({ height = '44px' }) => height};
  background-color: ${({ color }) => color};
  border-radius: ${({ borderRadius = '6px' }) => borderRadius};
  margin: 8px;
  opacity: 40%;
  min-width: 12px;
`;

export { RoundedRect };

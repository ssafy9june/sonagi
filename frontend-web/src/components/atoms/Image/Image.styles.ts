import { ComponentPropsWithRef } from 'react';
import styled from 'styled-components';

export interface ImageProps extends ComponentPropsWithRef<'img'> {
  width?: number;
  height?: number;
  $unit?: 'rem' | 'px' | 'em' | '%';
}

const ImageConatiner = styled.img<ImageProps>`
  width: ${props => `${props.width}${props.$unit}` || 'auto'};
  height: ${props => `${props.height}${props.$unit}` || 'auto'};
`;

export { ImageConatiner };

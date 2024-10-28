import * as S from '@/components/atoms/Image/Image.styles';

const Image = ({
  width,
  height,
  $unit = 'rem',
  ...attributes
}: S.ImageProps) => {
  return (
    <S.ImageConatiner
      width={width}
      height={height}
      $unit={$unit}
      {...attributes}
    />
  );
};

export { Image };

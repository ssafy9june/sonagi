import { Text } from '@/components/atoms/Text/Text.styles';
import { CardContentTextWrapper } from '@/components/molecules/CardContent/CardContentText.styles';

interface CardContentTextProps {
  type: string;
  data: string | number;
  unit: string;
  data2?: string | number;
  unit2?: string;
}

const CardContentText = ({
  type,
  data,
  unit,
  data2,
  unit2,
}: CardContentTextProps) => {
  return (
    <CardContentTextWrapper>
      <Text size="medium2">
        {type}

        <b>{data}</b>

        {unit}
        {Boolean(data2) && (
          <>
            <b>{data2}</b>
            {unit2}
          </>
        )}
      </Text>
    </CardContentTextWrapper>
  );
};

export { CardContentText };

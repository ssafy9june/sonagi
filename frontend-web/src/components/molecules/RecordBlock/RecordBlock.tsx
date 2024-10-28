import Button from '@/components/atoms/Button/Button';
import { RoundedRect } from '@/components/atoms/RoundedRect/RoundedRect';
import { Text } from '@/components/atoms/Text/Text.styles';
import { Image } from '@/components/atoms/Image/Image';
import theme from '@/styles/theme';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import { PATH } from '@/constants/path';
import { CombinedRecord } from '@/types/recordTypes';
import iconArrowMiniRightGrey from '@/assets/images/icon-arrow-mini-right-grey.png';

interface RecordBlockProps {
  color: string;
  recordType: string;
  time: string;
  record: CombinedRecord;
  recordId: string | number | undefined;
  amount?: number;
  // queryName:
  //   | 'infantFormulas'
  //   | 'breastFeedings'
  //   | 'babyFoods'
  //   | 'milks'
  //   | 'pumpingBreasts'
  //   | 'plays'
  //   | 'tummytimes'
  //   | 'pees'
  //   | 'poops'
  //   | 'fevers'
  //   | 'medications'
  //   | 'hospitals'
  //   | 'feedings'
  //   | 'snacks'
  //   | 'sleeps'
  //   | 'extras';
}

const TimeText = styled(Text)`
  margin: 0 12px 0 5px;
  color: ${theme.color.gray1};
  /* flex-grow: 1; */
`;

const MemoText = styled(TimeText)`
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  font-style: italic;
  margin-left: 6px;
  padding-right: 10px;
`;

const AmountButton = styled.div`
  /* color: ${theme.color.gray1}; */
  /* color: ${({ theme }) => theme.color.gray4}; */
  background-color: transparent;
  margin-left: 4px;
  width: fit-content;
  padding: 4px 7px;
  border-radius: 10px;
  border: 1px solid ${({ theme }) => theme.color.gray3};
  margin-top: 1px;
  /* border-color: ${theme.color.lightgrey}; */
  /* border: none; */
`;

const BlockButton = styled(Button)`
  display: flex;
  align-items: center;
  margin-bottom: 10px;
  border-radius: 14px;
  padding-right: 8px;
  background-color: ${theme.color.white2};

  img:last-child {
    margin-left: auto;
    justify-content: space-between;
  }
`;

const RecordBlock = ({
  color,
  recordType,
  time,
  record,
  recordId, // queryName,
  amount = 0,
}: RecordBlockProps) => {
  const navigate = useNavigate();
  const OnClickButton = () => {
    navigate(PATH.DETAILRECORD, {
      state: {
        recordType: recordType,
        recordName: record.category,
        recordId: recordId,
      },
    });
  };

  return (
    <BlockButton option="default" size="xLarge" onClick={OnClickButton}>
      <RoundedRect color={color} />
      <TimeText size="medium2">{time}</TimeText>
      <Text
        size="medium1"
        style={{ minWidth: 'fit-content', marginRight: '8px' }}
      >
        {recordType}
      </Text>
      {amount !== 0 && (
        <AmountButton>
          <Text size="medium3" color={theme.color.gray1}>
            {amount + 'ml'}
          </Text>
        </AmountButton>
      )}
      <MemoText size="medium2">{record.memo}</MemoText>
      <Image
        src={iconArrowMiniRightGrey}
        width={1.3}
        height={1.3}
        style={{ marginRight: '10px' }}
      />
    </BlockButton>
  );
};

export default RecordBlock;

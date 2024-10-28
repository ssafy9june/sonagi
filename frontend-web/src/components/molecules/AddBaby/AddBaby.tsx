import { Image } from '@/components/atoms/Image/Image';
import greenPlus from '@/assets/images/icon-plus-green-circle.png';
import AddBabyContainer from '@/components/molecules/AddBaby/AddBaby.style';
import { Text } from '@/components/atoms/Text/Text.styles';
import theme from '@/styles/theme';

const AddBaby = () => {
  return (
    <>
      <AddBabyContainer>
        <Image width={2} src={greenPlus}></Image>
        <Text size={theme.fontSize.medium1}>아이 추가</Text>
      </AddBabyContainer>
    </>
  );
};

export default AddBaby;
